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
public class Compra implements State{
   private Jogo jogo;
    
    public Compra(Jogo jogo){
        this.jogo=jogo;
    }
    public void maoinicial(){
          int i;
        List<Cartas> baralho = new ArrayList<Cartas>();
        List<Cartas> mao = new ArrayList<Cartas>();
        List<Cartas> limbo = new ArrayList<Cartas>();
        
        mao=jogo.getMao();
        baralho=jogo.getBaralho();
        limbo.addAll(mao);
        mao.removeAll(mao);
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
    public void baralha(){}
    public int compra_carta(){
        List<Cartas> baralho = new ArrayList<Cartas>();
        List<Cartas> mao = new ArrayList<Cartas>(); 
        mao=jogo.getMao();
        baralho=jogo.getBaralho();
        
        mao.add(baralho.get(baralho.size()-1));
        baralho.remove(baralho.size()-1);
        jogo.setBaralho(baralho);
        if(mao.get(mao.size()-1).getTipo()=='D'){
            jogo.setMao(mao);
            return 1;
        }else if(mao.get(mao.size()-1).getTipo()=='N'){
            mao.remove(mao.size()-1);
            jogo.setMao(mao);
            return 2;
        }
        jogo.setMao(mao);
        return 0;
    }
    public boolean joga_carta(int ind){
        return false;
    }
    public List<Cartas> profecia(){
        return null;
    }
    public List<Cartas> descarta_carta(int ind){return null;}
}


