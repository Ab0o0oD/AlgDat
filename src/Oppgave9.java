public class Oppgave9 {
    public static int[] tredjeMin(int[] a) {
        int n = a.length;
        if (n < 3) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 3!");



        int[] indekssortering = Oppgave8.indekssortering(a);

        int m = indekssortering[0];
        int nm = indekssortering[1];
        int nnm = indekssortering[2];


        int førsteMinsteVerdi = a[m];
        int andreMinsteVerdi = a[nm];
        int tredjeMinsteVerdi = a[nnm];

        for (int i = 3; i < n; i++) {

            if (a[i] < tredjeMinsteVerdi) { //Hvis a[i] er mindre enn trejminste så skal neste setning kjøres

                if (a[i] < andreMinsteVerdi) {  //Hvis a[i] er mindre enn nestminste verdi så skal neste setning kjøres

                    if (a[i] < førsteMinsteVerdi) {  //Hvis a[i] er mindre en minst verdi så skal ny verdi settes

                        nnm = nm;

                        tredjeMinsteVerdi = andreMinsteVerdi;



                        nm = m;

                        andreMinsteVerdi = førsteMinsteVerdi;



                        m = i;

                        førsteMinsteVerdi = a[m];

                    } else {

                        nnm = nm;

                        tredjeMinsteVerdi = andreMinsteVerdi;



                        nm = i;

                        andreMinsteVerdi = a[nm];

                    }

                } else {

                    nnm = i;

                    tredjeMinsteVerdi = a[nnm];

                }

            }

        }

        return new int[]{m, nm, nnm};//returnerer tabell med inndekser

    }
}
