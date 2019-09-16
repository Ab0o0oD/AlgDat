public class Oppgave4 {


    /**
     * Nikola
     * <p>
     * Oppgavetekst
     * <p>
     * Lag metoden ​public​ ​static​ ​void​ ​delsortering​(​int​[] a)​. Den skal dele parametertabellen ​a​ i to sorterte deler.
     * Venstre del skal inneholde oddetallene sortert og høyre del partallene sortert.
     * Flg. eksempel viser hvordan den skal virke:
     * <p>
     * ​int​[] a = {6,10,9,4,1,3,8,5,2,7};
     * delsortering(a);
     * System.​out​.println(Arrays.​toString​(a));
     * // Utskrift: [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]
     * <p>
     * Tabellen ​a​ kan være tom (det er ingen feilsituasjon), inneholde både negative og positive tall,
     * kun oddetall eller kun partall. ​Metoden skal ikke bruke hjelpetabeller​. Men en eller flere
     * hjelpevariabler kan inngå. Lag den så effektiv som mulig.
     */

    public static void delsortering(int[] a) {

        if (a.length == 0) {
            return;
        }

        int antallPartall = 0;
        int antallOddetall = 0;
        int teller = 0;
        int temp = 0;


        for (int i = 0; i < a.length; i++) { //teller antall partall og oddetall
            if (a[i] % 2 == 0) {
                antallPartall++;
            } else {
                antallOddetall++;
            }
        }

        if (a.length == antallOddetall) {
            for (int i = 0; i < a.length - 1; i++) { //forsøk på å sortere oddetallene
                if (a[i] > a[i + 1]) {
                    temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i + 1] = temp;
                }
            }
        }

        if (a.length == antallPartall) {
            for (int i = 0; i < a.length - 1; i++) { //forsøk på å sortere oddetallene
                if (a[i] > a[i + 1]) {
                    temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i + 1] = temp;
                }
            }
        }

        for (int i = 0; i < a.length - 1; i++) {  //hvis det er oddetall skal den legge tallet som opprinnelig var der om temp og den bytter plass med partall???
            if (a[i] % 2 != 0) { //hvis den finner oddetall, så.....
                temp = a[teller]; //teller er først 0 (det er plassen der det første oddetallet skal være
                a[teller] = a[i]; //når den finner oddetall går tallet (a[i]) det første oddetallet til teller (indeksen den skal ligge på)
                a[i] = temp; //da blir oddetallet lik temp (teller 0)
                teller++; //så går man videre i arrayet til teller = 1 (2 tallet i arrayet) og finner det andre oddetallet og setter det der, osv osv.
            }
        }

        for (int i = 0; i < antallOddetall; i++) { //forsøk på å sortere oddetallene
            if (a[i] > a[i + 1]) {
                temp = a[i + 1];
                a[i + 1] = a[i];
                a[i + 1] = temp;
            }
        }

        for (int i = antallOddetall; i < a.length - 1; i++) { //forsøk på å sortere partallene
            if (a[i] > a[i + 1]) {
                temp = a[i + 1];
                a[i + 1] = a[i];
                a[i + 1] = temp;
            }
        }
    }
}
