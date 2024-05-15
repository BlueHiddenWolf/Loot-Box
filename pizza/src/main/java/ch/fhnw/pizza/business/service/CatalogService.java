package ch.fhnw.pizza.business.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.data.domain.Catalog;
import com.webshop.data.domain.Game;
import com.webshop.data.repository.GameRepository;

@Service
public class CatalogService {

    @Autowired
    private GameRepository gameRepository;

    public Game findGameById(Long id) {
        try {
            Optional<Game> game = gameRepository.findById(id);
            if (game.isPresent()) {
                return game.get();
            } else {
                throw new RuntimeException("Game with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Game with id " + id + " not found", e);
        }
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game addGame(Game game) throws Exception {
        if (game.getTitle() != null) {
            if (gameRepository.findByTitle(game.getTitle()) == null)
                return gameRepository.save(game);
            throw new Exception("Game " + game.getTitle() + " already exists");
        }
        throw new Exception("Invalid game title");
    }

    public Game updateGame(Long id, Game game) throws Exception {
        Optional<Game> gameToUpdate = gameRepository.findById(id);
        if (gameToUpdate.isPresent()) {
            Game updatedGame = gameToUpdate.get();
            if (game.getTitle() != null)
                updatedGame.setTitle(game.getTitle());
            if (game.getGenre() != null)
                updatedGame.setGenre(game.getGenre());
            if (game.getDeveloper() != null)
                updatedGame.setDeveloper(game.getDeveloper());
            if (game.getPrice() != null)
                updatedGame.setPrice(game.getPrice());
            return gameRepository.save(updatedGame);
        }
        throw new Exception("Game with id " + id + " does not exist");
    }

    public void deleteGame(Long id) throws Exception {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new Exception("Game with id " + id + " does not exist");
        }
    }

    // Business Logic to get current offer according to the category of the games
    private String getCurrentOffer(String category) {
        String currentOffer = "No special offer for your selected category. Do check back again.";
        if ("Action".equalsIgnoreCase(category))
            currentOffer = "20% off on all Action games!!!";
        else if ("RPG".equalsIgnoreCase(category))
            currentOffer = "Buy one RPG game, get one free!!!";
        return currentOffer;
    }

    public Catalog getCatalogByCategory(String category) {
        String currentOffer = getCurrentOffer(category);
        List<Game> gameList = gameRepository.findByCategory(category);
        Catalog catalog = new Catalog();
        catalog.setGameList(gameList);
        catalog.setCurrentOffer(currentOffer);
        return catalog;
    }

    // New business logic

    // Search games by developer
    public List<Game> getGamesByDeveloper(String developer) {
        return gameRepository.findByDeveloper(developer);
    }

    // Filter games by price range
    public List<Game> getGamesByPriceRange(Double minPrice, Double maxPrice) {
        return gameRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Get top rated games
    public List<Game> getTopRatedGames() {
        return gameRepository.findAll().stream()
                .filter(game -> game.getRating() != null)
                .sorted((g1, g2) -> g2.getRating().compareTo(g1.getRating()))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Get games on special offer
    public List<Game> getGamesOnSpecialOffer() {
        return gameRepository.findBySpecialOfferTrue();
    }
}

