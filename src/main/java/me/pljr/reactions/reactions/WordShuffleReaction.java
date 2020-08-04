package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.pljrapi.utils.FormatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.CfgWords;
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

public class WordShuffleReaction extends Reaction implements Listener {
    private final String word;
    private final PluginManager pluginManager;

    public WordShuffleReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.WORD_SHUFFLE);
        setup();

        if (CfgWords.words.size() > 0){
            word = CfgWords.words.get(new Random().nextInt(CfgWords.words.size()));
        }else{
            word = "word";
        }
        setAnswer(word);

        String shuffledWord = FormatUtil.scramble(word);
        if (isChatBroadcast()){
            List<String> broadcast = new ArrayList<>();
            for (String line : getChatBroadcastStart()){
                broadcast.add(line.replace("%word", shuffledWord));
            }
            ChatUtil.broadcast(broadcast);
        }
        if (isTitleBroadcast()){
            PLJRTitle title = getTitleBroadcastEnd();
            TitleManager.broadcast(new PLJRTitle(
                    title.getTitle().replace("%word", shuffledWord),
                    title.getSubtitle().replace("%word", shuffledWord),
                    title.getIn(), title.getStay(), title.getOut()
            ));
        }
        if (isActionBarBroadcast()){
            PLJRActionBar actionBar = getActionBarBroadcastEnd();
            ActionBarManager.broadcast(new PLJRActionBar(
                    actionBar.getMessage().replace("%word", shuffledWord),
                    actionBar.getDuration()
            ));
        }
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event){
        if (event.getMessage().contains(word)){
            if (CfgSettings.clearAnswer) event.setCancelled(true);
            pluginManager.callEvent(new ReactionEvent(event.getPlayer(), getType()));
            HandlerList.unregisterAll(this);
        }
    }
}
