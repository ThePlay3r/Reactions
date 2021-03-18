package me.pljr.reactions.menus;

import lombok.Getter;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Lang;
import me.pljr.reactions.config.MenuItemType;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.objects.ReactionPlayer;
import me.pljr.reactions.objects.ReactionStat;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class StatsMenu {

    private final GUI gui;

    public StatsMenu(ReactionPlayer reactionPlayer, HashMap<ReactionType, ReactionStat> top){
        GUIBuilder inventory = new GUIBuilder(Lang.MENU_TITLE.get(), 6);

        HashMap<ReactionType, Integer> stats = reactionPlayer.getStats();

        inventory.setItem(0, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(9, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(10, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(19, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(20, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(36, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(37, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(46, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(42, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(43, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(44, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(2, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(3, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(12, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(5, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(6, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(14, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(48, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(39, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(30, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(32, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(41, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(50, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(8, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(17, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(16, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(25, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(52, MenuItemType.BACKGROUND_1.get());
        inventory.setItem(24, MenuItemType.BACKGROUND_1.get());

        inventory.setItem(1, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(7, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(45, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(53, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(47, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(38, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(26, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(18, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(29, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(28, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(27, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(51, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(42, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(33, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(34, MenuItemType.BACKGROUND_2.get());
        inventory.setItem(35, MenuItemType.BACKGROUND_2.get());

        for (ReactionType type : ReactionType.values()){
            inventory.setItem(type.getMenuSlot(),
                    new ItemBuilder(type.getMenuItem())
                            .replaceLore("{amount}", stats.get(type)+"")
                            .replaceLore("{topName}", top.get(type).getName())
                            .replaceLore("{topAmount}", top.get(type).getAmount()+"")
                            .create());
        }
        gui = inventory.create();
    }
}
