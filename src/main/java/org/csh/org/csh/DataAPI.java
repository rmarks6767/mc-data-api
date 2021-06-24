package org.csh;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.csh.models.PlayerData;

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

                getLogger().info(playerData.toString());
                data.add(playerData);
            });
        }, 0, 40);
    }

    @Override
    public void onDisable(){
        getLogger().info("Disabling DataAPI");
    }

}