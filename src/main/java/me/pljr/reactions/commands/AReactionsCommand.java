package me.pljr.reactions.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AReactionsCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "areactions.use")) return false;

        // /areactions help
        if (!(args.length > 0) || args[0].equalsIgnoreCase("help")){
            sendHelp(sender, CfgLang.adminHelp);
            return true;
        }

        // /areactions restart
        if (args[0].equalsIgnoreCase("restart")){
            if (!checkPerm(sender, "areactions.restart")) return false;
            Reactions.getReactionManager().restart(false);
            sendMessage(sender, CfgLang.lang.get(Lang.RESTART_SUCCESS));
            return true;
        }

        // /areactions end
        if (args[0].equalsIgnoreCase("end")){
            if (!checkPerm(sender, "areactions.end")) return false;
            Reactions.getReactionManager().end();
            sendMessage(sender, CfgLang.lang.get(Lang.END_SUCCESS));
            return true;
        }

        // /areactions start
        if (args[0].equalsIgnoreCase("start")){
            if (!checkPerm(sender, "areactions.start")) return false;
            if (Reactions.getReactionManager().isRunning()){
                sendMessage(sender, CfgLang.lang.get(Lang.START_FAILURE));
                return false;
            }
            Reactions.getReactionManager().start(null);
            sendMessage(sender, CfgLang.lang.get(Lang.START_SUCCESS));
            return true;
        }

        sendHelp(sender, CfgLang.adminHelp);
        return false;
    }
}
