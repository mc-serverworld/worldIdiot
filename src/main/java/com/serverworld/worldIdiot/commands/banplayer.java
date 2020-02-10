package com.serverworld.worldIdiot.commands;

import com.serverworld.worldIdiot.util.mysql;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.Proxy;

public class banplayer extends Command{
    public banplayer(Plugin plugin){
        super("dan");
    }
    //dan playername endtime reason
    public void execute(CommandSender commandSender, String[] strings) {
        if(strings.length>=2&&strings[1].length()>1) {
                if (strings[2].length() > 16) {
                    //uuid
                    String dateform = strings[1].substring(strings[1].length() - 1);
                    //check dateform import
                    switch (dateform) {
                        case "s": break;//sec
                        case "m": break;//min
                        case "h": break;//hour
                        case "d": break;//day
                        case "o": break;//mount
                        case "y": break;//year
                        default: {
                            commandSender.sendMessage(new TextComponent(ChatColor.RED + "wtf this not the dateform!" + ChatColor.YELLOW + "\nusage s/m/h/d/mo/y"));
                            return;
                        }
                    }
                    //end fon
                } else {
                    //id
                    for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                        Boolean ban =false;
                        //== not work, fuck this shit
                        if (player.getName().toString().equals(strings[0])) {
                            String dateform = strings[1].substring(strings[1].length() - 1);
                            //check dateform import
                            switch (dateform) {
                                case "s": break;//sec
                                case "m": break;//min
                                case "h": break;//hour
                                case "d": break;//day
                                case "o": break;//mount
                                case "y": break;//year
                                default: {
                                    commandSender.sendMessage(new TextComponent(ChatColor.RED + "wtf this not the dateform!" + ChatColor.YELLOW + "\nusage s/m/h/d/mo/y"));
                                    return;
                                }

                            }
                            mysql.ban(player.getUniqueId().toString(), commandSender.getName(), strings[2], strings[1]);
                            ban=true;
                        }
                        if(ban){
                            for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                                players.sendMessage(new TextComponent(ChatColor.RED + strings[0] + " got banned by "+commandSender.getName()));
                            }
                            break;
                        }

                    }
                    //end fon
                }
        }else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "wtf this not right input"));
            commandSender.sendMessage(new TextComponent(ChatColor.YELLOW + "/dan uuid/playerid time reason"));
        }
    }
}
