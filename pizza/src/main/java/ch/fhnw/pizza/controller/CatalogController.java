package ch.fhnw.pizza.controller;

import com.webshop.service.CatalogService;
import com.webshop.domain.Catalog;
import com.webshop.domain.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping(path="/games/{id}", produces = "application/json")
    public ResponseEntity getGame(@PathVariable Long id) {
        try {
            Game game = catalogService.findGameById(id);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No game found with given id");
        }
    }

    @GetMapping(path="/games", produces = "application/json")
    public List<Game> getGameList() {
        List<Game> gameList = catalogService.getAllGames();
        return gameList;
    }

    @PostMapping(path="/games", consumes="application/json", produces = "application/json")
    public ResponseEntity addGame(@RequestBody Game game) {
        try {
            game = catalogService.addGame(game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Game already exists with given name");
        }
        return ResponseEntity.ok(game);
    }

    @PutMapping(path="/games/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateGame(@PathVariable Long id, @RequestBody Game game) {
        try {
            game = catalogService.updateGame(id, game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No game found with given id");
        }
        return ResponseEntity.ok(game);
    }

    @DeleteMapping(path="/games/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        try {
            catalogService.deleteGame(id);
            return ResponseEntity.ok("Game with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
    }

    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<Catalog> getCatalog(@RequestParam String category) {
        Catalog catalog = catalogService.getCatalogByCategory(category);
        return ResponseEntity.ok(catalog);      
    }
    
}
