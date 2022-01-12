/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

/**
 *
 * @author Mário
 */
public class Cartas {
    private int cor;    //1 vermelho, 2 azul, 3 verde, 4 castanho, 0 sem cor
    private int id;     //identificação numerica unica
    private char tipo;  //tipo de carta, "D" door, "L" labirintos, "N" nightmare, 'S' sun, 'M' moon, 'K' key
    
    public Cartas(int cor, int id, char tipo){
        this.cor=cor;
        this.id=id;
        this.tipo=tipo;
    }

    public int getCor() {
        return cor;
    }

    public int getId() {
        return id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    
    
}