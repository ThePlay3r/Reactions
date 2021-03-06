package me.pljr.reactions.managers;

import lombok.AllArgsConstructor;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.objects.ReactionPlayer;
import me.pljr.reactions.objects.ReactionStat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class QueryManager {

    private final JavaPlugin plugin;
    private final DataSource dataSource;

    public ReactionPlayer loadPlayer(UUID uuid){
        ReactionPlayer reactionPlayer = new ReactionPlayer(uuid);
        try {
            HashMap<ReactionType, Integer> stats = new HashMap<>();

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reactions_players WHERE uuid=?"
            );
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                for (ReactionType type : ReactionType.values()){
                    stats.put(type, resultSet.getInt(type.toString()));
                }
            }else{
                for (ReactionType type : ReactionType.values()){
                    stats.put(type, 0);
                }
            }
            dataSource.close(connection, preparedStatement, resultSet);
            reactionPlayer = new ReactionPlayer(uuid, stats);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reactionPlayer;
    }

    public void savePlayer(ReactionPlayer reactionPlayer){
        try {
            HashMap<ReactionType, Integer> stats = reactionPlayer.getStats();

            Connection connection = dataSource.getConnection();
            StringBuilder statement = new StringBuilder("REPLACE INTO reactions_players VALUES (?");
            for (ReactionType ignored : ReactionType.values()){
                statement.append(",").append("?");
            }
            statement.append(");");
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());
            preparedStatement.setString(1, reactionPlayer.getUniqueId().toString());
            int i = 2;
            for (ReactionType type : ReactionType.values()){
                preparedStatement.setInt(i, stats.get(type));
                i++;
            }
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getLeaderboard(Consumer<HashMap<ReactionType, ReactionStat>> leaderboard){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()->{
            HashMap<ReactionType, ReactionStat> stats = new HashMap<>();
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM reactions_players"
                );
                ResultSet results = preparedStatement.executeQuery();
                while (results.next()){
                    UUID uuid = UUID.fromString(results.getString("uuid"));
                    String playerName = PlayerUtil.getName(Bukkit.getOfflinePlayer(uuid));
                    for (ReactionType type : ReactionType.values()){
                        int playerStat = results.getInt(type.toString());
                        if (stats.containsKey(type)){
                            if (playerStat > stats.get(type).getAmount()){
                                stats.put(type, new ReactionStat(playerName, results.getInt(type.toString())));
                            }
                        }else{
                            stats.put(type, new ReactionStat(playerName, results.getInt(type.toString())));
                        }
                    }
                }
                if (stats.size() == 0){
                    for (ReactionType type : ReactionType.values()){
                        stats.put(type, new ReactionStat("?", 0));
                    }
                }
                dataSource.close(connection, preparedStatement, results);
            }catch (SQLException e){
                e.printStackTrace();
            }
            leaderboard.accept(stats);
        });
    }

    public void setupTables() {
        try {
            Connection connection = dataSource.getConnection();
            StringBuilder statement = new StringBuilder("CREATE TABLE IF NOT EXISTS reactions_players (" +
                    "uuid char(36) NOT NULL PRIMARY KEY");
            for (ReactionType reactionType : ReactionType.values()){
                statement.append(",").append(reactionType.toString()).append(" int NOT NULL");
            }
            statement.append(");");
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
