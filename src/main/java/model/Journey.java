package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="journey")
public class Journey implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITIZEN.ID")
    private long idCitizen;

    private long idVehicle;
    private int departure, arrival, commute;

    public Journey(){}

    public Journey(int departure, int arrival){
        this.departure = departure;
        this.arrival = arrival;
        this.commute = arrival - departure;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getCommute() {
        return commute;
    }

    public void setCommute(int commute) {
        this.commute = commute;
    }

    @Override
    public String toString(){
        return "Journey [id= " + getId() + ", idCitizen= " + getIdCitizen() + ", idVehicle= " + getIdVehicle()
                + ", departure= " + getDeparture() + ", commute= " + getCommute() + ", arrival= " + getArrival()
                + "]";
    }

}
