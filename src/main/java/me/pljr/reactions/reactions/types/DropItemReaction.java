package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemReaction extends Reaction {

    public DropItemReaction(){
        super(ReactionType.DROP_ITEM);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        finish(event.getPlayer());
    }
}
