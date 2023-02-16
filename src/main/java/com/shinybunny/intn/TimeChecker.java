package com.shinybunny.intn;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeChecker extends BukkitRunnable {
    private final INeedTheNight plugin;
    private boolean didResetToday = false;

    public TimeChecker(INeedTheNight plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (Bukkit.getServer().getWorlds().get(0).isDayTime()) {
            if (!didResetToday) {
                plugin.needTheNight.clear();
                didResetToday = true;
            }
        } else {
            didResetToday = false;
        }
    }
}
