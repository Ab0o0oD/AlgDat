import java.util.NoSuchElementException;

public class Oppgave2 {

    public static int antallUlikeSorter(int[] a){

        int antallUlike = 0;
        if (a.length == 0){
            return antallUlike;
        }

        for(int i = 0; i<a.length-1; i++){
            if(a[i] > a[i+1]){
                throw new IllegalStateException("Tabellen er ikke sortert stigende");
            }
            else if(a[i] < a[i+1]){
                antallUlike++;
            }

            //Hvordan ta hensyn til om de er like?
        }
        return antallUlike;
    }


}
