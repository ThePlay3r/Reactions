package me.pljr.reactions.commands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.Lang;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AReactionsCommand extends BukkitCommand {

    private final Reactions plugin;

    public AReactionsCommand(Reactions plugin) {
        super("areactions", "areactions.use");
        this.plugin = plugin;
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
                plugin.setupConfig();
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
                plugin.setupConfig();
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
