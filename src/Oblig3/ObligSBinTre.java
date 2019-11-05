package Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

import java.lang.StringBuilder;


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
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot;
        Node<T> q = null;

        int cmp = 0;  // hjelpevariabel

        while (p != null) {     // fortsetter til p er ute av treet
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi, p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        p = new Node<T>(verdi, null, null, null);


        if (q == null) {
            rot = p;            // p blir rotnode om q == 0
        } else if (cmp < 0) {
            // q.venstre = p;
            q.venstre = p;      // p blir venstre barn til q
            p.forelder = q;     // p sin forelder blir q;
        } else {
            q.høyre = p;         // p blir høyre barn til q
            p.forelder = q; // Og p sin forelder blir q venstre
        }
        this.antall++;                           // én verdi mer i treet
        endringer++;
        return true;                            // vellykket innlegging
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
  public boolean fjern(T verdi) {
      if (verdi == null) return false;
      Node<T> p = rot, q = null;

      while (p != null) {
          int cmp = comp.compare(verdi, p.verdi);
          if (cmp < 0) { q = p; p = p.venstre;
          } else if (cmp > 0) { q = p; p = p.høyre;
          } else break;
      }
      if (p == null) return false;
      endringer--;

      if (p.venstre == null || p.høyre == null) {
          Node<T> b = p.venstre != null ? p.venstre : p.høyre;
          if (p == rot) {
              rot = b;

          } else if (p == q.venstre) {
              q.venstre = b;

          } else {
              q.høyre = b;
              if (b != null) b.forelder = q;
          }
      } else {
          Node<T> s = p, r = p.høyre;
          while (r.venstre != null) {
              s = r;
              r = r.venstre;
          }

          p.verdi = r.verdi;

          if (s != p) {
              s.venstre = r.høyre;
          } else {
              s.høyre = r.høyre;
              r.høyre.forelder = s;
          }
          endringer++;
      }
      antall--;
      return true;
  }

    public int fjernAlle(T verdi) {
        int i = 0;
        boolean fjernet = true;
        while(fjernet!=false){
            if(fjern(verdi))
                i++;
            else
                fjernet = false;
        }
        return i;
    }
  
  @Override
  public int antall()
  {
    return antall;
  }

    public int antall(T verdi) {

        if (verdi == null) {
            return 0; }

        int count = 0;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else if (cmp > 0) {
                p = p.høyre;
            } else {
                // DUPLIKATER GÅR ALLTID TIL HØYRE I TREET !!
                count++;
                p = p.høyre;
            }
        }
        return count;
    }
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill() {
      if (!tom()) nullstill(rot);
      rot = null;
      antall = 0;
  }

    private void nullstill(Node<T> p) {
        if (p.venstre != null) {
            nullstill(p.venstre);
            p.venstre = null;
        }
        if (p.høyre != null) {
            nullstill(p.høyre);
            p.høyre = null;
        }
        p.verdi = null;
        endringer++;
    }
  
    
  private static <T> Node<T> nesteInorden(Node<T> p) {
      Node<T> neste = null;
      Node<T> ane = p.forelder;
      
      if (p.høyre != null) {
          neste = finnLavesteVenstre(p.høyre);
      }
      else if (ane != null && p == ane.venstre) {
          neste = ane;
      }    
      else if (p.høyre == null) {
          while (ane != null && p == ane.høyre) {
                p = ane;
                ane = ane.forelder;
              }
          neste = ane;  
      }   
      else if (ane.høyre != null) {
          neste = finnLavesteVenstre(ane.høyre);
      }

      else if (ane.høyre == null) {
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


    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Stack stakk = new Stack();
        Node p = finnLavesteVenstre(rot);
        if(p != null){
            stakk.push (p);
            while (nesteInorden(p) != null) {
                p = nesteInorden(p);
                stakk.push (p);
            }
        }

        while ( !stakk.empty() )
        {
            s.append( stakk.pop() );
            if(!stakk.empty())
                s.append(",").append(" ");
        }
        s.append(']');
        return s.toString();
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


    //Oppgave8:
    //a)

    public String bladnodeverdier() {

        StringJoiner s = new StringJoiner(", ", "[",  "]");

        Node p = rot;
        if (rot == null){
            return s.toString ();

        }

        TabellStakk<Node> stakk = new TabellStakk<> () ;
        stakk.leggInn (p);
        while (!stakk.tom ()){
            Node q = stakk.taUt ();

            if (q.høyre != null){
                stakk.leggInn (q.høyre);
            }
            if(q.venstre != null){
                stakk.leggInn (q.venstre);
            } if (q.venstre == null && q.høyre == null){
                s.add (q.verdi.toString ());


            }
        }
        return s.toString ();
    }



    //8.b)

    public String postString() {


        StringJoiner s = new StringJoiner(", ", "[",  "]");


        Node p = rot;
        if (rot == null){
            return s.toString ();

        }
        TabellStakk<Node> stakk = new TabellStakk<> () ;
        stakk.leggInn (p);

        while (!stakk.tom ()){

            Node q = stakk.kikk ();

            if (q.høyre == null && q.venstre == null){
                Node r = stakk.taUt ();

                if (r.verdi != null) {

                    s.add (r.verdi.toString ());

                }
            }
            else{
                if(q.høyre != null){
                    stakk.leggInn (q.høyre);
                    q.høyre = null;
                }
                if (q.venstre !=null){
                    stakk.leggInn (q.venstre);
                    q.venstre=null;
                }
            }
        }

        return s.toString ();
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

      private BladnodeIterator() { // konstruktør
          if(tom()){
              return;
          }
          while(p.venstre != null || p.høyre != null){
              if(p.venstre != null) {
                  p = p.venstre;
              }
              else if(p.høyre != null){
                  p = p.høyre;
              }
          }
      }
    
    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Ikke flere noder!");
        }
        if (iteratorendringer != endringer) {
            throw new ConcurrentModificationException("Iteratorendring er " + iteratorendringer + " og " + endringer);
        }
        T a = p.verdi;
        q = p;
        p = nesteInorden(p);

        while (p != null && (p.venstre != null || p.høyre != null)) {
            p = nesteInorden(p);
        }
        removeOK = true;
        endringer++;
        iteratorendringer++;
        return a;
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
