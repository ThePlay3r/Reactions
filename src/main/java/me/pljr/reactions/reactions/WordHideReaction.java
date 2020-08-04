package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.CfgWords;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordHideReaction extends Reaction implements Listener {
    private final String word;
    private final PluginManager pluginManager;

    public WordHideReaction(){
        this.pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, Reactions.getInstance());

        setType(ReactionType.WORD_COPY);
        setup();

        if (CfgWords.words.size() > 0){
            word = CfgWords.words.get(new Random().nextInt(CfgWords.words.size()));
        }else{
            word = "word";
        }
        setAnswer(word);

        if (isChatBroadcast()){
            List<TextComponent> broadcast = new ArrayList<>();
            for (String line : getChatBroadcastStart()){
                TextComponent component = new TextComponent(line);
                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(word).create()));
                broadcast.add(component);
            }
            for (Player player : Bukkit.getOnlinePlayers()){
                for (TextComponent component : broadcast){
                    player.spigot().sendMessage(component);
                }
            }
        }
        if (isTitleBroadcast()){
            PLJRTitle title = getTitleBroadcastEnd();
            TitleManager.broadcast(new PLJRTitle(
                    title.getTitle().replace("%word", word),
                    title.getSubtitle().replace("%word", word),
                    title.getIn(), title.getStay(), title.getOut()
            ));
        }
        if (isActionBarBroadcast()){
            PLJRActionBar actionBar = getActionBarBroadcastEnd();
            ActionBarManager.broadcast(new PLJRActionBar(
                    actionBar.getMessage().replace("%word", word),
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
