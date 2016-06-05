
package edu.ufp.inf.aed2.project1;
import edu.princeton.cs.algs4.*;


public class FriendShipsGraph {
    private final ST<String, Integer> st_user;
    private final ST<String, Integer> st;  // string -> index
    private final String[] keys;           // index  -> string
    private final EdgeWeightedGraph G;

  
    public FriendShipsGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        st_user = new ST<>();
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
        //StdOut.println("Done reading " + filename);

        
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        //System.out.println(st.size());
        System.out.println("\n-------------//-------------\n");
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

      public double musicasComuns(Utilizador u1, Utilizador u2) {
        int count = 0;
        for (String m1 : u1.getHistoricoST().keys()) {
            String music1 = u1.getHistoricoST().get(m1);
            for (String m2 : u2.getHistoricoST().keys()) {
                String music2 = u2.getHistoricoST().get(m2);
                if(music1.equals(music2)){
                    count++;
                }
            }
        }
        return count;
    }
      
    public  Iterable<Edge> SPBetweenUsers(String u1, String u2){
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(G, st_user.get(u1));
        return sp.pathTo(st_user.get(u2));
    }
    
    public void  printSP(String u1, String u2){
        Iterable<Edge> p = SPBetweenUsers(u1, u2);
        System.out.println("Caminho mais curto:");
        for(Edge e : p){
            System.out.println(e.either() + " -> " + e.other(e.either()));
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : G.edges()){
            sb.append(this.name(e.either())).append(" : ").append(this.name(e.other(e.either()))).append(" -> ").append(e.weight()).append("\n");
        }
        return sb.toString();
    }

}
