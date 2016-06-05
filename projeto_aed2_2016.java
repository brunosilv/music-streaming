/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Bruno Silva
 */
public abstract class projeto_aed2_2016 {

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
        
        //symbol-graphs
        
        SymbolEdgeWeigthedGraph sgFollow = new SymbolEdgeWeigthedGraph(".//data//FollowUsers.txt", ";");
        System.out.println(sgFollow.toString());
        
        FriendShipsGraph sgFriends = new FriendShipsGraph(".//data//FriendshipsGraph.txt",";");
        System.out.println(sgFriends.toString());
        //sgFriends.SPBetweenUsers("arpinto", "jtorres");
        
        LikesGraph sgLikesMusic = new LikesGraph(".//data//LikesMusics.txt",";");
        System.out.println(sgLikesMusic.toString());
        
        LikesGraph sgLikesPlayList = new LikesGraph(".//data//LikesPlaylists.txt",";");
        System.out.println(sgLikesPlayList.toString());
        
        LikesGraph sgLikesArtist = new LikesGraph(".//data//LikesArtists.txt",";");
        System.out.println(sgLikesArtist.toString());
        
        /*
         *  Inicialização das St's
         */
        loadFromFileGenerosST(generosST, ".//data//geneross.txt");
        loadFromFileArtistasST(artistasST, ".//data//artistas.txt");
        loadFromFileUtilizadoresST(utilizadoresST, ".//data//pessoas.txt");
        loadFromFileMusicasST(musicasST, generosST, artistasST, playlistsST, utilizadoresST, ".//data//musicass.txt");
        loadFromFilePlaylistST(playlistsST, musicasST, ".//data//playlists.txt");
        loadFromFileHistory(utilizadoresST, ".//data//historico.txt");
        
        
 
        /* 
         *  Chamada dos Clientes 
         */
        //printMusicByGenres(generosST); //a funcionar
        //printMusicByArtist(artistasST); //a funcionar
        //printMusicByPlaylist(playlistsST); //a funcionar
        //printAll(musicasST, generosST, artistasST, playlistsST, utilizadoresST); //a funcionar
        //createGenreSt(generosST); //a funcionar
        //saveGenreSt(generosST, ".//data//generos.txt"); // a funcionar
        //createArtistSt(artistasST, generosST); // a funcionar
        //saveArtistSt(artistasST, ".//data//artista.txt"); // a funcionar
        //createMusicSt(musicasST, generosST, artistasST); // a funcionar
        //updateMusicSt(musicasST, artistasST, generosST); // a funcionar
        //deleteMusicSt(musicasST, artistasST, generosST); // a funcionar
        //saveMusicSt(musicasST, ".//data//musicas.txt"); // a funcionar
        //createUsersSt(utilizadoresST);
        //saveUserSt(utilizadoresST, ".//data//pessoas.txt"); // a funcionar
        //playMusic(musicasST, utilizadoresST, "jtorres"); // a funcionar
        //savePlayedMusics(utilizadoresST, ".//data//historico.txt"); // a funcionar
        //musicPlaylistSearch(playlistsST, musicasST);// a funcionar
    }


    /*
     *   Loads
     */
    /**
     *
     * @param utilizadorST
     * @param path
     */
    public static void loadFromFileHistory(SeparateChainingHashST1<String, Utilizador> utilizadorST, String path) {
        In in = new In(path);
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String username = texto[0];
            String date = texto[1];
            String isrc = texto[2];
            Utilizador u = utilizadorST.get(username);
            u.getHistoricoST().put(date, isrc);

        }
    }

    /**
     *
     * @param playlistsST
     * @param musicasST
     * @param path
     */
    public static void loadFromFilePlaylistST(RedBlackBST_Projecto<String, Playlist> playlistsST, RedBlackBST_Projecto<String, Musica> musicasST, String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];
            int numMusics = texto.length - 2;
            Playlist p = new Playlist(nome, username);
            playlistsST.put(username, p);
            for (int i = 0; i < numMusics; i++) {
                Musica m = (Musica) musicasST.get(texto[i + 2]);
                p.getPlaylistSt().put(texto[i + 2], m);
            }
        }

    }

    /**
     *
     * @param generoST
     * @param path
     */
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

    /**
     *
     * @param musicaST
     * @param generoST
     * @param artistaST
     * @param playlistST
     * @param utilizadoresST
     * @param path
     */
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

    /**
     *
     * @param artistasST
     * @param path
     */
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

    /**
     *
     * @param utilizadoresST
     * @param path
     */
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
    /**
     * Validação se o IRSC existe;
     *
     * @param musicaST
     * @param isrc
     * @return
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

    /**
     * Validação se o genero existe;
     *
     * @param generosST
     * @param genero
     * @return
     */
    public static boolean genreValidation(RedBlackBST_Projecto<String, Genero> generosST, String genero) {
        for (String key : generosST.keys()) {
            Genero g = (Genero) generosST.get(key);
            if (g.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validação se o artista existe;
     *
     * @param artistaST
     * @param artista
     * @return
     */
    public static boolean artistValidation(SeparateChainingHashST1<String, Artista> artistaST, String artista) {
        for (String username : artistaST.keys()) {
            Artista a = (Artista) artistaST.get(username);
            if (a.getNome().equals(artista)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validação se o utilizador existe;
     *
     * @param utilizadorST
     * @param utilizador
     * @return
     */
    public static boolean userValidation(SeparateChainingHashST1<String, Utilizador> utilizadorST, String utilizador) {
        for (String username : utilizadorST.keys()) {
            Utilizador u = (Utilizador) utilizadorST.get(username);
            if (u.getUsername().equals(utilizador)) {
                return true;
            }
        }
        return false;
    }

    /*
     *  Genero
     */
    /**
     * Verifica se o genero existe senão existir cria novo;
     *
     * @param generoST
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

    /**
     * Grava no ficheiro generos.txt;
     *
     * @param generoST
     * @param path
     */
    public static void saveGenreSt(RedBlackBST_Projecto<String, Genero> generoST, String path) {
        Out o = new Out(path);
        for (String genero : generoST.keys()) {
            Genero g = (Genero) generoST.get(genero);
            o.println(g.getGenero() + ";" + g.getDescricao());
        }

    }

    /*
     * Musica
     */
    /**
     * Criação de musicas, verifica se genero e artista se encontram na BD;
     *
     * @param musicaST
     * @param generoST
     * @param artistaST
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

    /**
     * Imprime musicas;
     *
     * @param musicaST
     */
    public static void printMusicST(RedBlackBST_Projecto<String, Musica> musicaST) {
        int i = 1;
        for (String isrc : musicaST.keys()) {
            Musica m = musicaST.get(isrc);
            StdOut.println(m.getISRC() + " " + m.getNome() + " " + m.getDuracao()
                    + " " + m.getArtista() + " " + m.getGenero() + "\n");

            i++;

        }
    }

    /**
     * Verifica se a musica (ISRC) existe se sim pg o que editar, faz a
     * verificação de genero e de artista;
     *
     * @param musicaST
     * @param artistaST
     * @param generoST
     */
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

    /**
     * Remove musica do ficheiro musicas.txt;
     *
     * @param musicaST
     * @param artistaST
     * @param generoST
     */
    public static void deleteMusicSt(RedBlackBST_Projecto<String, Musica> musicaST,
            SeparateChainingHashST1<String, Artista> artistaST, RedBlackBST_Projecto<String, Genero> generoST) {
        Scanner sca = new Scanner(System.in);
        String delete;
        printMusicST(musicaST);
        System.out.print("\nMusica a eliminar: ");
        delete = sca.nextLine();
        for (String isrc : musicaST.keys()) {
            Musica m = (Musica) musicaST.get(isrc);
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

    /**
     * Grava no ficheiro musicas.txt;
     *
     * @param musicaST
     * @param path
     */
    public static void saveMusicSt(RedBlackBST_Projecto<String, Musica> musicaST, String path) {
        Out o = new Out(path);
        for (String isrc : musicaST.keys()) {
            Musica m = (Musica) musicaST.get(isrc);
            o.println(m.getISRC() + ";" + m.getNome() + ";" + m.getDuracao() + ";" + m.getArtista() + ";" + m.getGenero());
        }

    }

    /*
     * Utilizador
     */
    /**
     * Cria utilizador no ficheiro pessoas.txt;
     *
     * @param utilizadorST
     * @return
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

    /**
     * Remove do ficheiro pessoas.txt, metodo manual key dada na chamada do
     * cliente;
     *
     * @param utilizadorST
     * @param key
     * @return
     */
    public static SeparateChainingHashST1 deleteUserSt(SeparateChainingHashST1 utilizadorST, Integer key) {
        utilizadorST.delete(key);
        return utilizadorST;
    }

    /**
     * Grava no ficheiro pessoas.txt;
     *
     * @param utilizadoresST
     * @param path
     */
    public static void saveUserSt(SeparateChainingHashST1<String, Utilizador> utilizadoresST, String path) {
        Out o = new Out(path);
        for (String username : utilizadoresST.keys()) {
            Utilizador u = (Utilizador) utilizadoresST.get(username);
            o.println(u.getNome() + ";" + u.getUsername() + ";" + u.getMail());
        }

    }

    /*
     * Artista
     */
    /**
     * Faz a validação se artista existe senão existir cria;
     *
     * @param artistasST
     * @param generosST
     */
    public static void createArtistSt(SeparateChainingHashST1<String, Artista> artistasST, RedBlackBST_Projecto<String, Genero> generosST) {
        String artista, username, genero;

        Scanner sca = new Scanner(System.in);

        System.out.print("Artista: ");
        artista = sca.nextLine();
        System.out.println("Username: ");
        username = sca.nextLine();
        while (artistValidation(artistasST, username) == true) {
            System.out.println("Artista já existe na BD!");
            System.out.print("Artista: ");
            artista = sca.nextLine();
            System.out.println("\nUsername: ");
            username = sca.nextLine();
        }
        System.out.print("\nGenero: ");
        genero = sca.nextLine();
        while (genreValidation(generosST, genero) == false) {
            System.out.println("Genero não se econtra na BD!");
            System.out.print("Genero: ");
            genero = sca.nextLine();
        }
        Artista a = new Artista(artista, username, genero);
        artistasST.put(username, a);

    }

    /**
     * Remove do ficheiro artista.txt, metodo manual key dada na chamada do
     * cliente;
     *
     * @param generoST
     * @param key
     * @return
     */
    public static SeparateChainingHashST1 deleteArtistSt(SeparateChainingHashST1 generoST, Integer key) {
        generoST.delete(key);
        return generoST;
    }

    /**
     * Grava no ficheiro artista.txt
     *
     * @param artistasST
     * @param path
     */
    public static void saveArtistSt(SeparateChainingHashST1<String, Artista> artistasST, String path) {
        Out o = new Out(path);
        for (String username : artistasST.keys()) {
            Artista a = (Artista) artistasST.get(username);
            o.println(a.getNome() + ";" + a.getUsername() + ";" + a.getGeneromusical());
        }

    }

    /*
     *  Testes e Listagens
     */
    /**
     * Lista Musicas por generos
     *
     * @param generoST
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

    /**
     * Lista musicas por artistas
     *
     * @param artistaST
     */
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

    /**
     * Lista musicas por playlists
     *
     * @param playlistST
     */
    public static void printMusicByPlaylist(RedBlackBST_Projecto<String, Playlist> playlistST) {
        StdOut.print("\n\nLista de Playlists por Utilizadores:\n\n");
        for (String username : playlistST.keys()) {
            Playlist p = (Playlist) playlistST.get(username);
            System.out.println(p.getNome() + " de " + p.getUsername());
            RedBlackBST_Projecto<String, Musica> musicas = playlistST.get(username).getPlaylistSt();

            for (String isrc : musicas.keys()) {
                Musica m = (Musica) musicas.get(isrc);
                StdOut.println("   Musicas: " + m.getNome());
            }
        }
    }

    /**
     * Lista todas as ST´s
     *
     * @param musicaST
     * @param generoST
     * @param artistasST
     * @param playlistST
     * @param utilizadorST
     */
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

        System.out.println("\nListar playlists:");
        for (String playlist : playlistST.keys()) {
            Playlist p = (Playlist) playlistST.get(playlist);
            System.out.println("- " + p.getNome());
        }

    }

    /**
     * Metodo para gerar historico, utilizador é definido na chamada de cliente;
     *
     * @param musicasST
     * @param utilizadoresST
     * @param user
     */
    public static void playMusic(RedBlackBST_Projecto<String, Musica> musicasST, SeparateChainingHashST1<String, Utilizador> utilizadoresST, String user) {

        Scanner sca = new Scanner(System.in);
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
        String param;
        printMusicST(musicasST);
        System.out.println("\n\nMusica a ouvir: ");
        param = sca.nextLine();
        for (String key : musicasST.keys()) {
            Musica m = musicasST.get(key);
            if (m.getISRC().equals(param)) {
                Utilizador u = utilizadoresST.get(user);
                u.getHistoricoST().put(ft.format(d), param);

            }
        }
    }

    /**
     * Guarda no ficheiro historico.txt, utilizador definido na chamada do
     * cliente;
     *
     * @param utilizadoresST
     * @param path
     * @param user
     */
    public static void savePlayedMusics(SeparateChainingHashST1<String, Utilizador> utilizadoresST, String path) {
        Out o = new Out(path);
        for (String u : utilizadoresST.keys()) {
            Utilizador utilizador = utilizadoresST.get(u);
            RedBlackBST_Projecto<String, String> h = utilizador.getHistoricoST();
            for (String key : h.keys()) {
                o.println(utilizador.getUsername() + ";" + key + ";" + h.get(key));

            }
        }

    }

    /**
     * Procura se a musica existe em alguma playlist;
     *
     * @param playlistsST
     * @param musicasST
     */
    public static void musicPlaylistSearch(RedBlackBST_Projecto<String, Playlist> playlistsST, RedBlackBST_Projecto<String, Musica> musicasST) {
        Scanner sca = new Scanner(System.in);
        String param;
        System.out.print("Musica: ");
        param = sca.nextLine();
        if (isrcValidation(musicasST, param) == true) {
            for (String username : playlistsST.keys()) {
                Playlist pl = (Playlist) playlistsST.get(username);
                RedBlackBST_Projecto<String, Musica> musicas = playlistsST.get(username).getPlaylistSt();
                for (String isrc : musicas.keys()) {
                    Musica m = (Musica) musicas.get(isrc);
                    Playlist p = (Playlist) playlistsST.get(username);
                    if (p.getPlaylistSt().get(isrc).getISRC().equals(param)) {
                        System.out.println("Musica encontra-se na playlist: " + p.getNome());
                    }
                }

            }
        } else {
            System.out.println("Musica não se encontra na BD!");
        }

    }

    public static void likeMusic() {

    }

    public static void likeArtist() {

    }

    public static void likePlaylist() {

    }

    public static void followUser() {

    }
}
