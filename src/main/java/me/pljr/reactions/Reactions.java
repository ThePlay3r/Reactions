package me.pljr.reactions;

import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.reactions.commands.AReactionsCommand;
import me.pljr.reactions.commands.ReactionsCommand;
import me.pljr.reactions.config.*;
import me.pljr.reactions.listeners.AsyncPlayerPreLoginListener;
import me.pljr.reactions.listeners.PlayerQuitListener;
import me.pljr.reactions.managers.PlayerManager;
import me.pljr.reactions.managers.QueryManager;
import me.pljr.reactions.managers.ReactionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Reactions extends JavaPlugin {
    private static Reactions instance;
    private static PlayerManager playerManager;
    private static ConfigManager configManager;
    private static ReactionManager reactionManager;
    private static QueryManager queryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupDatabase();
        setupManagers();
        setupListeners();
        setupCommand();
        loadPlayers();
        setupPapi();
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        CfgSettings.load(configManager);
        Lang.load(new ConfigManager(this, "lang.yml"));
        ReactionType.load(new ConfigManager(this, "reactions.yml"));
        MenuItemType.load(new ConfigManager(this, "menus.yml"));
        ActionBarType.load(new ConfigManager(this, "actionbars.yml"));
        TitleType.load(new ConfigManager(this, "titles.yml"));
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(reactionManager, this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
        reactionManager = new ReactionManager();
        reactionManager.start();
    }

    private void setupDatabase(){
        DataSource dataSource = DataSource.getFromConfig(configManager);
        queryManager = new QueryManager(dataSource);
        queryManager.setupTables();
    }

    private void setupCommand(){
        new ReactionsCommand().registerCommand(this);
        new AReactionsCommand().registerCommand(this);
    }

    private void loadPlayers(){
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.loadPlayer(player.getUniqueId());
        }
    }

    private void setupPapi(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PapiExpansion(this).register();
        }
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
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void savePlayers(){
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.savePlayerSync(player.getUniqueId());
        }
    }
}
