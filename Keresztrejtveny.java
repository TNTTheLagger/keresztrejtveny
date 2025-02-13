import java.io.*;
import java.util.*;

class KeresztrejtvenyRacs {
    private List<String> Adatsorok;
    private char[][] Racs;
    private int[][] Sorszamok;
    
    public KeresztrejtvenyRacs(String fajlNev) {
        Adatsorok = new ArrayList<>();
        BeolvasAdatsorok(fajlNev);
        int sorok = Adatsorok.size();
        int oszlopok = sorok > 0 ? Adatsorok.get(0).length() : 0;
        Racs = new char[sorok][oszlopok];
        Sorszamok = new int[sorok][oszlopok];
        FeltoltRacs();
        SzamozMezok();
    }
    
    public int getSorokDb() {
        return Racs.length;
    }
    
    public int getOszlopokDb() {
        return Racs[0].length;
    }
    
    private void BeolvasAdatsorok(String fajlNev) {
        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                Adatsorok.add(sor);
            }
        } catch (IOException e) {
            System.err.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }
    }
    
    private void FeltoltRacs() {
        for (int i = 0; i < getSorokDb(); i++) {
            Racs[i] = Adatsorok.get(i).toCharArray();
        }
    }
    
    public void MegjelenitMeret() {
        System.out.println("5. feladat:");
        System.out.println("Sorok száma: " + getSorokDb());
        System.out.println("Oszlopok száma: " + getOszlopokDb());
    }
    
    public void MegjelenitRacs() {
        System.out.println("6. feladat:");
        for (char[] sor : Racs) {
            for (char c : sor) {
                System.out.print(c == '#' ? "##" : "[]");
            }
            System.out.println();
        }
    }
    
    public int LegnagyobbFuggolegesSzo() {
        int maxHossz = 0;
        for (int oszlop = 0; oszlop < getOszlopokDb(); oszlop++) {
            int hossz = 0;
            for (int sor = 0; sor < getSorokDb(); sor++) {
                if (Racs[sor][oszlop] != '#') {
                    hossz++;
                    maxHossz = Math.max(maxHossz, hossz);
                } else {
                    hossz = 0;
                }
            }
        }
        return maxHossz;
    }
    
    public void VizszintesSzoStat() {
        System.out.println("8. feladat: Vízszintes szavak statisztikája");
        Map<Integer, Integer> stat = new TreeMap<>();
        
        for (char[] sor : Racs) {
            int hossz = 0;
            for (char c : sor) {
                if (c != '#') {
                    hossz++;
                } else {
                    if (hossz > 1) {
                        stat.put(hossz, stat.getOrDefault(hossz, 0) + 1);
                    }
                    hossz = 0;
                }
            }
            if (hossz > 1) {
                stat.put(hossz, stat.getOrDefault(hossz, 0) + 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : stat.entrySet()) {
            System.out.println(entry.getKey() + " betűs: " + entry.getValue() + " darab");
        }
    }
    
    public void SzamozMezok() {
        int szam = 1;
        for (int sor = 0; sor < getSorokDb(); sor++) {
            for (int oszlop = 0; oszlop < getOszlopokDb(); oszlop++) {
                if (Racs[sor][oszlop] != '#') {
                    boolean ujSzo = (oszlop == 0 || Racs[sor][oszlop - 1] == '#') || (sor == 0 || Racs[sor - 1][oszlop] == '#');
                    if (ujSzo) {
                        Sorszamok[sor][oszlop] = szam++;
                    }
                }
            }
        }
    }
    
    public void MegjelenitSzamozottRacs() {
        System.out.println("9. feladat:");
        for (int sor = 0; sor < getSorokDb(); sor++) {
            for (int oszlop = 0; oszlop < getOszlopokDb(); oszlop++) {
                if (Sorszamok[sor][oszlop] > 0) {
                    System.out.printf("%2d", Sorszamok[sor][oszlop]);
                } else {
                    System.out.print(Racs[sor][oszlop] == '#' ? "##" : "[]");
                }
            }
            System.out.println();
        }
    }
}

public class Keresztrejtveny {
    public static void main(String[] args) {
        KeresztrejtvenyRacs racs = new KeresztrejtvenyRacs("kr2.txt");
        racs.MegjelenitMeret();
        racs.MegjelenitRacs();
        System.out.println("7. feladat: A leghosszabb függ.: " + racs.LegnagyobbFuggolegesSzo() + " karakter");
        racs.VizszintesSzoStat();
        racs.MegjelenitSzamozottRacs();
    }
}
