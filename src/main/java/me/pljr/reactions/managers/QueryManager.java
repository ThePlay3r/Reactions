package me.pljr.reactions.managers;

import me.pljr.pljrapi.database.DataSource;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.objects.CorePlayer;
import me.pljr.reactions.objects.ReactionStat;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueryManager {
    private final Reactions instance = Reactions.getInstance();
    private final DataSource dataSource;

    public QueryManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void loadPlayerSync(UUID uuid){
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
            PlayerManager.setCorePlayer(uuid, new CorePlayer(stats));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(UUID uuid){
        CorePlayer corePlayer = PlayerManager.getCorePlayer(uuid);
        Bukkit.getScheduler().runTaskAsynchronously(instance, ()->{
            try {
                HashMap<ReactionType, Integer> stats = corePlayer.getStats();

                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "REPLACE INTO reactions_players VALUES (?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatement.setString(1, uuid.toString());
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
        });
    }

    public HashMap<ReactionType, ReactionStat> getLeaderboard(){
        HashMap<ReactionType, ReactionStat> stats = new HashMap<>();
        Bukkit.getScheduler().runTaskAsynchronously(instance, ()->{
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
           }catch (SQLException e){
               e.printStackTrace();
           }
        });
        return stats;
    }

    public void setupTables() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS reactions_players (" +
                            "uuid char(36) NOT NULL PRIMARY KEY," +
                            "WORD_SHUFFLE int NOT NULL," +
                            "WORD_COPY int NOT NULL," +
                            "WORD_HIDE int NOT NULL," +
                            "BLOCK_PLACE int NOT NULL," +
                            "BLOCK_BREAK int NOT NULL," +
                            "MOB_KILL int NOT NULL," +
                            "FISH_CATCH int NOT NULL," +
                            "MATH_SUMMATION int NOT NULL," +
                            "MATH_SUBSTRACTION int NOT NULL," +
                            "MATH_MULTIPLICATION int NOT NULL);"
            );
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
