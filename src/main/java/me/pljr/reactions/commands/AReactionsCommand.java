package me.pljr.reactions.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Lang;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AReactionsCommand extends CommandUtil {

    public AReactionsCommand() {
        super("areactions", "areactions.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length < 1){
            sendMessage(player, Lang.ADMIN_HELP.get());
            return;
        }

        switch (args[0].toUpperCase()){
            case "RELOAD":
            {
                if (!checkPerm(player, "areactions.reload")) return;
                Reactions.getInstance().setupConfig();
                sendMessage(player, Lang.RELOAD.get());
                return;
            }
            case "HELP":
            {
                sendMessage(player, Lang.ADMIN_HELP.get());
                return;
            }
        }
        sendMessage(player, Lang.ADMIN_HELP.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length < 1){
            sendMessage(sender, Lang.ADMIN_HELP.get());
            return;
        }

        switch (args[0].toUpperCase()){
            case "RELOAD":
            {
                Reactions.getInstance().setupConfig();
                sendMessage(sender, Lang.RELOAD.get());
                return;
            }
            case "HELP":
            {
                sendMessage(sender, Lang.ADMIN_HELP.get());
                return;
            }
        }
        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
