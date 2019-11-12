public class Thing{
    private int boxCarID;
    private String uniqueID;

    public Thing(){

    }

    public Thing(int boxCarID, String uniqueID){
        this.boxCarID = boxCarID;
        this.uniqueID = uniqueID;
    }

    public int getBoxCarID() {
        return boxCarID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setBoxCarID(int boxCarID) {
        this.boxCarID = boxCarID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void print(){
    }
}
