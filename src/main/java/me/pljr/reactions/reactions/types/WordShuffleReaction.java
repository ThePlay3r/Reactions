package me.pljr.reactions.reactions.types;

import me.pljr.pljrapispigot.utils.FormatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Settings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WordShuffleReaction extends Reaction implements Listener {

    public WordShuffleReaction(String word){
        super(ReactionType.WORD_SHUFFLE, word, FormatUtil.scramble(word));
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event){
        if (event.getMessage().contains(getAnswer())){
            if (SETTINGS.isClearAnswer()) event.setCancelled(true);
            Bukkit.getScheduler().runTask(Reactions.get(), ()-> finish(event.getPlayer()));
        }
    }
}
