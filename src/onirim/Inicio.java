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
public class Inicio implements State{
    
    private Jogo jogo;
    
    public Inicio(Jogo jogo){
        this.jogo=jogo;
    }
    
    public void maoinicial(){
        
        int i;
        List<Cartas> baralho = new ArrayList<Cartas>();
        List<Cartas> mao = new ArrayList<Cartas>();
        List<Cartas> limbo = new ArrayList<Cartas>();
        
        mao=jogo.getMao();
        baralho=jogo.getBaralho();
        
        for(i=0;i<5;i++){
            mao.add(baralho.get(baralho.size()-1));
            baralho.remove(baralho.size()-1);
        }
        i=0;
        while(i!=5){
            
            if(mao.get(i).getTipo() != 'M' && mao.get(i).getTipo() != 'S' && mao.get(i).getTipo() != 'K'){
                limbo.add(mao.get(i));
                mao.remove(i);
                mao.add(baralho.get(baralho.size()-1));
                baralho.remove(baralho.size()-1);       
            }
            else
                i++;
        }
        baralho.addAll(limbo);
        limbo.removeAll(limbo);
        
        jogo.setBaralho(baralho);
        jogo.setMao(mao);
        
    }
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
    public int compra_carta(){return 0;}
    public boolean joga_carta(int ind){return false;}
    public List<Cartas> profecia(){return null;}
    public List<Cartas> descarta_carta(int ind){return null;}
    
}
