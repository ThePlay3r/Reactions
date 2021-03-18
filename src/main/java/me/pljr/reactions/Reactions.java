package me.pljr.reactions;

import lombok.Getter;
import me.pljr.pljrapispigot.PLJRApiSpigot;
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

import java.util.logging.Logger;

public final class Reactions extends JavaPlugin {

    private static Reactions instance;
    public static Logger log;
    private PLJRApiSpigot pljrApiSpigot;

    private ConfigManager configManager;
    @Getter private Settings settings;

    @Getter private PlayerManager playerManager;
    private ReactionManager reactionManager;
    private QueryManager queryManager;

    public static Reactions get(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        log = getLogger();
        instance = this;
        if (!setupPLJRApi()) return;
        setupConfig();
        setupDatabase();
        setupManagers();
        setupListeners();
        setupCommand();
        loadPlayers();
        setupPapi();
    }

    public boolean setupPLJRApi(){
        if (PLJRApiSpigot.get() == null){
            getLogger().warning("PLJRApi-Spigot is not enabled!");
            return false;
        }
        pljrApiSpigot = PLJRApiSpigot.get();
        return true;
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        settings = new Settings(configManager);
        Lang.load(new ConfigManager(this, "lang.yml"));
        ReactionType.load(new ConfigManager(this, "reactions.yml"));
        MenuItemType.load(new ConfigManager(this, "menus.yml"));
        ActionBarType.load(new ConfigManager(this, "actionbars.yml"));
        TitleType.load(new ConfigManager(this, "titles.yml"));
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(reactionManager, this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(playerManager), this);
    }

    private void setupManagers(){
        playerManager = new PlayerManager(this, queryManager, settings.isCachePlayers());
        reactionManager = new ReactionManager(this, queryManager, settings);
        reactionManager.start();
    }

    private void setupDatabase(){
        DataSource dataSource = pljrApiSpigot.getDataSource(configManager);
        dataSource.initPool("Reactions-Pool");
        queryManager = new QueryManager(this, dataSource);
        queryManager.setupTables();
    }

    private void setupCommand(){
        new ReactionsCommand(playerManager, reactionManager).registerCommand(this);
        new AReactionsCommand(this).registerCommand(this);
    }

    private void loadPlayers(){
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.loadPlayer(player.getUniqueId());
        }
    }

    private void setupPapi(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PapiExpansion(this, playerManager, reactionManager).register();
        }
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()){
            playerManager.getPlayer(player.getUniqueId(), queryManager::savePlayer);
        }
    }
}
