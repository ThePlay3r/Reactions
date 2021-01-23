package me.pljr.reactions.reactions;

import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.VaultUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.*;
import me.pljr.reactions.events.ReactionEvent;
import me.pljr.reactions.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class Reaction implements Listener {
    private final ReactionType type;
    private final String answer;
    private boolean finished;

    public Reaction(ReactionType type){
        this(type, "", "");
    }

    public Reaction(ReactionType type, String answer){
        this(type, answer, "");
    }

    public Reaction(ReactionType type, String answer, String shownAnswer){
        this.answer = answer;
        this.type = type;
        this.finished = false;

        Bukkit.getServer().getPluginManager().registerEvents(this, Reactions.getInstance());

        if (CfgSettings.BROADCAST_CHAT){
            ChatUtil.broadcast(Lang.BROADCAST_START.get()
                    .replace("{message}", type.getMessage().replace("{word}", shownAnswer)), "", false);
        }
        if (CfgSettings.BROADCAST_TITLE){
            new TitleBuilder(TitleType.BROADCAST_START.get())
                    .replaceSubtitle("{message}", type.getMessage().replace("{word}", shownAnswer))
                    .create().broadcast();
        }
        if (CfgSettings.BROADCAST_ACTIONBAR){
            new ActionBarBuilder(ActionBarType.BROADCAST_START.get())
                    .replaceMessage("{message}", type.getMessage().replace("{word}", shownAnswer))
                    .create().broadcast();
        }
    }

    public ReactionType getType() {
        return type;
    }

    public String getAnswer() {
        return answer;
    }

    public void finish(Player player){
        if (finished) return;
        finished = true;
        HandlerList.unregisterAll(this);
        if (player == null){
            if (CfgSettings.BROADCAST_CHAT){
                ChatUtil.broadcast(Lang.BROADCAST_NO_WINNER.get()
                        .replace("{answer}", getAnswer())
                        .replace("{prize}", getType().getWinAmount()+""), "", false);
            }
            if (CfgSettings.BROADCAST_TITLE){
                new TitleBuilder(TitleType.BROADCAST_NO_WINNER.get())
                        .replaceSubtitle("{answer}", getAnswer())
                        .replaceSubtitle("{prize}", getType().getWinAmount()+"")
                        .create().broadcast();
            }
            if (CfgSettings.BROADCAST_ACTIONBAR){
                new ActionBarBuilder(ActionBarType.BROADCAST_NO_WINNER.get())
                        .replaceMessage("{answer}", getAnswer())
                        .replaceMessage("{prize}", getType().getWinAmount()+"")
                        .create().broadcast();
            }
            return;
        }
        new ReactionEvent(player, getType());
        String playerName = player.getName();
        UUID playerId = player.getUniqueId();
        if (CfgSettings.BROADCAST_CHAT){
            ChatUtil.broadcast(Lang.BROADCAST_END.get()
                    .replace("{answer}", getAnswer())
                    .replace("{prize}", getType().getWinAmount()+"")
                    .replace("{name}", playerName), "", false);
        }
        if (CfgSettings.BROADCAST_TITLE){
            new TitleBuilder(TitleType.BROADCAST_END.get())
                    .replaceSubtitle("{answer}", getAnswer())
                    .replaceSubtitle("{prize}", getType().getWinAmount()+"")
                    .replaceSubtitle("{name}", playerName)
                    .create().broadcast();
        }
        if (CfgSettings.BROADCAST_ACTIONBAR){
            new ActionBarBuilder(ActionBarType.BROADCAST_END.get())
                    .replaceMessage("{answer}", getAnswer())
                    .replaceMessage("{prize}", getType().getWinAmount()+"")
                    .replaceMessage("{name}", playerName)
                    .create().broadcast();
        }
        VaultUtil.deposit(player, getType().getWinAmount());
        CorePlayer corePlayer = Reactions.getPlayerManager().getCorePlayer(playerId);
        corePlayer.addReaction(getType(), 1);
        Reactions.getPlayerManager().setCorePlayer(playerId, corePlayer);
    }
}
