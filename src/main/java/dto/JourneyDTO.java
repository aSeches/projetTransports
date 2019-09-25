package dto;

import javax.ws.rs.Path;
import java.io.Serializable;
import java.time.LocalTime;

@Path("journey")
public class JourneyDTO implements Serializable {
    private long id, idCitizen, idVehicle;
    private LocalTime departure;
    private LocalTime arrival;
    private LocalTime commute;

    public JourneyDTO(){}

    public JourneyDTO(LocalTime departure, LocalTime arrival, LocalTime commute){
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

}
