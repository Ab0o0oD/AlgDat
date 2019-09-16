public class Oppgave6 {

    // Oppgave 6
    public static void rotasjon(char[] a, int rot) {

        int n = a.length;
        if (n<2) {
            return; }
        rot %= n;
        char[] b = new char[Math.abs(rot)];
        int c = 0;

        // mot høyre
        if (rot > 0) {
            for (int i = n - rot; i < n; i++) {
                b[c++] = a[i];
            }
            for (int i = n - rot - 1; i >= 0; i--) {
                a[rot + i] = a[i];
                if (i < rot) {
                    a[i] = b[i];
                }
            }

            // mot venstre

        } else if (rot<0) {
            rot = Math.abs(rot);
            // Fra i = 0, mens i er mindre enn antall rotasjoner,
            // 1 øker for hver gang. Vi kopierer
            for(int i = 0; i<rot; i++){
                b[i] = a[i];
            }

            for(int i = 0; i <= n-rot-1; i++){
                a[i] = a[rot+i];
            }

            for(int i = 0; i < b.length; i++){
                a[n-i-1] = b[rot - i - 1];
            }

        }
    }

}
