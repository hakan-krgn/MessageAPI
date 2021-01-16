package com.hakan.messageplugin.bar.api;

import com.hakan.messageplugin.bar.HBossBar;
import com.hakan.messageplugin.bar.bars.HBossBar_v1_8_R3;
import com.hakan.messageplugin.bar.bars.HBossBar_v1_8_R3_Plus;
import com.hakan.messageplugin.bar.enums.BossBarColor;
import com.hakan.messageplugin.bar.enums.BossBarFlag;
import com.hakan.messageplugin.bar.enums.BossBarStyle;
import com.hakan.messageplugin.bar.listeners.BarListener;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BossBarAPI {

    public BossBarAPI() {
    }

    public void setup(Plugin plugin) {
        if (MessageVariables.serverVersion.equals("v1_8_R3")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Map.Entry<Player, HBossBar> entry : MessageVariables.playerHBossBar.entrySet()) {
                        Player player = entry.getKey();
                        if (player == null || !player.isOnline()) {
                            MessageVariables.playerHBossBar.remove(player);
                            return;
                        }
                        HBossBar hBossBar = entry.getValue();
                        if (hBossBar != null && !hBossBar.isHiding()) {
                            hBossBar.getEntityWither(player).teleport(BarListener.calculateLocation(player));
                        }
                    }
                }
            }.runTaskTimer(plugin, 0, 1);
        }
    }

    public HBossBar getBossBar(Player player) {
        return MessageVariables.playerHBossBar.get(player);
    }

    public BossBarManager getBarManager() {
        return new BossBarManager();
    }

    public static class BossBarManager {

        private String title = "";
        private double progress = 0.0;
        private List<Player> playerList = new ArrayList<>();
        private BossBarColor barColor = BossBarColor.PURPLE;
        private BossBarStyle barStyle = BossBarStyle.SOLID;
        private List<BossBarFlag> barFlag = new ArrayList<>();

        private BossBarManager() {

        }

        public BossBarManager setTitle(String title) {
            this.title = title;
            return this;
        }

        public BossBarManager setProgress(double progress) {
            this.progress = progress;
            return this;
        }

        public BossBarManager setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public BossBarManager addPlayer(Player player) {
            this.playerList.add(player);
            return this;
        }

        public BossBarManager removePlayer(Player player) {
            this.playerList.remove(player);
            return this;
        }

        public BossBarManager setBarColor(BossBarColor barColor) {
            this.barColor = barColor;
            return this;
        }

        public BossBarManager setBarStyle(BossBarStyle barStyle) {
            this.barStyle = barStyle;
            return this;
        }

        public BossBarManager setBarFlag(List<BossBarFlag> barFlag) {
            this.barFlag = barFlag;
            return this;
        }

        public BossBarManager addBarFlag(BossBarFlag barFlag) {
            this.barFlag.add(barFlag);
            return this;
        }

        public BossBarManager removeBarFlag(BossBarFlag barFlag) {
            this.barFlag.remove(barFlag);
            return this;
        }

        public HBossBar create() {
            if (MessageVariables.serverVersion.equals("v1_8_R3")) {
                return new HBossBar_v1_8_R3(this.title, this.progress, this.playerList, this.barColor, this.barStyle, this.barFlag);
            }
            return new HBossBar_v1_8_R3_Plus(this.title, this.progress, this.playerList, this.barColor, this.barStyle, this.barFlag);
        }
    }
}