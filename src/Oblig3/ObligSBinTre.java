package no.oslomet.cs.algdat.Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  
  @Override
  public boolean leggInn(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      else return true;
    }

    return false;
  }
  
  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public int fjernAlle(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
    
  private static <T> Node<T> nesteInorden(Node<T> p)
  {
      Node<T> neste = null;
      Node<T> ane = p.forelder;
      
      if (p.høyre != null)
      {
          neste = finnLavesteVenstre(p.høyre);
      }
      else if (ane != null && p == ane.venstre)
      {
          neste = ane;
      }    
      else if (p.høyre == null)
      {
          while (ane != null && p == ane.høyre)
              {
                p = ane;
                ane = ane.forelder;
              }
          neste = ane;  
      }   
      else if (ane.høyre != null) 
      {
          neste = finnLavesteVenstre(ane.høyre);
      }

      else if (ane.høyre == null)
      {
          while (ane != null && p == ane.høyre)
              {
                p = ane;
                ane = ane.forelder;
              }
          neste = ane;           
      }
      return neste;
  
  }

  
  public static <T> Node<T> finnLavesteVenstre(Node<T> p)
  { 
      while (p != null)
      {
          if (p.venstre != null)
         { 
             p = p.venstre;
        }
        else return p;
      }      
      return p;
  }
  
  
  @Override
  public String toString()
  {      
    StringBuilder sb = new StringBuilder();
    
    if (!tom())
    {
        Node<T> forsteNode, nesteNode = null;
        forsteNode = finnLavesteVenstre(rot);
        
        sb.append("[");
        sb.append(forsteNode.verdi);
        
        if (antall > 1)
        {
            nesteNode = nesteInorden(forsteNode);
        }
        while (nesteNode != null)
        {
            sb.append(',').append(' ').append(nesteNode.toString());
            nesteNode = nesteInorden(nesteNode);
        }
        
        sb.append("]");
    }
    
    else sb.append("[]");
    
    return sb.toString();

    }
  
  
  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String høyreGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String lengstGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String postString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    private int iteratorendringer = endringer;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    
    @Override
        public void remove()
    {
      if (iteratorendringer != endringer) 
      {  
        throw new ConcurrentModificationException();
      }
      
      if (q == null)
      {
        throw new IllegalStateException();
      }
      
      if (bladNode(q))
      {
        if (q.forelder != null)
        {
            if (venstre(q)) q.forelder.venstre = null;
            else if (hoeyre(q)) q.forelder.høyre = null;
        }
        
        settForrigeBladnode();
        
        removeOK = false;
        endringer++;
        antall--;
        iteratorendringer++;   
      }
      else 
      {
        throw new IllegalStateException();
      }
    }
        
            
    public boolean hoeyre(Node<T> barn)
    {
        Node<T> ane = barn.forelder; 
        return (barn == ane.høyre);
    }
    
    public boolean venstre(Node<T> barn)
    {
        Node<T> ane = barn.forelder; 
        return (barn == ane.venstre);
    }

    public boolean bladNode(Node<T> node)
    {
        return (node.venstre == null && node.høyre == null);
    }
    
    public void settForrigeBladnode() 
    {
      Node<T> passendeAne = finnPassendeAne(q, "hoeyre");
     
      if (passendeAne != null) 
      {
        finnLaveste(passendeAne.venstre);
      }
      
      if (passendeAne == null) 
      {
          q = null;
      }
    }
        
    public void finnLaveste(Node<T> forrige) 
    {
        if (forrige.venstre == null && forrige.høyre == null)
        {
            q = forrige;
            return;
        }
        else if (forrige.høyre != null)
        {           
            finnLaveste(forrige.høyre);
        }  
        else if (forrige.høyre == null)
        {  
            finnLaveste(forrige.venstre);
        }
    }
    
  } // BladnodeIterator
  
  
      public Node<T> finnPassendeAne(Node<T> foerste, String retning)
    {
        Node<T> ane = foerste.forelder;
        
        boolean t = passende(ane, foerste, retning);
        
        while (!t && ane != null)
        {
            foerste = ane;
            ane = ane.forelder;
            t = passende(ane, foerste, retning);
        }
        
        if (passende(ane, foerste, retning)) 
        {
            return ane;
        }
        
        else return null;
    }
    
    public boolean passende(Node<T> ane, Node<T> barn, String retning)
    {
        if (ane == null) return false;
        else if (retning == "venstre")
        {
            return (barn == ane.venstre && ane.høyre != null);
        }
        else if (retning == "hoeyre")
        {
            return (barn == ane.høyre && ane.venstre != null);
        }
        return false;
    }

} // ObligSBinTre
