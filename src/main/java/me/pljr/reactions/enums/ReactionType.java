package me.pljr.reactions.enums;

import me.pljr.reactions.config.CfgReactions;

import java.util.Random;

public enum ReactionType {
    WORD_SHUFFLE,
    WORD_COPY,
    WORD_HIDE,
    BLOCK_PLACE,
    BLOCK_BREAK,
    MOB_KILL,
    FISH_CATCH,
    MATH_SUMMATION,
    MATH_SUBSTRACTION,
    MATH_MULTIPLICATION;

    public static ReactionType getRandom(){
        return CfgReactions.enabledReactions.get(new Random().nextInt(CfgReactions.enabledReactions.size()));
    }
}
