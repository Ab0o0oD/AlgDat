import java.util.NoSuchElementException;

public class Oppgave2 {


    public static int ombyttinger (int[] a){

        if(a.length == 0){
            throw new NoSuchElementException("Tabellen er tom");
        }

        int temp = 0;
        int hjelpevariabel = 0;

        for(int i = 0; i<a.length-1; i++){
            if(a[i]>a[i+1]){
                temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                hjelpevariabel++;
            }
        }

        int maks = a[a.length-1];
        return hjelpevariabel;
    }


    /*
    - Indikasjon på hvor mange det blir i gjennomsnitt.
      Er metoden maks bedre(eller dårligere) enn de maks-metodene
      vi har sett på tidligere?



     */

}
