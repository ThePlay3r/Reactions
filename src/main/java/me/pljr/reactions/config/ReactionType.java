package me.pljr.reactions.config;

import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.xseries.XMaterial;
import me.pljr.reactions.reactions.Reaction;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum ReactionType {
    WORD_SHUFFLE(11, new ItemBuilder(XMaterial.BOOK).withName("&eDeciphered words:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fDecipher &e{word} &fas first and win a prize!"),
    WORD_COPY(15, new ItemBuilder(XMaterial.PAPER).withName("&eCopied words:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fCopy &e{word} &fas first and win a prize!"),
    WORD_HIDE(4, new ItemBuilder(XMaterial.BARRIER).withName("&eFound words:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&e<hover:show_text:'{word}'>&eHover &fover and copy the word!"),
    BLOCK_PLACE(49, new ItemBuilder(XMaterial.GRASS).withName("&eBlocks placed:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&ePlace &fa random block and win a prize!"),
    BLOCK_BREAK(23, new ItemBuilder(XMaterial.DIAMOND_PICKAXE).withName("&eBlocks destroyed:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eBreak &fa random block and win a prize!"),
    MOB_KILL(21, new ItemBuilder(XMaterial.IRON_SWORD).withName("&eMobs killed:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eKill &fa random mob and win a prize!"),
    FISH_CATCH(40, new ItemBuilder(XMaterial.FISHING_ROD).withName("&eFish caught:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eCatch &fa fish and win a prize!"),
    MATH_SUMMATION(21, new ItemBuilder(XMaterial.PLAYER_HEAD).withOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWEyZDg5MWM2YWU5ZjZiYWEwNDBkNzM2YWI4NGQ0ODM0NGJiNmI3MGQ3ZjFhMjgwZGQxMmNiYWM0ZDc3NyJ9fX0=").withName("&eSummations solved:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fWhat is &e{word}&f?"),
    MATH_SUBTRACTION(31, new ItemBuilder(XMaterial.PLAYER_HEAD).withOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM1ZTRlMjZlYWZjMTFiNTJjMTE2NjhlMWQ2NjM0ZTdkMWQwZDIxYzQxMWNiMDg1ZjkzOTQyNjhlYjRjZGZiYSJ9fX0=").withName("&eSubstractions solved:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fWhat is &e{word}&f?"),
    MATH_MULTIPLICATION(13, new ItemBuilder(XMaterial.PLAYER_HEAD).withOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk0YjVlM2RkYzdhOGYzM2M3OTM0Njg2MGQzOTIzYzcxYTU4MmZkYTE2YWNkYmY0YWQzYzBjYzQ2NWZkOTI2In19fQ==").withName("&eMultiplications solved:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fWhat is &e{word}&f?"),
    JUMP(9, new ItemBuilder(XMaterial.FEATHER).withName("&eJumps made:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fFirst to &ejump &fwins!"),
    ARMOR_CHANGE(10, new ItemBuilder(XMaterial.DIAMOND_CHESTPLATE).withName("&eArmors changed:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fFirst to &echange armor &fwins!"),
    BED_ENTER(16, new ItemBuilder(XMaterial.RED_BED).withName("&eBeds entered:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fFirst to &eenter a bed &fwins!"),
    BUCKET_EMPTY(12, new ItemBuilder(XMaterial.BUCKET).withName("&eBuckets emptied:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eEmpty &fa bucket and win!"),
    BUCKET_FILL(14, new ItemBuilder(XMaterial.WATER_BUCKET).withName("&eBuckets filled:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eFill &fa bucket and win!"),
    PICKUP_ITEM(3, new ItemBuilder(XMaterial.DIAMOND).withName("&eItems picked up:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&fPick up &fan item and win!"),
    DROP_ITEM(4, new ItemBuilder(XMaterial.DIAMOND).withName("&eItems dropped:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eDrop &fan item and win!"),
    SNEAK(17, new ItemBuilder(XMaterial.BOOK).withName("&eSneaks toggled:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eSneak &fand win!"),
    QUIT(0, new ItemBuilder(XMaterial.REDSTONE_LAMP).withName("&eGames quited:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eQuit &fthe server and win!"),
    EAT(8, new ItemBuilder(XMaterial.COOKED_BEEF).withName("&eFood eaten:").withLore("&8► &7{amount}", "", "&aServer top:", "&f{topName} &7- &f{topAmount}").create(), 100.0, "&eConsume &fan item and win!");

    private static List<ReactionType> enabled;
    private static HashMap<ReactionType, Integer> menuSlots;
    private static HashMap<ReactionType, ItemStack> menuItems;
    private static HashMap<ReactionType, Double> winAmounts;
    private static HashMap<ReactionType, String> messages;
    private final int defaultMenuSlot;
    private final ItemStack defaultMenuItem;
    private final double defaultWinAmount;
    private final String defaultMessage;

    ReactionType(int defaultMenuSlot, ItemStack defaultMenuItem, double defaultWinAmount, String defaultMessage){
        this.defaultMenuSlot = defaultMenuSlot;
        this.defaultMenuItem = defaultMenuItem;
        this.defaultWinAmount = defaultWinAmount;
        this.defaultMessage = defaultMessage;
    }

    public static void load(ConfigManager config){
        enabled = new ArrayList<>();
        menuSlots = new HashMap<>();
        menuItems = new HashMap<>();
        winAmounts = new HashMap<>();
        messages = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (ReactionType type : values()){
            if (!fileConfig.isSet(type.toString())){
                fileConfig.set(type.toString()+".enabled", true);
                fileConfig.set(type.toString()+".menu-slot", type.getDefaultMenuSlot());
                config.setSimpleItemStack(type.toString()+".menu-item", type.getDefaultMenuItem());
                fileConfig.set(type.toString()+".win-amount", type.getDefaultWinAmount());
                fileConfig.set(type.toString()+".message", type.getDefaultMessage());
            }
            if (config.getBoolean(type.toString()+".enabled")){
                enabled.add(type);
            }
            menuSlots.put(type, config.getInt(type.toString()+".menu-slot"));
            menuItems.put(type, config.getSimpleItemStack(type.toString()+".menu-item"));
            winAmounts.put(type, config.getDouble(type.toString()+".win-amount"));
            messages.put(type, config.getString(type.toString()+".message"));
        }
        config.save();
    }

    public static ReactionType getRandom(){
        return enabled.get(new Random().nextInt(enabled.size()));
    }

    public int getDefaultMenuSlot(){
        return this.defaultMenuSlot;
    }

    public ItemStack getDefaultMenuItem() {
        return defaultMenuItem;
    }

    public double getDefaultWinAmount() {
        return defaultWinAmount;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public int getMenuSlot(){
        return menuSlots.get(this);
    }

    public ItemStack getMenuItem(){
        return menuItems.get(this);
    }

    public double getWinAmount(){
        return winAmounts.get(this);
    }

    public String getMessage(){
        return messages.get(this);
    }
}
