package eu.nvna;

public class Main {

    public static void main(String[] args) {
        var sales = new Sales(new TV[] {
                new TV("Sony",24),
                new TV("Yamaha",345)
        });

        var path = sales.writeTo("sales.txt");
        sales.edit(path.toString(), 0, new TV("North American Audio, Inc.", 123));

        // new to check if readAll works
        new Sales()
                .readAll(path.toString())
                .forEach(System.out::println);
    }
}
