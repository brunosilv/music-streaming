package edu.ufp.inf.aed2.project1;
import edu.princeton.cs.algs4.*;

/*************************************************************************
 *  Compilation:  javac SymbolDigraph.java
 *  Execution:    java SymbolDigraph
 *  Dependencies: ST.java Digraph.java In.java
 *  
 *  %  java SymbolDigraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  ATL
 *     HOU
 *     MCO
 *  LAX
 *
 *************************************************************************/

/**
 *  The <tt>SymbolWeightedDigraph</tt> class represents a digraph, where the
 *  vertex names are arbitrary strings.
 *  By providing mappings between string vertex names and integers,
 *  it serves as a wrapper around the
 *  {@link Digraph} data type, which assumes the vertex names are integers
 *  between 0 and <em>V</em> - 1.
 *  It also supports initializing a symbol digraph from a file.
 *  <p>
 *  This implementation uses an {@link ST} to map from strings to integers,
 *  an array to map from integers to strings, and a {@link Digraph} to store
 *  the underlying graph.
 *  The <em>index</em> and <em>contains</em> operations take time 
 *  proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 *  The <em>name</em> operation takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SymbolWeightedDigraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private EdgeWeightedDigraph G;

    /**  
     * Initializes a digraph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolWeightedDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length-1; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an aray
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the digraph by connecting first vertex on each
        // line to all others
        G = new EdgeWeightedDigraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length-1; i++) {
                int w = st.get(a[i]);
                double weight = Double.parseDouble(a[i+1]);
                G.addEdge(new DirectedEdge(v, w, weight));
            }
        }
    }


    public boolean contains(String s) {
        return st.contains(s);
    }


    public int index(String s) {
        return st.get(s);
    }


    public String name(int v) {
        return keys[v];
    }

    public EdgeWeightedDigraph G() {
        return G;
    }

    public ST<String, Integer> getSt() {
        return st;
    }

    public void setSt(ST<String, Integer> st) {
        this.st = st;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public void putSymbol(String val) {
        this.st.put(val, this.st.size());
        
        keys = new String[st.size()+1];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        
        keys[this.st.size()] = val;
    }
    
    /**
     * Remove from Mapping in the SymbolGraph
     * 
     * @param val
     */
    public void removeSymbol(String val) {
        if(this.st.contains(val)) {
            this.st.delete(val);
        }
    }
    
    public void addEdge(String s1, String s2, double weight) throws Exception {
        if(!contains(s1) || !contains(s2)) {
            throw new Exception("SymbolWeightedDigraph(): One of the username is not in ST, add it first with putSymbol()");
        }
        
        int i1,i2;
        i1 = index(s1);
        i2 = index(s2);
    
        // Fazer um copia do Grafo antigo e colocar no novo.
        EdgeWeightedDigraph g_newpointer = new EdgeWeightedDigraph(st.size());
        for (DirectedEdge g : G.edges()) {
            DirectedEdge e = new DirectedEdge(g.from(),g.to(),g.weight());
            g_newpointer.addEdge(e);
        }
        
        // Adicionar o novo Edge
        g_newpointer.addEdge(new DirectedEdge(i1,i2,weight));
        G = g_newpointer;
    }
   

    public void printSymbolDiagraph() {
        for (int i = 0; i < st.size(); i++) {    
            String username = keys[i];
            int id = st.get(username);
            System.out.println(id + " > " + username);
        }
    }
}


/*************************************************************************
 *  Copyright 2002-2012, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4-package.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4-package.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4-package.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with algs4-package.jar.  If not, see http://www.gnu.org/licenses.
 *************************************************************************/

