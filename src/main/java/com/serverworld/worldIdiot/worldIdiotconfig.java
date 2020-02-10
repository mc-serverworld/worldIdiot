package com.serverworld.worldIdiot;

public class worldIdiotconfig {
    private worldIdiot plugin;

    public worldIdiotconfig(worldIdiot i){
        plugin = i;
    }
    public void loadDefConfig(){ }
    public String host() {
        return plugin.configuration.getString("data.mysql.host");
    }
    public int port() {
        return plugin.configuration.getInt("data.mysql.port");
    }
    public String username() {
        return plugin.configuration.getString("data.mysql.username");
    }
    public String password() {
        return plugin.configuration.getString("data.mysql.password");
    }
    public String database() {
        return plugin.configuration.getString("data.mysql.database");
    }
}
