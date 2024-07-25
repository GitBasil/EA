package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.CD;


public interface CDRepository extends JpaRepository<CD, Long>, JpaSpecificationExecutor<CD> {
    List<CD> findByPriceLessThanAndArtist(double price, String artist);

    // named query on CD entity
    List<CD> findByArtist(@Param("artist") String artist);

    @Query("Select cd from CD cd Where cd.artist = :artist and cd.price > :price")
    List<CD> findAllCDsWithArtistAndPriceBiggerThan(String artist, double price);

    // @Query(value = "Select * from Product p Join CD cd on p.id = cd.id where cd.artist = :artist", nativeQuery = true)
    // List<CD> findAllCDsFromArtist(@Param("artist") String artist);
}
