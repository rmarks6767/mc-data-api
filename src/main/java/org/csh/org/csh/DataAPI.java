package org.csh;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.csh.data.dto.PlayerData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enter main loop to server and gather data
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            List<PlayerData> data = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(player -> {
                PlayerData playerData = new PlayerData();
                playerData.setArmor(1);
                playerData.setHealth(player.getHealth());
                playerData.setHunger(player.getFoodLevel());
                playerData.setUsername(player.getName());

                data.add(playerData);
            });

            if (data.size() > 0) {
                try {
                    URL url = new URL("http://localhost:8000/data-callback");

                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);
                    con.setDoInput(true);

                    new DataOutputStream(con.getOutputStream()) {{
                        writeBytes(new Gson().toJson(data));
                        flush();
                        close();
                    }};
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 100);
    }

    @Override
    public void onDisable(){
        getLogger().info("Disabling DataAPI");
    }

}