/*
 * KruskalsAlgorithm.java
 * Igor Podsechin
 */

public class KruskalsAlgorithm {

    Graph graph;
    //MST graph
    Graph T;
    //Contains all of g's edges
    UnlinkedEdge[] edges;
    int iterator = 0;
    int verticesAdded = 0;
    boolean connected = false;
    //Array which is used for checking if vertices are connected
    boolean[] visited;

    public KruskalsAlgorithm(Graph g) {
        graph = g;
        T = new Graph();
        edges = g.getUnlinkedEdgeArray();
        System.out.println("WORKGIN");
        sortEdges();
        visited = new boolean[g.getOrder() + 1];
        //Loop through the edges, starting from the smallest one, until there is n-1 of them added to T
        while (verticesAdded < g.getOrder() - 1) {
            //Clear the visited array
            for (int x = 0; x < visited.length; x++) {
                visited[x] = false;
            }
            //Check if the two vertices are connected, if no add to T
            checkIfConnected(edges[iterator].getVertice1(), edges[iterator].getVertice2());
            if (connected == false) {
                T.addVertices(edges[iterator].getVertice1(), edges[iterator].getVertice2(),
                        edges[iterator].getWeight());
                verticesAdded++;
            }
            connected = false;
            iterator++;
        }
    }
    
    //Sorts the edges array based on weights using bubblesort
    private void sortEdges() {
        boolean swapped = false;
        UnlinkedEdge temp;
        do {
            swapped = false;
            for (int i = 0; i < edges.length - 1; i++) {
                if (edges[i].getWeight() > edges[i + 1].getWeight()) {
                    temp = edges[i];
                    edges[i] = edges[i + 1];
                    edges[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    //Recursive function which determines if vertices v1 and v2 are connected
    private void checkIfConnected(int v1, int v2) {
        visited[v1] = true;
        if (v1 == v2) {
            connected = true;
        }
        if (T.getVertex(v1).getNumberOfEdges() > 0) {
            //Loop through edges
            for (int i = 0; i < T.getVertex(v1).getNumberOfEdges(); i++) {
                if (visited[T.getVertex(v1).getEdge(i).getVertex()] == false) {
                    checkIfConnected(T.getVertex(v1).getEdge(i).getVertex(), v2);
                }
            }
        }
    }

    public void printMST() {
        T.printGraph();
    }
}
