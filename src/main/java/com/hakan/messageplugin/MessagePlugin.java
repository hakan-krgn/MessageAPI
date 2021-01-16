package com.hakan.messageplugin;

import com.hakan.messageplugin.api.MessageAPI;
import com.hakan.messageplugin.bar.listeners.BarListener;
import com.hakan.messageplugin.bar.listeners.PluginDisableListener;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MessagePlugin extends JavaPlugin {

    public static Plugin instance;

    public static void setup(Plugin plugin) {
        if (instance != null) {
            Bukkit.getLogger().warning("MessageAPI already registered.");
            return;
        }
        instance = plugin;
        MessageVariables.serverVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BarListener(), plugin);
        pm.registerEvents(new PluginDisableListener(), plugin);
    }

    @Override
    public void onEnable() {
        MessageAPI.setup(this);

        new BukkitRunnable() {
            @Override
            public void run() {
                MessageAPI.getTitleAPI().getTitleManager().setTitle("sa").setSubtitle("as").setStay(20).create().sendAll();
                MessageAPI.getActionBarAPI().getActionBarManager().setText("sa").create().sendAll();
                MessageAPI.getBarAPI().getBarManager().create().addPlayer(Bukkit.getPlayer("blueybighats"));
            }
        }.runTaskLater(this, 20 * 5);
    }
}