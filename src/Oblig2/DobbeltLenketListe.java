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
        throw new NotImplementedException();
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
        Node nyNode = new Node<>(verdi, null, null);

        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Indeks må være større enn 0 og mindre enn "+antall);
        }
        else if (hode == null && hale == null){
            hode = nyNode;
            hale = nyNode;
        }
        else if (hode.neste == null) {
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
        }
        else if (indeks == antall) {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        }
        else {
            Node<T> nodeTilHøyre = finnNode(indeks);
            Node<T> nodeTilVenstre = nodeTilHøyre.forrige;

            nyNode.forrige = nodeTilVenstre;
            nyNode.neste = nodeTilHøyre;
        }

        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            throw new NotImplementedException();
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


