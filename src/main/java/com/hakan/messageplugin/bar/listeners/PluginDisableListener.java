package com.hakan.messageplugin.bar.listeners;

import com.hakan.messageplugin.MessagePlugin;
import com.hakan.messageplugin.bar.HBossBar;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import java.util.Map;

public class PluginDisableListener implements Listener {

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin().equals(MessagePlugin.instance)) {
            for (Map.Entry<Player, HBossBar> entry : MessageVariables.playerHBossBar.entrySet()) {
                HBossBar hBossBar = entry.getValue();
                hBossBar.removeAll();
            }
        }
    }
}