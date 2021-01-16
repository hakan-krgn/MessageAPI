package com.hakan.messageplugin.bar.listeners;

import com.hakan.messageplugin.bar.HBossBar;
import com.hakan.messageplugin.bar.api.BossBarAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class BarListener implements Listener {

    public static Location calculateLocation(Player player) {
        double p = -player.getLocation().getPitch();
        double pitch = Math.toRadians(p);
        Location location = player.getLocation().clone();
        double y = player.isSprinting() ? 63.92 : 52.91;
        if (p < -46 && p >= -50) {
            location = location.add(player.getLocation().getDirection().multiply(-1.4));
        } else if (p < -46 && p >= -75) {
            location = location.add(player.getLocation().getDirection().multiply(-1.16));
        } else if (p < -75) {
            location = location.add(player.getLocation().getDirection().multiply(-1.9));
        } else {
            Vector vector = new Vector();
            double rotX = player.getLocation().getYaw();
            vector.setX(-Math.sin(Math.toRadians(rotX))).setZ(Math.cos(Math.toRadians(rotX)));
            location = location.add(vector.multiply(75.0 * Math.cos(pitch) - y * Math.sin(pitch)));
            location.setY(location.getY() + 75.0 * Math.sin(pitch) + y * Math.cos(pitch));
        }
        return location;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        HBossBar hBossBar = new BossBarAPI().getBossBar(player);
        if (hBossBar != null) {
            hBossBar.removePlayer(player);
        }
    }
}