/*Oppgave 8
    Lag metoden public static int [] indekssortering ( int [] a) . Den skal returnere en tabell
    med indekser til verdiene i tabellen a der a ikke skal endres. Se på. flg. eksempel:
    int [] a = {6,10,16,11,7,12,3,9,8,5};
    int [] indeks = indekssortering (a);
System. out .println(Arrays. toString (a)); // skriver ut a
System. out .println(Arrays. toString (indeks)); // skriver ut indeks
    // Utskrift: [6, 10, 16, 11, 7, 12, 3, 9, 8, 5] a er ikke endret
// Utskrift: [6, 9, 0, 4, 8, 7, 1, 3, 5, 2]
    Første verdi i indeks-tabellen, dvs. indeks[0] , skal være indeksen til den minste verdien i a . Vi
    ser av utskriften at det er 6 og det passer siden den minste verdien i a (dvs. 3) har indeks 6. Neste
    verdi i indeks-tabellen, dvs. indeks[1] skal være indeksen til den nest minste verdien i a .
    Utskriften gir at indeks[1] = 9 og den nest minste verdien i a (dvs. 5) har indeks 9. Osv. til den
    siste. Det er 2 som er indeksen til den største verdien (dvs. 16) i a .
    På denne måten kan vi få ut verdiene i a i sortert rekkefølge ved å gå veien om indeks-tabellen.
            Dvs. slik
    int [] a = {6,10,16,11,7,12,3,9,8,5};
    int [] indeks = indekssortering (a);
for ( int i = 0; i < a. length ; i++) System. out .print(a[indeks[i]] + " " );
    // Utskrift: 3 5 6 7 8 9 10 11 12 16
    Dette kan løses på flere måter. Her blir det ikke lagt vekt på effektivitet, men kun på at det
    virker. Du kan også bruke hjelpetabeller - en eller flere. Opprett i hvert fall en tabell indeks av
    typen int[] med samme lengde som a . Det er den som til slutt skal returneres. Tabellen a kan
    inneholde like verdier! I så fall blir det ingen entydig løsning. Hvis f.eks. den minste verdien
    forkommer to ganger, spiller det ingen rolle hvem av dem indeks[0] refererer til. Men da
    må indeks[1] referere til den andre av de to. Hvis a er tom, skal også returtabellen være tom.*/
public class Oppgave8 {
}
