package me.pljr.reactions.reactions.types;

import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.managers.TitleManager;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

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
