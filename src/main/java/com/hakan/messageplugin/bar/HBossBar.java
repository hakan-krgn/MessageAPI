package com.hakan.messageplugin.bar;

import com.hakan.messageplugin.bar.enums.BossBarColor;
import com.hakan.messageplugin.bar.enums.BossBarFlag;
import com.hakan.messageplugin.bar.enums.BossBarStyle;
import com.hakan.messageplugin.bar.utils.EntityWitherCreator;
import org.bukkit.entity.Player;

import java.util.List;

public interface HBossBar {

    String getTitle();

    void setTitle(String title);

    BossBarColor getColor();

    void setColor(BossBarColor color);

    BossBarStyle getStyle();

    void setStyle(BossBarStyle style);

    void removeFlag(BossBarFlag flag);

    void addFlag(BossBarFlag flag);

    boolean hasFlag(BossBarFlag flag);

    double getProgress();

    void setProgress(double progress);

    void addPlayer(Player player);

    void removePlayer(Player player);

    void removeAll();

    List<Player> getPlayers();

    void show();

    void hide();

    EntityWitherCreator getEntityWither(Player player);

    boolean isHiding();
}