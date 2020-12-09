package eu.nvna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Sales implements IFileOperationsProvider<TV> {
    public Iterable<TV> getList() {
        return this.list;
    }

    private Collection<TV> list = new ArrayList<>();

    public Sales() {
    }

    public Sales(Collection<TV> list) {
        this.list = list;
    }

    public Sales(TV[] array) {
        this(Arrays.stream(array)
                .collect(Collectors
                        .toCollection(ArrayList::new)));
    }

    @Override
    public Iterable<TV> readAll(String filename) {
        try {
//            var file = new File(filename);
//            try (var scanner = new Scanner(file)) {
//                while (scanner.hasNextLine()) {
//                    var line = scanner.nextLine().split(",");
//                    this.list.add(new TV(line[0], Integer.parseInt(line[1])));
//                }
//            }
            // OR...
            this.list.addAll(
                    Files.readAllLines(Path.of(filename))
                            .stream()
                            .map(TV::deserialize)
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
//            try(var fw = new FileWriter(filename)){
//                for (var el : this.list) {
//                    fw.write(el.toString() + "\n");
//                }
//            }
            // OR...
            return Files.writeString(Path.of(filename),
                    this.list
                            .stream()
                            .map(TV::serialize)
                            .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Path edit(String filename, int row, TV tv) {
        try {
            var x = new ArrayList<>(Files.readAllLines(Path.of(filename)));
            x.set(row, tv.serialize());
            return Files.writeString(
                    Path.of(filename),
                    String.join("\n", x));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
