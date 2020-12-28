package me.pljr.reactions.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Lang;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AReactionsCommand extends CommandUtil {

    public AReactionsCommand(){
        super("areactions", "areactions.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /areactions help
            if (args[0].equalsIgnoreCase("help")){
                sendMessage(player, Lang.ADMIN_HELP);
                return;
            }

            // /areactions restart
            if (args[0].equalsIgnoreCase("restart")){
                if (!checkPerm(player, "areactions.restart")) return;
                Reactions.getReactionManager().restart(false);
                sendMessage(player, Lang.RESTART_SUCCESS.get());
                return;
            }

            // /areactions end
            if (args[0].equalsIgnoreCase("end")){
                if (!checkPerm(player, "areactions.end")) return;
                Reactions.getReactionManager().end();
                sendMessage(player, Lang.END_SUCCESS.get());
                return;
            }

            // /areactions start
            if (args[0].equalsIgnoreCase("start")){
                if (!checkPerm(player, "areactions.start")) return;
                if (Reactions.getReactionManager().isRunning()){
                    sendMessage(player, Lang.START_FAILURE.get());
                    return;
                }
                Reactions.getReactionManager().start(null);
                sendMessage(player, Lang.START_SUCCESS.get());
                return;
            }
        }
        sendMessage(player, Lang.ADMIN_HELP);
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /areactions help
            if (args[0].equalsIgnoreCase("help")){
                sendMessage(sender, Lang.ADMIN_HELP);
                return;
            }

            // /areactions restart
            if (args[0].equalsIgnoreCase("restart")){
                Reactions.getReactionManager().restart(false);
                sendMessage(sender, Lang.RESTART_SUCCESS.get());
                return;
            }

            // /areactions end
            if (args[0].equalsIgnoreCase("end")){
                Reactions.getReactionManager().end();
                sendMessage(sender, Lang.END_SUCCESS.get());
                return;
            }

            // /areactions start
            if (args[0].equalsIgnoreCase("start")){
                if (Reactions.getReactionManager().isRunning()){
                    sendMessage(sender, Lang.START_FAILURE.get());
                    return;
                }
                Reactions.getReactionManager().start(null);
                sendMessage(sender, Lang.START_SUCCESS.get());
                return;
            }
        }
        sendMessage(sender, Lang.ADMIN_HELP);
    }
}
