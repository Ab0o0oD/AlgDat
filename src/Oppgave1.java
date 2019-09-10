import java.util.NoSuchElementException;

public class Oppgave1 {

    //Oppgave 1

    public static int maks (int[] a){

        if(a.length == 0){
            throw new NoSuchElementException("Tabellen er tom");
        }

        int temp = 0;

        for(int i = 0; i<a.length-1; i++){
            if(a[i]>a[i+1]){
                temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
            }
        }

        int maks = a[a.length-1];
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

     */

}
