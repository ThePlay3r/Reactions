package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakReaction extends Reaction {

    public SneakReaction(){
        super(ReactionType.SNEAK);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        finish(event.getPlayer());
    }
}
