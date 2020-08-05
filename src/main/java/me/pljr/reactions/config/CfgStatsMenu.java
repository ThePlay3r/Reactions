package me.pljr.reactions.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.Reactions;
import org.bukkit.inventory.ItemStack;

public class CfgStatsMenu {
    public static final ConfigManager config = Reactions.getConfigManager();

    public static String title;
    public static ItemStack background1;
    public static ItemStack background2;
    public static ItemStack wordShuffle;
    public static ItemStack wordHide;
    public static ItemStack wordCopy;
    public static ItemStack mathMultiplication;
    public static ItemStack mathSubstraction;
    public static ItemStack mathSummation;
    public static ItemStack blockBreak;
    public static ItemStack blockPlace;
    public static ItemStack fishCatch;
    public static ItemStack mobKill;

    public static void load(){
        CfgStatsMenu.title = config.getString("stats-menu.title");
        CfgStatsMenu.background1 = config.getSimpleItemStack("stats-menu.background-1");
        CfgStatsMenu.background2 = config.getSimpleItemStack("stats-menu.background-2");
        CfgStatsMenu.wordShuffle = config.getSimpleItemStack("stats-menu.word-shuffle");
        CfgStatsMenu.wordHide = config.getSimpleItemStack("stats-menu.word-hide");
        CfgStatsMenu.wordCopy = config.getSimpleItemStack("stats-menu.word-copy");
        CfgStatsMenu.mathMultiplication = config.getSimpleItemStack("stats-menu.math-multiplication");
        CfgStatsMenu.mathSubstraction = config.getSimpleItemStack("stats-menu.math-substraction");
        CfgStatsMenu.mathSummation = config.getSimpleItemStack("stats-menu.math-summation");
        CfgStatsMenu.blockBreak = config.getSimpleItemStack("stats-menu.block-break");
        CfgStatsMenu.blockPlace = config.getSimpleItemStack("stats-menu.block-place");
        CfgStatsMenu.fishCatch = config.getSimpleItemStack("stats-menu.fish-catch");
        CfgStatsMenu.mobKill = config.getSimpleItemStack("stats-menu.mob-kill");
    }
}
