package ch.fhnw.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.CatalogService;
import ch.fhnw.pizza.business.service.CatalogService;
import ch.fhnw.pizza.data.domain.Game;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class GameShopApplication {

    @Autowired
    private CatalogService catalogService;

    public static void main(String[] args) {
        SpringApplication.run(GameShopApplication.class, args);
    }

    // Use this method to initialize placeholder data without using Postman
    // If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
    // To resolve the error, delete the file and restart the application
    @PostConstruct
    private void initPlaceholderData() throws Exception {
       
        // Action-Adventure
        Game game = new Game();
        game.setTitle("The Legend of Zelda: Breath of the Wild");
        game.setGenre("Action-Adventure");
        game.setDeveloper("Nintendo");
        game.setPrice(59.99);
        catalogService.addGame(game);

        game = new Game();
        game.setTitle("Uncharted 4: A Thief's End");
        game.setGenre("Action-Adventure");
        game.setDeveloper("Naughty Dog");
        game.setPrice(19.99);
        catalogService.addGame(game);

        // RPG
        game = new Game();
        game.setTitle("The Witcher 3: Wild Hunt");
        game.setGenre("RPG");
        game.setDeveloper("CD Projekt Red");
        game.setPrice(39.99);
        catalogService.addGame(game);

        game = new Game();
        game.setTitle("Final Fantasy XV");
        game.setGenre("RPG");
        game.setDeveloper("Square Enix");
        game.setPrice(29.99);
        catalogService.addGame(game);

        // Shooter
        game = new Game();
        game.setTitle("Call of Duty: Modern Warfare");
        game.setGenre("Shooter");
        game.setDeveloper("Infinity Ward");
        game.setPrice(59.99);
        catalogService.addGame(game);

        game = new Game();
        game.setTitle("Overwatch");
        game.setGenre("Shooter");
        game.setDeveloper("Blizzard Entertainment");
        game.setPrice(39.99);
        catalogService.addGame(game);

        // Sports
        game = new Game();
        game.setTitle("FIFA 24");
        game.setGenre("Sports");
        game.setDeveloper("EA Sports");
        game.setPrice(49.99);
        catalogService.addGame(game);

        game = new Game();
        game.setTitle("NBA 2K24");
        game.setGenre("Sports");
        game.setDeveloper("Visual Concepts");
        game.setPrice(59.99);
        catalogService.addGame(game);

        // Strategy
        game = new Game();
        game.setTitle("Civilization VI");
        game.setGenre("Strategy");
        game.setDeveloper("Firaxis Games");
        game.setPrice(29.99);
        catalogService.addGame(game);

        game = new Game();
        game.setTitle("StarCraft II");
        game.setGenre("Strategy");
        game.setDeveloper("Blizzard Entertainment");
        game.setPrice(39.99);
        catalogService.addGame(game);
    }

}