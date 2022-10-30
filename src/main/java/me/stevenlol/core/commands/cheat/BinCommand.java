package me.stevenlol.core.commands.cheat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;


public class BinCommand extends BaseCommand {

    @CommandAlias("bin|trash|garbage|b")
    @Description("Opens your trash can.")
    @CommandPermission("core.cheat.bin")
    @SuppressWarnings("unused")
    public void bin(Player player) {
        Inventory bin = player.getServer().createInventory(null, InventoryType.DISPENSER, Color.translate("&c&lTrash"));
        player.openInventory(bin);
    }

}
