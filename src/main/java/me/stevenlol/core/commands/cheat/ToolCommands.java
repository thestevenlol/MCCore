package me.stevenlol.core.commands.cheat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.Objects;

public class ToolCommands extends BaseCommand{


    /* Start Repair */

    @CommandAlias("repair")
    @CommandPermission("core.cheat.repair")
    @SuppressWarnings("unused")
    public void repair(Player player) {
        // check if is air
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage("You must be holding an item!");
            return;
        }

        // check if is a block
        if (player.getInventory().getItemInMainHand().getType().isBlock()) {
            player.sendMessage("You cannot repair blocks!");
            return;
        }

        // check if is a tool
        if (!player.getInventory().getItemInMainHand().getType().isItem()) {
            player.sendMessage("You cannot repair this item!");
            return;
        }

        // repair
        ItemStack item = player.getInventory().getItemInMainHand();
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDamage(item.getMaxItemUseDuration());
        item.setItemMeta(meta);
        player.sendMessage(Color.translate("&aYour item has been repaired!"));

    }

    /* End Repair */

    /* Start Enchant */

    @CommandAlias("enchant")
    @CommandPermission("core.cheat.enchant")
    @CommandCompletion("@enchantnames")
    @SuppressWarnings("unused")
    public void enchant(Player player, String[] args) {
        switch (args.length) {
            case 0 -> player.sendMessage(Color.translate("&cUsage: /enchant <enchant> [level]"));
            case 1 -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                String enchant = args[0];
                int level = 1;

                if (verifyEnchant(player)) {
                    item.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(enchant))), level);
                    player.sendMessage(Color.translate("&aSuccessfully enchanted your item!"));
                }
            }
            case 2 -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                String enchant = args[0];
                int level;

                try {
                    level = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(Color.translate("&cInvalid level!"));
                    return;
                }

                if (verifyEnchant(player)) {
                    item.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(enchant))), level);
                    player.sendMessage(Color.translate("&aSuccessfully enchanted your item!"));
                }
            }
        }
    }

    private boolean verifyEnchant(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) {
            player.sendMessage(Color.translate("&cYou cannot enchant this item."));
            return false;
        }
        return true;
    }

    /* End Enchant */

    /* Start Damage */

    @CommandAlias("damage")
    @CommandPermission("core.cheat.damage")
    @SuppressWarnings("unused")
    public void damage(Player player, String[] args) {
        switch (args.length) {
            case 0 -> player.sendMessage(Color.translate("&cUsage: /damage <amount>\nWill damage your currently held item, if able."));
            case 1 -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                int amount;

                try {
                    amount = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(Color.translate("&cInvalid amount!"));
                    return;
                }

                if (verifyDamage(player, amount)) {
                    Damageable meta = (Damageable) item.getItemMeta();
                    meta.setDamage(meta.getDamage() + amount);
                    item.setItemMeta(meta);
                    player.sendMessage(Color.translate("&aSuccessfully damaged your item!"));
                }
            }
        }
    }

    private boolean verifyDamage(Player player, int amount) {
        if (amount <= 0) {
            player.sendMessage(Color.translate("&cAmount must be greater than 0."));
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getItemMeta() == null || item.getItemMeta().isUnbreakable() || item.getType().isBlock()) {
            player.sendMessage(Color.translate("&cYou cannot damage this item."));
            return false;
        }
        return true;
    }

    /* End Damage */

}
