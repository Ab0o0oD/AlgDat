import java.util.NoSuchElementException;

public class Oppgave4 {


    /**
     * Nikola
     * OPPGAVE 4
     *
     * Lag metoden ​public​ ​static​ ​void​ ​delsortering​(​int​[] a)​. Den skal dele parametertabellen ​a​ i to sorterte deler.
     * Venstre del skal inneholde oddetallene sortert og høyre del partallene sortert.
     * Flg. eksempel viser hvordan den skal virke:
     *
     * ​int​[] a = {6,10,9,4,1,3,8,5,2,7};
     * delsortering(a);
     * System.​out​.println(Arrays.​toString​(a));
     * // Utskrift: [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]
     *
     * Tabellen ​a​ kan være tom (det er ingen feilsituasjon), inneholde både negative og positive tall, kun
     * oddetall eller kun partall. ​Metoden skal ikke bruke hjelpetabeller​. Men en eller flere hjelpevariabler
     * kan inngå. Lag den så effektiv som mulig.
     */

    public static void delsortering(int[] a){

        if(a.length == 0) {
            return;
        }

        int antallPartall = 0;
        int antallOddetall = 0;
        int teller = 0;
        int temp = 0;


        //teller antall partall og oddetall
        for(int i = 0; i<a.length; i++){
            if(a[i] % 2 == 0) {
                antallPartall++;
            }
            else {
                antallOddetall++;
            }
        }

        //forsøk på å sortere oddetallene hvis det KUN er de som er i tabellen
        if (a.length == antallOddetall) {
            kvikksortering(a, 0, a.length-1);
        }


        //forsøk på å sortere oddetallene hvis det KUN er de som er i tabellen
        else if (a.length == antallPartall) {
            kvikksortering(a, 0, a.length-1);
        }

        else{
            for(int i = 0; i<a.length; i++){  //hvis det er oddetall skal den legge tallet som opprinnelig var der om temp og den bytter plass med partall???
                if(a[i] % 2 != 0){              //hvis den finner oddetall, så.....
                    temp = a[teller];           //teller er først 0 (det er plassen der det første oddetallet skal være
                    a[teller] = a[i];           //når den finner oddetall går tallet (a[i]) det første oddetallet til teller (indeksen den skal ligge på)
                    a[i] = temp;                //da blir oddetallet lik temp (teller 0)
                    teller++;                   //så går man videre i arrayet til teller = 1 (2 tallet i arrayet) og finner det andre oddetallet og setter det der, osv osv.
                }
            }

            //Quicksortering
            kvikksortering(a, 0, antallOddetall-1);
            kvikksortering(a, antallOddetall, a.length-1);
        }
    }

    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }

    public static void fratilKontroll(int tablengde, int fra, int til) {

        if (fra < 0) {                           // fra er negativ
            throw new ArrayIndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }

        if (til > tablengde) {                   // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException("til(" + til + ") > tablengde(" + tablengde + ")");
        }

        if (fra > til) {                         // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }

        //(1.2.3c)
        if (fra == til) {
            throw new NoSuchElementException("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks) {
        bytt(a, indeks, h);                         // skilleverdi a[indeks] flyttes bakerst
        int pos = sParter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, pos, h);                           // bytter for å få skilleverdien på rett plass
        return pos;                                // returnerer posisjonen til skilleverdien

    }

    public static void bytt(int[] a, int i, int j) { //returnerer ny tabell hver gang den kalles
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
