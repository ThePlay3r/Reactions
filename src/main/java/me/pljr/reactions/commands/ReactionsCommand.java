package me.pljr.reactions.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.reactions.config.Lang;
import me.pljr.reactions.menus.StatsMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReactionsCommand extends CommandUtil {

    public ReactionsCommand(){
        super("reactions", "reactions.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /reactions
            StatsMenu.get(player).open(player);
            return;
        }

        else if (args.length == 1){
            // /reactions help
            if (args[0].equalsIgnoreCase("help") && checkPerm(player, "reactions.help")){
                sendMessage(player, Lang.HELP.get());
                return;
            }

            // /reactions <player>
            if (checkPerm(player, "reactions.use.others")){
                if (checkPlayer(player, args[0])){
                    StatsMenu.get(Bukkit.getPlayer(args[0])).open(player);
                }
                return;
            }
        }

        if (checkPerm(player, "reactions.help")) sendMessage(player, Lang.HELP.get());
    }
}
