package me.pljr.reactions.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.Reactions;

import java.util.List;

public class CfgWords {
    private static final ConfigManager config = Reactions.getConfigManager();

    public static List<String> words;

    public static void load(){
        CfgWords.words = config.getStringList("words");
    }
}
