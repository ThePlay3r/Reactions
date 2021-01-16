package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum TitleType {
    BROADCAST_START(new PLJRTitle("&a&lREACTIONS", "{message}", 10, 40 ,10)),
    BROADCAST_END(new PLJRTitle("&a&lREACTIONS", "&e{name} &fwon &e{prize}$&f!", 10, 40 , 10)),
    BROADCAST_NO_WINNER(new PLJRTitle("&a&lREACTIONS", "&eNoone &fwon. &c:(", 10, 40, 10));

    private static HashMap<TitleType, PLJRTitle> titles;
    private final PLJRTitle defaultValue;

    TitleType(PLJRTitle defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        titles = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (TitleType titleType : values()){
            if (!fileConfig.isSet(titleType.toString())){
                config.setPLJRTitle(titleType.toString(), titleType.getDefault());
            }
            titles.put(titleType, config.getPLJRTitle(titleType.toString()));
        }
        config.save();
    }

    public PLJRTitle get(){
        return titles.get(this);
    }

    public PLJRTitle getDefault(){
        return this.defaultValue;
    }
}
