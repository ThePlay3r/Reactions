package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitReaction extends Reaction {

    public QuitReaction(){
        super(ReactionType.QUIT);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        finish(event.getPlayer());
    }
}
