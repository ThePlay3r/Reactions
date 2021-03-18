package me.pljr.reactions.reactions.types;

import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Settings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MathSummationReaction extends Reaction {

    public MathSummationReaction(int a, int b){
        super(ReactionType.MATH_SUMMATION, a+b+"", a + " + " + b);
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event){
        if (event.getMessage().contains(getAnswer())){
            if (SETTINGS.isClearAnswer()) event.setCancelled(true);
            Bukkit.getScheduler().runTask(Reactions.get(), () -> finish(event.getPlayer()));
        }
    }
}
