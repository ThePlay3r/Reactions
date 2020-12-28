package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakReaction extends Reaction {

    public BlockBreakReaction(){
        super(ReactionType.BLOCK_BREAK);
    }

    @EventHandler
    private void onBreak(BlockBreakEvent event){
        finish(event.getPlayer());
    }
}
