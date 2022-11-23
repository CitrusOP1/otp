import java.io.*;
import java.util.*;

public class read_write {
    public static void main(String args[]) throws IOException, FileNotFoundException, ArrayIndexOutOfBoundsException {
        int LC = 0;
        String line;
        String code;
        INSTtable lookup = new INSTtable();

        LinkedHashMap<String, TableRow> SYMTAB = new LinkedHashMap<String, TableRow>();
        int symIndex = 0;
        BufferedReader br = new BufferedReader(
                new FileReader("a.txt"));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("IC.txt"));

        while ((line = br.readLine()) != null) {
            String parts[] = line.split(" ");
            if (parts[1].contentEquals("START")) {
                LC = Integer.parseInt(parts[2]);
                code = "(AD,01)\t" + "(C," + LC + ")";
                bw.write(code + "\n");
                continue;
            }
            if (parts[1].equals("END")) {
                code = "(AD,02)\t";
                bw.write(code + "\n");
                continue;
            }
            if (lookup.getType(parts[1]).equals("IS")) {
                code = "(IS,0" + lookup.getCode(parts[1]) + ")\t";
                int j = 2;
                String code2 = "";
                String code3 = "";
                while (j < parts.length) {
                    if (lookup.getType(parts[j]).contentEquals("RG")) {
                        code3 = code3 + lookup.getCode(parts[j]) + "\t";
                    } else {
                        if (SYMTAB.containsKey(parts[j])) {
                            int index = SYMTAB.get(parts[j]).getIndex();
                            code2 = code2 + "(S,0" + index + ")";
                        } else {
                            SYMTAB.put(parts[j], new TableRow(parts[j], -1, ++symIndex));
                            int index = SYMTAB.get(parts[j]).getIndex();
                            code2 = code2 + "(S,0" + index + ")";
                        }
                    }
                    j++;
                }
                LC++;
                // System.out.println(LC);
                bw.write(code + " " + code3 + "" + code2 + "\n");
                continue;
            }
            if (parts[2].equals("DC")) {
                code = "(DC,01) (C," + Integer.parseInt(parts[3]) + ")";
                // System.out.println(code);
                SYMTAB.put(parts[1], new TableRow(parts[1], LC, SYMTAB.get(parts[1]).getIndex()));
                bw.write(code + "\n");
                LC = LC + 1;
                continue;
            }
            if (parts[2].equals("DS")) {
                // LC=LC+Integer.parseInt(parts[3]);
                code = "(DS,02) (c," + Integer.parseInt(parts[3]) + ")";
                // System.out.println(code);
                SYMTAB.put(parts[1], new TableRow(parts[1], LC, SYMTAB.get(parts[1]).getIndex()));
                bw.write(code + "\n");
                LC = LC + Integer.parseInt(parts[3]);
                continue;
            }
        }
        br.close();
        bw.close();
        BufferedWriter bw1 = new BufferedWriter(
                new FileWriter("SYMTAB.txt"));
        Iterator<String> itr = SYMTAB.keySet().iterator();
        System.out.println("Symbol Table\n");
        while (itr.hasNext()) {
            String key = itr.next().toString();
            TableRow value = SYMTAB.get(key);
            System.out.println(value.getIndex() + "\t" + value.getSymbol() + "\t" + value.getAddress());
            bw1.write(value.getIndex() + "\t" + value.getSymbol() + "\t" + value.getAddress() + "\n");
        }
        bw1.close();
    }
}