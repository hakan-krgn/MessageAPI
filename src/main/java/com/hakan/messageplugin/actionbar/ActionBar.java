package com.hakan.messageplugin.actionbar;

import com.hakan.messageplugin.actionbar.actionbarnms.ActionBarNMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    private String text;

    public ActionBar(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void send(Player player) {
        if (player == null || !player.isOnline()) return;
        ActionBarNMS.getActionBarPacket().sendActionBar(player, this);
    }

    public void sendAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ActionBarNMS.getActionBarPacket().sendActionBar(player, this);
        }
    }
}