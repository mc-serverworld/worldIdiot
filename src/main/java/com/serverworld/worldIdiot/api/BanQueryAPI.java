package com.serverworld.worldIdiot.api;

import com.serverworld.worldIdiot.worldIdiot;
import net.md_5.bungee.api.ChatColor;
import net.minecrell.serverlistplus.core.ServerListPlusCore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class BanQueryAPI{
    worldIdiot worldidiot;
    public BanQueryAPI(worldIdiot worldidiot){
        this.worldidiot = worldidiot;
    }
    public static Boolean isBanned(String UUID){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + UUID + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
            Boolean banbefore =false;
            banbefore = rs.next();
            Date date = new Date();
            if(banbefore){
                if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static String getBanReason(String UUID){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + UUID + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
            Boolean banbefore =false;
            banbefore = rs.next();
            Date date = new Date();
            if(banbefore){
                if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                    return rs.getString("reason" ).toString();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static String getBanOperator(String UUID){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" +UUID + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
            Boolean banbefore =false;
            banbefore = rs.next();
            Date date = new Date();
            if(banbefore){
                if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                    return rs.getString("executor" ).toString();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Date getBanExpiration(String UUID){
        try {
            Statement statement = worldIdiot.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + UUID + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
            Boolean banbefore =false;
            banbefore = rs.next();
            Date date = new Date();
            if(banbefore){
                if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                    date.setTime(Long.valueOf(rs.getString("end" )));
                    return date;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
