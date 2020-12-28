package me.pljr.reactions.menus;

import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.objects.CorePlayer;
import me.pljr.reactions.objects.ReactionStat;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StatsMenu {

    public static GUI get(Player player){
        UUID requestId = player.getUniqueId();
        GUIBuilder inventory = new GUIBuilder(ReactionType.MENU_TITLE, 6);

        CorePlayer corePlayer = Reactions.getPlayerManager().getCorePlayer(requestId);
        HashMap<ReactionType, Integer> stats = corePlayer.getStats();
        HashMap<ReactionType, ReactionStat> top = Reactions.getReactionManager().getLeaderboard();

        inventory.setItem(0, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(9, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(10, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(19, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(20, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(36, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(37, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(46, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(42, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(43, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(44, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(2, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(3, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(12, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(5, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(6, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(14, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(48, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(39, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(30, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(32, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(41, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(50, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(8, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(17, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(16, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(25, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(52, ReactionType.MENU_BACKGROUND_1);
        inventory.setItem(24, ReactionType.MENU_BACKGROUND_1);

        inventory.setItem(1, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(7, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(45, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(53, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(47, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(38, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(26, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(18, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(29, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(28, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(27, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(51, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(42, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(33, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(34, ReactionType.MENU_BACKGROUND_2);
        inventory.setItem(35, ReactionType.MENU_BACKGROUND_2);

        for (ReactionType type : ReactionType.values()){
            inventory.setItem(type.getMenuSlot(),
                    new ItemBuilder(type.getItem())
                            .replaceLore("{amount}", stats.get(type)+"")
                            .replaceLore("{topName}", top.get(type).getName())
                            .replaceLore("{topAmount}", top.get(type).getAmount()+"")
                            .create());
        }
        return inventory.create();
    }
}
