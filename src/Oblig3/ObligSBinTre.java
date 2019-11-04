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

    //Oppgave 6a)
    public String høyreGren() {
        StringBuilder ut = new StringBuilder();
        ut.append("[");
        Node<T> current = rot;

        if(tom()){
            ut.append("]");
        }
        else{
            ut.append(current.verdi);
            while(current.høyre != null || current.venstre != null){
                if(current.høyre != null) { //Går til høyre for å få den høyre grenen
                    current = current.høyre;
                    ut.append(",").append(" ").append(current.verdi);
                }
                else if(current.venstre != null){
                    current = current.venstre;
                    ut.append(",").append(" ").append(current.verdi);
                }
            }
            ut.append("]");
        }
        return ut.toString();
    }

    //Hjelpemetode til oppgave 6b)
    private void lengstgren(Node<T> node, int[] lengstGrenSize, String[] lengstGren, ArrayDeque<T> gren)
    {
        gren.addLast(node.verdi);  //Legger inn første verdi bakerst

        if (node.høyre == null && node.venstre == null) {
            if (gren.size() > lengstGrenSize[0]){  //Dersom den nye grenen sin lengde er lenger enn den hittil lengste
                lengstGrenSize[0] = gren.size();   //Sett den ny grenen til sin lengde til å være lengst
                lengstGren[0] = gren.toString();   //Legger inn hele grenen i lengstGren-arrayet
            }
        }
        else {                              //Kjører rekursjon for å finne gå igjennom treet
            if (node.venstre != null){
                lengstgren(node.venstre, lengstGrenSize, lengstGren, gren);
            }
            if (node.høyre != null){
                lengstgren(node.høyre, lengstGrenSize, lengstGren, gren);
            }
        }
        gren.removeLast();  //Fjerner siste verdi
    }

    //Oppgave 6b)
    public String lengstGren(){
        if (tom()){
            return "[]";
        }
        int[] lengstGrenSize = {0}; //Holder rede på lengden til den lengste grenen hittil
        String[] lengstGren = {null}; //Her legges verdiene til den lengste grenen inn fra gren dequen
        ArrayDeque<T> gren = new ArrayDeque<>(); //Deque som tar vare på verdiene i en gren
        lengstgren(rot,lengstGrenSize,lengstGren,gren);
        return lengstGren[0];
    }

    //Oppgave 7
    public String[] grener() {
        return finnGrener(rot);
    }

    public String[] finnGrener(Node<T> p)
    {
        ArrayDeque<String> grenListe = new ArrayDeque<>();
        String grenStreng = "";

        String[] tomStreng = {};

        if (p == null)
        {
            return tomStreng;
        }

        else
        {
            grenListe = finnAlleGrener(p, grenListe, grenStreng, 0);
        }

        int antallGrener = grenListe.size();
        String[] sluttStrenger = new String[antallGrener];
        sluttStrenger = tilStringArray(grenListe, sluttStrenger);
        return sluttStrenger;
    }

    public String[] tilStringArray(ArrayDeque<String> grenListe, String[] sluttStrenger)
    {

        int antallGrener = grenListe.size();
        int i = antallGrener - 1;

        for(String gren : grenListe)
        {
            sluttStrenger[i] = gren;
            i--;
        }

        return sluttStrenger;
    }

    public ArrayDeque finnAlleGrener(Node<T> p, ArrayDeque<String> stringListe, String grenStreng, int teller)
    {
        ArrayDeque<T> tempListe = new ArrayDeque<>();

        if (p == null)
        {
            return stringListe;
        }
        else if (p.venstre == null && p.høyre == null)
        {
            if (teller == 0)
            {
                grenStreng = "[" + grenStreng + p.toString() + "]";
            }
            else
            {
                grenStreng = grenStreng + ", " + p.toString() + "]";
            }
            stringListe.addFirst(grenStreng);
            return stringListe;

        }
        else
        {
            if (teller == 0)
            {
                grenStreng = grenStreng + "[" + p.toString();
            }
            else
            {
                grenStreng = grenStreng + ", " + p.toString();
            }
            teller = teller + 1;

            stringListe = finnAlleGrener(p.venstre, stringListe, grenStreng, teller);

            stringListe = finnAlleGrener(p.høyre, stringListe, grenStreng, teller);
        }

        return stringListe;
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
