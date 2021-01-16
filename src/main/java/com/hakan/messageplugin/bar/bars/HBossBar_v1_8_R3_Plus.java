package com.hakan.messageplugin.bar.bars;

import com.hakan.messageplugin.bar.HBossBar;
import com.hakan.messageplugin.bar.enums.BossBarColor;
import com.hakan.messageplugin.bar.enums.BossBarFlag;
import com.hakan.messageplugin.bar.enums.BossBarStyle;
import com.hakan.messageplugin.bar.utils.EntityWitherCreator;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.List;

public class HBossBar_v1_8_R3_Plus implements HBossBar {

    private final List<Player> playerList;
    private final List<BossBarFlag> barFlags;
    private final BossBar bossBar;
    private String title;
    private double progress;
    private BossBarColor barColor;
    private BossBarStyle barStyle;
    private boolean isHiding;

    public HBossBar_v1_8_R3_Plus(String title, double progress, List<Player> playerList, BossBarColor barColor, BossBarStyle barStyle, List<BossBarFlag> barFlag) {
        BossBar bossBar = Bukkit.createBossBar(title, BarColor.valueOf(barColor.name()), BarStyle.valueOf(barStyle.name()));
        bossBar.setProgress(progress);
        for (BossBarFlag bossBarFlag : barFlag) {
            bossBar.addFlag(BarFlag.valueOf(bossBarFlag.name()));
        }
        for (Player player : playerList) {
            if (player == null || !player.isOnline()) {
                playerList.remove(player);
                continue;
            }
            bossBar.addPlayer(player);
        }

        this.bossBar = bossBar;

        this.isHiding = false;
        this.title = title;
        this.progress = progress;
        this.playerList = playerList;
        this.barColor = barColor;
        this.barStyle = barStyle;
        this.barFlags = barFlag;
    }

    @Override
    public EntityWitherCreator getEntityWither(Player player) {
        return null;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        this.bossBar.setTitle(title);
    }

    @Override
    public BossBarColor getColor() {
        return this.barColor;
    }

    @Override
    public void setColor(BossBarColor color) {
        this.barColor = color;
        this.bossBar.setColor(BarColor.valueOf(color.name()));
    }

    @Override
    public BossBarStyle getStyle() {
        return this.barStyle;
    }

    @Override
    public void setStyle(BossBarStyle style) {
        this.barStyle = style;
        this.bossBar.setStyle(BarStyle.valueOf(style.name()));
    }

    @Override
    public void removeFlag(BossBarFlag flag) {
        this.barFlags.remove(flag);
        this.bossBar.removeFlag(BarFlag.valueOf(flag.name()));
    }

    @Override
    public void addFlag(BossBarFlag flag) {
        this.barFlags.add(flag);
        this.bossBar.addFlag(BarFlag.valueOf(flag.name()));
    }

    @Override
    public boolean hasFlag(BossBarFlag flag) {
        return this.barFlags.contains(flag);
    }

    @Override
    public double getProgress() {
        return this.progress;
    }

    @Override
    public void setProgress(double progress) {
        if (progress > 1.0) {
            progress = 1.0;
        } else if (progress < 0.0) {
            progress = 0.0;
        }
        this.progress = progress;
        this.bossBar.setProgress(progress);
    }

    @Override
    public void addPlayer(Player player) {
        MessageVariables.playerHBossBar.put(player, this);
        this.playerList.add(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void removePlayer(Player player) {
        MessageVariables.playerHBossBar.remove(player);
        this.playerList.remove(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void removeAll() {
        for (Player player : this.playerList) {
            MessageVariables.playerHBossBar.remove(player);
        }
        this.playerList.clear();
        this.bossBar.removeAll();
    }

    @Override
    public List<Player> getPlayers() {
        return this.playerList;
    }

    @Override
    public void show() {
        this.isHiding = false;
        this.bossBar.show();
    }

    @Override
    public void hide() {
        this.isHiding = true;
        this.bossBar.hide();
    }

    @Override
    public boolean isHiding() {
        return this.isHiding;
    }
}