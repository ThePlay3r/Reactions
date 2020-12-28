package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;

import java.util.List;

public class CfgSettings {
    public static boolean BUNGEE;
    public static boolean START_ON_STARTUP;
    public static boolean RESTART_ON_END;
    public static boolean CLEAR_ANSWER;
    public static int COOLDOWN;
    public static int TIME;
    public static boolean REACTIONS_CHAT;
    public static List<String> REACTIONS_CHAT_START;
    public static List<String> REACTIONS_CHAT_END;
    public static List<String> REACTIONS_CHAT_NO_WINNER;
    public static boolean REACTIONS_TITLE;
    public static PLJRTitle REACTIONS_TITLE_START;
    public static PLJRTitle REACTIONS_TITLE_END;
    public static PLJRTitle REACTIONS_TITLE_NO_WINNER;
    public static boolean REACTIONS_ACTIONBAR;
    public static PLJRActionBar REACTIONS_ACTIONBAR_START;
    public static PLJRActionBar REACTIONS_ACTIONBAR_END;
    public static PLJRActionBar REACTIONS_ACTIONBAR_NO_WINNER;

    public static void load(ConfigManager config){
        CfgSettings.BUNGEE = config.getBoolean("settings.bungee");
        CfgSettings.START_ON_STARTUP = config.getBoolean("settings.start-on-startup");
        CfgSettings.RESTART_ON_END = config.getBoolean("settings.restart-on-end");
        CfgSettings.CLEAR_ANSWER = config.getBoolean("settings.clear-answer");
        CfgSettings.COOLDOWN = config.getInt("settings.cooldown");
        CfgSettings.TIME = config.getInt("settings.time");
        REACTIONS_CHAT = config.getBoolean("reactions.messages.chat.enabled");
        REACTIONS_CHAT_START = config.getStringList("reactions.messages.chat.start");
        REACTIONS_CHAT_END = config.getStringList("reactions.messages.chat.end");
        REACTIONS_CHAT_NO_WINNER = config.getStringList("reactions.messages.chat.no-winner");

        REACTIONS_TITLE = config.getBoolean("reactions.messages.title.enabled");
        REACTIONS_TITLE_START = config.getPLJRTitle("reactions.messages.title.start");
        REACTIONS_TITLE_END = config.getPLJRTitle("reactions.messages.title.end");
        REACTIONS_TITLE_NO_WINNER = config.getPLJRTitle("reactions.messages.title.no-winner");

        REACTIONS_ACTIONBAR = config.getBoolean("reactions.messages.actionbar.enabled");
        REACTIONS_ACTIONBAR_START = config.getPLJRActionBar("reactions.messages.actionbar.start");
        REACTIONS_ACTIONBAR_END = config.getPLJRActionBar("reactions.messages.actionbar.end");
        REACTIONS_ACTIONBAR_NO_WINNER = config.getPLJRActionBar("reactions.messages.actionbar.no-winner");
    }
}
