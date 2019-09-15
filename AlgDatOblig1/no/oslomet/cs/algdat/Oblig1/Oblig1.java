package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 - 2019 ////////////////////////

// Laget av Liv Ellingsen (s305483), ...

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class Oblig1 {
    private Oblig1() {
    }

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        throw new NotImplementedException();
    }

    public static int ombyttinger(int[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        throw new NotImplementedException();
    }


    ///// Oppgave 3 //////////////////////////////////////
    
// Passerer testene i testfilen

    public static int antallUlikeUsortert(int[] a) {
 
        // Sjekker for tomt array:
        
        if(a.length == 0){                               
            return 0; // Tom liste gir 0 unike tall 
        }        
        
        // teller  i for-loekke og int ulike starter paa 1 siden foerste 
        // element alltid vil vaere unikt, 
        // pluss kan da angi a-1 uten aa komme utenfor array:
        
        int ulike = 1; // Foerste element vil vaere unikt.
        
        for(int i = 1; a.length > i ; i++){
            
            boolean tallFinnes = finnesFraFoer(a, a[i], i-1);
            if (tallFinnes){
            } else { 
                ulike = ulike + 1;
            }
        }    
        
        System.out.println("Antall ulike er " + ulike);
        return ulike;
    }
    
//    finnesFraFoer(int[], int, int) gaar gjennom arrayet fra start frem til elementet 
//    foer det tallet vi vurderer om er unikt, og gir true dersom det allerede finnes i 
//    listen og false dersom det ikke allerede finnes i listen.      
//    sjekke om denne allerede finnes i kode...:
    
    public static boolean finnesFraFoer(int[] a, int tall, int til){
        
        for(int i = 0; i <= til; i++){
            if(tall == a[i]) {
                return true;
            }
        }
        return false;    
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        throw new NotImplementedException();
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        throw new NotImplementedException();
    }

    /// 7b)
    public static String flett(String... s) {
        throw new NotImplementedException();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new NotImplementedException();
    }


    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new NotImplementedException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    
    // Passerer testene i testfilen
    
    public static boolean inneholdt(String a, String b) {

        //Den tomme strengen er inneholdt i alle andre strenger.
        // Ikke noe annet enn den tomme strengen kan vaere 
        // inneholdt i en tom streng:
        
        if("".equals(a)){
            return true;
        } else if("".equals(b)){
            return false;
        }
        
        // sorterer begge strengene til char array med quicksort algoritme:
        char[] charArray1 = sorterStringTilChar(a);
        char[] charArray2 = sorterStringTilChar(b);
        
        // sjekker om streng a er inneholdt i streng b og lagrer resultatet i
        // variabelen resultat:
        boolean resultat = erInneholdt(charArray1, charArray2, 
                charArray1.length, charArray2.length);

        return resultat;        
    }
    
    public static boolean erInneholdt(char[] a, char[] b, int lengdeA, int lengdeB) {
        
        int i = 0;
        int j = 0;
        
        // vi sammenligner arrayene a og b saa lenge vi er innenfor lengden av begge.
        while(i < lengdeA && j < lengdeB) {
            
            // Saa lenge foerste char i a er stoerre enn foerste char i b 
            // traverserer vi bare videre i b:
            if(a[i] > b[j]) {
            j++;
            }
            // Hvis foerste char i a og b er lik, gaar vi videre til neste char
            // i baade a og b og sammenligner denne.
            else if(a[i] == b[j]){
                i++;
                j++;
            }
            // Dersom foerste char i a er mindre enn foerste char i b kan ikke
            // a lenger vaere inneholdt i b og vi returnerer false.
            else if (a[i] < b[j]) {
                return false;
            }
        }
        // Hvis vi har sammenlignet de to arrayene saa langt uten aa returnere false,
        // returnerer testen true dersom det ikke gjenstaar flere chars i a som 
        // ikke er matchet i b.
        if (i < lengdeA) {
            return false;
            } 
            else {
            return true;
            }
        
    }
    
public static char[] sorterStringTilChar(String a){
    
    // vi konverterer stringen til et char-array.
    char[] charArray = a.toCharArray(); 
    
    // vi finner foerste og siste posisjon i arrayet.
    int foerste = 0, siste = a.length() -1;
    
    // Vi sorterer arrayet med en quicksort-algoritme.
    quickSort(charArray, foerste, siste);
    
    return charArray;
 
}


// QuickSort-algoritme hentet fra begynnelse paa implementasjon under forelesning, 
// supplert fra pensumlitteratur og internett siden vi ikke ble ferdig under 
// forelesning.

public static void quickSort(char[] values, int left, int right){

        
    int i = left;
    int j = right;
    char temp;

    int pivot = (left + right) / 2;

    while (i <= j) {
        while(values[i] < values[pivot]){
            i++;
        }
        while(values[j] > values[pivot]){
            j--;
        }

        if(i <= j) {
            temp = values[i];
            values[i] = values[j];
            values[j] = temp;
            i++;
            j--;
        }
    }
    if(left < j){
        quickSort(values, left, j);
    }
    if(i < right){
        quickSort(values, i, right);
    }
        }
   

}  // Oblig1
