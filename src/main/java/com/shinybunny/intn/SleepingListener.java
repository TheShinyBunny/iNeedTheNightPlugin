package com.shinybunny.intn;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepingListener implements Listener {
    private final INeedTheNight plugin;

    public SleepingListener(INeedTheNight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (!plugin.needTheNight.isEmpty()) {
            String playerNames = INeedTheNight.combineNames(plugin.needTheNight.stream().map(Player::getName).toList());
            event.getPlayer().sendActionBar(MiniMessage.miniMessage()
                    .deserialize(plugin.getMessage("messages.cannot-sleep"), Placeholder.unparsed("players", playerNames)));
            event.setCancelled(true);
        }
    }
}
