package GraphFramework;

import java.util.PriorityQueue;

public class MHPrimAlg extends MSTAlgorithm {
	
//data field
private int Cost= 0;

//constructor
public MHPrimAlg (Graph graph) {
    this.graph= graph;
    MSTResultList= new Edge[graph.verticesNO]; }	


//--------------------------------------------------------------------------------
//method of prim algorthim
public void MHPrim() {
    //start with vertex at index'0'
    Vertex CurrentVertex= graph.Vertices[0]; 

    //we choose priority queue because the default PriorityQueue is implemented with Min-Heap
    //ceate a priority queue to store edges weights      
     PriorityQueue<Edge> minHeapP= new PriorityQueue<Edge>();

    //loop through all vertices except the first one
    for (int i = 0; i < MSTResultList.length - 1; i++) {
        // Add all adjacent edges of the current vertex to the priority queue

         for (Edge edge : CurrentVertex.AdjList) {
                edge.Source.isVisited = true; // Mark the source vertex as visited
                 if (!edge.Target.isVisited) minHeapP.add(edge); // Add the edge to the priority queue
         }

    // Find the minimum-weight edge from the priority queue
     while (!minHeapP.isEmpty()) {
        Edge minEdge = minHeapP.remove();
        if (!minEdge.Target.isVisited) {
            //++++++++++++++++++++++++++++
            minEdge.Target.isVisited = true; 
            // Add the minimum-weight edge to the MST list
            MSTResultList[i] = minEdge;

            // Update the cost of the MST
             Cost += minEdge.weight;

             // Mark the target vertex as visited and set it as the new current vertex
            // minEdge.target.isVisited = true;
            CurrentVertex = minEdge.Target;

        // Exit the loop after adding one result to the MST
        break;}
     }
    }


}

public void displayResultingMST() {

   for(int i=0; i<MSTResultList.length-1; i++) {
          Vertex vf = MSTResultList[i].Source;
          vf.displayInfo();
          System.out.print(" - ");
          Vertex vs = MSTResultList[i].Target;
          vs.displayInfo();

          Edge e = MSTResultList[i];
          e.displayInfo();
          System.out.println();  
   }

}


//--------------------------------------------------------------------------------
//method to display the cost that compute during the displayResultingMST method
public void displayMSTcost(){ System.out.println("The cost of designed phone network: "+ this.Cost); } 


} 