/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;


/**
 *
 * @author bluis
 */
public class Playlist {

    String nome;
    String username;

    private RedBlackBST_Projecto<String, Musica> playlistSt = new RedBlackBST_Projecto<>();//musicas nesta playlist

    /**
     *
     * @param nome
     * @param username
     */
    public Playlist(String nome, String username) {
        this.nome = nome;
        this.username = username;
    }

//Gets e Sets

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param e
     */
    public void musica(Musica e) {
        this.playlistSt.put(e.getISRC(), e);
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public RedBlackBST_Projecto<String, Musica> getPlaylistSt() {
        return playlistSt;
    }

    /**
     *
     * @param playlistSt
     */
    public void setPlaylistSt(RedBlackBST_Projecto<String, Musica> playlistSt) {
        this.playlistSt = playlistSt;
    }

}
