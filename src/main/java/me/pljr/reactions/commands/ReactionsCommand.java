package me.pljr.reactions.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReactionsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!CommandUtil.checkPerm(player, "reactions.use")) return false;

        // /reactions help
        if (!(args.length > 0) || args[0].equalsIgnoreCase("help")){
            CommandUtil.sendHelp(player, CfgLang.help);
            return true;
        }

        // /reactions top
        if (args[0].equalsIgnoreCase("top")){
            if (!CommandUtil.checkPerm(player, "reactions.top")) return false;
            //TODO: Open leaderboard menu.
        }

        // /reactions stats <player>
        if (args[0].equalsIgnoreCase("stats")){
            if (!CommandUtil.checkPerm(player, "reactions.stats")) return false;
            if (args.length > 1){
                if (!CommandUtil.checkPerm(player, "reactions.stats.others")) return false;
                //TODO: Open the stats GUI of requested player.
                return true;
            }
            //TODO: Open the stats GUI of player.
            return true;
        }

        CommandUtil.sendHelp(player, CfgLang.help);
        return false;
    }
}
