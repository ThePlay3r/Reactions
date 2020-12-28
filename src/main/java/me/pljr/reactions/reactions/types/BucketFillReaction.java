package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class BucketFillReaction extends Reaction {

    public BucketFillReaction(){
        super(ReactionType.BUCKET_FILL);
    }

    @EventHandler
    public void onFill(PlayerBucketFillEvent event){
        finish(event.getPlayer());
    }
}
