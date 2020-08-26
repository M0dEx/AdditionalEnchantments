package eu.m0dex.additionalenchantments.listeners;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    AdditionalEnchantments instance;

    public PlayerListener(AdditionalEnchantments _instance) {
        instance = _instance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        instance.getPlayerCache().addPlayer(e.getPlayer().getUniqueId());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuitEvent(PlayerQuitEvent e) {

        instance.getPlayerCache().removePlayer(e.getPlayer().getUniqueId());
    }
}
