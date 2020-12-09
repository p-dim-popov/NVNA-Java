package eu.nvna;

import java.time.LocalTime;

public class ShoppersAtTime {
    private final LocalTime timeOfEntry;
    private final long count;

    public ShoppersAtTime(LocalTime timeOfEntry, long count){
        this.timeOfEntry = timeOfEntry;
        this.count = count;
    }

    public LocalTime getTimeOfEntry() {
        return this.timeOfEntry;
    }

    public long getCount() {
        return this.count;
    }
}
