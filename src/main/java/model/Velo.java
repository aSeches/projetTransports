package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Entity
@Table(name="velo")
public class Velo implements Vehicle, Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ArrayList<Citizen> rider;
    private int maxCapacity = 1;
    private double kmhSpeed = 20.00;

    public Velo(){
        rider = new ArrayList<Citizen>();
    }

    @Override
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public double getSpeed() {
        return kmhSpeed;
    }

    @Override
    public void setSpeed(double speed) {
        this.kmhSpeed = speed;
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
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
    @Transient
    public boolean isFull() {
        if(rider.size() == maxCapacity){
            return true;
        } else {
            return false;
        }
    }

}
