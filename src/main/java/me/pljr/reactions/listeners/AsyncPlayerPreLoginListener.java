package me.pljr.reactions.listeners;

import lombok.AllArgsConstructor;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@AllArgsConstructor
public class AsyncPlayerPreLoginListener implements Listener {
    private final PlayerManager playerManager;

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        playerManager.getPlayer(event.getUniqueId(), ignored -> Reactions.log.info("Loaded " + event.getName()));
    }
}