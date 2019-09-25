package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
public class Journey implements Serializable {

    private long id, idCitizen, idVehicle;
    private LocalTime departure;
    private LocalTime arrival;
    private LocalTime commute;

    public Journey(){}

    public Journey(LocalTime departure, LocalTime arrival, LocalTime commute){
        this.departure = departure;
        this.arrival = arrival;
        this.commute = commute;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCitizen() {
        return idCitizen;
    }

    @Id
    public long getIdVehicle() {
        return idVehicle;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public LocalTime getCommute() {
        return commute;
    }

    public void setCommute(LocalTime commute) {
        this.commute = commute;
    }

    @Override
    public String toString(){
        return "Journey [id= " + id + ", idCitizen= " + idCitizen + ", idVehicle= " + idVehicle
                + ", departure= " + departure + ", commute= " + commute + ", arrival= " + arrival
                + "]";
    }
}
