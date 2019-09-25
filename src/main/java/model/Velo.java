package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Entity
public class Velo implements Vehicle, Serializable {

    private long id;
    private ArrayList<Citizen> rider;
    final private int maxCapacity = 1;
    final private double kmhSpeed = 20.00;

    public Velo(){
        rider = new ArrayList<Citizen>();
    }

    @Override
    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }

    @Override
    public ArrayList<Citizen> getPassengers(){
        return rider;
    }

    @Override
    public void setPassengers(ArrayList<Citizen> rider){
        this.rider = rider;
    }

    @Override
    public String toString(){
        return "Bus [id=" + id + ", rider= " + getPassengers() + ", speed=" + getSpeed() + "]";
    }

    @Override
    public double getSpeed() {
        return kmhSpeed;
    }

    @Override
    public void getIn(Citizen c) {
        if(!(rider.contains(c))){
            rider.add(c);
        }
    }

    @Override
    public void getOut(Citizen c) {
        if((!(rider.isEmpty())) && rider.contains(c)){
            rider.remove(c);
        }
    }

    @Override
    public boolean isFull() {
        if(rider.size() == maxCapacity){
            return true;
        } else {
            return false;
        }
    }

}
