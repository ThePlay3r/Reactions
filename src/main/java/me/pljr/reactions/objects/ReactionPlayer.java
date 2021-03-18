package me.pljr.reactions.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.pljr.reactions.config.ReactionType;

import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ReactionPlayer {
    private final UUID uniqueId;
    private HashMap<ReactionType, Integer> stats;

    public ReactionPlayer(UUID uniqueId){
        this.uniqueId = uniqueId;
    }

    public void addReaction(ReactionType type, int amount){
        if (stats.containsKey(type)){
            amount+=stats.get(type);
        }
        stats.put(type, amount);
    }
}
