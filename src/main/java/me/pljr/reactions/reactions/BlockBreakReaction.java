package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.managers.PlayerManager;
import me.pljr.reactions.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.PluginManager;

public class BlockBreakReaction extends Reaction implements Listener {
    private final PluginManager pluginManager;

    public BlockBreakReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.BLOCK_BREAK);
        setup();
        setAnswer("");

        if (isChatBroadcast()){
            ChatUtil.broadcast(getChatBroadcastStart());
        }
        if (isTitleBroadcast()){
            TitleManager.broadcast(getTitleBroadcastStart());
        }
        if (isActionBarBroadcast()){
            ActionBarManager.broadcast(getActionBarBroadcastStart());
        }
    }

    @EventHandler
    private void onChat(BlockBreakEvent event){
        pluginManager.callEvent(new ReactionEvent(event.getPlayer(), getType()));
        HandlerList.unregisterAll(this);
    }
}
