package me.pljr.reactions.events;

import me.pljr.reactions.enums.ReactionType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ReactionEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final Player player;
    private final ReactionType type;

    public ReactionEvent(Player player, ReactionType type){
        this.player = player;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }
    public ReactionType getType() {
        return type;
    }
}
