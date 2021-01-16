package com.hakan.messageplugin.title.titlenms.nms;

import com.hakan.messageplugin.title.Title;
import com.hakan.messageplugin.title.titlenms.TitleNMS;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title_v1_8_R3 implements TitleNMS.TitlePacket {

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