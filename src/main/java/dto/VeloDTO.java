package dto;

import model.Citizen;

import java.io.Serializable;
import java.util.ArrayList;

public class VeloDTO implements Serializable {

    private long id;
    private String name;
    private ArrayList<Citizen> rider;
    final private int maxCapacity = 1;
    final private long speed = 1;

    public VeloDTO(){}

    public VeloDTO(String name){
        this.rider = new ArrayList<Citizen>();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Citizen> getRider() {
        return rider;
    }

    public void setRider(ArrayList<Citizen> rider) {
        this.rider = rider;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getSpeed() {
        return speed;
    }
}
