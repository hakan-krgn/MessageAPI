package com.hakan.messageplugin.actionbar.actionbarnms;

import com.hakan.messageplugin.actionbar.ActionBar;
import com.hakan.messageplugin.actionbar.actionbarnms.nms.*;
import com.hakan.messageplugin.bar.utils.MessageVariables;
import org.bukkit.entity.Player;

public class ActionBarNMS {

    private static ActionBarNMS.ActionBarPacket actionBar;

    public static ActionBarNMS.ActionBarPacket getActionBarPacket() {
        return actionBar;
    }

    public void setup() {
        switch (MessageVariables.serverVersion) {
            case "v1_8_R3":
                actionBar = new ActionBar_v1_8_R3();
                break;
            case "v1_9_R1":
                actionBar = new ActionBar_v1_9_R1();
                break;
            case "v1_9_R2":
                actionBar = new ActionBar_v1_9_R2();
                break;
            case "v1_10_R1":
                actionBar = new ActionBar_v1_10_R1();
                break;
            case "v1_11_R1":
                actionBar = new ActionBar_v1_11_R1();
                break;
            case "v1_12_R1":
                actionBar = new ActionBar_v1_12_R1();
                break;
            case "v1_13_R1":
                actionBar = new ActionBar_v1_13_R1();
                break;
            case "v1_13_R2":
                actionBar = new ActionBar_v1_13_R2();
                break;
            case "v1_14_R1":
                actionBar = new ActionBar_v1_14_R1();
                break;
            case "v1_15_R1":
                actionBar = new ActionBar_v1_15_R1();
                break;
            case "v1_16_R1":
                actionBar = new ActionBar_v1_16_R1();
                break;
            case "v1_16_R2":
                actionBar = new ActionBar_v1_16_R2();
                break;
            case "v1_16_R3":
                actionBar = new ActionBar_v1_16_R3();
                break;
        }
    }

    public interface ActionBarPacket {

        void sendActionBar(Player player, ActionBar actionBar);

    }
}