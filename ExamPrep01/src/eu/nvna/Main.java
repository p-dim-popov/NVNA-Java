package eu.nvna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        var sales = new Sales(new TV[] {
                new TV("Sony",24),
                new TV("Yamaha",345)
        });

        sales.write("saveall.txt");
        sales.edit("saveall.txt", 0, new TV("Johny", 123));

        var salesResult = new Sales();
        salesResult.readAll("saveall.txt");
        System.out.println(Arrays.toString(salesResult.getList()));
    }
}
