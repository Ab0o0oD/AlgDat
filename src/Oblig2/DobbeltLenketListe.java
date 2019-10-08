package Oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

      
  // hjelpemetode
  private void indeksKontroll(int indeks) {
    if (indeks < 0)
    {
      throw new IndexOutOfBoundsException("Indeks " +
        indeks + " er negativ!");
    }
    else if (indeks >= antall)
    {
      throw new IndexOutOfBoundsException("Indeks " +
        indeks + " >= antall(" + antall + ") noder!");
    }
  }

  // hjelpemetode

  private Node<T> finnNode(int indeks) {

      Node<T> p;

      if (indeks <= antall / 2) {
          p = hode;

          for (int i = 0; i < indeks; i++){
              p = p.neste;
          }
      }
      else {
          p = hale;
          for (int i = antall - 1; i > indeks; i--){
              p = p.forrige;
          }
      }

      return p;
  }




    ////////////////////////////////////////////
    

    
    public DobbeltLenketListe() {
        //throw new NotImplementedException();
    }

    public DobbeltLenketListe(T[] a) {

            Objects.requireNonNull(a, "Tabellen a er null");

            int teller = 0;

            Node p = new Node(null, null, null);

            //Setter hode
            for (int i = 0; i < a.length; ++i) {
                if (a[i] != null) {
                    p.verdi = a[i];
                    hode = p;
                    teller++;
                    antall++;
                    break;

                } else {
                    teller++;
                }

            }

            if (a.length == 1){
                hale = hode;
            }
            else {
                // Legger inn resterende noder
                for (int i = teller; i < a.length; ++i) {

                    //Sjekker at verdien ikke er en null verdi
                    if (a[i] != null) {
                        //Lager ny node
                        Node q = new Node(a[i]);

                        //Setter forrige pekeren til p
                        //Setter neste pekeren til q
                        q.forrige = p;
                        p.neste = q;

                        //p peker på q
                        p = q;

                        //halen settes til q
                        hale = q;
                        antall++;
                    }
                }
            }
        }


    public Liste<T> subliste(int fra, int til){
            fratilKontroll(antall, fra, til);
            int antallElement = til - fra;
            if(antallElement < 1) return new DobbeltLenketListe<>();

            Node<T> current = finnNode(fra);

            DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

            while(antallElement > 0) {
                subliste.leggInn(current.verdi);
                current = current.neste;
                antallElement--;
            }
            return subliste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        boolean tom = false;
        if(antall() == 0){
            tom = true;
        }
        return tom;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null-verdier ikke tillatt");

        Node p = new Node(verdi, null, null);

        //listen på forhånd tom
        if(hale == null && hode == null){
            p.forrige = null;
            hode = p;
            hale = p;
            antall++;
            endringer++;
        }

        //listen er ikke tom
        else{
            hale.neste = p;
            p.forrige = hale;
            hale = p;
            antall++;
            endringer++;
        }

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {

        Objects.requireNonNull(verdi, "Null-verdier ikke tillatt.");
        Node<T> nyNode = new Node<>(verdi, null, null);

        if (indeks < 0 || indeks > antall)
        {
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " er negativ!");
        }

        else if (hode == null && hale == null)
        {
            hode = nyNode;
            hale = nyNode;
        }
        else if (indeks == 0)
        {
            nyNode.neste = hode;
            hode.forrige = nyNode;
            hode = nyNode;

        }
        else if (indeks == antall)
        {
            nyNode.forrige = hale;
            hale.neste = nyNode;
            hale = nyNode;
        }
        else
        {
            Node<T> nodeTilHøyre = finnNode(indeks);
            nyNode.forrige = nodeTilHøyre.forrige;
            nyNode.neste = nodeTilHøyre;
            nodeTilHøyre.forrige = nyNode;
            nyNode.forrige.neste = nyNode;
        }

        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks);

        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {

        Node<T> p = hode;

        for (int i = 0; i < antall; i++, p = p.neste) {
            if (p.verdi.equals(verdi)) {

                return i;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        indeksKontroll(indeks);

        if (nyverdi == null) {
            throw new NullPointerException("Ikke tillatt med null-verdier");
        }

        Node<T> p = finnNode(indeks);

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        endringer++;   // en endring i listen
        return gammelverdi;
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) return false;

        Node<T> p = hode;

        while (p != null){

            if (p.verdi.equals(verdi)) break;
            p = p.neste;
        }

        if (p == null) {
            return false;
        }
        else if (antall == 1){
            hode = hale = null;
        }
        else if (p == hode) {
            hode = hode.neste;
            hode.forrige = null;
        }
        else if (p == hale) {
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        p.verdi = null;
        p.forrige = p.neste = null;

        antall--;
        endringer++;

        return true;
    }

    @Override
    public T fjern(int indeks) {

      indeksKontroll(indeks);
        Node<T> p = hode;

        if (antall == 1){
            hode = hale = null;
        }
        else if (indeks == 0){
            hode = hode.neste;
            hode.forrige = null;
        }

        else if (indeks == antall - 1){
            p = hale;
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            p = finnNode(indeks);
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        T verdi = p.verdi;
        p.verdi = null;
        p.forrige = p.neste = null;

        antall--;
        endringer++;

        return verdi;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if(!tom()){
            stringBuilder.append("[");
            Node current = hode;
            stringBuilder.append(current.verdi);
            current = current.neste;
            while(current != null){
                stringBuilder.append(',').append(' ').append(current.verdi);
                current = current.neste;
            }
            stringBuilder.append("]");
        }
        else{
            stringBuilder.append("[]");
        }
        return stringBuilder.toString();
    }

    public String omvendtString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(!tom()){
            stringBuilder.append("[");
            Node current = hale;
            stringBuilder.append(current.verdi);
            current = current.forrige;
            while(current != null){
                stringBuilder.append(',').append(' ').append(current.verdi);
                current = current.forrige;
            }
            stringBuilder.append("]");
        }
        else{
            stringBuilder.append("[]");
        }

        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
       return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks);
        return new DobbeltLenketListeIterator(indeks);
    }
    

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;
        
         private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
}

        private DobbeltLenketListeIterator(int indeks){
            indeksKontroll(indeks);
            denne = finnNode(indeks);     
            fjernOK = false;  
            iteratorendringer = endringer;  

        }

        
        @Override
        public boolean hasNext(){
            return denne != null;
}

        @Override
        public T next(){
           if(iteratorendringer != endringer){
               throw new ConcurrentModificationException(); 
           } else if (!hasNext()) {
               throw new NoSuchElementException();
           } else {
               fjernOK = true;
               T orgVerdi = denne.verdi;
               denne = denne.neste;
               return orgVerdi;
           }         
        }



        @Override
        public void remove(){
            if (!fjernOK) throw
                    new IllegalStateException("kan ikke fjernes uten å kalle next()");

            if (iteratorendringer != endringer) throw
                    new ConcurrentModificationException("liste er endret");

            fjernOK = false;
            Node<T> q = hode;

            if (antall == 1)    // hvis det er bare en node i listen
            {
                hode = hale = null;
            }
            else if (denne == null)  // hvis den siste skal fjernes
            {
                q = hale;
                hale = hale.forrige;
                hale.neste = null;
            }
            else if (denne.forrige == hode)  // hvis den første skal fjernes
            {
                hode = hode.neste;
                hode.forrige = null;
            }
            else
            {
                q = denne.forrige;  // q skal fjernes
                q.forrige.neste = q.neste;
                q.neste.forrige = q.forrige;
            }

            q.verdi = null;
            q.forrige = q.neste = null;

            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator



    public static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");
        if (fra > til)                                  // fra er negativ
            throw new IllegalArgumentException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > antall)                                // fra er større enn til
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        for (int i = 0; i < liste.antall(); i++){

            for (int j = 0; j < liste.antall(); j++) {


                if ((c.compare(liste.hent(i), liste.hent(j))) < 0) {

                    T venstre = liste.hent(i);
                    T hoyre = liste.hent(j);


                    liste.oppdater(i, hoyre);
                    liste.oppdater(j, venstre);

                }
            }
        }

    }

    @Override
    public void nullstill (){


        Node<T> p = hode;
        Node<T> q;
        while (p.neste != null) {
            q=p.neste;
            fjern( 0 );
            p = q;
        }
        hode = hale = null;
        antall = 0;
        endringer++;

    }




} // class DobbeltLenketListe


