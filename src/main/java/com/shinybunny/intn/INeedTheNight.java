package com.shinybunny.intn;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import javax.annotation.Nullable;
import java.util.*;

public final class INeedTheNight extends JavaPlugin {
    public final Set<Player> needTheNight = new HashSet<>();
    private Map<String, String> messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);
        commandHandler.register(new MyCommands());
        Bukkit.getPluginManager().registerEvents(new SleepingListener(this), this);
        TimeChecker timeChecker = new TimeChecker(this);
        timeChecker.start();

        loadMessages();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Gets the requested message from the config file. Might return null if key does not exist.
     * @param key The Key leading to the message. Sections are seperated with a dot.
     * @return The message stored on the given key, or alternatively null if there is no value for the given key.
     */
    public @Nullable String getMessage(String key) {
        return messages.get(key);
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

    private Map<String, String> loadMessages() {
        Map<String, String> messages = new HashMap<>();

        getConfig().getKeys(true).forEach(key -> messages.put(key, getConfig().getString(key)));

        return messages;
    }
    
}
