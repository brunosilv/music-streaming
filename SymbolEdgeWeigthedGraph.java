
package edu.ufp.inf.aed2.project1;
import edu.princeton.cs.algs4.*;


public class SymbolEdgeWeigthedGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private EdgeWeightedGraph G;

  
    public SymbolEdgeWeigthedGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        In in = new In(filename);
        
    while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            int i =0;
            if(!st.contains(a[i]))
                st.put(a[i], st.size());
            
            for ( i = 1; i < a.length; i=i+2) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
        StdOut.println("Done reading " + filename);

        
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        System.out.println(st.size());
        G = new EdgeWeightedGraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int u1 = st.get(a[0]);
            int u2 = st.get(a[1]);
            double weight = Double.parseDouble(a[2]);
            G.addEdge(new Edge(u1, u2, weight));
        }
    }

      public int musicasEmComum(Utilizador u1, Utilizador u2) {
 
        return 0;
    }
    /**
     * Does the graph contain the vertex named <tt>s</tt>?
     * @param s the name of a vertex
     * @return <tt>true</tt> if <tt>s</tt> is the name of a vertex, and <tt>false</tt> otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named <tt>s</tt>.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named <tt>s</tt>
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer <tt>v</tt>.
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer <tt>v</tt>
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public EdgeWeightedGraph G() {
        return G;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : G.edges()){
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    
    

    /**
     * Unit tests the <tt>SymbolGraph</tt> data type.
     */
    /*
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolEdgeWeigthedGraph sg = new SymbolEdgeWeigthedGraph(filename, delimiter);
        Graph G = sg.G();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : G.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }*/
}
