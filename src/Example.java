/*
 * Example.java
 * Igor Podsechin
 * In this example a minimum spanning tree is calculated for a simple graph
 * using Prim's algorithm.
 */

class Example {

    public static void main(String[] args) {
        System.out.println("Constructing graph...");
        Graph g = new Graph();
        g.readInput();
        
        System.out.println("Calculating minimum spanning tree...");
        PrimsAlgorithm prim = new PrimsAlgorithm(g);
        prim.printMST();

    }
}