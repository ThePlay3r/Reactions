package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.List;
import java.util.Random;

public class CfgSettings {
    public static boolean CLEAR_ANSWER;
    public static int COOLDOWN;
    public static int TIME;
    public static boolean BROADCAST_CHAT;
    public static boolean BROADCAST_TITLE;
    public static boolean BROADCAST_ACTIONBAR;
    private static List<String> WORDS;
    public static String getRandomWord(){
        return WORDS.get(new Random().nextInt(WORDS.size()));
    }

    public static void load(ConfigManager config){
        CLEAR_ANSWER = config.getBoolean("settings.clear-answer");
        COOLDOWN = config.getInt("settings.cooldown");
        TIME = config.getInt("settings.time");
        BROADCAST_CHAT = config.getBoolean("settings.broadcast-chat");
        BROADCAST_TITLE = config.getBoolean("settings.broadcast-title");
        BROADCAST_ACTIONBAR = config.getBoolean("settings.broadcast-actionbar");
        WORDS = config.getStringList("words");
    }
}
