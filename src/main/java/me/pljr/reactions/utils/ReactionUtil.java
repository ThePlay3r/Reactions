package me.pljr.reactions.utils;

import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.managers.TitleManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import me.pljr.pljrapispigot.utils.ChatUtil;
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
            if (CfgSettings.REACTIONS_CHAT){
                List<String> broadcast = new ArrayList<>();
                for (String line : reaction.getChatBroadcastNoWinner()){
                    broadcast.add(line.replace("%answer", answer).replace("%prize", prize+""));
                }
                ChatUtil.broadcast(broadcast, "", CfgSettings.BUNGEE);
            }
            if (CfgSettings.REACTIONS_TITLE){
                PLJRTitle title = reaction.getTitleBroadcastNoWinner();
                TitleManager.broadcast(new PLJRTitle(
                        title.getTitle().replace("%answer", answer).replace("%prize", prize+""),
                        title.getSubtitle().replace("%answer", answer).replace("%prize", prize+""),
                        title.getIn(), title.getStay(), title.getOut()
                ));
            }
            if (CfgSettings.REACTIONS_ACTIONBAR){
                PLJRActionBar actionBar = reaction.getActionBarBroadcastNoWinner();
                ActionBarManager.broadcast(new PLJRActionBar(
                        actionBar.getMessage().replace("%answer", answer).replace("%prize", prize+""),
                        actionBar.getDuration()
                ));
            }
        }else{
            if (CfgSettings.REACTIONS_CHAT){
                List<String> broadcast = new ArrayList<>();
                for (String line : reaction.getChatBroadcastEnd()){
                    broadcast.add(line.replace("%name", name).replace("%answer", answer).replace("%prize", prize+""));
                }
                ChatUtil.broadcast(broadcast, "", CfgSettings.BUNGEE);
            }
            if (CfgSettings.REACTIONS_TITLE){
                PLJRTitle title = reaction.getTitleBroadcastEnd();
                TitleManager.broadcast(new PLJRTitle(
                        title.getTitle().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        title.getSubtitle().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        title.getIn(), title.getStay(), title.getOut()
                ));
            }
            if (CfgSettings.REACTIONS_ACTIONBAR){
                PLJRActionBar actionBar = reaction.getActionBarBroadcastEnd();
                ActionBarManager.broadcast(new PLJRActionBar(
                        actionBar.getMessage().replace("%name", name).replace("%answer", answer).replace("%prize", prize+""),
                        actionBar.getDuration()
                ));
            }
        }
    }
}
