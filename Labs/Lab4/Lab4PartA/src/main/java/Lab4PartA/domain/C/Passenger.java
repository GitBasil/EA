package Lab4PartA.domain.C;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderColumn(name="sequence")
    private List<Flight> flights = new ArrayList<>();

    public Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addFlight(Flight flight)
    {
        flights.add(flight);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights= " + flights +
                "}";
    }
}
