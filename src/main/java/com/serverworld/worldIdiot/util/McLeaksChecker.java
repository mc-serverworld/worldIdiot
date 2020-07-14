package com.serverworld.worldIdiot.util;

import com.google.gson.JsonObject;
import com.serverworld.worldIdiot.worldIdiot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class McLeaksChecker {

    public static void checkUUID(String UUID, ProxiedPlayer player){
        //info about schedule
        //https://ci.md-5.net/job/BungeeCord/ws/api/target/apidocs/net/md_5/bungee/api/scheduler/TaskScheduler.html
        ProxyServer.getInstance().getScheduler().runAsync(worldIdiot.getInstance(), new Runnable() {
            public void run() {
                try {
                    if(player==null){
                        DebugMessage.sendWarring(ChatColor.YELLOW + "Opps, where is the player?");
                        return;
                    }
                    String playername = player.getName();
                    JSONObject json = jsonReader.readJsonFromUrl("https://mcleaks.themrgong.xyz/api/v3/isuuidmcleaks/"+player.getUniqueId().toString());//try to get json

                    //if is mcleak
                    if(json.getBoolean("isMcleaks")){
                        DebugMessage.sendInfo(ChatColor.YELLOW + UUID + " " + playername + ChatColor.RED+" is a MCLeaks account.");
                        //ban player
                        if(player!=null){
                            mysql.ban(player.getUniqueId().toString(),"worldidiot_audoban","mcleak accont are not allow!","10y");
                            player.disconnect(new TextComponent(ChatColor.RED+"\nYou are banned from this server."+ChatColor.WHITE+"\n\nreason: mcleak accont are not allow!"));
                        }

                    }
                    //if not mcleak
                    else if(!json.getBoolean("isMcleaks")){
                        DebugMessage.sendWarring(ChatColor.YELLOW+UUID+" "+playername+ ChatColor.GREEN+" is NOT a MCLeaks account.");

                    }
                    //if json not return boolean
                    else{
                        DebugMessage.sendWarring(ChatColor.RED+"returned JSON not true / false : "+json.toString());
                    }
                }
                //if something go wrong
                catch (Exception e) {
                    DebugMessage.sendWarring(ChatColor.RED+"network might be unreachable or something went wrong. Read the exception stacktrace.");
                }
            }
        });


    }
}
