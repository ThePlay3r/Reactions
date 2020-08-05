package me.pljr.reactions.objects;

public class ReactionStat {
    private final String name;
    private final int amount;

    public ReactionStat(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
