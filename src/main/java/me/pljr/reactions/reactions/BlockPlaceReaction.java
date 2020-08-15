package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginManager;

public class BlockPlaceReaction extends Reaction implements Listener {
    private final PluginManager pluginManager;

    public BlockPlaceReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.BLOCK_PLACE);
        setup();
        setAnswer("");

        if (isChatBroadcast()){
            ChatUtil.broadcast(getChatBroadcastStart(), CfgSettings.bungee);
        }
        if (isTitleBroadcast()){
            TitleManager.broadcast(getTitleBroadcastStart());
        }
        if (isActionBarBroadcast()){
            ActionBarManager.broadcast(getActionBarBroadcastStart());
        }
    }

    @EventHandler
    private void onChat(BlockPlaceEvent event){
        pluginManager.callEvent(new ReactionEvent(event.getPlayer(), getType()));
        HandlerList.unregisterAll(this);
    }
}
