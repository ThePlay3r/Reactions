package me.pljr.reactions.managers;

import me.pljr.reactions.Reactions;
import me.pljr.reactions.objects.CorePlayer;

import java.util.HashMap;

public class PlayerManager {
    private static final HashMap<String, CorePlayer> players = new HashMap<>();
    private static final QueryManager query = Reactions.getQueryManager();

    public static CorePlayer getCorePlayer(String pName){
        if (players.containsKey(pName)){
            return players.get(pName);
        }
        query.loadPlayerSync(pName);
        return getCorePlayer(pName);
    }

    public static void setCorePlayer(String pName, CorePlayer corePlayer){
        players.put(pName, corePlayer);
    }

    public static void savePlayer(String pName){
        if (!players.containsKey(pName)) return;
        query.savePlayer(pName);
    }
}
