/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author Enrique
 */
public class ListaItemLibro {
    private Vector<ItemLibro> L ;

    public ListaItemLibro()
    {
        L = new Vector<ItemLibro>();
    }

    public void agregar(ItemLibro e)
    {
        L.add(e);
    }

    // el indexOf busca usando el equals de las clases
    public int buscar(Autor e)
    {
        return L.indexOf(e);
    }

    public void ordenar(Comparator<ItemLibro> cs)
    {
        Collections.sort(L,cs);
    }


    public int buscar(ItemLibro key,Comparator<ItemLibro> cs)
    {
        Collections.sort(L,cs);
        return Collections.binarySearch(L, key, cs);
    }

    public void eliminar(int i)
    {
        L.remove(i);
    }

    public ItemLibro getElemento(int i)
    {
        return L.get(i);
    }

    public int getN()
    {
        return L.size();
    }

    public void inserta(int i, ItemLibro e)
    {
        L.add(i,e);
    }

    public void reemplaza(int i, ItemLibro e)
    {
         L.set(i,e);
    }

    public Vector<ItemLibro> getL()
    {
        return L;
    }
    public Object[][] devuelvedatos()
    {
      Object datos[][]=new Object[L.size()][2];

      for(int i=0;i<L.size();i++)
      {
        ItemLibro x=L.get(i);
        datos[i][0]=x.getAutor().getIdautor();
        datos[i][1]=x.getAutor().getNombre();
      }
      return datos;
    }
}
