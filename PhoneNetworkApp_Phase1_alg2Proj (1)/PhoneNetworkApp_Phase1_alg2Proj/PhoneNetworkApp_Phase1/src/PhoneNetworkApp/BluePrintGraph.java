package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

public class BluePrintGraph extends Graph {
    
//constructors
public BluePrintGraph(){}

public BluePrintGraph(int verticesNO ,int edgeNO ,boolean isDigraph){ super(verticesNO ,edgeNO ,isDigraph); }


//--------------------------------------------------
//overriding createVertex & createEdge methods
@Override
public Vertex createVertex(int label){ return new Office(label); }

@Override
public Edge createEdge(Vertex Source ,Vertex Target ,int weight){ return new Line(Source ,Target ,weight); }

}
