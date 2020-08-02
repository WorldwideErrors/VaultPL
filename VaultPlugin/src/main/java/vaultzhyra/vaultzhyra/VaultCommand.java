package vaultzhyra.vaultzhyra;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import vaultzhyra.vaultzhyra.utils.Utils;

import static vaultzhyra.vaultzhyra.VaultZhyra.PersonalVault;

public class VaultCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            Inventory inv = Bukkit.createInventory(p, 36, (Utils.chat("&5" + p.getName() + "'s &0kluis")));

                p.sendMessage(Utils.chat("&5[Vault] &fKluis aan het openen...")); //Speler wordt op de hoogte gebracht van dat zijn kluis wordt geopend
                if (PersonalVault.containsKey(p.getUniqueId().toString())){
                    //Als de kluis niet leeg is, worden de spullen erin gelegd vanuit de map
                    inv.setContents(PersonalVault.get(p.getUniqueId().toString()));
                    p.openInventory(inv);
                }
            //Lege kluis wordt geopend
            p.openInventory(inv);
            return true;

        }
        return false;
    }
}
