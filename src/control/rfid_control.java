package control;

import com.uhf.detailwith.InventoryDetailWith;
import com.uhf.linkage.Linkage;
import com.uhf.structures.InventoryArea;
import com.uhf.structures.RwData;
import com.uhf.utils.StringUtils;

import java.util.Map;

public class rfid_control {

    public static void getInventoryArea() {
        InventoryArea inventoryArea = new InventoryArea();
        int status = Linkage.getInstance().getInventoryArea(inventoryArea);
        if (status == 0) {
            System.out.println("area:" + inventoryArea.area);
            System.out.println("startAddr:" + inventoryArea.startAddr);
            System.out.println("wordLen:" + inventoryArea.wordLen);
            System.out.println("getInventoryArea success");
            return;
        }
        System.out.println("getInventoryArea failed");
    }

    public static void setInventoryArea() {
        InventoryArea inventoryArea = new InventoryArea();
        inventoryArea.setValue(2, 0, 6);// 2为epc+user
        int status = Linkage.getInstance().setInventoryArea(inventoryArea);
        if (status == 0) {
            System.out.println("setInventoryArea success");
            return;
        }
        System.out.println("setInventoryArea failed");
    }

    public static void startInventory() {// 开始盘点 startInventory
        InventoryArea inventory = new InventoryArea();
        inventory.setValue(2, 0, 6);
        Linkage.getInstance().setInventoryArea(inventory);
        InventoryDetailWith.tagCount = 0;// 获取个数  Get the number
        Linkage.getInstance().startInventory(2, 0);
        InventoryDetailWith.startTime = System.currentTimeMillis();// 盘点的开始时间 Start time of Inventory

        while (InventoryDetailWith.totalCount < 100) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        stopInventory();// 进行停止盘点 stopInventory

        for (Map<String, Object> _map : InventoryDetailWith.list) {
            System.out.println(_map);
            System.out.println("天线号(antennaPort)：" + _map.get("antennaPort"));
            System.out.println("epc码：" + _map.get("epc"));
            System.out.println("TID/USER码：" + _map.get("externalData"));
            System.out.println("次数(count)：" + _map.get("count"));
            System.out.println("Rssi：" + _map.get("rssi"));
        }

        long m_lEndTime = System.currentTimeMillis();// 当前时间 The current time
        double Rate = Math.ceil((InventoryDetailWith.tagCount * 1.0) * 1000
                / (m_lEndTime - InventoryDetailWith.startTime));
        long total_time = m_lEndTime - InventoryDetailWith.startTime;
        String dateStr = StringUtils.getTimeFromMillisecond(total_time);
        int tag = InventoryDetailWith.list.size();
        System.out.println("盘点速率(Inventory rate)：" + Rate);

        if (tag != 0) {
            System.out.println("盘点时间(Inventory time)：" + dateStr);
        } else {
            System.out.println("盘点时间(Inventory time)：" + "0时0分0秒0毫秒");
        }
        System.out.println("标签个数(The number of tag)：" + tag);
    }

    public static void stopInventory() {// 停止盘点 stopInventory
        Linkage.getInstance().stopInventory();
    }

    public static String tidRead(){
        RwData rwData = new RwData();
        byte[] password = StringUtils.stringToByte("00000000");
        int status =  1;
        while(status!=0) {
            status=Linkage.getInstance().readTagSync(password, 2, 0, 2, 3000, rwData);//调用linkage中的tid读取函数 注意参数  Invoking the tid reading function in linkage and note the arguments
            //添加循环验证，避免读取失败 Add loop validation to avoid read failure
            if (status == 0) {
                String result = "";
                String epc = "";
                if (rwData.status == 0) {
                    if (rwData.rwDataLen > 0) {
                        result = StringUtils.byteToHexString(rwData.rwData,
                                rwData.rwDataLen);
                    }
                    if (rwData.epcLen > 0) {
                        epc = StringUtils
                                .byteToHexString(rwData.epc, rwData.epcLen);
                    }
                    //System.out.println("tidData====" + result);
                    //System.out.println("epc====" + epc);
                    //System.out.println("tid read success");
                    return epc;
                }
            }
            //System.out.println("tid read failed");
        }
        return "fail";
    }

}
