/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mário
 */
public class Baralha implements State{
    private Jogo jogo;
    public Baralha(Jogo jogo){
        this.jogo=jogo;
    }
    public void maoinicial(){}
    public void baralha(){
        List<Cartas> cartas_baralhadas = new ArrayList<Cartas>();
        List<Cartas> aux = new ArrayList<Cartas>(); 
        List<Cartas> baralho = new ArrayList<Cartas>();
        baralho=jogo.getBaralho();
        int c_random;
       
        aux.addAll(baralho);
        for(int i=0; i< baralho.size();i++){
            c_random =  0 + (int)(Math.random()* aux.size());
            cartas_baralhadas.add(aux.get(c_random));
            aux.remove(c_random);
        }
        jogo.setBaralho( cartas_baralhadas);
    }
    public int compra_carta(){ return 0;}
    public boolean joga_carta(int ind){return false;}
    public List<Cartas> profecia(){return null;}
    public List<Cartas> descarta_carta(int ind){return null;}
}
