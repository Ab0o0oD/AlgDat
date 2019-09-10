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

        int partall = 0;
        int oddetall = 0;
        int m = (0 + a.length) / 2;


        for(int i = 0; i < a.length; i++){
            if(a[i] % 2 != 0){
                oddetall = a[i];
                oddetall++;
            }
            else {
                partall = a[i];
                partall++;
            }

            //oddetall = a[m+a.length];
            //partall = a[a.length+m-1];

            //Hvordan dele tabellen i to?
        }
    }
}
