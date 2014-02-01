/*
 * BoruvkasAlgorithm.java
 * Igor Podsechin
 */

import java.util.*;

public class BoruvkasAlgorithm {    
    //MST graph
    Graph T;
    int edgeIterator = 0;
    UnlinkedEdge[] edges;
    //Holds the subgraphs that are generated
    Vector[] subgraph;
    int subIterator;
    //Used for deapth first search
    boolean[] visited;
    int visitedIterator = 0;

    public BoruvkasAlgorithm(Graph g) {
        Edge e = new Edge(0, 0);
        T = new Graph();
        edges = new UnlinkedEdge[g.getNumOfEdges()];
        subgraph = new Vector[g.getOrder()];
        visited = new boolean[g.getOrder() + 1];
        //Find smallest edge for each vertice and add it to T
        for (int i = 1; i < g.getOrder() + 1; i++) {
            if (notAlreadyPartOfT(i, g.getVertex(i).getSmallestEdge().getVertex())) {
                T.addVertices(i, g.getVertex(i).getSmallestEdge().getVertex(),
                        g.getVertex(i).getSmallestEdge().getWeight());
            }
        }
        //The loop which iterates until one subgraph is present
        while (true) {
            subIterator = 0;
            //Sort the vertices into subgraphs,
            for (int j = 1; j < visited.length; j++) {
                if (visited[j] == false) {
                    subgraph[subIterator] = new Vector(1);
                    deapthFirstSearch(j);
                    subIterator++;
                }
            }
            System.out.println(subIterator);
            //End loop when all subgraphs are joined
            if (subIterator == 1) {
                break;
            }
            //Now find the shortest edge for each subgraph, so loop through subgraphs
            for (int x = 0; x < subIterator; x++) {
                int[] subArray = new int[subgraph[x].size()];
                int smallestWeight = Integer.MAX_VALUE;
                int indexSmallest = -1;
                //Make an array out of the subgraph vector, since getSmallestFreeEdge() function accepts only arrays
                for (int i = 0; i < subgraph[x].size(); i++) {
                    Integer temp = (Integer) subgraph[x].elementAt(i);
                    subArray[i] = temp.intValue();
                }
                //loop through edges of subgraph and find least heavy edge that goes out of subgraph
                for (int y = 0; y < subArray.length; y++) {
                    e = g.getVertex(subArray[y]).getSmallestFreeEdge(subArray, subArray.length);
                    if (e != null && e.getWeight() <= smallestWeight) {
                        smallestWeight = e.getWeight();
                        indexSmallest = y;
                    }
                }
                //Add the smallest Edge to T, if it hasn't already been added
                e = g.getVertex(subArray[indexSmallest]).getSmallestFreeEdge(subArray,
                        subArray.length);
                if (notAlreadyPartOfT(subArray[indexSmallest], e.getVertex())) {
                    T.addVertices(subArray[indexSmallest], e.getVertex(), e.getWeight());
                }
                smallestWeight = Integer.MAX_VALUE;
                indexSmallest = -1;
                resetVisitedArray();
            }
        }
    }
    
    //Clear the visited array
    private void resetVisitedArray() {
        for (int z = 0; z < visited.length; z++) {
            visited[z] = false;
        }
    }

    private boolean notAlreadyPartOfT(int v1, int v2) {
        for (int x = 0; x < edgeIterator; x++) {
            if ((edges[x].getVertice1() == v1 && edges[x].getVertice2() == v2)
                    || (edges[x].getVertice1() == v2 && edges[x].getVertice2() == v1)) {
                return false;
            }
        }
        edges[edgeIterator] = new UnlinkedEdge(v1, v2);
        edgeIterator++;
        return true;
    }
    
    //Recursive function which looks for subgraphs
    private void deapthFirstSearch(int num) {
        subgraph[subIterator].add(new Integer(num));
        visited[num] = true;
        if (T.getVertex(num).getNumberOfEdges() > 0) {
            //Loop through edges
            for (int i = 0; i < T.getVertex(num).getNumberOfEdges(); i++) {
                if (visited[T.getVertex(num).getEdge(i).getVertex()] == false) {
                    deapthFirstSearch(T.getVertex(num).getEdge(i).getVertex());
                }
            }
        }
    }

    public void printMST() {
        T.printGraph();
    }
}