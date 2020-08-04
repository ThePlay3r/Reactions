package me.pljr.reactions.managers;

import me.pljr.pljrapi.database.DataSource;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgLang;
import me.pljr.reactions.enums.Lang;
import me.pljr.reactions.enums.ReactionType;
import me.pljr.reactions.objects.CorePlayer;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryManager {
    private final Reactions instance = Reactions.getInstance();
    private final DataSource dataSource;

    public QueryManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void loadPlayerSync(String playerName){
        try {
            int wordShuffle = 0;
            int wordCopy = 0;
            int wordHide = 0;
            int blockPlace = 0;
            int blockBreak = 0;
            int mobKill = 0;
            int fishCatch = 0;
            int mathSummation = 0;
            int mathSubstraction = 0;
            int mathMultiplication = 0;

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reactions_players WHERE username=?"
            );
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                wordShuffle = resultSet.getInt("wordShuffle");
                wordCopy = resultSet.getInt("wordCopy");
                wordHide = resultSet.getInt("wordHide");
                blockPlace = resultSet.getInt("blockPlace");
                blockBreak = resultSet.getInt("blockBreak");
                mobKill = resultSet.getInt("mobKill");
                fishCatch = resultSet.getInt("fishCatch");
                mathSummation = resultSet.getInt("mathSummation");
                mathSubstraction = resultSet.getInt("mathSubstraction");
                mathMultiplication = resultSet.getInt("mathMultiplication");
            }
            dataSource.close(connection, preparedStatement, resultSet);
            PlayerManager.setCorePlayer(playerName, new CorePlayer(wordShuffle, wordCopy, wordHide, blockPlace, blockBreak, mobKill, fishCatch, mathSummation, mathSubstraction, mathMultiplication));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(String playerName){
        CorePlayer corePlayer = PlayerManager.getCorePlayer(playerName);
        Bukkit.getScheduler().runTaskAsynchronously(instance, ()->{
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "REPLACE INTO reactions_players VALUES (?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatement.setString(1, playerName);
                preparedStatement.setInt(2, corePlayer.getWordShuffle());
                preparedStatement.setInt(3, corePlayer.getWordCopy());
                preparedStatement.setInt(4, corePlayer.getWordHide());
                preparedStatement.setInt(5, corePlayer.getBlockPlace());
                preparedStatement.setInt(6, corePlayer.getBlockBreak());
                preparedStatement.setInt(7, corePlayer.getMobKill());
                preparedStatement.setInt(8, corePlayer.getFishCatch());
                preparedStatement.setInt(9, corePlayer.getMathSummation());
                preparedStatement.setInt(10, corePlayer.getMathSubstraction());
                preparedStatement.setInt(11, corePlayer.getMathMultiplication());
                preparedStatement.executeUpdate();
                dataSource.close(connection, preparedStatement, null);
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public HashMap<ReactionType, String> getLeaderboard(){
        HashMap<ReactionType, String> map = new HashMap<>();
        Bukkit.getScheduler().runTaskAsynchronously(instance, ()->{
           try {
               Connection connection = dataSource.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(
                       "SELECT * FROM reactions_players"
               );
               ResultSet results = preparedStatement.executeQuery();
               HashMap<String, Integer> wordShuffle = new HashMap<>();
               HashMap<String, Integer> wordCopy = new HashMap<>();
               HashMap<String, Integer> wordHide = new HashMap<>();
               HashMap<String, Integer> blockPlace = new HashMap<>();
               HashMap<String, Integer> blockBreak = new HashMap<>();
               HashMap<String, Integer> mobKill = new HashMap<>();
               HashMap<String, Integer> fishCatch = new HashMap<>();
               HashMap<String, Integer> mathSummation = new HashMap<>();
               HashMap<String, Integer> mathSubstraction = new HashMap<>();
               HashMap<String, Integer> mathMultiplication = new HashMap<>();
               while (results.next()){
                   String playerName = results.getString("username");
                   wordShuffle.put(playerName, results.getInt("wordShuffle"));
                   wordCopy.put(playerName, results.getInt("wordCopy"));
                   wordHide.put(playerName, results.getInt("wordHide"));
                   blockPlace.put(playerName, results.getInt("blockPlace"));
                   blockBreak.put(playerName, results.getInt("blockBreak"));
                   mobKill.put(playerName, results.getInt("mobKill"));
                   fishCatch.put(playerName, results.getInt("fishCatch"));
                   mathSummation.put(playerName, results.getInt("mathSummation"));
                   mathSubstraction.put(playerName, results.getInt("mathSubstraction"));
                   mathMultiplication.put(playerName, results.getInt("mathMultiplication"));
               }
               String none = CfgLang.lang.get(Lang.NONE);
               Map.Entry<String, Integer> maxWordShuffle = null;
               Map.Entry<String, Integer> maxWordCopy = null;
               Map.Entry<String, Integer> maxWordHide = null;
               Map.Entry<String, Integer> maxBlockPlace = null;
               Map.Entry<String, Integer> maxBlockBreak = null;
               Map.Entry<String, Integer> maxMobKill = null;
               Map.Entry<String, Integer> maxFishCatch = null;
               Map.Entry<String, Integer> maxMathSummation = null;
               Map.Entry<String, Integer> maxMathSubstraction = null;
               Map.Entry<String, Integer> maxMathMultiplication = null;
               for (Map.Entry<String, Integer> entry : wordShuffle.entrySet()){
                   if (maxWordShuffle == null || entry.getValue().compareTo(maxWordShuffle.getValue()) > 0)
                   {
                       maxWordShuffle = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : wordCopy.entrySet()){
                   if (maxWordCopy == null || entry.getValue().compareTo(maxWordCopy.getValue()) > 0)
                   {
                       maxWordCopy = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : wordHide.entrySet()){
                   if (maxWordHide == null || entry.getValue().compareTo(maxWordHide.getValue()) > 0)
                   {
                       maxWordHide = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : blockPlace.entrySet()){
                   if (maxBlockPlace == null || entry.getValue().compareTo(maxBlockPlace.getValue()) > 0)
                   {
                       maxBlockPlace = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : blockBreak.entrySet()){
                   if (maxBlockBreak == null || entry.getValue().compareTo(maxBlockBreak.getValue()) > 0)
                   {
                       maxBlockBreak = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : mobKill.entrySet()){
                   if (maxMobKill == null || entry.getValue().compareTo(maxMobKill.getValue()) > 0)
                   {
                       maxMobKill = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : fishCatch.entrySet()){
                   if (maxFishCatch == null || entry.getValue().compareTo(maxFishCatch.getValue()) > 0)
                   {
                       maxFishCatch = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : mathSummation.entrySet()){
                   if (maxMathSummation == null || entry.getValue().compareTo(maxMathSummation.getValue()) > 0)
                   {
                       maxMathSummation = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : mathSubstraction.entrySet()){
                   if (maxMathSubstraction == null || entry.getValue().compareTo(maxMathSubstraction.getValue()) > 0)
                   {
                       maxMathSubstraction = entry;
                   }
               }
               for (Map.Entry<String, Integer> entry : mathMultiplication.entrySet()){
                   if (maxMathMultiplication == null || entry.getValue().compareTo(maxMathMultiplication.getValue()) > 0)
                   {
                       maxMathMultiplication = entry;
                   }
               }
               if (maxWordShuffle == null){
                   map.put(ReactionType.WORD_SHUFFLE, none);
               }else{
                   map.put(ReactionType.WORD_SHUFFLE, maxWordShuffle.getKey());
               }
               if (maxWordCopy == null){
                   map.put(ReactionType.WORD_COPY, none);
               }else{
                   map.put(ReactionType.WORD_COPY, maxWordCopy.getKey());
               }
               if (maxWordHide == null){
                   map.put(ReactionType.WORD_HIDE, none);
               }else{
                   map.put(ReactionType.WORD_HIDE, maxWordHide.getKey());
               }
               if (maxBlockPlace == null){
                   map.put(ReactionType.BLOCK_PLACE, none);
               }else{
                   map.put(ReactionType.BLOCK_PLACE, maxBlockPlace.getKey());
               }
               if (maxBlockBreak == null){
                   map.put(ReactionType.BLOCK_BREAK, none);
               }else{
                   map.put(ReactionType.BLOCK_BREAK, maxBlockBreak.getKey());
               }
               if (maxMobKill == null){
                   map.put(ReactionType.MOB_KILL, none);
               }else{
                   map.put(ReactionType.MOB_KILL, maxMobKill.getKey());
               }
               if (maxFishCatch == null){
                   map.put(ReactionType.FISH_CATCH, none);
               }else{
                   map.put(ReactionType.FISH_CATCH, maxFishCatch.getKey());
               }
               if (maxMathSummation == null){
                   map.put(ReactionType.MATH_SUMMATION, none);
               }else{
                   map.put(ReactionType.MATH_SUMMATION, maxMathSummation.getKey());
               }
               if (maxMathSubstraction == null){
                   map.put(ReactionType.MATH_SUBSTRACTION, none);
               }else{
                   map.put(ReactionType.MATH_SUBSTRACTION, maxMathSubstraction.getKey());
               }
               if (maxMathMultiplication == null){
                   map.put(ReactionType.MATH_MULTIPLICATION, none);
               }else{
                   map.put(ReactionType.MATH_MULTIPLICATION, maxMathMultiplication.getKey());
               }
           }catch (SQLException e){
               e.printStackTrace();
           }
        });
        return map;
    }

    public void setupTables() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS reactions_players (" +
                            "username varchar(16) NOT NULL PRIMARY KEY," +
                            "wordShuffle int NOT NULL," +
                            "wordCopy int NOT NULL," +
                            "wordHide int NOT NULL," +
                            "blockPlace int NOT NULL," +
                            "blockBreak int NOT NULL," +
                            "mobKill int NOT NULL," +
                            "fishCatch int NOT NULL," +
                            "mathSummation int NOT NULL," +
                            "mathSubstraction int NOT NULL," +
                            "mathMultiplication int NOT NULL);"
            );
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
