package me.pljr.reactions.managers;

import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.objects.CorePlayer;
import me.pljr.reactions.reactions.*;
import me.pljr.reactions.utils.ReactionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class ReactionManager extends ReactionUtil implements Listener {
    private Reaction running;

    public void start(Reaction reaction){
        if (reaction == null){
            ReactionType type = ReactionType.getRandom();
            switch (type){
                case BLOCK_BREAK:
                    this.running = new BlockBreakReaction();
                    break;
                case BLOCK_PLACE:
                    this.running = new BlockPlaceReaction();
                    break;
                case FISH_CATCH:
                    this.running = new FishCatchReaction();
                    break;
                case MATH_MULTIPLICATION:
                    this.running = new MathMultiplicationReaction();
                    break;
                case MATH_SUBSTRACTION:
                    this.running = new MathSubstractionReaction();
                    break;
                case MATH_SUMMATION:
                    this.running = new MathSummationReaction();
                    break;
                case MOB_KILL:
                    this.running = new MobKillReaction();
                    break;
                case WORD_COPY:
                    this.running = new WordCopyReaction();
                    break;
                case WORD_HIDE:
                    this.running = new WordHideReaction();
                    break;
                case WORD_SHUFFLE:
                    this.running = new WordShuffleReaction();
                    break;
            }
        }else{
            this.running = reaction;
        }
        setReaction(this.running);
        Bukkit.getScheduler().runTaskLater(Reactions.getInstance(), ()->{
            if (running==null) return;
            broadcastEnd(null, running.getAnswer(), running.getWin());
            restart(true);
        }, CfgSettings.time*20);
    }

    public void restart(boolean cooldown){
        if (running != null){
            HandlerList.unregisterAll((Listener) running);
            running = null;
        }
        if (CfgSettings.restartOnEnd){
            if (cooldown){
                Bukkit.getScheduler().runTaskLater(Reactions.getInstance(), ()->{
                    if (running != null) return;
                    start(null);
                }, CfgSettings.cooldown*20);
            }else{
                start(null);
            }
        }
    }

    public void end(){
        if (running != null){
            HandlerList.unregisterAll((Listener) running);
            running = null;
        }
    }

    public boolean isRunning(){
        return running != null;
    }

    @EventHandler
    private void onReact(ReactionEvent event){
        Player player = event.getPlayer();
        String playerName = player.getName();
        broadcastEnd(playerName, running.getAnswer(), running.getWin());
        CorePlayer corePlayer = PlayerManager.getCorePlayer(playerName);
        corePlayer.addReaction(event.getType(), 1);
        PlayerManager.setCorePlayer(playerName, corePlayer);
        Reactions.getQueryManager().savePlayer(playerName);
        restart(true);
    }
}
