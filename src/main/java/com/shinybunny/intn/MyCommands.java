package com.shinybunny.intn;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Dependency;
import revxrsal.commands.annotation.Optional;

import java.util.HashSet;
import java.util.Set;

public class MyCommands {
    @Dependency
    private INeedTheNight plugin;
    @Command({"ineedthenight","needthenight","dontsleep","disablesleep"})
    public void ineedthenight(Player sender) {
        if (plugin.needTheNight.add(sender)) {
            Bukkit.broadcast(MiniMessage.miniMessage().deserialize(plugin.getMessage("messages.disabled-sleep"), Placeholder.component("player", sender.displayName())));
        } else {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getMessage("messages.already-prevented")));
        }
    }

    private final Set<Player> awaitingConfirmation = new HashSet<>();

    @Command({"iwanttosleep","allowsleep","letmesleep"})
    @AutoComplete("* confirm")
    public void iwanttosleep(Player sender, @Optional String confirm) {
        if (confirm != null && confirm.equalsIgnoreCase("confirm")) {
            if (awaitingConfirmation.contains(sender)) {
                plugin.needTheNight.clear();
                Bukkit.broadcast(MiniMessage.miniMessage().deserialize(plugin.getMessage("messages.enabled-sleep"), Placeholder.component("player", sender.displayName())));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getMessage("messages.confirmation-required")));
            }
        } else {
            awaitingConfirmation.add(sender);
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getMessage("messages.confirm-enable-sleep")));
        }
    }
}
