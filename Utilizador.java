/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;


/**
 *
 * @author Bruno Silva
 */
public class Utilizador {

    String nome;
    String username;
    String mail;
   
    private RedBlackBST_Projecto<String, String> historicoST = new RedBlackBST_Projecto<>();
    
    /**
     *
     * @param nome
     * @param username
     * @param mail
     */
    public Utilizador(String nome, String username, String mail) {
        this.nome = nome;
        this.username = username;
        this.mail = mail;
    }

    /**
     *
     * @return
     */
    public RedBlackBST_Projecto<String, String> getHistoricoST() {
        return historicoST;
    }

    /**
     *
     * @param historicoST
     */
    public void setHistoricoST(RedBlackBST_Projecto<String, String> historicoST) {
        this.historicoST = historicoST;
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
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

}
