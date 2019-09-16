import java.util.NoSuchElementException;

public class Oppgave1 {

    //Oppgave 1

    public static int maks (int[] a){

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

    public static int ombyttinger (int[] a){

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

}
