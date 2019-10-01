package model;

import java.util.ArrayList;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public interface Vehicle {

    long getId();

    void setId(long id);

    double getSpeed();

    void setSpeed(double speed);

    int getMaxCapacity();

    void setMaxCapacity(int maxCapacity);

    ArrayList<Citizen> getPassengers();

    void setPassengers(ArrayList<Citizen> passengers);

    void getIn(Citizen c);

    void getOut(Citizen c);

    boolean isFull();
}
