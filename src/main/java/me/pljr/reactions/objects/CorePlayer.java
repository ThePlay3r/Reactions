package me.pljr.reactions.objects;

import me.pljr.reactions.enums.ReactionType;

public class CorePlayer {
    private int wordShuffle;
    private int wordCopy;
    private int wordHide;
    private int blockPlace;
    private int blockBreak;
    private int mobKill;
    private int fishCatch;
    private int mathSummation;
    private int mathSubstraction;
    private int mathMultiplication;

    public CorePlayer(int wordShuffle, int wordCopy, int wordHide, int blockPlace, int blockBreak, int mobKill, int fishCatch, int mathSummation, int mathSubstraction, int mathMultiplication) {
        this.wordShuffle = wordShuffle;
        this.wordCopy = wordCopy;
        this.wordHide = wordHide;
        this.blockPlace = blockPlace;
        this.blockBreak = blockBreak;
        this.mobKill = mobKill;
        this.fishCatch = fishCatch;
        this.mathSummation = mathSummation;
        this.mathSubstraction = mathSubstraction;
        this.mathMultiplication = mathMultiplication;
    }

    public void addReaction(ReactionType type, int amount){
        switch (type){
            case MOB_KILL:
                this.mobKill += amount;
                break;
            case WORD_COPY:
                this.wordCopy += amount;
                break;
            case WORD_HIDE:
                this.wordHide += amount;
                break;
            case FISH_CATCH:
                this.fishCatch += amount;
                break;
            case BLOCK_BREAK:
                this.blockBreak += amount;
                break;
            case BLOCK_PLACE:
                this.blockPlace += amount;
            case WORD_SHUFFLE:
                this.wordShuffle += amount;
                break;
            case MATH_SUMMATION:
                this.mathSummation += amount;
                break;
            case MATH_SUBSTRACTION:
                this.mathSubstraction += amount;
                break;
            case MATH_MULTIPLICATION:
                this.mathMultiplication += amount;
                break;
        }
    }

    public int getWordShuffle() {
        return wordShuffle;
    }

    public void setWordShuffle(int wordShuffle) {
        this.wordShuffle = wordShuffle;
    }

    public int getWordCopy() {
        return wordCopy;
    }

    public void setWordCopy(int wordCopy) {
        this.wordCopy = wordCopy;
    }

    public int getWordHide() {
        return wordHide;
    }

    public void setWordHide(int wordHide) {
        this.wordHide = wordHide;
    }

    public int getBlockPlace() {
        return blockPlace;
    }

    public void setBlockPlace(int blockPlace) {
        this.blockPlace = blockPlace;
    }

    public int getBlockBreak() {
        return blockBreak;
    }

    public void setBlockBreak(int blockBreak) {
        this.blockBreak = blockBreak;
    }

    public int getMobKill() {
        return mobKill;
    }

    public void setMobKill(int mobKill) {
        this.mobKill = mobKill;
    }

    public int getFishCatch() {
        return fishCatch;
    }

    public void setFishCatch(int fishCatch) {
        this.fishCatch = fishCatch;
    }

    public int getMathSummation() {
        return mathSummation;
    }

    public void setMathSummation(int mathSummation) {
        this.mathSummation = mathSummation;
    }

    public int getMathSubstraction() {
        return mathSubstraction;
    }

    public void setMathSubstraction(int mathSubstraction) {
        this.mathSubstraction = mathSubstraction;
    }

    public int getMathMultiplication() {
        return mathMultiplication;
    }

    public void setMathMultiplication(int mathMultiplication) {
        this.mathMultiplication = mathMultiplication;
    }
}
