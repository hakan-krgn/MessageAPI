package com.hakan.messageplugin.title.titlenms.nms;

import com.hakan.messageplugin.title.Title;
import com.hakan.messageplugin.title.titlenms.TitleNMS;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title_v1_13_R2 implements TitleNMS.TitlePacket {

    @Override
    public void send(Player player, Title title) {

        IChatBaseComponent titleString = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title.getTitle() + "\"}");
        IChatBaseComponent subtitleString = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title.getSubtitle() + "\"}");

        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleString, title.getFadeIn(), title.getStay(), title.getFadeOut());
        PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleString, title.getFadeIn(), title.getStay(), title.getFadeOut());

        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(packetPlayOutTitle);
        playerConnection.sendPacket(packetPlayOutSubTitle);
    }
}