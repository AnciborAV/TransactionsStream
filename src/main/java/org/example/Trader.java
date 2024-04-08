package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trader {
    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Trader: %s in %s", name, city);
    }
}
