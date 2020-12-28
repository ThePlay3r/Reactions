package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum Lang {
    NO_CONSOLE,
    RESTART_SUCCESS,
    END_SUCCESS,
    START_SUCCESS,
    START_FAILURE,
    NONE;
    public static List<String> HELP;
    public static List<String> ADMIN_HELP;
    public static List<String> WORDS;

    private static final Random random = new Random();
    private static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        HELP = config.getStringList("help");
        ADMIN_HELP = config.getStringList("admin-help");
        WORDS = config.getStringList("words");
        lang = new HashMap<>();
        for (Lang lang : values()){
            Lang.lang.put(lang, config.getString("lang."+lang));
        }
    }

    public static String getRandomWord(){
        return WORDS.get(random.nextInt(WORDS.size()));
    }

    public String get(){
        return lang.get(this);
    }
}
