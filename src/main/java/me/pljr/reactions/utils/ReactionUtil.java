package me.pljr.reactions.utils;

import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.reactions.config.CfgSettings;
import me.pljr.reactions.reactions.Reaction;

import java.util.ArrayList;
import java.util.List;

public abstract class ReactionUtil {
    private Reaction reaction;

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public void broadcastEnd(String name, String answer, int prize){
        if (name == null){
            if (reaction.isChatBroadcast()){
                List<String> broadcast = new ArrayList<>();
                for (String line : reaction.getChatBroadcastNoWinner()){
                    broadcast.add(line.replace("%answer", answer).replace("%prize", prize+""));
                }
                ChatUtil.broadcast(broadcast, "", CfgSettings.bungee);
            }
            if (reaction.isTitleBroadcast()){
                PLJRTitle title = reaction.getTitleBroadcastNoWinner();
                TitleManager.broadcast(new PLJRTitle(
                        title.getTitle().replace("%answer", answer).replace("%prize", prize+""),
                        title.getSubtitle().replace("%answer", answer).replace("%prize", prize+""),
                        title.getIn(), title.getStay(), title.getOut()
                ));
            }
            if (reaction.isActionBarBroadcast()){
                PLJRActionBar actionBar = reaction.getActionBarBroadcastNoWinner();
                ActionBarManager.broadcast(new PLJRActionBar(
                        actionBar.getMessage().replace("%answer", answer).replace("%prize", prize+""),
                        actionBar.getDuration()
                ));
            }
        }else{
            if (reaction.isChatBroadcast()){
                List<String> broadcast = new ArrayList<>();
                for (String line : reaction.getChatBroadcastEnd()){
                    broadcast.add(line.replace("%name", name).replace("%answer", answer).replace("%prize", prize+""));
                }
                ChatUtil.broadcast(broadcast, "", CfgSettings.bungee);
            }
            if (reaction.isTitleBroadcast()){
                PLJRTitle title = reaction.getTitleBroadcastEnd();
                TitleManager.broadcast(new PLJRTitle(
                        title.getTitle().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        title.getSubtitle().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        title.getIn(), title.getStay(), title.getOut()
                ));
            }
            if (reaction.isActionBarBroadcast()){
                PLJRActionBar actionBar = reaction.getActionBarBroadcastEnd();
                ActionBarManager.broadcast(new PLJRActionBar(
                        actionBar.getMessage().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        actionBar.getDuration()
                ));
            }
        }
    }
}
