package me.pljr.reactions.reactions.types;

import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillReaction extends Reaction {

    public MobKillReaction(){
        super(ReactionType.MOB_KILL);
    }

    @EventHandler
    private void onDeath(EntityDeathEvent event){
        Player player = event.getEntity().getKiller();
        if (player == null) return;
        finish(player);
    }
}
