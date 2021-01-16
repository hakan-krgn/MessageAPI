package com.hakan.messageplugin.title.titlenms;

import com.hakan.messageplugin.bar.utils.MessageVariables;
import com.hakan.messageplugin.title.Title;
import com.hakan.messageplugin.title.titlenms.nms.*;
import org.bukkit.entity.Player;

public class TitleNMS {

    private static TitlePacket titlePacket;

    public static TitlePacket getTitlePacket() {
        return titlePacket;
    }

    public void setup() {
        switch (MessageVariables.serverVersion) {
            case "v1_8_R3":
                titlePacket = new Title_v1_8_R3();
                break;
            case "v1_9_R1":
                titlePacket = new Title_v1_9_R1();
                break;
            case "v1_9_R2":
                titlePacket = new Title_v1_9_R2();
                break;
            case "v1_10_R1":
                titlePacket = new Title_v1_10_R1();
                break;
            case "v1_11_R1":
                titlePacket = new Title_v1_11_R1();
                break;
            case "v1_12_R1":
                titlePacket = new Title_v1_12_R1();
                break;
            case "v1_13_R1":
                titlePacket = new Title_v1_13_R1();
                break;
            case "v1_13_R2":
                titlePacket = new Title_v1_13_R2();
                break;
            case "v1_14_R1":
                titlePacket = new Title_v1_14_R1();
                break;
            case "v1_15_R1":
                titlePacket = new Title_v1_15_R1();
                break;
            case "v1_16_R1":
                titlePacket = new Title_v1_16_R1();
                break;
            case "v1_16_R2":
                titlePacket = new Title_v1_16_R2();
                break;
            case "v1_16_R3":
                titlePacket = new Title_v1_16_R3();
                break;
        }
    }

    public interface TitlePacket {

        void send(Player player, Title title);

    }
}