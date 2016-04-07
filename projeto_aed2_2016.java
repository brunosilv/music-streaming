/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Bruno Silva
 */
public class projeto_aed2_2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
         * St's Principais 
         */
        // red-black trees
        RedBlackBST_Projecto<String, Genero> generosST = new RedBlackBST_Projecto<>();
        RedBlackBST_Projecto<String, Musica> musicasST = new RedBlackBST_Projecto<>();
        RedBlackBST_Projecto<String, Playlist> playlistsST = new RedBlackBST_Projecto<>();

        //hashing trees
        SeparateChainingHashST1<String, Artista> artistasST = new SeparateChainingHashST1<>();
        SeparateChainingHashST1<String, Utilizador> utilizadoresST = new SeparateChainingHashST1<>();
        /*
         * Inicialização das St's
         */
        loadFromFileGenerosST(generosST, ".//data//generos.txt");
        loadFromFileArtistasST(artistasST, ".//data//artista.txt");
        loadFromFileUtilizadoresST(utilizadoresST, ".//data//pessoas.txt");
        loadFromFileMusicasST(musicasST, generosST, artistasST, playlistsST, utilizadoresST, ".//data//musicas.txt");
        loadFromFilePlaylistST(playlistsST, utilizadoresST, musicasST, ".//data//playlists.txt");
        /* 
         *  Chamada dos Clientes 
         */
        //printMusicByGenres(generosST);
        //printMusicByArtist(artistasST);
        //printMusicByPlaylist(playlistsST);
        //printMusicByHistory(historyST);
        //createGenreSt(generosST);
        //readGenreSt(generosST);
        //updateGenreSt(generosST);
        //deleteGenreSt(generosST);
        //createArtistSt(artistasST);
        //readArtistSt(artistasST);
        //updateArtistSt(artistasST);
        //deleteArtistSt(artistasST);
        //createMusicSt(musicasST);
        //readMusicSt(musicasST);
        //updateMusicSt(musicasST);
        //deleteMusicSt(musicasST);
        //createUsersSt(utilizadoresST);
        //readUsersSt(utilizadoresST);
        //updateUsersSt(utilizadoresST);
        //deleteUsersSt(utilizadoresST);
    }

    public static void loadFromFilePlaylistST(RedBlackBST_Projecto<String, Playlist> playlistST, SeparateChainingHashST1<String, Utilizador> utilizadoresST, RedBlackBST_Projecto<String, Musica> musicasST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];

            Playlist p = new Playlist(nome);
            playlistST.put(nome, p);

            Utilizador u = utilizadoresST.get(username);
            u.getUserplSt().put(nome, p);
            for (int i = 1; i < texto.length; i++) {
                if (musicasST.contains(texto[i])) {
                    playlistST.get(nome).musica(musicasST.get(texto[i]));
                }
            }

        }
    }

    public static void loadFromFileGenerosST(RedBlackBST_Projecto<String, Genero> generoST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String genero = texto[0];
            String descricao = texto[1];

            Genero g = new Genero(genero, descricao);
            generoST.put(genero, g);
        }
    }

    public static void loadFromFileMusicasST(RedBlackBST_Projecto<String, Musica> musicaST, RedBlackBST_Projecto<String, Genero> generoST, SeparateChainingHashST1<String, Artista> artistaST, RedBlackBST_Projecto<String, Playlist> playlistST, SeparateChainingHashST1<String, Utilizador> utilizadoresST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String ISRC = texto[0];
            String nome = texto[1];
            Float duracao = Float.parseFloat(texto[2]);
            String artista = texto[3];
            String genero = texto[4];

            Musica m = new Musica(ISRC, nome, duracao, artista, genero);
            musicaST.put(ISRC, m);

            Genero g = generoST.get(genero);
            g.getGeneroMusicsST().put(ISRC, m);

            Artista a = artistaST.get(artista);
            a.getArtistMusicSt().put(ISRC, m);

        }
    }

    public static void loadFromFileArtistasST(SeparateChainingHashST1<String, Artista> artistasST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String username = texto[1];
            String nome = texto[0];
            String generomusical = texto[2];

            Artista a = new Artista(username, nome, generomusical);
            artistasST.put(username, a);
        }
    }

    public static void loadFromFileUtilizadoresST(SeparateChainingHashST1<String, Utilizador> utilizadoresST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];
            String email = texto[2];

            Utilizador u = new Utilizador(nome, username, email);
            utilizadoresST.put(username, u);
        }
    }

    /*
    Create, Read, Update, Delete (Genero)
     */
    public static RedBlackBST_Projecto createGenreSt(RedBlackBST_Projecto generoST) {

        In in = new In(".//data//generos.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String genero = texto[0];
            String descricao = texto[1];

            Genero g = new Genero(genero, descricao);
            generoST.put(genero, g);
        }

        return generoST;

    }

    public static RedBlackBST_Projecto updateGenreSt(RedBlackBST_Projecto<String, Genero> generoST, String key) {
        In in = new In();
        String genero = null, descricao = null;

        Genero g = new Genero(genero, descricao);
        g = (Genero) generoST.get(key);
        StdOut.print("O que pretende editar?");
        StdOut.print("1-Genero\n");
        StdOut.print("2-Descricao\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                genero = StdIn.readString();
                g.setGenero(genero);
                generoST.put(key, null);
                generoST.put(key, g);
                break;
            case 2:
                descricao = StdIn.readString();
                g.setDescricao(descricao);
                generoST.put(key, null);
                generoST.put(key, g);
                break;
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return generoST;
    }

    public static RedBlackBST_Projecto deleteGenreSt(RedBlackBST_Projecto generoST, Integer key) {
        generoST.delete(key);
        return generoST;
    }

    /*
    Create, Read, Update, Delete (Musica)
     */
    public static RedBlackBST_Projecto createMusicSt(RedBlackBST_Projecto musicaST) {

        In in = new In(".//data//musicas.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String ISRC = texto[0];
            String nome = texto[1];
            Float duracao = Float.parseFloat(texto[2]);
            String artista = texto[3];
            String genero = texto[4];

            Musica g = new Musica(ISRC, nome, duracao, artista, genero);
            musicaST.put(genero, g);
        }

        return musicaST;

    }

    public static RedBlackBST_Projecto updateMusicSt(RedBlackBST_Projecto<String, Musica> musicaST, String key) {
        In in = new In();
        String ISRC = null, nome = null, artista = null, genero = null;
        Float duracao = null;

        Musica m = new Musica(ISRC, nome, duracao, artista, genero);
        m = (Musica) musicaST.get(key);
        StdOut.print("O que pretende editar?");
        StdOut.print("1-ISRC\n");
        StdOut.print("2-Nome\n");
        StdOut.print("3-Duração\n");
        StdOut.print("4-Artista\n");
        StdOut.print("5-Genero\n");
        StdOut.print("6-Playlist\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                ISRC = StdIn.readString();
                m.setISRC(ISRC);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 2:
                nome = StdIn.readString();
                m.setNome(nome);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 3:
                duracao = StdIn.readFloat();
                m.setDuracao(duracao);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 4:
                artista = StdIn.readString();
                m.setArtista(artista);
                musicaST.put(key, null);
                musicaST.put(key, m);
            case 5:
                genero = StdIn.readString();
                m.setGenero(genero);
                musicaST.put(key, null);
                musicaST.put(key, m);
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return musicaST;
    }

    public static RedBlackBST_Projecto deleteMusicSt(RedBlackBST_Projecto musicaST, Integer key) {
        musicaST.delete(key);
        return musicaST;
    }

    /*
    Create, Read, Update, Delete (Utilizador)
     */
    public static SeparateChainingHashST1 createUsersSt(SeparateChainingHashST1 utilizadorST) {

        In in = new In(".//data//pessoas.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];
            String email = texto[2];

            Utilizador u = new Utilizador(nome, username, email);
            utilizadorST.put(username, u);
        }

        return utilizadorST;
    }

    public static SeparateChainingHashST1 updateUserSt(SeparateChainingHashST1<String, Utilizador> utilizadorST, String key) {
        In in = new In();
        String nome = null, username = null, email = null;

        Utilizador u = new Utilizador(nome, username, email);
        u = (Utilizador) utilizadorST.get(key);
        StdOut.print("O que pretende editar");
        StdOut.print("1-Nome\n");
        StdOut.print("2-Username\n");
        StdOut.print("3-Email\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                nome = StdIn.readString();
                u.setNome(nome);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
                break;
            case 2:
                username = StdIn.readString();
                u.setUsername(username);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
                break;
            case 3:
                email = StdIn.readString();
                u.setMail(email);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return utilizadorST;
    }

    public static SeparateChainingHashST1 deleteUserSt(SeparateChainingHashST1 utilizadorST, Integer key) {
        utilizadorST.delete(key);
        return utilizadorST;
    }

    /*
    Create, Read, Update, Delete (Artista)
     */
    public static SeparateChainingHashST1 createArtistsSt(SeparateChainingHashST1 artistaST) {

        In in = new In(".//data//artista.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String username = texto[0];
            String nome = texto[1];
            String generomusical = texto[2];

            Artista a = new Artista(username, nome, generomusical);
            artistaST.put(username, a);
        }

        return artistaST;

    }

    public static SeparateChainingHashST1 updateArtistSt(SeparateChainingHashST1<String, Artista> artistaST, String key) {
        In in = new In();
        String username = null, nome = null, generomusical = null;

        Artista a = new Artista(username, nome, generomusical);
        a = (Artista) artistaST.get(key);
        StdOut.print("O que pretende editar");
        StdOut.print("1-Nome\n");
        StdOut.print("2-Genero Musica do Artista\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                nome = StdIn.readString();
                a.setNome(nome);
                artistaST.put(key, null);
                artistaST.put(key, a);
                break;
            case 2:
                generomusical = StdIn.readString();
                a.setGeneromusical(generomusical);
                artistaST.put(key, null);
                artistaST.put(key, a);
                break;
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return artistaST;
    }

    public static SeparateChainingHashST1 deleteArtistSt(SeparateChainingHashST1 generoST, Integer key) {
        generoST.delete(key);
        return generoST;
    }

    /*
    Testes e Listagens
     */
    public static void printMusicByGenres(RedBlackBST_Projecto<String, Genero> generoST) {
        StdOut.print("\n\nLista de Musica por Generos:\n\n");
        for (String g : generoST.inOrder()) {
            StdOut.println("Género: " + g);

            RedBlackBST_Projecto<String, Musica> musicas = generoST.get(g).getGeneroMusicsST();

            for (String isrc : musicas.keys()) {
                Musica m = (Musica) musicas.get(isrc);
                StdOut.println("      Musica: " + m.getNome());
            }
        }
    }

    public static void printMusicByArtist(SeparateChainingHashST1<String, Artista> artistaST) {
        StdOut.print("\n\nLista de Musica por Artistas:\n\n");
        for (String a : artistaST.keys()) {
            StdOut.println("Artista: " + a);

            RedBlackBST_Projecto<String, Musica> musicas = artistaST.get(a).getArtistMusicSt();

            for (String isrc : musicas.keys()) {
                Musica m = (Musica) musicas.get(isrc);
                StdOut.println("    Musica: " + m.getNome());
            }
        }
    }

    public static void printMusicByPlaylist(RedBlackBST_Projecto<String, Playlist> playlistST) {
        StdOut.print("\n\nLista de Musicas por Playlist:\n\n");
        for (String p : playlistST.inOrder()) {
            StdOut.println("Playlist: " + p);
            RedBlackBST_Projecto<String, Musica> musicas = playlistST.get(p).getPlaylistSt();

            for (String isrc : musicas.keys()) {
                Musica m = (Musica) musicas.get(isrc);
                StdOut.println("    Musica: " + m.getNome());
            }
        }
    }
}
