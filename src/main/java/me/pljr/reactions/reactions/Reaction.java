package me.pljr.reactions.reactions;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.reactions.Reactions;
import me.pljr.reactions.enums.ReactionType;

import java.util.List;

public abstract class Reaction {
    private final ConfigManager config = Reactions.getConfigManager();

    private ReactionType type;
    private boolean chatBroadcast;
    private List<String> chatBroadcastStart;
    private List<String> chatBroadcastEnd;
    private List<String> chatBroadcastNoWinner;
    private boolean titleBroadcast;
    private PLJRTitle titleBroadcastStart;
    private PLJRTitle titleBroadcastEnd;
    private PLJRTitle titleBroadcastNoWinner;
    private boolean actionBarBroadcast;
    private PLJRActionBar actionBarBroadcastStart;
    private PLJRActionBar actionBarBroadcastEnd;
    private PLJRActionBar actionBarBroadcastNoWinner;
    private int win;
    private String answer;

    public void setup(){
        this.win = (config.getInt("reactions."+type+".win-amount"));
        this.chatBroadcast = (config.getBoolean("reactions."+type+".broadcast.chat.enabled"));
        this.chatBroadcastStart = (config.getStringList("reactions."+type+".broadcast.chat.start"));
        this.chatBroadcastEnd = (config.getStringList("reactions."+type+".broadcast.chat.end"));
        this.chatBroadcastNoWinner = (config.getStringList("reactions."+type+".broadcast.chat.no-winner"));
        this.titleBroadcast = (config.getBoolean("reactions."+type+".broadcast.title.enabled"));
        this.titleBroadcastStart = (config.getPLJRTitle("reactions."+type+".broadcast.title.start"));
        this.titleBroadcastEnd = (config.getPLJRTitle("reactions."+type+".broadcast.title.end"));
        this.titleBroadcastNoWinner = (config.getPLJRTitle("reactions."+type+".broadcast.title.no-winner"));
        this.actionBarBroadcast = (config.getBoolean("reactions."+type+".broadcast.actionbar.enabled"));
        this.actionBarBroadcastStart = (config.getPLJRActionBar("reactions."+type+".broadcast.actionbar.start"));
        this.actionBarBroadcastEnd = (config.getPLJRActionBar("reactions."+type+".broadcast.actionbar.end"));
        this.actionBarBroadcastNoWinner = (config.getPLJRActionBar("reactions."+type+".broadcast.actionbar.no-winner"));
    }

    public ReactionType getType() {
        return type;
    }
    public void setType(ReactionType type) {
        this.type = type;
    }


    public boolean isChatBroadcast() {
        return chatBroadcast;
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

    public boolean isTitleBroadcast() {
        return titleBroadcast;
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

    public boolean isActionBarBroadcast() {
        return actionBarBroadcast;
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
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
