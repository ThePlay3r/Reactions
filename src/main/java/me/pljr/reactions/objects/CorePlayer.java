package me.pljr.reactions.objects;

import me.pljr.reactions.enums.ReactionType;

import java.util.HashMap;

public class CorePlayer {
    private HashMap<ReactionType, Integer> stats;

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

    public void setStats(HashMap<ReactionType, Integer> stats) {
        this.stats = stats;
    }
}
