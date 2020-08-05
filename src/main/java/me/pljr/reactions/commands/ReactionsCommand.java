package me.pljr.reactions.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import me.pljr.reactions.menus.StatsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReactionsCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "reactions.use")) return false;

        // /reactions help
        if (!(args.length > 0) || args[0].equalsIgnoreCase("help")){
            sendHelp(player, CfgLang.help);
            return true;
        }

        // /reactions stats <player>
        if (args[0].equalsIgnoreCase("stats")){
            if (!checkPerm(player, "reactions.stats")) return false;
            if (args.length > 1){
                if (!checkPerm(player, "reactions.stats.others")) return false;
                StatsMenu.open(player, args[1]);
                return true;
            }
            StatsMenu.open(player, player.getName());
            return true;
        }

        sendHelp(player, CfgLang.help);
        return false;
    }
}
