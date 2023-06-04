package GraphFramework;

import java.util.ArrayList;
import java.util.Collections;



public class KruskalAlg extends MSTAlgorithm {
    // Data fields
    private int cost = 0;


    public KruskalAlg(Graph graph) {
         this.graph=graph;
         MSTresultList = new Edge[graph.verticesNO]; // MST List
    }
	
	
	
    public void kruskal() {
          MSTresultList = new Edge[graph.verticesNO]; // MST List
          Vertex vv; // Vertex source
          Vertex vu; // Vertex target
          Edge edge; // Vertex edge
          ArrayList<Edge> edges = new ArrayList<Edge>(); //PriorityQueue to store edges weights                
          // Loop through ALL vertices
          for(int i=0; i< graph.verticesNO; i++) {
               vv = graph.vertices[i];
               // Loop through adjacent list of this vertex
               for(int j=0; j<vv.adjList.size() ; j++) {
                   edges.add(vv.adjList.get(j));	
                } // end of inner for-loop
           } // end of outer for-loop
           // Sort All Edges in non-decreasing Order 
           Collections.sort(edges);

           // Make Set for Each Vertex
           Vertex[] quickFindDS = new Vertex[graph.verticesNO]; // Set the DS as the number of vertices 
           makeSet(quickFindDS); // Make set for each vertex
           int encounter = 0; 

           // Loop through ALL edges
           while(encounter < MSTresultList.length-1) {

                // Get Minimum-weight Edge & its source & target
                edge = edges.remove(0);
                vv = edge.source;
                vu = edge.target;

                // Find Representative Subset from the QuickFind Disjoint Sets
                if(!findSet(quickFindDS[vv.label].label, quickFindDS[vu.label].label)) {
                     // Append VT to VU & and update their representative value; 
                     union(quickFindDS, vv, vu);			
                     MSTresultList[encounter] = edge; 	 
                     cost += MSTresultList[encounter].weight; 
                     encounter++; 
                } // End of if
           } // End of the loop
     } // End of Method
	
	

    
    public void makeSet(Vertex[] quickFindDS) {
        /* Loop through the number of vertices:
           - Create a new vertex for each index in the array
           - Each vertex is initially in its own set
        */
        for (int i = 0; i < quickFindDS.length; i++) {
            Vertex vn = new Vertex(i);
            quickFindDS[i] = vn;
        }
     } // End of makeSet method
    
    
     public boolean findSet(int v1, int v2) {
            return v1 == v2;
     } // End of FindSet Method

     public void union(Vertex[] quickFindDS, Vertex vv, Vertex vu) {	
            int vvRepresentative = Integer.parseInt(quickFindDS[Integer.parseInt(vv.label)].label); // Get VV representative 
            int vuRepresentative = Integer.parseInt(quickFindDS[Integer.parseInt(vu.label)].label); // Get VU representative

            boolean vvNoRepresentative = findSet(Integer.parseInt(vv.label), vvRepresentative); // Find if VV has a representative or not
            boolean vuNoRepresentative = findSet(Integer.parseInt(vu.label), vuRepresentative); // Find if VU has a representative or not

            // Check if current VV and VU are representative of a set 
            for (int i = 0; i < quickFindDS.length; i++) {

                // Check if VV is a representative of another vertex (excluding its own representative)
                if (vvRepresentative == Integer.parseInt(quickFindDS[i].label) && (i != Integer.parseInt(vv.label))) {
                    vvNoRepresentative = false; // Set to false when VV has another vertex as its representative
                } // End of if-statement

                // Check if VU is a representative of another vertex (excluding its own representative)
                if (vuRepresentative == Integer.parseInt(quickFindDS[i].label) && (i != Integer.parseInt(vu.label))) {
                    vuNoRepresentative = false; // Set to false when VU has another vertex as its representative
                } // End of if-statement

            } // End of for-loop

            // If VV has a representative and VU has no representative OR both VV and VU have no representative
            if (((!vvNoRepresentative) && (vuNoRepresentative)) || (vvNoRepresentative && vuNoRepresentative)) {

                // Make VV the new representative
                quickFindDS[Integer.parseInt(vv.label)] = quickFindDS[Integer.parseInt(vv.label)];
                quickFindDS[Integer.parseInt(vu.label)] = quickFindDS[Integer.parseInt(vv.label)];
            } // End of if-statement

            // If VV has no representative and VU has a representative
            else if (vvNoRepresentative && (!vuNoRepresentative)) {
                quickFindDS[Integer.parseInt(vv.label)] = quickFindDS[Integer.parseInt(vu.label)];
            } // End of else-if

            // If both VV and VU have a representative
            else {

                int maxRepresentative = Math.max(vvRepresentative, vuRepresentative); // Get the maximum representative to overwrite its children
                int minRepresentative = Math.min(vvRepresentative, vuRepresentative); // Get the minimum to set it as the new representative

                // Loop through the QuickFind Disjoint Subset
                for (int i = 0; i < quickFindDS.length; i++) {

                    // Find all the children of the max representative
                    if (Integer.parseInt(quickFindDS[i].label) == maxRepresentative) {
                        quickFindDS[i] = quickFindDS[minRepresentative]; // Update all representatives to the minimum representative
                    } // End of if-statement

                } // End of for-loop
            } // End of else
        } // End of Union method
    
   public void displayResultingMST() {
           // Office No. A – Office No. A B :

           for(int i=0; i<MSTresultList.length-1; i++) {
                  Vertex vf =  MSTresultList[i].source;
                  vf.displayInfo(); 
                  System.out.print(" - ");
                  Vertex vs = MSTresultList[i].target;
                  vs.displayInfo(); 
                  Edge e = MSTresultList[i];
                  e.displayInfo(); System.out.println();  
           }


   }
    
	
    
    public void displayMSTcost() {
        System.out.println("\nThe cost of designed phone network: " + this.cost);
    }

        
} // End of Class
