package me.pljr.reactions.config;

import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.xseries.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum MenuItemType {
    BACKGROUND_1(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("&0").create()),
    BACKGROUND_2(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&0").create());

    private static HashMap<MenuItemType, ItemStack> items;
    private final ItemStack defaultValue;

    MenuItemType(ItemStack defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        items = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (MenuItemType menuItemType : values()){
            if (!fileConfig.isSet(menuItemType.toString())){
                config.setSimpleItemStack(menuItemType.toString(), menuItemType.getDefault());
            }
            items.put(menuItemType, config.getSimpleItemStack(menuItemType.toString()));
        }
        config.save();
    }

    public ItemStack get(){
        return items.get(this);
    }

    public ItemStack getDefault(){
        return this.defaultValue;
    }
}
