package me.pljr.reactions.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.enums.ReactionType;

import java.util.ArrayList;
import java.util.List;

public class CfgReactions {
    private static final ConfigManager config = Reactions.getConfigManager();

    public static List<ReactionType> enabledReactions = new ArrayList<>();

    public static void load(){
        for (ReactionType type : ReactionType.values()){
            if (config.getBoolean("reactions." + type + ".enabled")){
                CfgReactions.enabledReactions.add(type);
            }
        }
    }
}
