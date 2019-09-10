
  /**  Oppgave 7
    a) Lag metoden public static String flett (String s, String t) . Den skal «flette»
    sammen tegnstrengene s og t slik at resultatet blir en tegnstreng der annethvert tegn kommer
    fra s og annethvert fra t . Hvis s og t har ulik lengde, skal det som er «til overs» legges inn
    bakerst. Resultatet skal returneres. Flg. eksempel viser hvordan det skal virke:
    String a = flett ( "ABC" , "DEFGH" );
    String b = flett ( "IJKLMN" , "OPQ" );
    String b = flett ( "" , "AB" );
System. out .println(a + " " + b + " " + c);
// Utskrift: ADBECFGH IOJPKQLMN AB
    b) Lag metoden public static String flett (String... s) . Den skal «flette» sammen
    tegnstrengene i s . Husk at s nå er en tabell av tegnstrenger. I koden vil derfor s[0] være første
    streng i tabellen s , osv. Flettingen skal være slik: Først hentes fortløpende det første tegnet fra
    hver tegnstreng, deretter fortløpende det andre tegnet, osv. De tegnstrengene som er «brukt opp»,
    dvs. vi er ferdige med alle tegnene der, hoppes over. Resultatet skal returneres. Flg. eksempel
    viser hvordan den skal virke:
    String a = flett ( "AM " , "L" , "GEDS" , "ORATKRR" , "" , "R TRTE" , "IO" , "TGAUU" );
System. out .println(a);
// Utskrift: ALGORITMER OG DATASTRUKTURER

   **/
  public class Oppgave7 {
}
