package vaultzhyra.vaultzhyra;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VaultZhyra extends JavaPlugin {

    public static Map<String, ItemStack[]> PersonalVault = new HashMap<String, ItemStack[]>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new VaultEvent(), this);
        getCommand("vault").setExecutor(new VaultCommand());
        this.saveDefaultConfig();
        this.RestoreInv();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!PersonalVault.isEmpty()){
            this.SaveInv();
        }
    }

    public void SaveInv(){
        for (Map.Entry<String, ItemStack[]> entry : PersonalVault.entrySet()){
            this.getConfig().set("data." + entry.getKey(), entry.getValue());

            this.saveConfig();
        }
    }

    public void RestoreInv(){
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            ItemStack[] content = ((List<ItemStack>) this.getConfig().get("data." + key)).toArray(new ItemStack[0]);
            PersonalVault.put(key, content);
        });
    }
}
