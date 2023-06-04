package GraphFramework;

import java.util.LinkedList;

public class Vertex {

//data fields
LinkedList<Edge>AdjList;
Boolean isVisited;
String label;


//constructors
public Vertex(){ AdjList= new LinkedList<Edge>(); }    

public Vertex(String label){
    AdjList= new LinkedList<Edge>();
    this.isVisited= false;
    this.label= label;
}


//--------------------------------------------------
//DisplayInfo method 
public void displayInfo(){}

} 

