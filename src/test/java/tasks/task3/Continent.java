package tasks.task3;

import java.util.Set;

public enum Continent {
    EUROPE(Set.of("Poland", "Finland", "Germany", "Spain", "Norway")),
    ASIA(Set.of("Russia", "Japan", "China")),
    AFRICA(Set.of("Egypt", "Algeria", "Morocco", "Zimbabwe")),
    NORTH_AMERICA(Set.of("USA", "Canada", "Mexico")),
    SOUTH_AMERICA(Set.of("Argentina", "Brazil", "Colombia", "Peru")),
    AUSTRALIA(Set.of("Australia")),
    ANTARCTICA(Set.of());

    private final Set<String> countries;

    Continent(Set<String> countries){
        this.countries = countries;
    }

    public Set<String> getCountries(){
        return countries;
    }
}
