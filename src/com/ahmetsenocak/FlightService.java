package com.ahmetsenocak;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightService {
    public Stream<CompletableFuture<Quote>> getQuotes() {
        var sites = List.of("site1", "Site2", "Site3");

        return sites.stream()
                .map(this::getQuote);//For building pipeline

    }

    public CompletableFuture<Quote> getQuote(String site) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting quote from " + site);


            var random = new Random();
            LongTask.simulate(1_000 + random.nextInt(2_000));

            var price = 100 + random.nextInt(10);
            return new Quote(site, price);

        });
    }
}
