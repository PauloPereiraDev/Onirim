/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mário
 */
public class Joga implements State{
   private Jogo jogo;
    
    public Joga(Jogo jogo){
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
        jogo.setBaralho(cartas_baralhadas);
    }
    public int compra_carta(){
        return 0;
    }
    public int compra_carta(int cor){//para comprar porta da cor
        List<Cartas> baralho = new ArrayList<Cartas>();
        
        List<Cartas> mesa = new ArrayList<Cartas>();
        
        
        baralho=jogo.getBaralho();
        mesa=jogo.getMesa();
        
        for(int i=0; i< baralho.size();i++){
            if(baralho.get(i).getTipo() == 'D' && baralho.get(i).getCor()== cor){
                mesa.add(baralho.get(i));
                baralho.remove(i);
                jogo.setBaralho(baralho);
                jogo.setMesa(mesa);
                baralha();
                return 0;
            }
        }
        
        return 1;
        
    }
    public boolean joga_carta(int ind){
      
        List<Cartas> mao = new ArrayList<Cartas>();
        List<Cartas> mesa = new ArrayList<Cartas>();
        int flag=jogo.getFlag();
        
        mao=jogo.getMao();
        mesa=jogo.getMesa();
        
            if(mesa.size() > 0){
            if(mesa.get(mesa.size()-1).getTipo() != mao.get(ind).getTipo()){
                    mesa.add(mao.get(ind));
                    mao.remove(ind);

                    if((mesa.size()-flag) > 2){
                        //if(mesa.get(mesa.size()-3).getCor() == mesa.get(mesa.size()-2).getCor() && mesa.get(mesa.size()-3).getCor() == mesa.get(mesa.size()-1).getCor())
                        if(mesa.get(mesa.size()-3).getCor() != mesa.get(mesa.size()-2).getCor() && mesa.get(mesa.size()-3).getCor() != mesa.get(mesa.size()-1).getCor()&& mesa.get(mesa.size()-2).getCor() != mesa.get(mesa.size()-1).getCor())
                            if(compra_carta(mesa.get(mesa.size()-1).getCor())==0){//para comprar porta da cor
                                flag=mesa.size()-1;
                                jogo.setFlag(flag);
                                if(mesa.get(mesa.size()-3).getTipo()=='K'||mesa.get(mesa.size()-2).getTipo()=='K'||mesa.get(mesa.size()-1).getTipo()=='K')
                                      profeciaav();
                            }
                    }
                }
            }
            else{
                mesa.add(mao.get(ind));
                mao.remove(ind);
            }
            jogo.setMao(mao);
            jogo.setMesa(mesa);
            return true;
        
    }
    public void profeciaav(){
        List<Cartas> aux = new ArrayList<Cartas>();
        List<Cartas> baralho = new ArrayList<Cartas>();
        Scanner s = new Scanner(System.in);
        int op;
        baralho=jogo.getBaralho();
        
         for(int i=0;i<2;i++){
             aux.add(baralho.get(baralho.size()-1));
             baralho.remove(baralho.size()-1);
         }
         while(!aux.isEmpty()){
            System.out.println("Qual a carta que deseja enviar para o baralho");
            for(int i=0;i<aux.size();i++)
                System.out.println("id" + i +"  "+ aux.get(i).getTipo());
            do{ 
                op=s.nextInt();
            }while(op<0||op>aux.size()-1);
            jogo.adiciona(aux.get(op));
            aux.remove(op);
        }
         
         jogo.setBaralho(baralho);
    }
    
    public List<Cartas> profecia(){
        List<Cartas> aux = new ArrayList<Cartas>();
        List<Cartas> baralho = new ArrayList<Cartas>();
        baralho=jogo.getBaralho();
        
        
         int tam=5;
         if(baralho.size()<5)
             tam=baralho.size();
         for(int i=0;i<tam;i++){
             aux.add(baralho.get(baralho.size()-1));
             baralho.remove(baralho.size()-1);
         }
         
         jogo.setBaralho(baralho);
         return aux;
    }
    public List<Cartas> descarta_carta(int ind){
        List<Cartas> mao = new ArrayList<Cartas>();
        mao=jogo.getMao();
        if(mao.get(ind).getTipo()!='K'){
            mao.remove(ind);
            jogo.setMao(mao);
        }
        return null;
    }
}
