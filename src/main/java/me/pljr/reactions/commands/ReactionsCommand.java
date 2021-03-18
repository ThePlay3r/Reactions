package me.pljr.reactions.commands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.reactions.config.Lang;
import me.pljr.reactions.managers.PlayerManager;
import me.pljr.reactions.managers.ReactionManager;
import me.pljr.reactions.menus.StatsMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReactionsCommand extends BukkitCommand {

    private final PlayerManager playerManager;
    private final ReactionManager reactionManager;

    public ReactionsCommand(PlayerManager playerManager, ReactionManager reactionManager){
        super("reactions", "reactions.use");
        this.playerManager = playerManager;
        this.reactionManager = reactionManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /reactions
            playerManager.getPlayer(player.getUniqueId(), reactionPlayer -> {
                new StatsMenu(reactionPlayer, reactionManager.getLeaderboard()).getGui().open(player);
            });
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
                if (!checkPlayer(player, args[0])) return;
                playerManager.getPlayer(player.getUniqueId(), reactionPlayer -> {
                    new StatsMenu(reactionPlayer, reactionManager.getLeaderboard()).getGui().open(player);
                });
                return;
            }
        }

        if (checkPerm(player, "reactions.help")) sendMessage(player, Lang.HELP.get());
    }
}
