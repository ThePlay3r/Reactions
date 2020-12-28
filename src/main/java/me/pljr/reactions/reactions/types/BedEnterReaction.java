package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEnterReaction extends Reaction {

    public BedEnterReaction(){
        super(ReactionType.BED_ENTER);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event){
        finish(event.getPlayer());
    }
}
