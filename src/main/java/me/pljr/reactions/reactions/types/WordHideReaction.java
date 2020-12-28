package me.pljr.reactions.reactions.types;

import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
