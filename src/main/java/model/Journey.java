package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name="JOURNEY")
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
    @NotNull
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @NotNull
    public long getIdCitizen() {
        return idCitizen;
    }

    public void setIdCitizen(long idCitizen) {
        this.idCitizen = idCitizen;
    }

    @Id
    @NotNull
    public long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(long idVehicle) {
        this.idVehicle = idVehicle;
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
