File: BoxCar.java
*/

import java.util.ArrayList;
import java.util.Objects;

public class BoxCar<T>{
    private String type;
    private int capacity;
    private int numItems = 0;
    //Creates a BoxCar(arrayList) for either Person or Cargo objects.
    private ArrayList<Thing> boxCar;

    public BoxCar(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;

        boxCar = new ArrayList<>(this.capacity);
    }


    public void loadBoxCarCargo(int boxCarID, String cargoID, int weight, int height){
        Thing newObject = new CARGO(boxCarID, cargoID, weight, height);

        if(isRepeat(newObject)){
            System.out.println("    ERROR: Invalid item, item with id "+ cargoID +" already exists.");
        }
        else {
            boxCar.add(newObject);
            numItems++;
        }
    }

    public void loadBoxCarPerson(int boxCarID, String govID, String name, int age){
        Thing newObject = new PERSON(boxCarID, govID, name, age);
        if(isRepeat(newObject)){
            System.out.println("    ERROR: Invalid item, item with id "+ govID +" already exists.");
        }
        else {
            boxCar.add(newObject);
            numItems++;
        }
    }

    public void Unload(String uniqueID){
        for(int i = 0; i < boxCar.size(); i++){
            if(Objects.equals(uniqueID, boxCar.get(i).getUniqueID())) {
                boxCar.remove(i);
                numItems--;
                return;
            }
        }
        System.out.println("  ERROR: Invalid item id, item not found");
    }

    public boolean isRepeat(Thing newThing){
        int count = 0;
        String temp = newThing.getUniqueID();
        while(count < boxCar.size()){
            if(Objects.equals(temp, boxCar.get(count).getUniqueID())){
                return true;
            }
            count++;
        }
        return false;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void print(){
        for(int i = 0; i < boxCar.size(); i ++)
            boxCar.get(i).print();


    }
}
