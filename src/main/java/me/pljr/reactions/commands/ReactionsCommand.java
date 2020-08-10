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
        String playerName = player.getName();
        if (!checkPerm(player, "reactions.use")) return false;

        // /reactions
        if (!(args.length > 0)){
            StatsMenu.open(player, playerName);
            return true;
        }

        // /reactions help
        if (args[0].equalsIgnoreCase("help") && checkPerm(player, "reactions.help")){
            sendHelp(player, CfgLang.help);
            return true;
        }

        // /reactions <player>
        if (checkPerm(player, "reactions.use.others")){
            StatsMenu.open(player, args[1]);
            return true;
        }

        if (checkPerm(player, "reactions.help")){
            sendHelp(player, CfgLang.help);
        }
        return false;
    }
}
