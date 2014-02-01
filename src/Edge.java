/*
 * Edge.java
 * Igor Podsechin
 * The class which defines and holds an edge
 */

public class Edge {

    int vertex;
    int weight;

    public Edge(int v, int w) {
        vertex = v;
        weight = w;
    }

    public int getWeight() {
        return weight;
    }

    public int getVertex() {
        return vertex;
    }
}