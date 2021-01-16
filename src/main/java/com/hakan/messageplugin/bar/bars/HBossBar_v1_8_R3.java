package com.hakan.messageplugin.bar.bars;

import com.hakan.messageplugin.bar.HBossBar;
import com.hakan.messageplugin.bar.enums.BossBarColor;
import com.hakan.messageplugin.bar.enums.BossBarFlag;
import com.hakan.messageplugin.bar.enums.BossBarStyle;
import com.hakan.messageplugin.bar.listeners.BarListener;
import com.hakan.messageplugin.bar.utils.EntityWitherCreator;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HBossBar_v1_8_R3 implements HBossBar {

    private final List<Player> playerList;
    private final List<BossBarFlag> barFlags;
    private final List<EntityWitherCreator> bossBar = new ArrayList<>();
    private String title;
    private double progress;
    private BossBarColor barColor;
    private BossBarStyle barStyle;
    private boolean isHiding;

    public HBossBar_v1_8_R3(String title, double progress, List<Player> playerList, BossBarColor barColor, BossBarStyle barStyle, List<BossBarFlag> barFlag) {

        for (Player player : playerList) {
            if (player == null || !player.isOnline()) {
                playerList.remove(player);
                continue;
            }
            bossBar.add(new EntityWitherCreator(player).create(BarListener.calculateLocation(player)).setTitle(title).setProgress(progress));
            MessageVariables.playerHBossBar.put(player, this);
        }

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
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            if (entityWitherCreator.getPlayer().equals(player)) {
                return entityWitherCreator;
            }
        }
        return null;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            entityWitherCreator.setTitle(title);
        }
    }

    @Override
    public BossBarColor getColor() {
        return this.barColor;
    }

    @Override
    public void setColor(BossBarColor color) {
        this.barColor = color;
    }

    @Override
    public BossBarStyle getStyle() {
        return this.barStyle;
    }

    @Override
    public void setStyle(BossBarStyle style) {
        this.barStyle = style;
    }

    @Override
    public void removeFlag(BossBarFlag flag) {
        this.barFlags.remove(flag);
    }

    @Override
    public void addFlag(BossBarFlag flag) {
        this.barFlags.add(flag);
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
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            entityWitherCreator.setProgress(progress);
        }
    }

    @Override
    public void addPlayer(Player player) {
        MessageVariables.playerHBossBar.put(player, this);
        this.playerList.add(player);

        bossBar.add(new EntityWitherCreator(player).create(player.getLocation()).setTitle(this.title).setProgress(this.progress));
    }

    @Override
    public void removePlayer(Player player) {
        MessageVariables.playerHBossBar.remove(player);
        this.playerList.remove(player);
        getEntityWither(player).delete();
    }

    @Override
    public void removeAll() {
        for (Player player : this.playerList) {
            MessageVariables.playerHBossBar.remove(player);
        }
        this.playerList.clear();
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            entityWitherCreator.delete();
        }
    }

    @Override
    public List<Player> getPlayers() {
        return this.playerList;
    }

    @Override
    public void show() {
        this.isHiding = false;
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            entityWitherCreator.show();
        }
    }

    @Override
    public void hide() {
        this.isHiding = true;
        for (EntityWitherCreator entityWitherCreator : this.bossBar) {
            entityWitherCreator.hide();
        }
    }

    @Override
    public boolean isHiding() {
        return this.isHiding;
    }
}