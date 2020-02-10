package com.serverworld.worldIdiot.util;

import com.serverworld.worldIdiot.worldIdiot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebUtil {
    public static worldIdiot plugin;
    public static void setmainplugin(worldIdiot p){
        plugin = p;
    }

    public static JSONParser parser = new JSONParser();
    public static JSONObject json = new JSONObject();
    public static ArrayList<String> UUIDList = new ArrayList<String>();

    public static final String MCLeaksWebAPI = "https://mcleaks.themrgong.xyz/api/v3/isuuidmcleaks/";
    @SuppressWarnings("deprecation")
    public static void testUUID(final String UUID, final ProxiedPlayer player){
        //info about schedule
        //https://ci.md-5.net/job/BungeeCord/ws/api/target/apidocs/net/md_5/bungee/api/scheduler/TaskScheduler.html
        ProxyServer.getInstance().getScheduler().schedule(plugin, new Runnable() {
            public void run() {
                try {
                    String playername = "";
                    if(player!=null){ playername = player.getName(); }

                    json = getJSONObjectFromURL(MCLeaksWebAPI+UUID,"GET");//try to get json
                    String isMCLeaksValue = json.get("isMcleaks").toString();

                    //if mcleak
                    if(isMCLeaksValue.equals("true")){
                        plugin.getLogger().info(ChatColor.YELLOW+UUID+" "+playername+ ChatColor.RED+" is a MCLeaks account.");
                        //add ban info to sql, will be ban soon
                        if(player!=null){
                            //UUIDList.add(player.getUniqueId().toString());
                            player.disconnect(new TextComponent("You are banned from this server. reason: mcleak accont are not allow!"));
                        }

                    }
                    //if not mcleak
                    else if(isMCLeaksValue.equals("false")){
                        plugin.getLogger().info(ChatColor.YELLOW+UUID+" "+playername+ ChatColor.GREEN+" is NOT a MCLeaks account.");

                    }
                    //if json not return boolean
                    else{
                        plugin.getLogger().warning(ChatColor.RED+"returned JSON not true / false : "+json.toString());
                    }
                }
                //if something go wrong
                catch (NullPointerException e) {
                    plugin.getLogger().warning(ChatColor.RED+"network might be unreachable or something went wrong. Read the exception stacktrace.");
                }
            }
        },1,TimeUnit.SECONDS);


    }
    private static JSONObject getJSONObjectFromURL(String urlString,String requestType){

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = setupHTTPConnection(url,requestType);
            connection.connect();

            InputStream is  = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8"));
            StringBuilder sb = new StringBuilder();

            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            is.close();
            return (JSONObject) parser.parse(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (SSLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().warning(ChatColor.RED+"Not a valid UUID");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static HttpURLConnection setupHTTPConnection(URL url, String requestType) throws IOException{
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000 );
        connection.setRequestMethod(requestType);
        return connection;
    }


}
