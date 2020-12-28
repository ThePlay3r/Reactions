package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class BucketEmptyReaction extends Reaction {

    public BucketEmptyReaction(){
        super(ReactionType.BUCKET_EMPTY);
    }

    @EventHandler
    public void onEmpty(PlayerBucketEmptyEvent event){
        finish(event.getPlayer());
    }
}
