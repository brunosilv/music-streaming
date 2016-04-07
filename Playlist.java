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
   
    private RedBlackBST_Projecto<String, Musica> playlistSt = new RedBlackBST_Projecto<>();//musicas nesta playlist
    

    public Playlist(String nome) {
        this.nome = nome;
    }
    

//Gets e Sets

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RedBlackBST_Projecto<String, Musica> getPlaylistSt() {
        return playlistSt;
    }

    public void setPlaylistSt(RedBlackBST_Projecto<String, Musica> playlistSt) {
        this.playlistSt = playlistSt;
    }



}
