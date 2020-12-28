package me.pljr.reactions.reactions;

import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.managers.TitleManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.FormatUtil;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.config.ReactionType;
import me.pljr.reactions.events.ReactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Reaction  implements Listener {
    private final ConfigManager config = Reactions.getConfigManager();

    private ReactionType type;
    private List<String> chatBroadcastStart;
    private List<String> chatBroadcastEnd;
    private List<String> chatBroadcastNoWinner;
    private PLJRTitle titleBroadcastStart;
    private PLJRTitle titleBroadcastEnd;
    private PLJRTitle titleBroadcastNoWinner;
    private PLJRActionBar actionBarBroadcastStart;
    private PLJRActionBar actionBarBroadcastEnd;
    private PLJRActionBar actionBarBroadcastNoWinner;
    private int win;
    private String answer;

    public Reaction(ReactionType type){
        this(type, "", "");
    }

    public Reaction(ReactionType type, String answer){
        this(type, answer, "");
    }

    public Reaction(ReactionType type, String answer, String shownAnswer){
        this.win = (config.getInt("reactions."+type+".win-amount"));
        String message = config.getString("reactions."+type+".message");

        List<String> chatBroadcastStart = new ArrayList<>();
        for (String line : CfgSettings.REACTIONS_CHAT_START){
            chatBroadcastStart.add(line.replace("%message", message));
        }
        this.chatBroadcastStart = chatBroadcastStart;
        List<String> chatBroadcastEnd = new ArrayList<>();
        for (String line : CfgSettings.REACTIONS_CHAT_END){
            chatBroadcastEnd.add(line.replace("%message", message));
        }
        this.chatBroadcastEnd = chatBroadcastEnd;
        List<String> chatBroadcastNoWinner = new ArrayList<>();
        for (String line : CfgSettings.REACTIONS_CHAT_NO_WINNER){
            chatBroadcastNoWinner.add(line.replace("%message", message));
        }
        this.chatBroadcastNoWinner = chatBroadcastNoWinner;

        this.titleBroadcastStart = new TitleBuilder(CfgSettings.REACTIONS_TITLE_START)
                .replaceTitle("%message", message)
                .replaceSubtitle("%message", message)
                .create();
        this.titleBroadcastEnd = new TitleBuilder(CfgSettings.REACTIONS_TITLE_END)
                .replaceTitle("%message", message)
                .replaceSubtitle("%message", message)
                .create();
        this.titleBroadcastNoWinner = new TitleBuilder(CfgSettings.REACTIONS_TITLE_NO_WINNER)
                .replaceTitle("%message", message)
                .replaceSubtitle("%message", message)
                .create();
        this.actionBarBroadcastStart = new ActionBarBuilder(CfgSettings.REACTIONS_ACTIONBAR_START)
                .replaceMessage("%message", message)
                .create();
        this.actionBarBroadcastEnd = new ActionBarBuilder(CfgSettings.REACTIONS_ACTIONBAR_END)
                .replaceMessage("%message", message)
                .create();
        this.actionBarBroadcastNoWinner = new ActionBarBuilder(CfgSettings.REACTIONS_ACTIONBAR_NO_WINNER)
                .replaceMessage("%message", message)
                .create();
        this.answer = answer;
        this.type = type;

        Bukkit.getServer().getPluginManager().registerEvents(this, Reactions.getInstance());

        if (shownAnswer != null && shownAnswer != ""){
            List<String> replacedChatBroadcastStart = new ArrayList<>();
            for (String line : chatBroadcastStart){
                replacedChatBroadcastStart.add(line.replace("{word}", shownAnswer));
            }
            titleBroadcastStart = new TitleBuilder(titleBroadcastStart)
                    .replaceTitle("{word}", shownAnswer)
                    .replaceSubtitle("{word}", shownAnswer)
                    .create();
            actionBarBroadcastStart = new ActionBarBuilder(actionBarBroadcastStart)
                    .replaceMessage("{word}", shownAnswer)
                    .create();
        }

        if (CfgSettings.REACTIONS_CHAT){
            ChatUtil.broadcast(getChatBroadcastStart(), "", CfgSettings.BUNGEE);
        }
        if (CfgSettings.REACTIONS_TITLE){
            TitleManager.broadcast(getTitleBroadcastStart());
        }
        if (CfgSettings.REACTIONS_ACTIONBAR){
            ActionBarManager.broadcast(getActionBarBroadcastStart());
        }
    }

    public ReactionType getType() {
        return type;
    }

    public List<String> getChatBroadcastStart() {
        return chatBroadcastStart;
    }

    public List<String> getChatBroadcastEnd() {
        return chatBroadcastEnd;
    }

    public List<String> getChatBroadcastNoWinner() {
        return chatBroadcastNoWinner;
    }

    public PLJRTitle getTitleBroadcastStart() {
        return titleBroadcastStart;
    }

    public PLJRTitle getTitleBroadcastEnd() {
        return titleBroadcastEnd;
    }

    public PLJRTitle getTitleBroadcastNoWinner() {
        return titleBroadcastNoWinner;
    }

    public PLJRActionBar getActionBarBroadcastStart() {
        return actionBarBroadcastStart;
    }

    public PLJRActionBar getActionBarBroadcastEnd() {
        return actionBarBroadcastEnd;
    }

    public PLJRActionBar getActionBarBroadcastNoWinner() {
        return actionBarBroadcastNoWinner;
    }

    public int getWin() {
        return win;
    }

    public String getAnswer() {
        return answer;
    }

    public void finish(Player player){
        new ReactionEvent(player, getType());
        HandlerList.unregisterAll(this);
    }
}
