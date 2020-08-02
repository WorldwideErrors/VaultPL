package vaultzhyra.vaultzhyra;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import vaultzhyra.vaultzhyra.utils.Utils;

import static vaultzhyra.vaultzhyra.VaultZhyra.PersonalVault;

public class VaultEvent implements Listener {

    @EventHandler
    public void onGUIClose(InventoryCloseEvent e){
        if (e.getView().getTitle().contains(Utils.chat("&5" + e.getPlayer().getName() + "'s &0kluis"))){
            //Opslaan van de spullen in de kluis.
            PersonalVault.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }
    }

    @EventHandler
    public void onECOpen(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (block.getType().equals(Material.ENDER_CHEST)){
                event.setCancelled(true); //Het openen van de enderchest wordt gecancelled, zodat de standaard enderchest niet geopend wordt.
                Inventory inv = Bukkit.createInventory(p, 36, (Utils.chat("&5" + p.getName() + "'s &0kluis"))); //Custom inventory wordt gemaakt
                p.sendMessage(Utils.chat("&5[Vault] &fKluis aan het openen...")); //Speler wordt op de hoogte gebracht van dat zijn kluis wordt geopend
                if (PersonalVault.containsKey(p.getUniqueId().toString())){
                    //Als de kluis niet leeg is, worden de spullen erin gelegd vanuit de map
                    inv.setContents(PersonalVault.get(p.getUniqueId().toString()));
                    p.openInventory(inv);
                }
                //Lege kluis wordt geopend
                p.openInventory(inv);
            }
        }

    }
}
