public class Oppgave5 {

 // Oppgave 5

    public static void rotasjon (char[]  a) {

        // får hjelpevariabel n som er lengden til a.
        int n = a.length;

        // Her sjekker vi om lengden til a  er stor nok til å kunne
        // gjøre en rotasjon. Ingenting skjer om n er mindre enn 2.
        if (n<2) return;

        // char b er hjelpevariabel som kopierer den siste i a.
        char b = a[n-1];

        // Under forflytter alle bokstavene i a et steg til høyre
        for (int i = n - 1; i >= 1; i--) {
            a[i] = a[i-1];
        }
        // Første indeks i a blir her satt til b, som var siste indeks før
        // rotasjonen.
        a[0] = b;
    }


}
