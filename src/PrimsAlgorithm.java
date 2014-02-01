/*
 * PrimsAlgorithm.java
 * Igor Podsechin
 */

public class PrimsAlgorithm {

    Graph T;
    //number of vertices
    int n;
    //This array contains the vertices that have been added
    int[] addedVertexIndex;
    //We use this variable to count how many vertices have been added
    int iterator = 0;

    public PrimsAlgorithm(Graph g) {
        Edge e = new Edge(0, 0);
        int smallestWeight = Integer.MAX_VALUE;
        int indexSmallest = -1;
        //The size of the array is the same as in the order of the original graph
        addedVertexIndex = new int[g.getOrder()];
        //The graph where the MST will be stored
        T = new Graph();
        //Add the first vertex into the array, could be any vertex
        addedVertexIndex[0] = 1;
        //Loop n-1 times (just as many as there are edges in the minimum spanning tree)
        while (iterator++ < addedVertexIndex.length - 1) {
            //Loop through the already added vertices and see which one has smallest edge
            for (int j = 0; j < iterator; j++) {
                e = g.getVertex(addedVertexIndex[j]).getSmallestFreeEdge(addedVertexIndex,
                        iterator);
                //Select the vertex which has the smallest weight
                if (e != null && e.getWeight() <= smallestWeight) {
                    smallestWeight = e.getWeight();
                    indexSmallest = j;
                }
            }
            //Add the edge to T
            e = g.getVertex(addedVertexIndex[indexSmallest]).getSmallestFreeEdge(addedVertexIndex,
                    iterator);
            T.addVertices(addedVertexIndex[indexSmallest], e.getVertex(), e.getWeight());
            addedVertexIndex[iterator] = e.getVertex();
            indexSmallest = -1;
            smallestWeight = Integer.MAX_VALUE;
        }
    }

    public void printMST() {
        T.printGraph();
    }
}
