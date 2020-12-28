package me.pljr.reactions.reactions.types;

import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.managers.TitleManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.Lang;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.reactions.Reaction;
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

public class WordHideReaction extends Reaction {

    public WordHideReaction(String word){
        super(ReactionType.WORD_HIDE, word, word);
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event){
        if (event.getMessage().contains(getAnswer())){
            if (CfgSettings.CLEAR_ANSWER) event.setCancelled(true);
            Bukkit.getScheduler().runTask(Reactions.getInstance(), ()-> finish(event.getPlayer()));
        }
    }
}
