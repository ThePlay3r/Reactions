package me.pljr.reactions.listeners;

import me.pljr.reactions.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        PlayerManager.savePlayer(event.getPlayer().getUniqueId());
    }
}
