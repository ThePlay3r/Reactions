package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItemReaction extends Reaction {

    public PickupItemReaction(){
        super(ReactionType.PICKUP_ITEM);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if (event.getEntity() instanceof Player){
            finish((Player)event.getEntity());
        }
    }
}
