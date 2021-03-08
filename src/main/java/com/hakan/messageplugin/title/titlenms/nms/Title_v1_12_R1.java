package com.hakan.messageplugin.title.titlenms.nms;

import com.hakan.messageplugin.title.Title;
import com.hakan.messageplugin.title.titlenms.TitleNMS;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title_v1_12_R1 implements TitleNMS.TitlePacket {

    @Override
    public void send(Player player, Title title) {

        IChatBaseComponent titleString = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title.getTitle() + "\"}");
        IChatBaseComponent subtitleString = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title.getSubtitle() + "\"}");

        PacketPlayOutTitle packetPlayOutTitleTime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, title.getFadeIn(), title.getStay(), title.getFadeOut());
        PacketPlayOutTitle packetPlayOutSubTitleTime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, title.getFadeIn(), title.getStay(), title.getFadeOut());
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleString, title.getFadeIn(), title.getStay(), title.getFadeOut());
        PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleString, title.getFadeIn(), title.getStay(), title.getFadeOut());

        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        playerConnection.sendPacket(packetPlayOutTitleTime);
        playerConnection.sendPacket(packetPlayOutSubTitleTime);
        playerConnection.sendPacket(packetPlayOutTitle);
        playerConnection.sendPacket(packetPlayOutSubTitle);
    }
}