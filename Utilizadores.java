/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Diogo
 */
public class Utilizadores {
  String name;
  String user;
  String mail;

 
  
public Utilizadores(String nome,String username,String email)
{
    user = username;
    name = nome;
    mail = email;
    
}
 @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    String NEW_LINE = System.getProperty("line.separator");
    result.append(" Nome: " + name + NEW_LINE);
    result.append(" Email: " + mail + NEW_LINE );
    return result.toString();
    
  }
  
    
    public static LinearProbingHashST1 inserirUtilizador(LinearProbingHashST1 LinearProbingHashST1)
    {
        In in = new In(".//data//pessoas.txt");
         while (!in.isEmpty()) 
        {
            String[] texto = in.readLine().split(";");
            String nome = texto[1];
            String username = texto[2];
            String email = texto[3];

            Utilizadores utilizador = new Utilizadores(nome, username, email);
            LinearProbingHashST1.put(username,utilizador);
        }
        return LinearProbingHashST1;
    }
    public static LinearProbingHashST1 removerUtilizador(LinearProbingHashST1 LinearProbingHashST1, String key)
    {
        LinearProbingHashST1.delete(key);
        return LinearProbingHashST1;
       
    }
    public static LinearProbingHashST1 editarUtilizador(LinearProbingHashST1 LinearProbingHashST1, String key)
    {
        In in = new In();
        String username=null;
        String nome=null;
        String email=null;
        Utilizadores val = new Utilizadores(nome, username, email);
        StdOut.print("O que pretende editar,selecione um numero de 0 a 3");
        StdOut.print("1-Username");
        StdOut.print("2-Nome");
        StdOut.print("3-Email");
        StdOut.print("0-Sair");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch(menuItem)
        {
         case 1:
        username=StdIn.readString();
        val=(Utilizadores) LinearProbingHashST1.get(key);
        val.user = username;
        LinearProbingHashST1.put(key, null);
        LinearProbingHashST1.put(key, val);
        break;
         case 2:
        nome=StdIn.readString();
        val=(Utilizadores) LinearProbingHashST1.get(key);
        val.name = nome;
        LinearProbingHashST1.put(key, null);
        LinearProbingHashST1.put(key, val);
         break;
         case 3:
        email=StdIn.readString();
        val=(Utilizadores) LinearProbingHashST1.get(key);
        val.mail = email;
        LinearProbingHashST1.put(key, null);
        LinearProbingHashST1.put(key, val);
         break;
         case 0:
         quit = true;    
         break;
         default:
             System.out.println("Opção inválida");
            }
        //}while(!quit);
      return  LinearProbingHashST1;
    }
    public static void main(String[] args) {
        String username = null;
        String nome = null;
        String email = null;
        
        
        
        Utilizadores ut = new Utilizadores(username, nome, email);
        LinearProbingHashST1 st1;
        LinearProbingHashST1 st2 = new LinearProbingHashST1() ;
        
       st1 = ut.inserirUtilizador(st2);
       //StdOut.print("Introduza o utilizador que pretende editar: ");
       //String users = StdIn.readString();
       //ut.removerUtilizador(st1,users);
       //ut.editarUtilizador(st1,users);
       
       for (Object s: st1.keys()) 
            StdOut.println(s + " " + st1.get(s)); 
    }    
}

