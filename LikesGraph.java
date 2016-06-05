package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.*;

public class LikesGraph {

    private final ST<String, Integer> st_user;  // string -> index
    private final String[] keysusers;              // index  -> string
    private final ST<String, Integer> st_musica;  // string -> index
    private final String[] keysmusics;           // index  -> string
    private int count = 0;
    private final EdgeWeightedGraph G;

    public LikesGraph(String filename, String delimiter) {
        st_user = new ST<>();
        st_musica = new ST<>();
        count = 0;
        In in = new In(filename);

        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (String a1 : a) {
                if (!st_user.contains(a1)) {
                    st_user.put(a1, count++);
                }
                for (int i = 1; i < a.length; i++) {
                    if (!st_musica.contains(a1)) {
                        st_musica.put(a[i], count++);
                    }
                }
            }
        }
        keysusers = new String[count];
        for (String name : st_user.keys()) {
            keysusers[st_user.get(name)] = name;
        }

        keysmusics = new String[count];
        for (String name : st_musica.keys()) {
            keysmusics[st_musica.get(name)] = name;
        }
        
        System.out.println("\n-------------//-------------\n");
        G = new EdgeWeightedGraph(count);
        in = new In(filename);

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st_user.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st_musica.get(a[i]);

                G.addEdge(new Edge(v, w, 0));
            }
        }
    }

    public boolean contains(String s) {
        return st_user.contains(s);
    }

    public int index(String s) {
        return st_user.get(s);
    }

    public String name(int v) {
        if (keysusers[v] != null) {
            return keysusers[v];
        } else {
            return keysmusics[v];
        }

    }

    public EdgeWeightedGraph G() {
        return G;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : G.edges()) {
            sb.append(this.name(e.either())).append(" (y) ").append(this.name(e.other(e.either()))).append("\n");
        }
        return sb.toString();
    }

}
