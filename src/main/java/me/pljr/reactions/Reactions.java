package me.pljr.reactions;

import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.database.DataSource;
import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.reactions.commands.AReactionsCommand;
import me.pljr.reactions.commands.ReactionsCommand;
import me.pljr.reactions.config.CfgReactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.CfgWords;
import me.pljr.reactions.managers.QueryManager;
import me.pljr.reactions.managers.ReactionManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Reactions extends JavaPlugin {
    private static Reactions instance;
    private static ConfigManager configManager;
    private static ReactionManager reactionManager;
    private static QueryManager queryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        if (!setupPLJRApi()) return;
        setupConfig();
        setupReactionManager();
        setupListeners();
        setupDatabase();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cReactions: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aReactions: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cReactions:", "config.yml");
        CfgReactions.load();
        CfgSettings.load();
        CfgWords.load();
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(reactionManager, this);
    }

    private void setupReactionManager(){
        reactionManager = new ReactionManager();
        if (CfgSettings.startOnStartup){
            reactionManager.start(null);
        }
    }

    private void setupDatabase(){
        DataSource dataSource = PLJRApi.getDataSource();
        queryManager = new QueryManager(dataSource);
        queryManager.setupTables();
    }

    private void setupCommand(){
        getCommand("reactions").setExecutor(new ReactionsCommand());
        getCommand("areactions").setExecutor(new AReactionsCommand());
    }

    public static Reactions getInstance() {
        return instance;
    }
    public static ConfigManager getConfigManager() {
        return configManager;
    }
    public static QueryManager getQueryManager() {
        return queryManager;
    }
    public static ReactionManager getReactionManager() {
        return reactionManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
