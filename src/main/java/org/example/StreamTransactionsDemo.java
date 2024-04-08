package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class StreamTransactionsDemo {
    public static void main(String... args) {
        var a = new Trader("A", "London");
        var b = new Trader("B", "Moscow");
        var c = new Trader("C", "Milan");
        var d = new Trader("D", "Tokyo");
        var e = new Trader("E", "Paris");
        var f = new Trader("F", "Kyiv");
        var g = new Trader("G", "London");


        List<Transaction> transactions = List.of(
                new Transaction(a, 2014, 555),
                new Transaction(b, 2024, 101),
                new Transaction(a, 1983, 50),
                new Transaction(c, 1996, 354),
                new Transaction(g, 2020, 42),
                new Transaction(e, 2023, 328),
                new Transaction(f, 2024, 200),
                new Transaction(a, 2016, 1),
                new Transaction(d, 2000, 780),
                new Transaction(b, 2022, 1000)
        );

        // 1
        List<Transaction> tr1983 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 1983)
                .sorted(comparing(Transaction::getValue))
                .toList();
        System.out.println(tr1983);
        System.out.println("---------------------------------------");

        // 2
        Set<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());
        System.out.println(cities);
        System.out.println("---------------------------------------");

        // 3
        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("London"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .toList();
        System.out.println(traders);
        System.out.println("---------------------------------------");

        // 4
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
//                        .reduce("", (n1, n2) -> n1 + n2 + " ");
                        .collect(joining(", "));
        System.out.println(traderStr);
        System.out.println("---------------------------------------");

        // 5
        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
//                                .equals("Milan"));
                                .equals("Minsk"));
        System.out.println(milanBased);
        System.out.println("---------------------------------------");

        // 6
        transactions.stream()
                .filter(transaction -> "London".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("---------------------------------------");

        // 7
        Optional<Integer> highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        System.out.println(highestValue);
        System.out.println("---------------------------------------");

        // 8
        Optional<Transaction> smallestTransaction =
                transactions.stream()
//                        .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
                        .min(comparing(Transaction::getValue));
        System.out.println(smallestTransaction);
        System.out.println("---------------------------------------");

        // 9
        int sum = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println("---------------------------------------");

        // 10
        double average = transactions.stream()
                .collect(Collectors.averagingDouble(Transaction::getValue));
        System.out.println(average);
        System.out.println("---------------------------------------");
    }
}
