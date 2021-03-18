package me.pljr.reactions.managers;

import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.*;
import me.pljr.reactions.objects.ReactionStat;
import me.pljr.reactions.reactions.Reaction;
import me.pljr.reactions.reactions.types.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Random;

public class ReactionManager implements Listener {
    private final Random random;
    private final JavaPlugin plugin;
    private final QueryManager queryManager;
    private final Settings settings;
    private Reaction running;
    private HashMap<ReactionType, ReactionStat> leaderboard;

    public ReactionManager(JavaPlugin plugin, QueryManager queryManager, Settings settings){
        this.random = new Random();
        this.plugin = plugin;
        queryManager.getLeaderboard(leaderboard -> this.leaderboard = leaderboard);
        this.queryManager = queryManager;
        this.settings = settings;
    }

    public void start(){
        switch (ReactionType.getRandom()){
            case BLOCK_BREAK:
                this.running = new BlockBreakReaction();
                break;
            case BLOCK_PLACE:
                this.running = new BlockPlaceReaction();
                break;
            case FISH_CATCH:
                this.running = new FishCatchReaction();
                break;
            case MATH_MULTIPLICATION:
                this.running = new MathMultiplicationReaction(random.nextInt(100), random.nextInt(100));
                break;
            case MATH_SUBTRACTION:
                this.running = new MathSubstractionReaction(random.nextInt(100), random.nextInt(100));
                break;
            case MATH_SUMMATION:
                this.running = new MathSummationReaction(random.nextInt(100), random.nextInt(100));
                break;
            case MOB_KILL:
                this.running = new MobKillReaction();
                break;
            case WORD_COPY:
                this.running = new WordCopyReaction(settings.getRandomWord());
                break;
            case WORD_HIDE:
                this.running = new WordHideReaction(settings.getRandomWord());
                break;
            case WORD_SHUFFLE:
                this.running = new WordShuffleReaction(settings.getRandomWord());
                break;
            case BUCKET_EMPTY:
                this.running = new BucketEmptyReaction();
                break;
            case ARMOR_CHANGE:
                this.running = new ArmorChangeReaction();
                break;
            case PICKUP_ITEM:
                this.running = new PickupItemReaction();
                break;
            case BUCKET_FILL:
                this.running = new BucketFillReaction();
                break;
            case DROP_ITEM:
                this.running = new DropItemReaction();
                break;
            case BED_ENTER:
                this.running = new BedEnterReaction();
                break;
            case SNEAK:
                this.running = new SneakReaction();
                break;
            case QUIT:
                this.running = new QuitReaction();
                break;
            case JUMP:
                this.running = new JumpReaction();
                break;
            case EAT:
                this.running = new EatReaction();
                break;
        }
        Bukkit.getScheduler().runTaskLater(plugin, ()->{
            running.finish(null);
            Bukkit.getScheduler().runTaskLater(plugin, this::start, settings.getCooldown() * 20L);
            queryManager.getLeaderboard(leaderboard -> this.leaderboard = leaderboard);
        }, settings.getTime() * 20L);
    }

    public HashMap<ReactionType, ReactionStat> getLeaderboard() {
        return leaderboard;
    }
}
