/*
File: Train.java
*/

import java.util.ArrayList;
import java.util.Objects;

public class Train {
    private String currLocation;
    private int minSpeed;
    private int maxSpeed;
    private int trainCap;
    private ArrayList<BoxCar> train = new ArrayList<BoxCar>(trainCap);
    private String destination;
    private int currSpeed;

    public Train(String currLocation, int minSpeed, int maxSpeed, int trainCap){
        this.currLocation = currLocation;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.trainCap = trainCap;
    }

    public void Prints(){
        System.out.println("PRINT");
        System.out.println("Train Status");
        System.out.println("------------");
        System.out.println("    Current Speed: " + getCurrSpeed());
        System.out.println("    Minimum Speed: " + getMinSpeed());
        System.out.println("    Maximum Speed: " + getMaxSpeed());
        //Checks if the train is stopped
        if(getCurrSpeed() == 0 ) {
            System.out.println("    Current Postion:    Stopped in " + getCurrLocation());
        }
        //Checks if the train is moving
        else {
            System.out.println("    Current Position:   Travaling from " + getCurrLocation() + " to " + getDestination());
        }

        System.out.println("    Current Number of Boxcars: " + train.size());
        System.out.println("    Maximum Number of Boxcars: " + trainCap);

        //Prints items from the train
        if(train.size() > 0){
            int count1 = 0;
            while(count1 < train.size()){
                System.out.println("BOXCAR: " + count1);
                System.out.println("---------");
                System.out.println("Contents:");
                train.get(count1).print();
                count1++;
            }
        }

    }

    public void Depart(String destination){
        setDestination(destination);
        setCurrSpeed(minSpeed);
        System.out.println("DEPART " + destination);
    }
    public void Arrive(){
        setCurrlocation(getDestination());
        setCurrSpeed(0);
        System.out.println("Arrive");
    }

    public void SpeedUp(int mph){
        System.out.println("SPEEDUP  " + mph);
        //The Train is not moving
        if(currSpeed == 0) {
            System.out.println("    ERROR: The Train has not departed yet.");
        }
        else if((currSpeed + mph) > maxSpeed){
            System.out.println("    ERROR: Speed can not be increased, it would exceed it's maximum speed.");
        }
        else{
            setCurrSpeed(currSpeed + mph);
        }
    }

    public void SlowDown(int mph){
        System.out.println("SLOWDOWN " + mph);
        //The train is not moving bc the speed is 0
        if(currSpeed == 0){
            System.out.println("    ERROR: The Train has not departed yet.");
        }
        else if((currSpeed + mph) < minSpeed){
            System.out.println("    ERROR: Speed can not be decreased, it would be slower than its minimum speed.");
        }
        //Its good
        else{
            //Sets the new speed
            setCurrSpeed(currSpeed - mph);
        }
    }

    public void AddCar(String type, int carCapacity){
        BoxCar newBoxCar = new BoxCar<>(type, carCapacity);
        train.add(newBoxCar);
        System.out.println("ADDCAR " + type + " " + carCapacity);
    }


    public void RemoveCar(int boxCarID){
        System.out.println("REMOVECAR " + boxCarID);
        System.out.println(train.get(boxCarID).getNumItems());
        //Checks if the BoxCar is empty or not
        if(train.get(boxCarID).getNumItems() > 0){
            System.out.println("    ERROR: Boxcar "+ boxCarID +", is not empty.");
            System.out.println("    THERE ARE "+ train.get(boxCarID).getNumItems()+" ITEMS.");
        }
        //Checks if there is a car to remove
        else if(boxCarID > train.size()){
            System.out.println("    ERROR: Car does not exist.");
        }
        //Checks if the train is moving
        else if(currSpeed > 0){
            System.out.println("    ERROR: The train has not arrived in "+ destination +" yet.");
        }
        else{
            train.remove(boxCarID);
        }
    }

    public void quit(){
        System.out.println("QUIT");
        System.out.println("Quitting...");
        return;
    }

    public void LoadCargo(int boxCarID, String cargoID, int weight, int height) {
        System.out.println("LOAD "+ boxCarID + " "+ weight + " " + height);
        if (isEmpty()) {
            System.out.println("    ERROR: Train has no boxcars");
        }
        else {
            train.get(boxCarID).loadBoxCarCargo(boxCarID, cargoID, weight, height);
        }
    }

    public void LoadPerson(int boxCarID, String govID, String name, int age) {
        System.out.println("LOAD "+ boxCarID + " "+ govID + " " + name + " " + age);
        //Checks if the Train is empty
        if (isEmpty()) {
            System.out.println("    ERROR: Train has no boxcars");
        }
        //Checks capacity of box car
        else if(train.get(boxCarID).getNumItems() + 1 > train.get(boxCarID).getCapacity()) {
            System.out.println("    ERROR: Not enough room for the given item.");
        }
        else {
            train.get(boxCarID).loadBoxCarPerson(boxCarID, govID, name, age);
            PERSON newPerson = new PERSON(boxCarID, govID, name, age);
        }
    }

    public void Unload(int boxCarID, String uniqueID){
        System.out.println("UNLOAD "+ boxCarID + " " + uniqueID);
        //Checks if boxcar is empty
        if(train.get(boxCarID).getNumItems() == 0) {
            System.out.println("ERROR: The boxcar is empty.");
        }
        else{
            train.get(boxCarID).Unload(uniqueID);
        }
    }

    public boolean isEmpty(){
        if(train.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setCurrSpeed(int currSpeed) {
        this.currSpeed = currSpeed;
    }

    public int getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrlocation(String currLocation) {
        this.currLocation = currLocation;
    }

    public String getCurrLocation() {
        return currLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
