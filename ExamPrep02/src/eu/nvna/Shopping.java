package eu.nvna;

import java.time.Duration;
import java.time.LocalTime;

public class Shopping implements Comparable<Shopping>, ISerializable {
    private LocalTime timeOfEntry = LocalTime.now();
    private Duration inShopDuration = Duration.ofNanos(0);
    private double cost = 0;

    public Shopping(LocalTime timeOfEntry) {
        this.setTimeOfEntry(timeOfEntry);
    }

    public void setTimeOfEntry(LocalTime timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public LocalTime getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setInShopDuration(Duration inShopDuration) {
        this.inShopDuration = inShopDuration;
    }

    public Duration getInShopDuration() {
        return inShopDuration;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String serialize() {
        return this.getTimeOfEntry().getNano() + "," +
                this.getCost() + "," +
                this.getInShopDuration().getNano();
    }

    public static Shopping deserialize(String obj) {
        var data = obj.split(",");
        var shopping = new Shopping(LocalTime.ofNanoOfDay(Integer.parseInt(data[0])));
        shopping.setCost(Double.parseDouble(data[1]));
        shopping.setInShopDuration(Duration.ofNanos(Long.parseLong(data[2])));
        return shopping;
    }

    @Override
    public int compareTo(Shopping o) {
        return this.getTimeOfEntry().compareTo(o.getTimeOfEntry());
    }

    @Override
    public String toString() {
        return "{" +
                "timeOfEntry:" + this.getTimeOfEntry() + "," +
                "inShopDuration:" + this.getInShopDuration() + "," +
                "cost:" + this.getInShopDuration() +
                "}";
    }
}
