package me.pljr.reactions.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.List;
import java.util.Random;

@Getter
public class Settings {
    private final static String PATH = "settings";

    private final boolean clearAnswer;
    private final  int cooldown;
    private final  int time;
    private final  boolean broadcastChat;
    private final  boolean broadcastTitle;
    private final boolean broadcastActionbar;
    private final List<String> words;
    private final boolean cachePlayers;

    public Settings(ConfigManager config){
        clearAnswer = config.getBoolean(PATH+".clear-answer");
        cooldown = config.getInt(PATH+".cooldown");
        time = config.getInt(PATH+".time");
        broadcastChat = config.getBoolean(PATH+".broadcast-chat");
        broadcastTitle = config.getBoolean(PATH+".broadcast-title");
        broadcastActionbar = config.getBoolean(PATH+".broadcast-actionbar");
        words = config.getStringList("words");
        cachePlayers = config.getBoolean(PATH+".cache-players");
    }

    public String getRandomWord(){
        return words.get(new Random().nextInt(words.size()));
    }
}
