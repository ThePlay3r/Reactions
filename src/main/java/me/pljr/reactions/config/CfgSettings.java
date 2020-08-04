package me.pljr.reactions.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.Reactions;

public class CfgSettings {
    private static final ConfigManager config = Reactions.getConfigManager();

    public static boolean startOnStartup;
    public static boolean restartOnEnd;
    public static boolean clearAnswer;
    public static int cooldown;
    public static int time;

    public static void load(){
        CfgSettings.startOnStartup = config.getBoolean("settings.start-on-startup");
        CfgSettings.restartOnEnd = config.getBoolean("settings.restart-on-end");
        CfgSettings.clearAnswer = config.getBoolean("settings.clear-answer");
        CfgSettings.cooldown = config.getInt("settings.cooldown");
        CfgSettings.time = config.getInt("settings.time");
    }
}
