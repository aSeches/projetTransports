package dto;

import javax.ws.rs.Path;
import java.io.Serializable;

@Path("journey")
public class JourneyDTO implements Serializable {
    private int id, idCitizen, idVehicle;
    private int departure;
    private int arrival;
    private int commute;

    public JourneyDTO(){}

    public JourneyDTO(int departure, int arrival, int commute){
        this.departure = departure;
        this.arrival = arrival;
        this.commute = commute;
    }

    public long getId() {
        return id;
    }

    public long getIdCitizen() {
        return idCitizen;
    }

    public long getIdVehicle() {
        return idVehicle;
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

}
