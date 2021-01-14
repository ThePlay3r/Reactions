package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum ActionBarType {
    BROADCAST_START(new PLJRActionBar("&a&lREACTIONS: {message}", 40)),
    BROADCAST_END(new PLJRActionBar("&a&lREACTIONS: &e{name} &fwon &e{prize}$&f!", 40)),
    BROADCAST_NO_WINNER(new PLJRActionBar("&a&lREACTIONS: &eNoone &fwon. &c:(", 40));

    private static HashMap<ActionBarType, PLJRActionBar> actionbars;
    private final PLJRActionBar defaultValue;

    ActionBarType(PLJRActionBar defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        actionbars = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (ActionBarType actionBarType : values()){
            if (!fileConfig.isSet(actionBarType.toString())){
                config.setPLJRActionBar(actionBarType.toString(), actionBarType.getDefault());
            }
            actionbars.put(actionBarType, config.getPLJRActionBar(actionBarType.toString()));
        }
    }

    public PLJRActionBar get(){
        return actionbars.get(this);
    }

    public PLJRActionBar getDefault(){
        return this.defaultValue;
    }
}
