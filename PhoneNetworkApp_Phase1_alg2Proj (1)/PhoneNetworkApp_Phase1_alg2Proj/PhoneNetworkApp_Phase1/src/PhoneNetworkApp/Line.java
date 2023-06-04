package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

public class Line extends Edge{
//data field
private int LLength;

//constructor
public Line(Vertex Source ,Vertex Target ,int Weight){
    super(Source ,Target ,Weight);
    this.LLength= Weight*5;
} 

//--------------------------------------------------
//overriding DisplayInfo method
@Override
public void displayInfo(){ System.out.print(" : line length: "+ LLength); } 

}


