package me.pljr.reactions.menus;

import me.pljr.pljrapi.utils.ItemStackUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgStatsMenu;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.managers.PlayerManager;
import me.pljr.reactions.objects.CorePlayer;
import me.pljr.reactions.objects.ReactionStat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class StatsMenu implements Listener {

    public static void open(Player player, Player request){
        UUID requestId = request.getUniqueId();
        Inventory inventory = Bukkit.createInventory(player, 6*9, CfgStatsMenu.title);

        CorePlayer corePlayer = Reactions.getPlayerManager().getCorePlayer(requestId);
        HashMap<ReactionType, Integer> stats = corePlayer.getStats();
        HashMap<ReactionType, ReactionStat> top = Reactions.getReactionManager().getLeaderboard();

        inventory.setItem(0, CfgStatsMenu.background1);
        inventory.setItem(9, CfgStatsMenu.background1);
        inventory.setItem(10, CfgStatsMenu.background1);
        inventory.setItem(19, CfgStatsMenu.background1);
        inventory.setItem(20, CfgStatsMenu.background1);
        inventory.setItem(36, CfgStatsMenu.background1);
        inventory.setItem(37, CfgStatsMenu.background1);
        inventory.setItem(46, CfgStatsMenu.background1);
        inventory.setItem(42, CfgStatsMenu.background1);
        inventory.setItem(43, CfgStatsMenu.background1);
        inventory.setItem(44, CfgStatsMenu.background1);
        inventory.setItem(2, CfgStatsMenu.background1);
        inventory.setItem(3, CfgStatsMenu.background1);
        inventory.setItem(12, CfgStatsMenu.background1);
        inventory.setItem(5, CfgStatsMenu.background1);
        inventory.setItem(6, CfgStatsMenu.background1);
        inventory.setItem(14, CfgStatsMenu.background1);
        inventory.setItem(48, CfgStatsMenu.background1);
        inventory.setItem(39, CfgStatsMenu.background1);
        inventory.setItem(30, CfgStatsMenu.background1);
        inventory.setItem(32, CfgStatsMenu.background1);
        inventory.setItem(41, CfgStatsMenu.background1);
        inventory.setItem(50, CfgStatsMenu.background1);
        inventory.setItem(8, CfgStatsMenu.background1);
        inventory.setItem(17, CfgStatsMenu.background1);
        inventory.setItem(16, CfgStatsMenu.background1);
        inventory.setItem(25, CfgStatsMenu.background1);
        inventory.setItem(52, CfgStatsMenu.background1);
        inventory.setItem(24, CfgStatsMenu.background1);

        inventory.setItem(1, CfgStatsMenu.background2);
        inventory.setItem(7, CfgStatsMenu.background2);
        inventory.setItem(45, CfgStatsMenu.background2);
        inventory.setItem(53, CfgStatsMenu.background2);
        inventory.setItem(47, CfgStatsMenu.background2);
        inventory.setItem(38, CfgStatsMenu.background2);
        inventory.setItem(26, CfgStatsMenu.background2);
        inventory.setItem(18, CfgStatsMenu.background2);
        inventory.setItem(29, CfgStatsMenu.background2);
        inventory.setItem(28, CfgStatsMenu.background2);
        inventory.setItem(27, CfgStatsMenu.background2);
        inventory.setItem(51, CfgStatsMenu.background2);
        inventory.setItem(42, CfgStatsMenu.background2);
        inventory.setItem(33, CfgStatsMenu.background2);
        inventory.setItem(34, CfgStatsMenu.background2);
        inventory.setItem(35, CfgStatsMenu.background2);

        inventory.setItem(4, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.wordHide,
                "%amount", stats.get(ReactionType.WORD_HIDE)+""),
                "%topName", top.get(ReactionType.WORD_HIDE).getName()),
                "%topAmount", top.get(ReactionType.WORD_HIDE).getAmount()+"")
        );
        inventory.setItem(13, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.mathMultiplication,
                "%amount", stats.get(ReactionType.MATH_MULTIPLICATION)+""),
                "%topName", top.get(ReactionType.MATH_MULTIPLICATION).getName()),
                "%topAmount", top.get(ReactionType.MATH_MULTIPLICATION).getAmount()+"")
        );
        inventory.setItem(22, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.mathSummation,
                "%amount", stats.get(ReactionType.MATH_SUMMATION)+""),
                "%topName", top.get(ReactionType.MATH_SUMMATION).getName()),
                "%topAmount", top.get(ReactionType.MATH_SUMMATION).getAmount()+"")
        );
        inventory.setItem(31, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.mathSubstraction,
                "%amount", stats.get(ReactionType.MATH_SUBSTRACTION)+""),
                "%topName", top.get(ReactionType.MATH_SUBSTRACTION).getName()),
                "%topAmount", top.get(ReactionType.MATH_SUBSTRACTION).getAmount()+"")
        );
        inventory.setItem(40, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.fishCatch,
                "%amount", stats.get(ReactionType.FISH_CATCH)+""),
                "%topName", top.get(ReactionType.FISH_CATCH).getName()),
                "%topAmount", top.get(ReactionType.FISH_CATCH).getAmount()+"")
        );
        inventory.setItem(49, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.blockPlace,
                "%amount", stats.get(ReactionType.BLOCK_PLACE)+""),
                "%topName", top.get(ReactionType.BLOCK_PLACE).getName()),
                "%topAmount", top.get(ReactionType.BLOCK_PLACE).getAmount()+"")
        );
        inventory.setItem(11, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.wordShuffle,
                "%amount", stats.get(ReactionType.WORD_SHUFFLE)+""),
                "%topName", top.get(ReactionType.WORD_SHUFFLE).getName()),
                "%topAmount", top.get(ReactionType.WORD_SHUFFLE).getAmount()+"")
        );
        inventory.setItem(15, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.wordCopy,
                "%amount", stats.get(ReactionType.WORD_COPY)+""),
                "%topName", top.get(ReactionType.WORD_COPY).getName()),
                "%topAmount", top.get(ReactionType.WORD_COPY).getAmount()+"")
        );
        inventory.setItem(21, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.mobKill,
                "%amount", stats.get(ReactionType.MOB_KILL)+""),
                "%topName", top.get(ReactionType.MOB_KILL).getName()),
                "%topAmount", top.get(ReactionType.MOB_KILL).getAmount()+"")
        );
        inventory.setItem(23, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(CfgStatsMenu.blockBreak,
                "%amount", stats.get(ReactionType.BLOCK_BREAK)+""),
                "%topName", top.get(ReactionType.BLOCK_BREAK).getName()),
                "%topAmount", top.get(ReactionType.BLOCK_BREAK).getAmount()+"")
        );

        player.openInventory(inventory);
    }

    @EventHandler
    public static void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgStatsMenu.title)){
            event.setCancelled(true);
        }
    }
}
