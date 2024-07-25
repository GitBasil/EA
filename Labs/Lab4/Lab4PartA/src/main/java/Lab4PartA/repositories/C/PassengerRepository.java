package Lab4PartA.repositories.C;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.C.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    public Passenger findFirstByName(String name);
}
