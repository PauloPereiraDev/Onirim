/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onirim;

import java.util.List;

/**
 *
 * @author Mário
 */
public interface State {
    public void maoinicial();
    public void baralha();
    public int compra_carta();
    public boolean joga_carta(int ind);
    public List<Cartas> profecia();
    public List<Cartas> descarta_carta(int ind);
}
