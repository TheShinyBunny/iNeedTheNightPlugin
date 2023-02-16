package com.shinybunny.intn;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class INeedTheNight extends JavaPlugin {
    public final Set<Player> needTheNight = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);
        commandHandler.register(new MyCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String getMessage(String key) {
        return getConfig().getString(key, "Error: missing message " + key);
    }

    public static String combineNames(List<String> items) {
        if (items == null || items.isEmpty()) {
            return "";
        }
        if (items.size() == 1) {
            return items.get(0);
        }
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (String item : items) {
            if (i == items.size() - 1) {
                str.append(" and ");
            } else if (i > 0) {
                str.append(", ");
            }
            if (item != null) {
                str.append(item);
            }
            i++;
        }
        return str.toString();
    }
}
