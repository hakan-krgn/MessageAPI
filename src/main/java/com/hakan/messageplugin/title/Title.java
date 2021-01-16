package com.hakan.messageplugin.title;

import com.hakan.messageplugin.title.titlenms.TitleNMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title {

    private String title;
    private String subtitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public void send(Player player) {
        if (player == null || !player.isOnline()) return;
        TitleNMS.getTitlePacket().send(player, this);
    }

    public void sendAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            TitleNMS.getTitlePacket().send(player, this);
        }
    }
}