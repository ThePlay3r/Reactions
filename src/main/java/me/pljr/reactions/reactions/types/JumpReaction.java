package me.pljr.reactions.reactions.types;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.event.EventHandler;

public class JumpReaction extends Reaction {

    public JumpReaction(){
        super(ReactionType.JUMP);
    }

    @EventHandler
    private void onJump(PlayerJumpEvent event){
        finish(event.getPlayer());
    }
}
