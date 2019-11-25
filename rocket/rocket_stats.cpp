#include <iostream>
#include <fstream>
using namespace std;

int main() {
  ofstream outfile("hw1.txt");
  

  //const float LENGTH = 0.311;
  //const float DIAMETER = 0.0241;
  const float BODY_AREA = 0.000506;
  const float CD_BODY = 0.45;
  const float FIN_AREA =0.00496;
  const float CD_FIN = 0.01;
  const float MASS_G = 0.0340;
  const float ENGINE_MASS= 0.0242;
  //const float FENGINE_MASS = 0.0094;
  const float RHO = 1.293;
  const float GRAVITY = 9.80665;


  double t = 0;   
  double s = 0;   
  double v = 0;  
  double a = 0;
  double m = 0;
  double F = 0;
  
  
  double dt = 0.1;
  double ft = 0;

  
  m = ENGINE_MASS + MASS_G;
  
  while (v>=0){
    //cout<<"TIME: "<<t<<endl;

    //force of drag on the body 
    float fd_body = (CD_BODY * RHO * BODY_AREA * (v*v ) )/2;
    //cout<<"FD_BODY: "<<fd_body<<endl;
    //Force of drag on the fin 
    float fd_fin = (CD_FIN * RHO * FIN_AREA * (v*v) ) /2;
    //Force of gravity 
    float fg = m * GRAVITY;
    
    //Determines the thrust 
    if (t == 0.0 || t >= 1.9){
       ft = 0;} 
    else if (t == 0.1){
      ft = 6;}
    else if(t == 0.2){
      ft = 14;}
    else if (t == 0.3){
      ft = 5.5;}
    else if (t >= 0.4 && t <= 0.7){
      ft = 5;}
    else if (t >= 0.8 && t <= 1.2){
      ft = 4.5;}
    else if (t >= 1.3 && t <= 1.6){
      ft =4.0;}
    else if (t >= 1.7 &&t <= 1.8){
      ft = 3.3;}

    //Total force 
    F = ft - (fd_body + fd_fin + fg);
    //F = 6 at time 0
    if (t == 0)
      F = 6.0;

    //Acceleration 
    a = F/m;
    //Velocity 
    v = v + (a * dt); 
    //Stops if velocity becomes negative
    if (v<=0){
      break;}

    //new postion of the rocket 
    s = s + (v * dt);
    
    //new mass
    m = m - (ft * .0001644);
      
    cout<<"t:"<<t<<" s:"<<s<<" v:"<<v<<" a "<<a<<" m:"<<m<<" ft:"<<ft<<endl;
    outfile<<"t:"<<t<<" s:"<<s<<" v:"<<v<<" a "<<a<<" m:"<<m<<" ft:"<<ft<<endl;

    //Update time
    t = t + dt;

  }
  cout<<"MAX HEIGHT: "<<s<<endl;
  outfile<<"MAX HEIGHT: "<<s<<endl;
  return 0;
}
