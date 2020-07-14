package com.serverworld.worldIdiot;

import com.serverworld.worldIdiot.Listeners.PlayerLogin;
import com.serverworld.worldIdiot.api.BanQuery;
import com.serverworld.worldIdiot.api.BanQueryAPI;
import com.serverworld.worldIdiot.commands.*;
import com.serverworld.worldIdiot.util.mysql;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.minecrell.serverlistplus.core.ServerListPlusCore;
import org.bstats.bungeecord.Metrics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class worldIdiot extends Plugin {
    private File file;
    private static worldIdiot worldIdiot;
    private worldIdiotconfig config;
    public static Configuration configuration;
    public static Connection connection;
    private String host, database, username, password;
    private int port;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        config = new worldIdiotconfig(this);
        //config.loadDefConfig();
        host = config.host();
        port = config.port();
        database = config.database();
        username = config.username();
        password = config.password();
        worldIdiot = this;
        new PlayerLogin(this);
        new mysql(this);

        try {
            openConnection();
            Statement statement = connection.createStatement();
            //create database
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worldidiot_bandata` (`id` int(10) NOT NULL AUTO_INCREMENT,`PlayerUUID` char(36),`executor` char(16),`start` char(14),`end` char(14),`reason` TEXT, PRIMARY KEY(id),INDEX (PlayerUUID))");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            getLogger().warning("Error while connection to database");
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().warning("Error while connection to database");
        }
        try {
            openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //end
        getProxy().getPluginManager().registerCommand(this, new banplayer(this));
        getProxy().getPluginManager().registerCommand(this, new unbanplayer(this));
    }
    @Override
    public void onEnable() {
        try {
            if (getProxy().getPluginManager().getPlugin("ServerListPlus") != null) {
                getLogger().info(ChatColor.YELLOW + "Found ServerListPlus, hooking it");
                getProxy().getScheduler().schedule(this, new Runnable() {
                    public void run() {
                        new BanQuery(worldIdiot.this);
                    }
                }, 1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            getLogger().info(ChatColor.RED + "Error");
            e.printStackTrace();
        }
        int pluginId = 7519; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this,pluginId);

        getLogger().info("Yay! It loads!");
        getLogger().info(ChatColor.GREEN + "Hello world");
    }


    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            getLogger().info("Connected to database!");
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database+"?autoReconnect=true&characterEncoding=utf-8", this.username, this.password);
        }
    }

    public BanQueryAPI getAPI(){
        return new BanQueryAPI(this);
    }

    public static worldIdiot getInstance(){
        return worldIdiot;
    }

}
