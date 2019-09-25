package dto;

import model.Citizen;

import java.io.Serializable;
import java.util.ArrayList;

public class BusDTO implements Serializable {

    private long id;
    private String name;
    private ArrayList<Citizen> passengers;
    final private int maxCapacity = 40;
    final private long kmhSpeed = 50;

    public BusDTO(){};

    public BusDTO(String name){
        this.passengers = new ArrayList<Citizen>();
        this.name = name;
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
        return kmhSpeed;
    }
}
