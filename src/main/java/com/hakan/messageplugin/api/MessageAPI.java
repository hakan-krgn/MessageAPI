package com.hakan.messageplugin.api;

import com.hakan.messageplugin.MessagePlugin;
import com.hakan.messageplugin.actionbar.api.ActionbarAPI;
import com.hakan.messageplugin.bar.api.BossBarAPI;
import com.hakan.messageplugin.title.api.TitleAPI;
import org.bukkit.plugin.Plugin;

public class MessageAPI {

    public MessageAPI() {
    }

    public static void setup(Plugin plugin) {
        MessagePlugin.setup(plugin);
        getBarAPI().setup(plugin);
        getTitleAPI().setup();
        getActionBarAPI().setup();
    }

    public static BossBarAPI getBarAPI() {
        return new BossBarAPI();
    }

    public static TitleAPI getTitleAPI() {
        return new TitleAPI();
    }

    public static ActionbarAPI getActionBarAPI() {
        return new ActionbarAPI();
    }
}