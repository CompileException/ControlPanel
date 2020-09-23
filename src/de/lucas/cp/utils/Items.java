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
}
