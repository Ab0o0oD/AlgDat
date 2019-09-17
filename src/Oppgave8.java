import java.util.Arrays;
import java.util.NoSuchElementException;

/*Oppgave 8
    Lag metoden public static int [] indekssortering ( int [] a) . Den skal returnere en tabell
    med indekser til verdiene i tabellen a der a ikke skal endres. Se på. flg. eksempel:
    int [] a = {6,10,16,11,7,12,3,9,8,5};
    int [] indeks = indekssortering (a);
System. out .println(Arrays. toString (a)); // skriver ut a
System. out .println(Arrays. toString (indeks)); // skriver ut indeks
    // Utskrift: [6, 10, 16, 11, 7, 12, 3, 9, 8, 5] a er ikke endret
// Utskrift: [6, 9, 0, 4, 8, 7, 1, 3, 5, 2]
    Første verdi i indeks-tabellen, dvs. indeks[0] , skal være indeksen til den minste verdien i a . Vi
    ser av utskriften at det er 6 og det passer siden den minste verdien i a (dvs. 3) har indeks 6. Neste
    verdi i indeks-tabellen, dvs. indeks[1] skal være indeksen til den nest minste verdien i a .
    Utskriften gir at indeks[1] = 9 og den nest minste verdien i a (dvs. 5) har indeks 9. Osv. til den
    siste. Det er 2 som er indeksen til den største verdien (dvs. 16) i a .
    På denne måten kan vi få ut verdiene i a i sortert rekkefølge ved å gå veien om indeks-tabellen.
            Dvs. slik
    int [] a = {6,10,16,11,7,12,3,9,8,5};
    int [] indeks = indekssortering (a);
for ( int i = 0; i < a. length ; i++) System. out .print(a[indeks[i]] + " " );
    // Utskrift: 3 5 6 7 8 9 10 11 12 16
    Dette kan løses på flere måter. Her blir det ikke lagt vekt på effektivitet, men kun på at det
    virker. Du kan også bruke hjelpetabeller - en eller flere. Opprett i hvert fall en tabell indeks av
    typen int[] med samme lengde som a . Det er den som til slutt skal returneres. Tabellen a kan
    inneholde like verdier! I så fall blir det ingen entydig løsning. Hvis f.eks. den minste verdien
    forkommer to ganger, spiller det ingen rolle hvem av dem indeks[0] refererer til. Men da
    må indeks[1] referere til den andre av de to. Hvis a er tom, skal også returtabellen være tom.*/
public class Oppgave8 {


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

    public static int maks(int[] a, int fra, int til) {

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

    public static int maks(int[] a)  // bruker hele tabellen
    {
        return maks( a, 0, a.length );  // kaller metoden over
    }


    public static int min(int[] a)  // bruker hele tabellen
    {
        return min( a, 0, a.length );  // kaller metoden over
    }


    // Kode bit for indeks sortering

    public static int[] indekssortering(int[] a) {

        if (a.length == 0) return a;

        int[] temp = Arrays.copyOf( a, a.length );

        int[] indekser = new int[a.length];

        int maks = maks( temp );
        int k = 0;

        for (int i = a.length; i > 1; i--) {
            int m = min( temp );
            indekser[k] = m;
            temp[m] = 2147483647;   //maks value for int
            k++;
        }
        indekser[temp.length - 1] = maks;

        return indekser;
    }
}

