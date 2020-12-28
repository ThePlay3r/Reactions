package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

public class FishCatchReaction extends Reaction {

    public FishCatchReaction(){
        super(ReactionType.FISH_CATCH);
    }

    @EventHandler
    private void onFish(PlayerFishEvent event){
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        finish(event.getPlayer());
    }
}
