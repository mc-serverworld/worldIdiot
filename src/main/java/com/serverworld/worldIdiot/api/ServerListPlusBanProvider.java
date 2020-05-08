package com.serverworld.worldIdiot.api;

    import com.serverworld.worldIdiot.worldIdiot;
    //import net.minecrell.serverlistplus.core.player.PlayerIdentity;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.text.SimpleDateFormat;
    import java.util.Date;

    public class ServerListPlusBanProvider {
        /*
        public boolean isBanned(PlayerIdentity playerIdentity){
            try {
                Statement statement = worldIdiot.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + playerIdentity.getUuid() + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
                Boolean banbefore =false;
                banbefore = rs.next();
                Date date = new Date();
                if(banbefore){
                    if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                        return true; //.rs.getString("reason" )
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }

        public String getBanReason(PlayerIdentity playerIdentity){
            try {
                Statement statement = worldIdiot.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + playerIdentity.getUuid() + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
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

        public String getBanOperator(PlayerIdentity playerIdentity){
            try {
                Statement statement = worldIdiot.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + playerIdentity.getUuid() + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
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

        public Date getBanExpiration(PlayerIdentity playerIdentity){
            try {
                Statement statement = worldIdiot.connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='" + playerIdentity.getUuid() + "'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
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
        }*/
    }
