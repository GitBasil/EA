package Lab4PartA.domain.C;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private long id;
    private String flightnumber;
    @Column(name="cfrom")
    private String from;
    @Column(name="cto")
    private String to;
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    public Flight() {
    }

    public Flight(String flightnumber, String from, String to, LocalDate date) {
       this.flightnumber = flightnumber;
       this.from = from;
       this.to = to;
       this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getFlightnumber() {
        return flightnumber;
    }
    public String getFrom() {
        return from;
    }
    public long getId() {
        return id;
    }
    public String getTo() {
        return to;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightnumber='" + flightnumber + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }

}
