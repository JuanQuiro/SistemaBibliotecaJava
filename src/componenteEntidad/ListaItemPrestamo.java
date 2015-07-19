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
public class ListaItemPrestamo {
    private Vector<ItemPrestamo> L ;

    public ListaItemPrestamo()
    {
        L = new Vector<ItemPrestamo>();
    }

    public void agregar(ItemPrestamo e)
    {
        L.add(e);
    }

    // el indexOf busca usando el equals de las clases
    public int buscar(Libro e)
    {
        return L.indexOf(e);
    }

    public void ordenar(Comparator<ItemPrestamo> cs)
    {
        Collections.sort(L,cs);
    }


    public int buscar(ItemPrestamo key,Comparator<ItemPrestamo> cs)
    {
        Collections.sort(L,cs);
        return Collections.binarySearch(L, key, cs);
    }

    public void eliminar(int i)
    {
        L.remove(i);
    }

    public ItemPrestamo getElemento(int i)
    {
        return L.get(i);
    }

    public int getN()
    {
        return L.size();
    }

    public void inserta(int i, ItemPrestamo e)
    {
        L.add(i,e);
    }

    public void reemplaza(int i, ItemPrestamo e)
    {
         L.set(i,e);
    }

    public Vector<ItemPrestamo> getL()
    {
        return L;
    }
    public Object[][] devuelvedatos()
    {
      Object datos[][]=new Object[L.size()][2];

      for(int i=0;i<L.size();i++)
      {
        ItemPrestamo x=L.get(i);
        datos[i][0]=x.getLibro().getIdlibro();
        datos[i][1]=x.getLibro().getTitulo();
      }
      return datos;
    }
}
