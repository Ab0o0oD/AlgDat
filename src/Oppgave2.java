import java.util.NoSuchElementException;

public class Oppgave2 {

    public static int antallUlikeSorter(int[] a){

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
}
