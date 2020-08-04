package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathSubstractionReaction extends Reaction implements Listener {
    private final int answer;
    private final PluginManager pluginManager;

    public MathSubstractionReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.WORD_COPY);
        setup();

        int a = new Random().nextInt(100);
        int b = new Random().nextInt(100);
        answer = a-b;

        setAnswer(answer+"");

        if (isChatBroadcast()){
            List<String> broadcast = new ArrayList<>();
            for (String line : getChatBroadcastStart()){
                broadcast.add(line.replace("%a", a+"").replace("%b", b+""));
            }
            ChatUtil.broadcast(broadcast);
        }
        if (isTitleBroadcast()){
            PLJRTitle title = getTitleBroadcastEnd();
            TitleManager.broadcast(new PLJRTitle(
                    title.getTitle().replace("%a", a+"").replace("%b", b+""),
                    title.getSubtitle().replace("%a", a+"").replace("%b", b+""),
                    title.getIn(), title.getStay(), title.getOut()
            ));
        }
        if (isActionBarBroadcast()){
            PLJRActionBar actionBar = getActionBarBroadcastEnd();
            ActionBarManager.broadcast(new PLJRActionBar(
                    actionBar.getMessage().replace("%a", a+"").replace("%b", b+""),
                    actionBar.getDuration()
            ));
        }
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event){
        if (event.getMessage().contains(answer+"")){
            if (CfgSettings.clearAnswer) event.setCancelled(true);
            pluginManager.callEvent(new ReactionEvent(event.getPlayer(), getType()));
            HandlerList.unregisterAll(this);
        }
    }
}
