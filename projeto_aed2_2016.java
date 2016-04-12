/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Scanner;

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
         *  Inicialização das St's
         */
        loadFromFileGenerosST(generosST, ".//data//generos.txt");
        loadFromFileArtistasST(artistasST, ".//data//artista.txt");
        loadFromFileUtilizadoresST(utilizadoresST, ".//data//pessoas.txt");
        loadFromFileMusicasST(musicasST, generosST, artistasST, playlistsST, utilizadoresST, ".//data//musicas.txt");
        loadFromFilePlaylistST(playlistsST, utilizadoresST, musicasST, ".//data//playlists.txt");
        /* 
         *  Chamada dos Clientes 
         */
        printMusicByGenres(generosST); //a funcionar
        printMusicByArtist(artistasST); //a funcionar
        printMusicByPlaylist(playlistsST); //a funcionar
        printAll(musicasST, generosST, artistasST, playlistsST, utilizadoresST); //a funcionar
        //printMusicByHistory(historyST);
        createGenreSt(generosST); //a funcionar
        //updateGenreSt(generosST;
        //deleteGenreSt(generosST);
        saveGenreSt(generosST, ".//data//generos.txt"); // a funcionar
        //createArtistSt(artistasST);
        //updateArtistSt(artistasST);
        //deleteArtistSt(artistasST);
        //saveArtistSt(artistasST, ".//data//artista.txt");
        createMusicSt(musicasST, generosST, artistasST); //a funcionar
        updateMusicSt(musicasST, artistasST, generosST); //a funcionar
        deleteMusicSt(musicasST, artistasST, generosST); //a funcionar
        saveMusicSt(musicasST, ".//data//musicas.txt"); //a funcionar
        //createUsersSt(utilizadoresST);
        //updateUsersSt(utilizadoresST);
        //deleteUsersSt(utilizadoresST);
        //saveUserSt(utilizadoresST, ".//data//pessoas.txt");
    }
    /*
    *   Loads
    */
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
     *  Validações
     */
    public static boolean isrcValidation(RedBlackBST_Projecto<String, Musica> musicaST, String isrc) {
        for (String key : musicaST.keys()) {
            Musica m = musicaST.get(key);
            if (m.getISRC().equals(isrc)) {
                return true;
            }
        }
        return false;
    }

    public static boolean genreValidation(RedBlackBST_Projecto<String, Genero> generosST, String genero) {
        for (String key : generosST.keys()) {
            Genero g = (Genero) generosST.get(key);
            if (g.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    public static boolean artistValidation(SeparateChainingHashST1<String, Artista> artistaST, String artista) {
        for (String username : artistaST.keys()) {
            Artista a = (Artista) artistaST.get(username);
            if (a.getUsername().equals(artista)) {
                return true;
            }
        }
        return false;
    }

    /*
     *  Genero
     */
    public static void createGenreSt(RedBlackBST_Projecto<String, Genero> generoST) {

        String genero, desc;

        Scanner sca = new Scanner(System.in);

        System.out.print("Genre: ");
        genero = sca.nextLine();
        while (genreValidation(generoST, genero) == true) {
            System.out.println("Genero já se encontra na base de dados");
            System.out.print("\nGenero: ");
            sca.nextLine();
            genero = sca.nextLine();
        }
        System.out.print("\nDescrição: ");
        desc = sca.nextLine();
        Genero g = new Genero(genero, desc);
        generoST.put(genero, g);

    }

    public static void saveGenreSt(RedBlackBST_Projecto<String, Genero> generoST, String path) {
        Out o = new Out(path);
        for (String genero : generoST.keys()) {
            Genero g = (Genero) generoST.get(genero);
            o.println(g.getGenero() + ";" + g.getDescricao());
        }

    }

    public static RedBlackBST_Projecto deleteGenreSt(RedBlackBST_Projecto generoST, Integer key) {
        generoST.delete(key);
        return generoST;
    }

    /*
     *  Musica
     */
    public static void createMusicSt(RedBlackBST_Projecto<String, Musica> musicaST, RedBlackBST_Projecto<String, Genero> generoST,
            SeparateChainingHashST1<String, Artista> artistaST) {
        String isrc, nome, artista, genero;
        String duracao;
        float daux;
        Scanner sca = new Scanner(System.in);
        System.out.print("ISRC: ");
        isrc = sca.nextLine();
        System.out.print("\nNome da musica: ");
        nome = sca.nextLine();
        System.out.print("\nDuração(mm.ss): ");
        duracao = sca.nextLine();
        daux = Float.parseFloat(duracao);
        System.out.print("\nArtista: ");
        artista = sca.nextLine();
        //Verifica se o artista existe na BD
        while (artistValidation(artistaST, artista) == false) {
            System.out.println("Artista não se encontra na BD!");
            System.out.print("\nArtista: ");
            artista = sca.nextLine();
        }
        System.out.print("\nGenero: ");
        genero = sca.nextLine();
        //Verifica se o genero existe na BD
        while (genreValidation(generoST, genero) == false) {
            System.out.println("Genero não se encontra na BD!");
            System.out.print("\nGenero: ");
            genero = sca.nextLine();
        }

        Musica m = new Musica(isrc, nome, daux, artista, genero);
        musicaST.put(isrc, m);
        Genero g = generoST.get(genero);
        g.getGeneroMusicsST().put(isrc, m);
        Artista a = artistaST.get(artista);
        a.getArtistMusicSt().put(isrc, m);

    }

    public static void printMusicST(RedBlackBST_Projecto<String, Musica> musicaST) {
        int i = 1;
        for (String isrc : musicaST.keys()) {
            Musica m = musicaST.get(isrc);
            StdOut.println(m.getISRC() + " " + m.getNome() + " " + m.getDuracao()
                    + " " + m.getArtista() + " " + m.getGenero() + "\n");

            i++;

        }
    }

    public static void updateMusicSt(RedBlackBST_Projecto<String, Musica> musicaST, SeparateChainingHashST1<String, Artista> artistaST,
            RedBlackBST_Projecto<String, Genero> generoST) {
        Scanner sca = new Scanner(System.in);
        String isrc, param, editar;
        printMusicST(musicaST);

        System.out.print("\nMusica a editar: ");
        isrc = sca.nextLine();
        if (isrcValidation(musicaST, isrc) == true) {
            System.out.println(isrc);
            Musica m = musicaST.get(isrc);
            if (m.getISRC().equals(isrc)) {
                do {
                    System.out.println("O que vai editar:  1-ISRC, 2-Nome, 3-Duração, 4-Artista, 5-Genero\n");
                    param = sca.nextLine();
                    switch (param) {
                        case "1": {
                            System.out.println("Novo ISRC: ");
                            editar = sca.nextLine();
                            m.setISRC(editar);
                            return;
                        }
                        case "2": {
                            System.out.println("Novo nome: ");
                            editar = sca.nextLine();
                            m.setNome(editar);
                            Artista a = artistaST.get(m.getArtista());
                            a.getArtistMusicSt().get(isrc).setNome(editar);
                            Genero g = generoST.get(m.getGenero());
                            g.getGeneroMusicsST().get(isrc);
                            return;
                        }
                        case "3": {
                            System.out.println("Nova duração: ");
                            editar = sca.nextLine();
                            m.setDuracao(Float.parseFloat(editar));
                            return;
                        }
                        case "4": {
                            System.out.println("Novo artista: ");
                            editar = sca.nextLine();

                            if (artistValidation(artistaST, editar) == true) {
                                m.setArtista(editar);
                                Artista a = artistaST.get(m.getArtista());
                                a.getArtistMusicSt().delete(isrc);
                                Artista aaux = artistaST.get(editar);
                                aaux.getArtistMusicSt().put(isrc, m);

                            } else {
                                System.out.println("Artista não se encontra na BD!");
                            }

                            return;
                        }

                        case "5": {
                            System.out.println("Novo genero: ");
                            editar = sca.nextLine();

                            if (genreValidation(generoST, editar) == true) {
                                m.setGenero(editar);
                                Genero g = generoST.get(m.getGenero());
                                g.getGeneroMusicsST().delete(isrc);
                                Genero gaux = generoST.get(editar);
                                gaux.getGeneroMusicsST().put(isrc, m);

                            } else {
                                System.out.println("Genero não se encontra na BD!");
                            }
                            return;
                        }

                    }
                } while (param.equals("isrc") || param.equals("nome") || param.equals("duracao") || param.equals("genero") || param.equals("artista"));
            }
        } else {
            System.out.println("Musica não se encontra numa playlist");
        }

    }

    public static void deleteMusicSt(RedBlackBST_Projecto<String, Musica> musicaST,
            SeparateChainingHashST1<String, Artista> artistaST, RedBlackBST_Projecto<String, Genero> generoST) {
        Scanner sca = new Scanner(System.in);
        String delete;
        boolean check = false;
        printMusicST(musicaST);
        System.out.print("\nMusica a eliminar: ");
        delete = sca.nextLine();
        for (String isrc : musicaST.keys()) {
            Musica m = (Musica) musicaST.get(isrc); // redblack artista genero
            if (m.getNome().equals(delete)) {
                musicaST.delete(isrc);
                Artista a = artistaST.get(m.getArtista());
                a.getArtistMusicSt().delete(isrc);
                Genero g = generoST.get(m.getGenero());
                g.getGeneroMusicsST().delete(isrc);
            }

        }
        System.out.println("Musica removida com sucesso");

    }

    public static void saveMusicSt(RedBlackBST_Projecto<String, Musica> musicaST, String path) {
        Out o = new Out(path);
        for (String isrc : musicaST.keys()) {
            Musica m = (Musica) musicaST.get(isrc);
            o.println(m.getISRC() + ";" + m.getNome() + ";" + m.getDuracao() + ";" + m.getArtista() + ";" + m.getGenero());
        }

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
                StdOut.println(m.getNome());
            }
        }
    }

    public static void printAll(RedBlackBST_Projecto<String, Musica> musicaST, RedBlackBST_Projecto<String, Genero> generoST,
            SeparateChainingHashST1<String, Artista> artistasST, RedBlackBST_Projecto<String, Playlist> playlistST,
            SeparateChainingHashST1<String, Utilizador> utilizadorST) {
        System.out.println("\nListar musicas:");
        for (String isrc : musicaST.keys()) {
            Musica m = (Musica) musicaST.get(isrc);
            System.out.println("- " + m.getNome());
        }

        System.out.println("\nListar artistas:");
        for (String username : artistasST.keys()) {
            Artista a = (Artista) artistasST.get(username);
            System.out.println("- " + a.getNome());
        }

        System.out.println("\nListar genero:");
        for (String genre : generoST.keys()) {
            Genero g = (Genero) generoST.get(genre);
            System.out.println("- " + g.getGenero());
        }

        System.out.println("\nListar utilizadores:");
        for (String user : utilizadorST.keys()) {
            Utilizador u = (Utilizador) utilizadorST.get(user);
            System.out.println("- " + u.getNome());
        }

        System.out.println("\nList of playlists:");
        for (String playlist : playlistST.keys()) {
            Playlist p = (Playlist) playlistST.get(playlist);
            System.out.println("- " + p.getNome());
        }

    }

    public static boolean verificarISRC(RedBlackBST_Projecto<String, Musica> musicaST, String isrc) {
        for (String key : musicaST.keys()) {
            Musica m = musicaST.get(key);
            if (m.getISRC().equals(isrc)) {
                return true;
            }
        }
        return false;
    }
}
