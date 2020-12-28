package me.pljr.reactions.objects;

import me.pljr.reactions.config.ReactionType;

import java.util.HashMap;

public class CorePlayer {
    private final HashMap<ReactionType, Integer> stats;

    public CorePlayer(HashMap<ReactionType, Integer> stats) {
        this.stats = stats;
    }

    public void addReaction(ReactionType type, int amount){
        if (stats.containsKey(type)){
            amount+=stats.get(type);
        }
        stats.put(type, amount);
    }

    public HashMap<ReactionType, Integer> getStats() {
        return stats;
    }
}
