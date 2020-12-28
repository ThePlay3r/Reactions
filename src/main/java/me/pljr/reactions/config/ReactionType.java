package me.pljr.reactions.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum ReactionType {
    WORD_SHUFFLE(11),
    WORD_COPY(15),
    WORD_HIDE(4),
    BLOCK_PLACE(49),
    BLOCK_BREAK(23),
    MOB_KILL(21),
    FISH_CATCH(40),
    MATH_SUMMATION(21),
    MATH_SUBSTRACTION(31),
    MATH_MULTIPLICATION(13),
    JUMP(9),
    ARMOR_CHANGE(10),
    BED_ENTER(16),
    BUCKET_EMPTY(12),
    BUCKET_FILL(14),
    PICKUP_ITEM(3),
    DROP_ITEM(4),
    SNEAK(17),
    QUIT(0),
    EAT(8);


    public static String MENU_TITLE;
    public static ItemStack MENU_BACKGROUND_1;
    public static ItemStack MENU_BACKGROUND_2;

    private final int menuSlot;

    ReactionType(int menuSlot){
        this.menuSlot = menuSlot;
    }

    public int getMenuSlot() {
        return menuSlot;
    }

    private static List<ReactionType> enabledReactions = new ArrayList<>();
    private static HashMap<ReactionType, ItemStack> items = new HashMap<>();

    public static void load(ConfigManager config){
        MENU_TITLE = config.getString("stats-menu.title");
        MENU_BACKGROUND_1 = config.getSimpleItemStack("stats-menu.background-1");
        MENU_BACKGROUND_2 = config.getSimpleItemStack("stats-menu.background-2");
        enabledReactions = new ArrayList<>();
        items = new HashMap<>();
        for (ReactionType type : values()){
            if (config.getBoolean("reactions." + type + ".enabled")){
                enabledReactions.add(type);
            }
            items.put(type, config.getSimpleItemStack("stats-menu."+type));
        }
    }

    public static ReactionType getRandom(){
        return enabledReactions.get(new Random().nextInt(enabledReactions.size()));
    }

    public ItemStack getItem(){
        return items.get(this);
    }
}
