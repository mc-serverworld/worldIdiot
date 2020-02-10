package com.serverworld.worldIdiot.commands;

import com.serverworld.worldIdiot.util.mysql;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class unbanplayer extends Command{
    public unbanplayer(Plugin plugin){
        super("undan");
    }
    //undan banid
    public void execute(CommandSender commandSender, String[] strings){
        if(strings.length>=1){if (strings[1].equals("confirm")){
            mysql.unban(strings[0]);
        }else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "you must confirm"));
            commandSender.sendMessage(new TextComponent(ChatColor.YELLOW + "/undan banid confirm"));
        }

        }else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "wtf this not right input"));
            commandSender.sendMessage(new TextComponent(ChatColor.YELLOW + "/undan banid time reason"));
        }
    }

}
