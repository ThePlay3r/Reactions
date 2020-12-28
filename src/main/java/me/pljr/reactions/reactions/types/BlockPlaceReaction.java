package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceReaction extends Reaction {

    public BlockPlaceReaction(){
        super(ReactionType.BLOCK_PLACE);
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event){
        finish(event.getPlayer());
    }
}
