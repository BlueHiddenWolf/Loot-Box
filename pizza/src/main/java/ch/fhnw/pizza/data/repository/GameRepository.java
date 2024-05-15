package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Game;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
    List<Game> findByDeveloper(String developer);
    List<Game> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Game> findByCategory(String category);
    List<Game> findBySpecialOfferTrue();
}
