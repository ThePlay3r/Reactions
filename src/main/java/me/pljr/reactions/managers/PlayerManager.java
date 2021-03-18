package me.pljr.reactions.managers;

import lombok.AllArgsConstructor;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.objects.ReactionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class PlayerManager {
    private final static int AUTOSAVE = 12000;

    private final HashMap<UUID, ReactionPlayer> players = new HashMap<>();
    private final JavaPlugin plugin;
    private final QueryManager queryManager;
    private final boolean cachePlayers;

    public void getPlayer(UUID uuid, Consumer<ReactionPlayer> consumer){
        if (!players.containsKey(uuid)){
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                ReactionPlayer player = queryManager.loadPlayer(uuid);
                setPlayer(uuid, player);
                consumer.accept(player);
            });
        }else{
            consumer.accept(players.get(uuid));
        }
    }

    public ReactionPlayer getPlayer(UUID uuid){
        if (!players.containsKey(uuid)){
            ReactionPlayer player = queryManager.loadPlayer(uuid);
            setPlayer(uuid, player);
            return player;
        }else{
            return players.get(uuid);
        }
    }

    public void setPlayer(UUID uuid, ReactionPlayer player){
        players.put(uuid, player);
    }

    public void savePlayer(UUID uuid){
        if (!cachePlayers) players.remove(uuid);
        queryManager.savePlayer(players.get(uuid));
    }

    public void initAutoSave(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Reactions.log.info("Saving players..");
            for (Map.Entry<UUID, ReactionPlayer> entry : players.entrySet()){
                savePlayer(entry.getKey());
            }
            Reactions.log.info("All players were saved.");
        }, AUTOSAVE, AUTOSAVE);
    }
}
