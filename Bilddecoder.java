import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Bilddecoder {
    public static void main(String[] args) throws Exception {
        Scanner a = new Scanner(System.in);
        System.out.println("Zu entschlüsselndes Bild:");
        String inputPic = a.nextLine();
        a.close();
        String num = "";
        ArrayList<Integer> abc = new ArrayList();
        int count = 0;
        short s = 1764;
        try {
            Scanner b;
            for(b = new Scanner(new File(inputPic)); b.hasNext() && count < s + 4; ++count) {
                num = b.next();
                if (count > 3) {
                    int x = Integer.parseInt(num);
                    if (x % 2 == 0) {
                        abc.add(0);
                    } else {
                        abc.add(1);
                    }
                }
            }
            String out = "";
            if (abc.size() % 7 == 0) {
                for(int i = 0; i < abc.size() / 7; ++i) {
                    char c = 0;

                    for(int j = 0; j < 7; ++j) {
                        c = (char)(c | (Integer)abc.get(j + 7 * i) << 6 - j);
                    }
                    out = out + c;
                }
            }
            System.out.println(out);
            b.close();
        } catch (Exception var12) {
            System.out.println(var12);
        }
    }
}
