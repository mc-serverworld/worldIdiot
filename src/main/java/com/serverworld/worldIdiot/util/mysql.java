package com.serverworld.worldIdiot.util;

import com.serverworld.worldIdiot.worldIdiot;
import com.serverworld.worldIdiot.util.time;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mysql {
    public worldIdiot worldidiot;
    public mysql(worldIdiot plugin){worldidiot=plugin;}
    public static boolean ban(String uuid ,String executor,String reason,String endtime){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            Date date = new Date();
            String updateer = "INSERT INTO worldidiot_bandata (PlayerUUID, executor,start,end,reason) VALUES ('"+uuid+"', '"+executor+"', '"+date.getTime()+"','"+String.valueOf(time.converttime(endtime))+"','"+reason+"')";
            statement.executeUpdate(updateer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean unban(String banid){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            String updateer = "DELETE FROM `worldidiot_bandata` WHERE `worldidiot_bandata`.`id` = "+banid+";";
            statement.executeUpdate(updateer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
