/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

import java.util.*;

/**
 *
 * @author Mário
 */
public class Jogo{
    
    private State inicio;
    private State joga;
    private State compra;
    private State baralha;
 
    private State state;
    private int count = 0;
    
    
    private List<Cartas> baralho = new ArrayList<Cartas>();     //o cimo do baralho, com as cartas viradas para baixo, corresponde as ultimas posicoes no array
    private List<Cartas> mao = new ArrayList<Cartas>();
    private List<Cartas> limbo = new ArrayList<Cartas>(); 
    private List<Cartas> mesa = new ArrayList<Cartas>(); 
    private int flag;     //flag para saber se a carta esta disponovel ou "gasta"

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
    public Jogo(){      //construtor do jogo
        
        inicio = new Inicio(this);
        joga = new Joga(this);
        compra = new Compra(this);
        baralha = new Baralha(this);
        
        Cartas c;
        for(int i=1;i<5;i++){           //4 cores
             //j numero de cartas da cor i
               
               for(int k=10-i;k>0;k--){ //adiciona n sóis
                   c=new Labirintos(i,baralho.size(),'S');//SUN
                   baralho.add(c);
               }
               
               for(int k=0;k<4;k++){//4 luas
                   c=new Labirintos(i,baralho.size(),'M');//MOON
                   baralho.add(c);
               }
               for(int k=0;k<3;k++){//3 chaves
                   c=new Labirintos(i,baralho.size(),'K');//key
                   baralho.add(c);
               }
               for(int k=0;k<2;k++){//2 portas
                  c=new Portas(i,baralho.size(),'D');//door
                  baralho.add(c); 
               }
                   
            }
        
        for(int k=0;k<10;k++){// adiciona 10 pesadelos
                  c=new Pesadelos(0,baralho.size(),'N');//nightmare
                  baralho.add(c); 
       }
        flag=0;
    }
    public void maoinicial(){
         state.maoinicial();
     }
    public void baralha(){
        state.baralha();
    }
    public void compra_carta(){
        state.compra_carta();
    }
    public void joga_carta(int ind){
        state.joga_carta(ind);
    }
    public void profecia(){
        state.profecia();
    }
    public void descarta_carta(int ind){
        state.descarta_carta(ind);
    }

    public void jogar_main(){
        int n_portas = 0;
        Interaccao it=new Interaccao();
        int[] op;
        op=new int [2];
        int idc;
         List<Cartas> aux = new ArrayList<Cartas>();
        state=inicio;
        //comeco do jogo, baralha
        state.baralha();
        //depois de baralhado, iniciar mao
        state.maoinicial();

        
        while(n_portas<8){
            state=joga;
            System.out.println("Baralho:");
            it.mostra_baralho(baralho,baralho.size());
            System.out.println("Mão:");
            it.mostra_mao(mao);
            System.out.println("Mesa:");
            it.mostra_mesa(mesa);
            op=it.fase1(mao.size()-1);         
            switch(op[0]){
                case 1:  state.joga_carta(op[1]);break;
                case 2:
                    if(mao.get(op[1]).getTipo()=='K'){
                        state.descarta_carta(op[1]);
                        aux=state.profecia();                    
                        it.mostralook(this, aux);
                    }else
                        state.descarta_carta(op[1]); 
                        break;
                case 3: System.out.println("Baralho:");
                         it.mostra_baralho(baralho,7);break;
                case 4:acrescenta_mao(op[1]);break;
                case 5:acrescenta_topo(op[1]);break;
                case 6:acrescenta_mesa(op[1]);break;
                default: break;
                
            }
            
            state=compra;
            idc=state.compra_carta();
            if(idc==1){
                for(int i=0;i<mao.size()-2;i++){
                    if(mao.get(i).getCor()==mao.get(mao.size()-1).getCor()&&mao.get(i).getTipo()=='K'){
                        state=joga;
                        mao.remove(i);
                        state.joga_carta(mao.size()-1);
                        i=mao.size()+2;
                    }   
                }
                if(mao.get(mao.size()-1).getTipo()=='D'){
                        baralho.add(mao.get(mao.size()-1));
                        mao.remove(mao.get(mao.size()-1));    
                }
            }else if(idc==2){
                state.maoinicial();
            }
            state=baralha;
            state.baralha();
            n_portas=conta_portas();
            
            if(n_portas==8){
                System.out.println("\n***Parabéns ganhou o jogo!***");
                System.exit(0);
            }else if(n_portas==-1){
                System.out.println("\n***Ohhh :'( conseguiu perder um jogo para meninos xD!***");
                System.exit(0);
            }
        }
    
    
    
    }
    public int conta_portas(){
        int j=0;
        for(int i=0;i<mesa.size();i++)
            if(mesa.get(i).getTipo()=='D')
                j++;
        if(j!=8)
            if(baralho.isEmpty())
                return -1;                
        return j;
    }
    public void acrescenta_mao(int i){
        mao.add(baralho.get(i));
        baralho.remove(i);
    }
    public void acrescenta_topo(int i){
        Cartas e;
        e=baralho.get(i);
        baralho.remove(i);
        baralho.add(e);
    }
    public void acrescenta_mesa(int i){
        mesa.add(baralho.get(i));
        baralho.remove(i);
    }
    
     
    public List<Cartas> getBaralho() {
        return baralho;
    }

    public void setBaralho(List<Cartas> baralho) {
        this.baralho = baralho;
    }

    public List<Cartas> getLimbo() {
        return limbo;
    }

    public void setLimbo(List<Cartas> limbo) {
        this.limbo = limbo;
    }

    public List<Cartas> getMao() {
        return mao;
    }

    public void setMao(List<Cartas> mao) {
        this.mao = mao;
    }

    public List<Cartas> getMesa() {
        return mesa;
    }

    public void setMesa(List<Cartas> mesa) {
        this.mesa = mesa;
    }
    
    public void adiciona(Cartas c){
        baralho.add(c);
    }
}


