package GraphFramework;

public class Edge implements Comparable<Edge>{

//data fields
Vertex Parent; 
Vertex Target;
Vertex Source;
int weight;

//constructors 
public Edge(){ 
    Parent= new Vertex();
    Target= new Vertex();
    Source= new Vertex();      
}

public Edge(Vertex Source ,Vertex Target ,int weight){
    Parent= new Vertex();
    this.Target= Target;
    this.Source= Source;
    this.weight= weight;        
}   


//--------------------------------------------------
//overriding compareTO method
@Override
public int compareTo(Edge W){
    if(this.weight == W.weight)
        return 0;
    else if (this.weight > W.weight)
        return 1;
    else return -1;     
}

 //DisplayInfo method for edges info
    public void displayInfo(){}

}


