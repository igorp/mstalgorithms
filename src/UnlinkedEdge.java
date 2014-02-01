/*
 * UnlinkedEdge.java
 * Igor Podsechin
 * This class is needed in Kruskal's and Boruvka's algorithms
 */

public class UnlinkedEdge {

    int v1;
    int v2;
    int w;

    public UnlinkedEdge(int vertex1, int vertex2) {
        v1 = vertex1;
        v2 = vertex2;
    }

    public UnlinkedEdge(int vertex1, int vertex2, int weight) {
        v1 = vertex1;
        v2 = vertex2;
        w = weight;
    }

    public int getWeight() {
        return w;
    }

    public void printEdge() {
        System.out.println("w:" + w + " v:" + v1 + " v:" + v2);
    }

    public int getVertice1() {
        return v1;
    }

    public int getVertice2() {
        return v2;
    }
}