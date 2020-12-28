package me.pljr.reactions.listeners;

import me.pljr.reactions.Reactions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Reactions.getPlayerManager().savePlayer(event.getPlayer().getUniqueId());
    }
}
