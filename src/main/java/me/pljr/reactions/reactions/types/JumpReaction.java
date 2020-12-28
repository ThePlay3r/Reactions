package me.pljr.reactions.reactions.types;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.managers.TitleManager;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.PluginManager;

public class JumpReaction extends Reaction {

    public JumpReaction(){
        super(ReactionType.JUMP);
    }

    @EventHandler
    private void onJump(PlayerJumpEvent event){
        finish(event.getPlayer());
    }
}
