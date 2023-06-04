package PhoneNetworkApp;


import GraphFramework.Vertex;

public class Office extends Vertex{
//data field
private String OfficeName;

//constructor 
public Office(int label){
    super( Integer.toString(label) );
    this.OfficeName= String.valueOf( (char)(label + 65) );
}


//--------------------------------------------------
//overriding DisplayInfo method
@Override
public void displayInfo(){ System.out.print("Office No."+ OfficeName); } 
 
    
}
