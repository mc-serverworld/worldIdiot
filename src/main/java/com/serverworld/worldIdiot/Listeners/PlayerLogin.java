package com.serverworld.worldIdiot.Listeners;

import com.serverworld.worldIdiot.worldIdiot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PlayerLogin implements Listener {

    public PlayerLogin(Plugin plugin) {
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        try {
            Statement statement = worldIdiot.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM worldidiot_bandata WHERE PlayerUUID='"+event.getPlayer().getUniqueId() +"'ORDER BY worldidiot_bandata.id DESC LIMIT 1;");
            Boolean banbefore =false;
            banbefore = rs.next();
            Date date = new Date();
            if(banbefore){
                if(Long.valueOf(rs.getString("end" )) > date.getTime()){
                    long endime =Long.valueOf(rs.getString("end" ));
                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    event.getPlayer().disconnect(new TextComponent(ChatColor.RED+"\nYou are banned from this server."+ChatColor.WHITE+"\n\nreason: "+rs.getString("reason" )+  "\nBanID: "+rs.getString("id" )+"\n\nyour ban will remove at "+sdFormat.format(endime)));
                }
            }else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            //player.sendMessage(new TextComponent(event.getPlayer().getName() + " joined the network"));
        }
    }

}
