package com.hse.db.hw.hw7.seeder;

import com.github.javafaker.Faker;
import com.hse.db.hw.hw7.entity.*;
import com.hse.db.hw.hw7.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final int NUMBER_OF_RECORDS = 100;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OlimpicsRepository olympicsRepository;

    @Autowired
    private ResultRepository resultRepository;

    private final Faker faker = new Faker();


    public void seedDatabase(Map<String, Integer> recordsPerTable) {
        seedCountries(recordsPerTable.getOrDefault("countries", 0));
        seedOlympics(recordsPerTable.getOrDefault("olympics", 0));
        seedPlayers(recordsPerTable.getOrDefault("players", 0));
        seedEvents(recordsPerTable.getOrDefault("events", 0));
        seedResults(recordsPerTable.getOrDefault("results", 0));
    }

    private void seedCountries(int count) {
        for (int i = 0; i < count; i++) {
            Country country = new Country();
            String countryName = faker.country().name();
            String countryId = countryName.substring(0, Math.min(3, countryName.length())).toUpperCase(); // Аббревиатура страны
            country.setCountryId(countryId);
            country.setName(countryName.substring(0, Math.min(40, countryName.length())));
            country.setAreaSqkm(faker.number().numberBetween(1000, 1000000));
            country.setPopulation(faker.number().numberBetween(10000, 100000000));
            countryRepository.save(country);
        }
    }

    private void seedOlympics(int count) {
        for (int i = 0; i < count; i++) {
            Olympics olympics = new Olympics();
            String city = faker.address().cityName();
            int year = faker.number().numberBetween(2000, 2024);
            olympics.setOlympicId(city.substring(0, Math.min(3, city.length())).toUpperCase() + year); // Формат <CITY><YEAR>
            olympics.setCountry(getRandomCountry());
            olympics.setCity(city.substring(0, Math.min(50, city.length())));
            olympics.setYear(year);
            olympics.setStartDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            olympics.setEndDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            olympicsRepository.save(olympics);
        }
    }

    private void seedPlayers(int count) {
        for (int i = 0; i < count; i++) {
            Player player = new Player();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String playerId = (lastName + firstName.substring(0, Math.min(2, firstName.length())) + String.format("%02d", i)).toUpperCase(); // Формат <Фамилия><Имя><Номер>
            player.setPlayerId(playerId.substring(0, Math.min(10, playerId.length()))); // Обрезаем до 10 символов
            player.setName((firstName + " " + lastName).substring(0, Math.min(40, (firstName + " " + lastName).length()))); // Обрезаем до 40 символов
            player.setCountry(getRandomCountry());
            player.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            playerRepository.save(player);
        }
    }

    private void seedEvents(int count) {
        for (int i = 0; i < count; i++) {
            Event event = new Event();
            event.setEventId(String.format("E%05d", i)); // Формат E00001

            String eventName = faker.esports().event();
            event.setName(eventName.length() > 40 ? eventName.substring(0, 40) : eventName);

            event.setEventType(faker.options().option("ATH", "SWIM", "CYCL", "ROW"));
            event.setOlympics(getRandomOlympics());
            event.setIsTeamEvent(faker.bool().bool() ? 1 : 0);
            event.setNumPlayersInTeam(event.getIsTeamEvent() == 1 ? faker.number().numberBetween(2, 11) : null);

            String resultNotedIn = faker.lorem().word();
            event.setResultNotedIn(resultNotedIn.length() > 100 ? resultNotedIn.substring(0, 100) : resultNotedIn);

            eventRepository.save(event);
        }
    }

    private void seedResults(int count) {
        for (int i = 0; i < count; i++) {
            Result result = new Result();
            result.setEvent(getRandomEvent());
            result.setPlayer(getRandomPlayer());
            result.setMedal(faker.options().option("GOLD   ", "SILVER ", "BRONZE "));
            result.setResult(faker.number().randomDouble(2, 10, 100));
            resultRepository.save(result);
        }
    }


    private Country getRandomCountry() {
        List<Country> countries = (List<Country>) countryRepository.findAll();
        return countries.get(faker.random().nextInt(countries.size()));
    }

    private Olympics getRandomOlympics() {
        List<Olympics> olympicsList = (List<Olympics>) olympicsRepository.findAll();
        return olympicsList.get(faker.random().nextInt(olympicsList.size()));
    }

    private Player getRandomPlayer() {
        List<Player> players = (List<Player>) playerRepository.findAll();
        return players.get(faker.random().nextInt(players.size()));
    }

    private Event getRandomEvent() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events.get(faker.random().nextInt(events.size()));
    }

    private String getRandomMedal() {
        String[] medals = {"GOLD", "SILVER", "BRONZE", null};
        return medals[faker.random().nextInt(medals.length)];
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase(Map.of(
                "countries", NUMBER_OF_RECORDS / 10,
                "olympics", NUMBER_OF_RECORDS / 50,
                "players", NUMBER_OF_RECORDS,
                "events", NUMBER_OF_RECORDS / 5,
                "results", NUMBER_OF_RECORDS
        ));
    }
}
