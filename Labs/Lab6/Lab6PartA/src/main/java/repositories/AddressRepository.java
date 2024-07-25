package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {

    // @Query(value = "Select * from Address a where a.city = :city", nativeQuery = true)
    // List<Address> findAllAddressInCity(@Param("city") String city);

    List<String> findStreetByCityAndZip(String city, String zip);
}
