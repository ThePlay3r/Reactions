package me.pljr.reactions.listeners;

import me.pljr.reactions.Reactions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        Reactions.getQueryManager().loadPlayerSync(event.getName());
    }
}
