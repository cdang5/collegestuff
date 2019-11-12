/*
File:    Proj1.cpp
Project: CMSC 202 Project 1, Spring 2017
Author:  Christopher Dang
Date:    2/11/2017
Section: 05
E-mail:  cdang3@umbc.edu 
*/


#include <iostream>
#include <string>
#include <fstream>
using namespace std;

const int MAP_SIZE = 10;

//startMenu
//Prints out the start menu to prompt user to start the simulation.
void startMenu();

//mainMenu
//Prints out main menu to prompt the user to choose a direction or quit the simulation.
void mainMenu();

//printMap
//Prints the updated map within the while loop.
void printMap(char board[][MAP_SIZE]);

//checkSearch
//Checks if the users movements are within the limits of the map.
int checkSearch(int positionX, int positionY);

//directionCheck
//Prompts the user which direction to take to get to the target. 
void directionCheck(int positionX, int positionY, int targetX, int targetY);

int main(){
  int start = 0; //Use to make the first decision from the Start Menu
  int xI = 0;
  int yI = 0;
  int xT = 0;
  int yT = 0;
  fstream inputStream;
  
  cout<<"Manhunt"<<endl;
  
  char board[MAP_SIZE][MAP_SIZE]; //creates the map 
  for ( int row = 0; row < MAP_SIZE; row+=1){
    for (int column = 0; column < MAP_SIZE; column+=1){
      board[row][column] = '_';
    }
  }
  
  startMenu();
  while ((start > 3) or (start < 1)){ //Only takes numbers 1-3
    cin >> start;
  }
  switch(start){
  case 1:
    char fileName[8];
    cout<<"What is the name of the file?:"<<endl;
    cin >> fileName;
    inputStream.open(fileName);
    inputStream >> xI;
    inputStream >> yI;
    inputStream >> xT;
    inputStream >> yT;
    
    inputStream.close();
    cout<<"Map loaded"<<endl;
    break;
  
  case 2: //Create a new map.
    cout<<"What is the X axis of the investigator?:"<<endl;
    cin >> xI;
    while ((xI >= 10) or (xI <= -1)){
      cin >> xI;
    }
    
    cout<<"What is the Y axis of the investigator?:"<<endl;
    cin >> yI;
    while ((yI >= 10) or (yI <= -1)){
      cin >> yI;
    }

    cout<<"What is the X axis of the target?:"<<endl;
    cin >> xT;
    while ((xT >= 10) or (xT <= -1)){
      cin >> xT;
    }

    cout<<"What is the X axis of the target?:"<<endl;
    cin >> yT;
    while ((yT >= 10) or (yT <= -1)){
      cin >> yT;
    }

    break;
  
  case 3://exits the program
    cout<<"Giving up so soon?"<<endl<<"Good-bye"<<endl;
    return 0;
  }
  board[xI][yI] = 'I';
  printMap(board);
  int direction = 1;
  
  while ((direction >=1 ) and (direction <= 5)){
    if ((xI == xT) && (yI == yT))//checks if the user wins.
      return 0;
    
    mainMenu();
    cin>>direction;
    
    while((direction > 6) or (direction < 1)){//keeps withing 1-6
      cin>>direction;
    }

    switch(direction){
    case 1: //moves north
      if (xI == 0){
	cout<<"Invalid"<<endl;
	break;
      }
      board[xI][yI] = '*';
      xI--;
      board[xI][yI] = 'I';
      break;
    case 2://moves east
      if (yI == 9){
        cout<<"Invalid"<<endl;
        break;
      }
      board[xI][yI] = '*';
      yI++;
      board[xI][yI] = 'I';
      break;
    case 3://moves south
      if (xI == 9){
        cout<<"Invalid"<<endl;
        break;
      }
      board[xI][yI] = '*';
      xI++;
      board[xI][yI] = 'I';
      break;
    case 4://moves west
      if (yI == 0){
        cout<<"Invalid"<<endl;
        break;
      }
      board[xI][yI] = '*';
      yI--;
      board[xI][yI] = 'I';
      break;
    case 5:
      printMap(board);
      cout<<endl;
      break;
    case 6:
      cout<<"Giving up so soon?"<<endl<<"Goodbye"<<endl;
      return 0;
      break;
    default:
      cout<<"That is not right."<<endl;
      break;
      
  }
    if ((xI == xT) && (yI == yT)){//checks if they win
      cout<<"You found the target"<<endl;
      return 0;
    }
    else{//if they don't win then it keeps looping through
      directionCheck(xI, yI, xT, yT);
      checkSearch(xI, yI);
      printMap(board);
    }
  }
}

void startMenu(){
  cout<<"What would you like to do?"<<endl;
  cout<<"1. Load a simulation form file"<<endl;
  cout<<"2. Start a new simulation"<<endl;
  cout<<"3. Exit"<<endl;
  cout<<"Enter 1 -3:"<<endl;
}

void mainMenu(){
    cout<<"What would you like to do?"<<endl;
    cout<<"1. Search North"<<endl;
    cout<<"2. Search East"<<endl;
    cout<<"3. Search South"<<endl;
    cout<<"4. Search West"<<endl;
    cout<<"5. See Map"<<endl;
    cout<<"6. Exit"<<endl;
    cout<<"Enter 1-6:"<<endl;
}

void printMap(char board[][MAP_SIZE]){

  for (int row = 0; row < MAP_SIZE; row+=1){
    for (int column = 0; column < MAP_SIZE; column +=1){
      cout<<board[row][column]<<" ";
      }
    cout<<endl;
  }
}

int checkSearch(int positionX, int positionY){
  if ((positionX > 9) or (positionX < 0)){
    cout<<"WRONG WAY"<<endl;
    return 0;
  }
  if ((positionY > 9) or (positionY < 0)){
    cout<<"WRONG WAY"<<endl;
    return 0; 
  }
  return 0;
}


void directionCheck(int positionX, int positionY, int targetX, int targetY){
  
  if (targetY > positionY)
    cout<<"Try searching East"<<endl;

  else if(targetY < positionY)
    cout<<"Try searching West"<<endl;

  else if(targetX > positionX)
    cout<<"Try searching South"<<endl;

  else if(targetX < positionX)
    cout<<"Try searching North"<<endl;

  else
    cout<<"You found the target."<<endl;
}
