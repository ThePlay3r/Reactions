package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class EatReaction extends Reaction {

    public EatReaction(){
        super(ReactionType.EAT);
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        finish(event.getPlayer());
    }
}
