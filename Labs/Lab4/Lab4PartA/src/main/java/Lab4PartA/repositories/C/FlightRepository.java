package Lab4PartA.repositories.C;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.C.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findFirstByflightnumber(String flightnumber);
}
