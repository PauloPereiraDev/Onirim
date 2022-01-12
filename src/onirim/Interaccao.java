/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Mário
 */
public class Interaccao {
    private Vector<String> ficheiros=new Vector<String>();
    private Vector<String> ficheiro_save=new Vector<String>();
    private Vector<String> menu =new Vector<String>();
   
    public Interaccao(){};

    public void mostralook(Jogo jogo,List<Cartas> look){
        Scanner s = new Scanner(System.in);
        int op;
        
         // escolhe carta a eliminar
         System.out.println("Qual a carta que deseja descartar");
        for(int i=0;i<look.size();i++)
            
            System.out.println("id" + i +"  "+ look.get(i).getTipo());
        do{ 
            op=s.nextInt();
        }while((op<0||op>look.size())||look.get(op).getTipo()=='D');
        look.remove(op);
        
        //escolhe nova ordem
        while(!look.isEmpty()){
            System.out.println("Qual a carta que deseja enviar para o baralho");
            for(int i=0;i<look.size();i++)
                
                System.out.println("id" + i +"  "+ look.get(i).getTipo());
            do{ 
                op=s.nextInt();
            }while(op<0||op>look.size()-1);
            jogo.adiciona(look.get(op));
            look.remove(op);
        }
   }
    public void MenuPrincipal(){
        
        if(menu.isEmpty()){
            menu.add("Novo jogo");
            menu.add("Carregar jogo");
            menu.add("Sair");
        }
        //ficheiros
        if(ficheiros.isEmpty())
            ficheiros.add("Nivel_base");
        
        //mostrar menu
        for(int i=0;i<menu.size();i++)
            System.out.println(i+1 + "." + menu.get(i) + ""); 
        
        int tecla;
        Scanner s = new Scanner(System.in);
        
       Jogo j= new Jogo();
       boolean b=true;
      
       while(b==true){
           System.out.print("Opção: ");
           tecla=s.nextInt();

           switch(tecla){
               case 1: j.jogar_main();
                   break;
               
               case 3:
                   System.out.println("Já que não quer nada comigo, vou-me embora :(");
                   System.exit(0);
               
               default:
                   System.out.println("Opção inválida!");
           }//switch
       }//while
    }//MenuPrincipal
    public int[] fase1(int tam){
        Scanner s = new Scanner(System.in);
        int[] op;
        op=new int [2];
        
//        System.out.println("Deseja jogar(1) ou descartar(2)?");
//         do{ 
//            op[0]=s.nextInt();
//         }while(op[0]<1||op[0]>6);
//         
//            System.out.println("Qual a carta que quer jogar/descartar?");
//            do{ 
//                op[1]=s.nextInt();
//            }while(op[1]<0||op[1]>tam);
//            return op;
        System.out.println("1-Jogar\n2-descarta\n3-Ver 7 cartas do topo\n4-acrescentar carta à mao\n5--acrescentar carta ao topo\n6--acrescentar carta à mesa");
        do{ 
            op[0]=s.nextInt();
        }while(op[0]<1||op[0]>6);
        switch(op[0]){
            case 1:System.out.println("Qual a carta que quer jogar?");
            do{ 
               op[1]=s.nextInt();
            }while(op[1]<0||op[1]>tam);break;
            case 2:System.out.println("Qual a carta que quer Descartar?");
            do{ 
               op[1]=s.nextInt();
            }while(op[1]<0||op[1]>tam);break;            
            case 3:break;            
            case 4:System.out.println("Qual a carta que quer acrescentar à mao?");
                   op[1]=s.nextInt();
            break;
            case 5:System.out.println("Qual a carta que quer acrescentar ao topo?");
                   op[1]=s.nextInt();
                break;
            case 6:
                System.out.println("Qual a carta que quer acrescentar à mesa?");
                op[1]=s.nextInt();break;
            default:break;
        }
        return op;
    }
    public void mostra_mao(List<Cartas> mao){
        for(int i=0;i<mao.size();++i)
            System.out.println(i+". "+mao.get(i).getTipo()+". "+mao.get(i).getCor());
    }
    public void mostra_baralho(List<Cartas> baralho,int t){
         for(int i=baralho.size()-1;i>=(baralho.size()-t);i--)
            System.out.println(i+". "+baralho.get(i).getTipo()+". "+baralho.get(i).getCor());
    }
    public void mostra_mesa(List<Cartas> mesa){
         for(int i=0;i<mesa.size();i++)
            System.out.println(i+". "+mesa.get(i).getTipo()+". "+mesa.get(i).getCor());
    }
}//classe Interaccao
