package me.pljr.reactions.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.enums.Lang;

import java.util.HashMap;
import java.util.List;

public class CfgLang {
    private static final ConfigManager config = Reactions.getConfigManager();

    public static List<String> help;
    public static List<String> adminHelp;
    public static HashMap<Lang, String> lang = new HashMap<>();

    public static void load(){
        CfgLang.help = config.getStringList("help");
        CfgLang.adminHelp = config.getStringList("admin-help");
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang." + lang.toString()));
        }
    }
}
