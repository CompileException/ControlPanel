/*
 * Copyright (c) 23.9.2020.
 *
 * CP copyright by Lucas
 */

package de.lucas.cp.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class Items {
    public static ItemStack creatItem(Material material, int subid, String displayname) {

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }
    public static ItemStack createItemEnch(Material material, int subid, Enchantment ent, int Level , String displayname) {

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);
        item.addUnsafeEnchantment(ent, Level);
        return item;

    }
    public static ItemStack createItemLore(Material material, int subid, List<String> string, String displayname) {

        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setLore(string);
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }

    public static ItemStack getSkull(String name, String displayname) {
        @SuppressWarnings("deprecation")
        ItemStack skull = new ItemStack(397, 1, (short) 3);
        SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
        skullmeta.setDisplayName(displayname);
        skullmeta.setOwner(name);
        skull.setItemMeta(skullmeta);
        return skull;
    }


    public static void setjoinItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(0, Items.creatItem(Material.COMPASS
                , 0, "§6Navigator §7x Rechtsklick"));
        player.getInventory().setItem(1, Items.creatItem(Material.BLAZE_ROD
                , 0, "§6Spieler-Verstecken §7x Rechtsklick"));

        if(player.hasPermission("lobbysystem.silentlobby.item")) {
            player.getInventory().setItem(3, Items.creatItem(Material.TNT
                    , 0, "§6Silentlobby §7x Rechtsklick"));
            player.getInventory().setItem(4, Items.creatItem(Material.EYE_OF_ENDER
                    , 0, "§6Schutzschild §7x Rechtsklick"));
            player.getInventory().setItem(5, Items.creatItem(Material.NAME_TAG
                    , 0, "§6Nick §7x Rechtsklick"));
        }
        if(player.hasPermission("lobbysystem.bauserver.item")) {
            player.getInventory().setItem(35, Items.creatItem(Material.COMMAND_MINECART
                    , 0, "§eBauServer §7x Linksklick"));
        }

        player.getInventory().setItem(7, Items.creatItem(Material.ENDER_CHEST
                , 0, "§6Gadgets §7x Rechtsklick"));
        player.getInventory().setItem(8, Items.getSkull(player.getName()
                , "§6Freunde §7x Rechtsklick"));


        player.getInventory().setItem(21, Items.creatItem(Material.COAL
                , 0, "§cLobby-1 §7x Linksklick"));
        player.getInventory().setItem(22, Items.creatItem(Material.COAL
                , 0, "§cLobby-2 §7x Linksklick"));
        player.getInventory().setItem(23, Items.creatItem(Material.COAL
                , 0, "§cLobby-3 §7x Linksklick"));
    }
}
