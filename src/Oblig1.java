
////// Løsningsforslag Oblig 1 - 2019 ////////////////////////

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

    private Oblig1() {
    }

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {


        if(a.length == 0){
            throw new NoSuchElementException("Tabellen er tom");
        }

        int temp = 0;   //for mellomlagring av verdi

        for(int i = 0; i<a.length-1; i++){
            if(a[i]>a[i+1]){    //sammenligner et element, og elementet ved siden av
                temp = a[i];    //mellomlagrer a[i]
                a[i] = a[i+1];  //setter a[i] til å være neste element
                a[i+1] = temp;  //setter a[i] til å være temp, veriden vi mellomlagret
            }
        }

        int maks = a[a.length-1];   //maksverdi er da: verdien av siste element i arrayet
        return maks;

    }

       /*
    - Når blir det flest ombyttinger?
      Når største verdi ligger først.

    - Når blir det færrest?
      Når største verdi ligger bakerst.

    - Hvor mange blir det i gjennomsnitt?

        Hvor mange operasjoner det blir i gjennomsnitt:
        12+12n+9*log(n)-0,423

        I gjennomsnitt antall a[i]>a[i+1] er sann,
        altså hvor mange ombyttinger i gjennomsnitt: log(n)-0,423

     */


    public static int ombyttinger(int[] a) {

        if(a.length == 0){
            throw new NoSuchElementException("Tabellen er tom");
        }

        int temp = 0;
        int antallOmbyttinger = 0;

        for(int i = 0; i<a.length-1; i++){
            if(a[i]>a[i+1]){
                temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                antallOmbyttinger++;
            }
        }

        int maks = a[a.length-1];
        return antallOmbyttinger;
    }


    /*
    - Indikasjon på hvor mange det blir i gjennomsnitt.
      Er metoden maks bedre(eller dårligere) enn de maks-metodene
      vi har sett på tidligere?

        Mindre effektiv da den har flere antall operasjoner:
        12+12n+9*log(n)-0,423

     */



    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        int antallUlike = 1;

        if (a.length == 0){  //Sjekker om array er tomt, isåfall returneres 0
            return 0;
        }

        for(int i = 0; i< a.length-1; i++){     //Sjekker om arrayet er sortert
            if(a[i] > a[i+1]){
                throw new IllegalStateException("Tabellen er ikke sortert stigende");
            }
        }

        for(int i = 0; i<a.length-1; i++){  //Sjekker antall unike tall
            if(a[i] < a[i+1]){
                antallUlike++;
            }
        }

        return antallUlike;
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

        // får hjelpevariabel n som er lengden til a.
        int n = a.length;

        // Her sjekker vi om lengden til a  er stor nok til å kunne
        // gjøre en rotasjon. Ingenting skjer om n er mindre enn 2.
        if (n<2) return;

        // char b er hjelpevariabel som kopierer den siste i a.
        char b = a[n-1];

        // Under forflytter alle bokstavene i a et steg til høyre
        for (int i = n - 1; i >= 1; i--) {
            a[i] = a[i-1];
        }
        // Første indeks i a blir her satt til b, som var siste indeks før
        // rotasjonen.
        a[0] = b;
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        int n = a.length;
        if (n<2) {
            return; }
        k %= n;
        char[] b = new char[Math.abs(k)];
        int c = 0;

        // mot høyre
        if (k > 0) {
            for (int i = n - k; i < n; i++) {
                b[c++] = a[i];
            }
            for (int i = n - k - 1; i >= 0; i--) {
                a[k + i] = a[i];
                if (i < k) {
                    a[i] = b[i];
                }
            }

            // mot venstre

        } else if (k<0) {
            k = Math.abs(k);
            // Fra i = 0, mens i er mindre enn antall rotasjoner,
            // 1 øker for hver gang. Vi kopierer
            for(int i = 0; i<k; i++){
                b[i] = a[i];
            }

            for(int i = 0; i <= n-k-1; i++){
                a[i] = a[k+i];
            }

            for(int i = 0; i < b.length; i++){
                a[n-i-1] = b[k - i - 1];
            }

        }
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {

        int k = Math.min( s.length(), t.length() );


        StringBuilder sp = new StringBuilder();

        for (int i = 0; i < k; i++) {

            sp.append( s.charAt( i ) ).append( t.charAt( i ) );
        }

        sp.append( s.substring( k ) ).append( t.substring( k ) );

        return sp.toString();
    }

    /// 7b)
    public static String flett(String... s) {



        int storstelength = 0;

        for (int i = 0; i < s.length; i++) { //finner største string length i s Arrayet

            if (s[i].length() > storstelength) {

                storstelength = s[i].length();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= storstelength; i++) {

            for (int j = 0; j <= s.length - 1; j++) {

                if (i <= s[j].length()) {

                    sb.append( s[j].charAt( i - 1 ) );
                }
            }
        }
        return sb.toString();

    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {

        if (a.length == 0) return a;

        int[] temp = Arrays.copyOf( a, a.length );

        int[] indekser = new int[a.length];

        int maks = maksverdi( temp );
        int k = 0;

        for (int i = a.length; i > 1; i--) {
            int m = min( temp );
            indekser[k] = m;
            temp[m] = 2147483647;   //maks value for int
            k++;
        }
        indekser[temp.length - 1] = maks;

        for (int s : a) {
            System.out.print( s + " " );
        }
        return indekser;



    }


    ///// Oppgave 9 //////////////////////////////////////


    public static int[] tredjeMin(int[] a) {
        int n = a.length;
        if (n < 3) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 3!");

        int[] førsteTreiA = Arrays.copyOfRange(a,0,3);

        int[]    index = Oppgave8.indekssortering(førsteTreiA);


        int m = index[0];
        int nm = index[1];
        int nnm = index[2];

        int forsteMinsteVerdi = a[m];
        int andreMinsteVerdi = a[nm];
        int tredjeMinsteVerdi = a[nnm];

        for (int i = 3; i < n; i++) {

            if (a[i] < tredjeMinsteVerdi) { //Hvis a[i] er mindre enn trejminste så skal neste setning kjøres

                if (a[i] < andreMinsteVerdi) {  //Hvis a[i] er mindre enn nestminste verdi så skal neste setning kjøres

                    if (a[i] < forsteMinsteVerdi) {  //Hvis a[i] er mindre en minst verdi så skal ny verdi settes

                        nnm = nm;
                        tredjeMinsteVerdi = andreMinsteVerdi;

                        nm = m;
                        andreMinsteVerdi = forsteMinsteVerdi;

                        m = i;
                        forsteMinsteVerdi = a[m];

                    } else {
                        nnm = nm;
                        tredjeMinsteVerdi = andreMinsteVerdi;

                        nm = i;
                        andreMinsteVerdi = a[nm];

                    }
                } else {

                    nnm = i;
                    tredjeMinsteVerdi = a[nnm];
                }

            }
        }

        return new int[]{m, nm, nnm};//returnerer tabell med inndekser

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




//---------------------------------------------------------------------------------------//


    //Her brukte jeg hjelpe metoder fra kompendiet:

    public static void fratilKontroll(int tablengde, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ( "fra(" + fra + ") er negativ!" );

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ( "til(" + til + ") > tablengde(" + tablengde + ")" );

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ( "fra(" + fra + ") > til(" + til + ") - illegalt intervall!" );
    }


    public static int min(int[] a, int fra, int til) {

        if (a == null) throw new NullPointerException
                ( "parametertabellen a er null!" );

        fratilKontroll( a.length, fra, til );

        if (fra == til) throw new NoSuchElementException
                ( "fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!" );

        int m = fra;             // indeks til minste verdi i a[fra:til>
        int minsverdi = a[fra];  // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
            if (a[i] < minsverdi) {
                m = i;               // indeks til minste verdi oppdateres
                minsverdi = a[m];    // minste verdi oppdateres
            }

        return m;  // posisjonen til minst verdi i a[fra:til>
    }

    public static int maksverdi(int[] a, int fra, int til) {

        if (a == null) throw new NullPointerException
                ( "parametertabellen a er null!" );

        fratilKontroll( a.length, fra, til );

        if (fra == til) throw new NoSuchElementException
                ( "fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!" );

        int m = fra;             // indeks til største verdi i a[fra:til>
        int maksverdi = a[fra];  // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
            if (a[i] > maksverdi) {
                m = i;               // indeks til største verdi oppdateres
                maksverdi = a[m];    // største verdi oppdateres
            }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int maksverdi(int[] a)  // bruker hele tabellen
    {
        return maksverdi( a, 0, a.length );  // kaller metoden over
    }


    public static int min(int[] a)  // bruker hele tabellen
    {
        return min( a, 0, a.length );  // kaller metoden over
    }

}