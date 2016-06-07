package edu.ufp.inf.aed2.project1;
import edu.princeton.cs.algs4.*;

/*************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/routes.txt
 *                http://algs4.cs.princeton.edu/41undirected/movies.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviestiny.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviesG.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviestopGrossing.txt
 *  
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 * 
 *  Assumes that input file is encoded using UTF-8.
 *  % iconv -f ISO-8859-1 -t UTF-8 movies-iso8859.txt > movies.txt
 *
 *************************************************************************/

/**
 *  The <tt>SymbolGraph</tt> class represents an undirected graph, where the
 *  vertex names are arbitrary strings.
 *  By providing mappings between string vertex names and integers,
 *  it serves as a wrapper around the
 *  {@link Graph} data type, which assumes the vertex names are integers
 *  between 0 and <em>V</em> - 1.
 *  It also supports initializing a symbol graph from a file.
 *  <p>
 *  This implementation uses an {@link ST} to map from strings to integers,
 *  an array to map from integers to strings, and a {@link Graph} to store
 *  the underlying graph.
 *  The <em>index</em> and <em>contains</em> operations take time 
 *  proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 *  The <em>name</em> operation takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41undirected">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SymbolWeightedGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private EdgeWeightedGraph G;

    /**  
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolWeightedGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
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

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        G = new EdgeWeightedGraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length-1; i++) {
                int w = st.get(a[i]);
                double weight = Double.parseDouble(a[i+1]);
                G.addEdge(new Edge(v, w, weight));
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


    public EdgeWeightedGraph G() {
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

    public void addEdge(String s1, String s2, double weight) throws Exception {
        if(!contains(s1) || !contains(s2)) {
            throw new Exception("SymbolWeightedGraph(): One of the username is not in ST, add it first with putSymbol()");
        }
        
        int i1,i2;
        i1 = index(s1);
        i2 = index(s2);
    
        // Fazes copia do grafo antigo e cria novo
        EdgeWeightedGraph g_npoint = new EdgeWeightedGraph(st.size());
        for (Edge g : G.edges()) {
            Edge e = new Edge(g.either(),g.other(g.either()),g.weight());
            g_npoint.addEdge(e);
        }
        
        g_npoint.addEdge(new Edge(i1,i2,weight));
        G = g_npoint;
    }
    

    public void printSymbolGraph() {
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

