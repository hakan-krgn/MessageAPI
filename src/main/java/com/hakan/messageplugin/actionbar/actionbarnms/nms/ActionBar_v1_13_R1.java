package com.hakan.messageplugin.actionbar.actionbarnms.nms;

import com.hakan.messageplugin.actionbar.ActionBar;
import com.hakan.messageplugin.actionbar.actionbarnms.ActionBarNMS;
import net.minecraft.server.v1_13_R1.ChatMessageType;
import net.minecraft.server.v1_13_R1.IChatBaseComponent;
import net.minecraft.server.v1_13_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar_v1_13_R1 implements ActionBarNMS.ActionBarPacket {

    @Override
    public void sendActionBar(Player player, ActionBar actionBar) {
        if (player == null || !player.isOnline()) return;
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + actionBar.getText() + "\"}"), ChatMessageType.GAME_INFO);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}