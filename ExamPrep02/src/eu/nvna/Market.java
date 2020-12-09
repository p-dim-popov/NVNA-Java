package eu.nvna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Market implements IFileOperationsProvider<Shopping> {
    private final Collection<Shopping> list;

    public Market(Shopping[] list) {
        this(Arrays.stream(list).collect(Collectors.toList()));
    }

    public Market(Collection<Shopping> shoppings) {
        this.list = shoppings;
    }

    public double mean() {
        return (1.0 * this.list
                .stream()
                .mapToLong(x -> x.getInShopDuration().getSeconds())
                .sum() / this.list.size()) / 60.0;
    }

    public double maxShoppingTime() {
        return this.list
                .stream()
                .mapToDouble(x -> 1.0 * x.getInShopDuration().getSeconds() / 60.0)
                .max()
                .orElse(0);
    }

    public double minShoppingTime() {
        return this.list
                .stream()
                .mapToDouble(x -> 1.0 * x.getInShopDuration().getSeconds() / 60.0)
                .min()
                .orElse(0);
    }

    public long getPeopleAt(LocalTime reference) {
        return this.list
                .stream()
                .filter(x -> x.getTimeOfEntry().equals(reference))
                .count();
    }

    public Collection<ShoppersAtTime> getPeopleHourly() {
        return IntStream.rangeClosed(0, 23)
                .mapToObj(x -> {
                    var time = LocalTime.of(x, 0);
                    return new ShoppersAtTime(time, this.getPeopleAt(time));
                })
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "{" +
                "shoppings:" + Arrays.toString(list.toArray(Shopping[]::new)) +
                '}';
    }

    @Override
    public Iterable<Shopping> readAll(String filename) {
        try {
            this.list.addAll(
                    Files.readAllLines(Path.of(filename))
                            .stream()
                            .map(Shopping::deserialize)
                            .collect(Collectors
                                    .toCollection(ArrayList::new)));
            return this.list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Path writeTo(String filename) {
        try {
            return Files.writeString(Path.of(filename),
                    this.list
                            .stream()
                            .map(Shopping::serialize)
                            .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Path edit(String filename, int row, Shopping element) throws IOException {
            var x = new ArrayList<>(Files.readAllLines(Path.of(filename)));
            x.set(row, element.serialize());
            return Files.writeString(
                    Path.of(filename),
                    String.join("\n", x));
    }
}
