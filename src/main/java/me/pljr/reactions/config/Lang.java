package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum Lang {
    HELP("" +
            "\n&a&lReactions Help" +
            "\n" +
            "\n&e/reactions <player> &8» &fOpens GUI with player's statistics." +
            "\n&e/reactions help &8» &fDisplays this message."),

    BROADCAST_START("" +
            "\n&a&lREACTIONS" +
            "\n{message}" +
            "\n"),

    BROADCAST_END("" +
            "\n&a&lREACTIONS" +
            "\n&e{name} &fwon &e{prize}$&f!" +
            "\n&fThe word was: &e{answer}&f." +
            "\n"),

    BROADCAST_NO_WINNER("" +
            "\n&a&lREACTIONS" +
            "\n&eNoone &fwon. &c:(" +
            "\n&fThe word was: &e{answer}&f." +
            "\n"),

    MENU_TITLE("&8&lReactions");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        lang = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (Lang lang : values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.getDefault());
            }
            Lang.lang.put(lang, config.getString(lang.toString()));
        }
        config.save();
    }

    public String get(){
        return lang.get(this);
    }

    public String getDefault(){
        return this.defaultValue;
    }
}
