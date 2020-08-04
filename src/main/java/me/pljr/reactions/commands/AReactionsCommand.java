package me.pljr.reactions.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AReactionsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!CommandUtil.checkPerm(sender, "areactions.use")) return false;

        // /areactions help
        if (!(args.length > 0) || args[0].equalsIgnoreCase("help")){
            CommandUtil.sendHelp(sender, CfgLang.adminHelp);
            return true;
        }

        // /areactions restart
        if (args[0].equalsIgnoreCase("restart")){
            if (!CommandUtil.checkPerm(sender, "areactions.restart")) return false;
            Reactions.getReactionManager().restart(false);
            sender.sendMessage(CfgLang.lang.get(Lang.RESTART_SUCCESS));
            return true;
        }

        // /areactions end
        if (args[0].equalsIgnoreCase("end")){
            if (!CommandUtil.checkPerm(sender, "areactions.end")) return false;
            Reactions.getReactionManager().end();
            sender.sendMessage(CfgLang.lang.get(Lang.END_SUCCESS));
            return true;
        }

        // /areactions start
        if (args[0].equalsIgnoreCase("start")){
            if (!CommandUtil.checkPerm(sender, "areactions.start")) return false;
            if (Reactions.getReactionManager().isRunning()){
                sender.sendMessage(CfgLang.lang.get(Lang.START_FAILURE));
                return false;
            }
            Reactions.getReactionManager().start(null);
            sender.sendMessage(CfgLang.lang.get(Lang.START_SUCCESS));
            return true;
        }

        CommandUtil.sendHelp(sender, CfgLang.adminHelp);
        return false;
    }
}
