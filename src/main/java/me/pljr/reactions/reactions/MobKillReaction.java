package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

public class MobKillReaction extends Reaction implements Listener {
    private final PluginManager pluginManager;

    public MobKillReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.MOB_KILL);
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
    private void onChat(EntityDeathEvent event){
        Player player = event.getEntity().getKiller();
        if (player == null) return;
        pluginManager.callEvent(new ReactionEvent(player, getType()));
        HandlerList.unregisterAll(this);
    }
}
