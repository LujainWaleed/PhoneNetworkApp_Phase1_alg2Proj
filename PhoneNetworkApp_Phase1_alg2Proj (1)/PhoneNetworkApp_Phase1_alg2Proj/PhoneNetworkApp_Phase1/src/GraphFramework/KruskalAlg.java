package GraphFramework;



import java.util.ArrayList;
import java.util.Collections;


public class KruskalAlg extends MSTAlgorithm {
    //data field
    private int Cost = 0;
		
//constructor
public KruskalAlg(Graph graph) {
    this.graph= graph;
    MSTResultList= new Edge[graph.verticesNO]; }
	
	
//--------------------------------------------------
//kruskal method
public void kruskal() {

    MSTResultList= new Edge[graph.verticesNO];
    Vertex vv; // Vertex source
    Vertex vu; // Vertex target
    Edge edge; // Vertex edge
    ArrayList<Edge> edges = new ArrayList<Edge>(); // PriorityQueue to store edges weights                
    // Loop through ALL vertices
    for (int i = 0; i < graph.verticesNO; i++) {
        vv = graph.Vertices[i];
        // Loop through adjacent list of this vertex
        for (int j = 0; j < vv.AdjList.size(); j++)  edges.add(vv.AdjList.get(j));         
    }

    // Sort all edges in non-decreasing order 
    Collections.sort(edges);

    // Make set for each vertex
    Vertex[] quickFindDS = new Vertex[graph.verticesNO]; // Set the DS as the number of vertices 
    makeSet(quickFindDS); 
    int encounter = 0; 

    // Loop through all edges
    while (encounter < MSTResultList.length - 1) {

        // Get minimum-weight edge and its source and target
        edge = edges.remove(0);
        vv = edge.Source;
        vu = edge.Target;

        // Find representative subset from the QuickFind disjoint sets
        if (!findSet(Integer.parseInt(quickFindDS[Integer.parseInt(vv.label)].label), 
                      Integer.parseInt(quickFindDS[Integer.parseInt(vu.label)].label))) {

            // Append VT to VU and update their representative value
            union(quickFindDS, vv, vu);            

            MSTResultList[encounter] = edge; // Add the target edge to the MST list
            Cost += MSTResultList[encounter].weight; // Get cost of minimum-weight edges (MST)

            encounter++; // Increment number of edges encountered
        } 
    } 

}


//--------------------------------------------------------------------------------
public void makeSet(Vertex[] quickFindDS) {
   /* Loop through the number of vertices:
   - Create a new vertex for each index in the array
   - Each vertex is initially in its own set
   */
   for (int i = 0; i < quickFindDS.length; i++) {
       Vertex vn = new Vertex(Integer.toString(i));
       quickFindDS[i] = vn;
   }
} // End of makeSet Method


//--------------------------------------------------------------------------------
public boolean findSet(int v1, int v2){ return v1 == v2; } 


//--------------------------------------------------------------------------------
public void union(Vertex[] quickFindDS, Vertex vv, Vertex vu) {	
    int vvRepresentative = Integer.parseInt(quickFindDS[Integer.parseInt(vv.label)].label); // Get VV representative 
    int vuRepresentative = Integer.parseInt(quickFindDS[Integer.parseInt(vu.label)].label); // Get VU representative

    boolean vvNoRepresentative = findSet(Integer.parseInt(vv.label), vvRepresentative); // Find if VV has a representative or not
    boolean vuNoRepresentative = findSet(Integer.parseInt(vu.label), vuRepresentative); // Find if VU has a representative or not

    // Check if current VV and VU are representative of a set 
    for (int i = 0; i < quickFindDS.length; i++) {

        // Check if VV is a representative of another vertex (excluding its own representative)
        if (vvRepresentative == Integer.parseInt(quickFindDS[i].label) && (i != Integer.parseInt(vv.label))) 
            vvNoRepresentative = false; // Set to false when VV has another vertex as its representative      

        // Check if VU is a representative of another vertex (excluding its own representative)
        if (vuRepresentative == Integer.parseInt(quickFindDS[i].label) && (i != Integer.parseInt(vu.label))) 
            vuNoRepresentative = false; // Set to false when VU has another vertex as its representative
    }

    // If VV has a representative and VU has no representative OR both VV and VU have no representative
    if (((!vvNoRepresentative) && (vuNoRepresentative)) || (vvNoRepresentative && vuNoRepresentative)) {

        // Make VV the new representative
        quickFindDS[Integer.parseInt(vv.label)] = quickFindDS[Integer.parseInt(vv.label)];
        quickFindDS[Integer.parseInt(vu.label)] = quickFindDS[Integer.parseInt(vv.label)];
    } 

    // If VV has no representative and VU has a representative
    else if (vvNoRepresentative && (!vuNoRepresentative)) 
        quickFindDS[Integer.parseInt(vv.label)] = quickFindDS[Integer.parseInt(vu.label)];
   

    // If both VV and VU have a representative
    else {
        int maxRepresentative = Math.max(vvRepresentative, vuRepresentative); // Get the maximum representative to overwrite its children
        int minRepresentative = Math.min(vvRepresentative, vuRepresentative); // Get the minimum to set it as the new representative

        // Loop through the QuickFind Disjoint Subset
        for (int i = 0; i < quickFindDS.length; i++) {

            // Find all the children of the max representative
            if (Integer.parseInt(quickFindDS[i].label) == maxRepresentative) 
                quickFindDS[i] = quickFindDS[minRepresentative]; // Update all representatives to the minimum representative
        }
    } 
    
}


//--------------------------------------------------------------------------------
public void displayResultingMST() {
   for(int i=0; i<MSTResultList.length-1; i++) {
          Vertex vf =  MSTResultList[i].Source;
          vf.displayInfo();
          System.out.print(" - ");
          Vertex vs = MSTResultList[i].Target;
          vs.displayInfo();
          Edge e = MSTResultList[i];
          e.displayInfo();System.out.println();  
   }


}


//--------------------------------------------------------------------------------
//method to display the cost that compute during the displayResultingMST method
public void displayMSTcost(){ System.out.println("The cost of designed phone network: " + this.Cost); }


}
