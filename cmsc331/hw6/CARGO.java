/*
File: Cargo.java
*/

public class CARGO extends Thing{
    private int boxCarID;
    private String cargoID;
    private int height;
    private int weight;

    public CARGO(int boxCarID, String cargoID, int weight, int height){
        this.boxCarID = boxCarID;
        this.cargoID = cargoID;
        this.weight = weight;
        this.height = height;
    }

    public int getBoxCarID() {
        return boxCarID;
    }

    public String getUniqueID() {
        return cargoID;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
    public void print(){
        System.out.println("          " + cargoID+ ":   Weight: "+ weight + "    Height: "+height);

    }
}
