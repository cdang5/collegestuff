/*
File: hw6.java
Contains main() function
*/

import java.util.*;
import java.io.*;


public class hw6 {
    private static Train T = new Train("New York", 10 , 50, 3);

    public static void main(String [] args) throws FileNotFoundException{

	Scanner input = new Scanner(new FileInputStream("train_commands.txt"));
	String line;

	while (input.hasNextLine()) {
	    line = input.nextLine();

	    
	    switch (line) {
	    case "PRINT":
		T.Prints();
		break;

	    case "ARRIVE":
		T.Arrive();
		break;

	    case "DEPART":
		T.Depart(input.nextLine());
		break;

	    case "SPEEDUP":
		T.SpeedUp(Integer.parseInt(input.nextLine()));
		break;

	    case "SLOWDOWN":
		T.SlowDown(Integer.parseInt(input.nextLine()));
		break;

	    case "ADDCAR":
		T.AddCar(input.nextLine(), Integer.parseInt(input.nextLine()));
		break;

	    case "REMOVECAR":
		T.RemoveCar(Integer.parseInt(input.nextLine()));
		break;

	    case "QUIT":
		T.quit();
		break;

	    case "LOAD":
		//Make sure you dont need type for something else
		String type = input.nextLine();
		if (Objects.equals(type, "CARGO")) {
		    T.LoadCargo(Integer.parseInt(input.nextLine()), input.nextLine(), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine()));
		} else if (Objects.equals(type, "PERSON")) {
		    T.LoadPerson(Integer.parseInt(input.nextLine()), input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()));
		}
		break;

	    case "UNLOAD":
		T.Unload(Integer.parseInt(input.nextLine()), input.nextLine());
		break;
	    }
	}
    }
}
