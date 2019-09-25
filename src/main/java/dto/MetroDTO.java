package dto;

import model.Citizen;

import java.io.Serializable;
import java.util.ArrayList;

public class MetroDTO implements Serializable {

    private long id;
    private String name;
    private ArrayList<Citizen> passengers;
    final private int maxCapacity = 60;
    final private long speed = 5;

    public MetroDTO(){}

    public MetroDTO(String name){
        this.name = name;
        this.passengers = new ArrayList<Citizen>();
    }

    public long getId() {
        return id;
    }

    public ArrayList<Citizen> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Citizen> passengers) {
        this.passengers = passengers;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getSpeed() {
        return speed;
    }
}
