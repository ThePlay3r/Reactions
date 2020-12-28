package me.pljr.reactions.reactions.types;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;

public class ArmorChangeReaction extends Reaction {

    public ArmorChangeReaction(){
        super(ReactionType.ARMOR_CHANGE);
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event){
        finish(event.getPlayer());
    }
}
