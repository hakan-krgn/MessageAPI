package com.hakan.messageplugin.bar.utils;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Objects;

public class EntityWitherCreator {

    private final Player player;
    private EntityWither entityWither;
    private String name;
    private Float health;

    private Location lastLoc;

    public EntityWitherCreator(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public EntityWitherCreator create(Location location) {
        EntityWither entityWither = new EntityWither(((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle());
        entityWither.setCustomNameVisible(true);
        entityWither.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entityWither.setInvisible(true);

        if (this.name != null) {
            entityWither.setCustomName(this.name);
        }
        if (this.health != null) {
            entityWither.setHealth(this.health);
        }

        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(entityWither);
        PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(entityWither.getId(), entityWither.getDataWatcher(), false);

        PlayerConnection playerConnection = ((CraftPlayer) this.player).getHandle().playerConnection;
        playerConnection.sendPacket(spawnPacket);
        playerConnection.sendPacket(metadataPacket);

        this.entityWither = entityWither;

        return this;
    }

    public EntityWitherCreator delete() {
        PacketPlayOutEntityDestroy deletePacket = new PacketPlayOutEntityDestroy(this.entityWither.getId());
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(deletePacket);
        return this;
    }

    public EntityWitherCreator teleport(Location location) {
        delete();
        create(location);
        return this;
    }

    public EntityWitherCreator setTitle(String title) {
        this.name = title;
        EntityWither entityWither = this.entityWither;
        entityWither.setCustomName(title);
        PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(entityWither.getId(), entityWither.getDataWatcher(), false);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(metadataPacket);
        return this;
    }

    public EntityWitherCreator setProgress(double progress) {
        this.health = (float) (progress * entityWither.getMaxHealth());
        EntityWither entityWither = this.entityWither;
        entityWither.setHealth(this.health);
        PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(entityWither.getId(), entityWither.getDataWatcher(), false);
        ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(metadataPacket);
        return this;
    }

    public EntityWitherCreator show() {
        create(this.lastLoc);
        this.lastLoc = null;
        return this;
    }

    public EntityWitherCreator hide() {
        this.lastLoc = this.entityWither.getBukkitEntity().getLocation();
        delete();
        return this;
    }
}