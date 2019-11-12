/*
File: Person.java
*/

public class PERSON extends Thing{
    private int boxCarID;
    private String name;
    private String personID;
    private int age;

    public PERSON(int boxCarID, String personID, String name, int age){
        this.boxCarID = boxCarID;
        this.personID = personID;
        this.name = name;
        this.age = age;
    }

    public int getBoxCarID() {
        return boxCarID;
    }

    public String getUniqueID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public void print(){
        System.out.println("          " + personID +":   Name: "+ name + "    Age: "+ age);

    }
}
