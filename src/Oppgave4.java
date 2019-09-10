public class Oppgave4 {


    /**
     * Nikola
     *
     * Oppgavetekst
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
     * Tabellen ​a​ kan være tom (det er ingen feilsituasjon), inneholde både negative og positive tall,
     * kun oddetall eller kun partall. ​Metoden skal ikke bruke hjelpetabeller​. Men en eller flere
     * hjelpevariabler kan inngå. Lag den så effektiv som mulig.
     */

    public static void delsortering(int[] a){

        //Forsøk på å sortere tabellen før jeg deler den opp i partall og oddetall
        int m = 0;

        for(int i = 1; i < a.length; i++) { //starter nest bakerst
            if (a[i] > a[m]) { //indeksen oppdateres
                m = i;
            }
        }

        int partall = 0;
        int oddetall = 0;
        int m2 = (0 + a.length) / 2;         //prøver å lage en skille i midten


        for(int j = 0; j < a.length; j++){  //løper gjennom arrayet
            if(a[j] % 2 != 0){              //hvis det er rest skal den plasseres i oddetall hjelpevariavel?
                oddetall = a[j];
                oddetall++;                 //legger til alle tallene som er oddetall?
                System.out.println(oddetall);
            }
            else {                          //hvis det er partall skal det plasseres i partall variabelen
                partall = a[j];
                partall++;                  //legger til alle tallene som er partall?
                System.out.println(partall+" ");
            }

            //Hvordan dele tabellen i to?
            oddetall = a[m2+a.length];       //forsøk på å bestemme at venstre del av tabellen skal ha partall og andre skal ha oddetall
            partall = a[a.length+m2-1];

        }
    }
}
