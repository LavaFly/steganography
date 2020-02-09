import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class Bildcoder {
    private int x = 0;
    private int count = 0;
    private int secondCount = 0;
    private int thirdCount = 0;
    private String y = "";
    private Scanner a;
    private String inputPic;
    private String outputPic;
    private String Message;
    private char[] messageChar;
    private int[] messageASCII;
    private String[] messageBinary;
    private char[][] messageBinChar;

    public Bildcoder() {
        this.a = new Scanner(System.in);
        System.out.println("Input Bild:");
        this.inputPic = this.a.nextLine();
        System.out.println("Neues Bild:");
        this.outputPic = this.a.nextLine();
        System.out.println("Was willst du schreiben:");
        this.Message = this.a.nextLine();
        this.a.close();
        this.Wandler(this.Message);
    }

    public Bildcoder(String inPic, String outPic, String msg) {
        this.a = new Scanner(System.in);
        this.inputPic = inPic;
        this.outputPic = outPic;
        this.Message = msg;
        this.a.close();
        this.Wandler(this.Message);
    }

    private void Wandler(String b) {
        this.messageChar = b.toCharArray();
        this.messageASCII = new int[this.messageChar.length];
        this.messageBinary = new String[this.messageChar.length];
        this.messageBinChar = new char[this.messageChar.length][this.messageChar.length * 8];

        int i;
        for(i = 0; i < this.messageChar.length; ++i) {
            this.messageASCII[i] = this.messageChar[i];
        }

        for(i = 0; i < this.messageChar.length; ++i) {
            this.messageBinary[i] = String.format("%7s", Integer.toBinaryString(this.messageASCII[i])).replace(' ', '0');
            this.messageBinChar[i] = this.messageBinary[i].toCharArray();
        }

    }

    public void MainStuff() {
        try {
            Scanner o = new Scanner(new File(this.inputPic));
            PrintWriter edit;
            for(edit = new PrintWriter(this.outputPic); o.hasNext(); ++this.count) {
                this.y = o.next();
                if (this.count > 3 && this.count < this.messageASCII.length * 7 + 4) {
                    this.x = Integer.parseInt(this.y);
                    int ho = this.messageBinChar[this.thirdCount][this.secondCount] - 48;
                    if ((ho != 1 || this.x % 2 != 0) && (ho != 0 || this.x % 2 != 1)) {
                        System.out.println(ho);
                        edit.println(this.x);
                    } else {
                        System.out.println(ho);
                        --this.x;
                        edit.println(this.x);
                    }
                    if (this.secondCount == 6) {
                        this.secondCount = -1;
                        ++this.thirdCount;
                    }
                    ++this.secondCount;
                } else {
                    edit.println(this.y);
                }
            }
            edit.close();
            o.close();
        } catch (Exception e) {
            System.out.println("You've failed");
            System.out.println(e);
        }
    }
    public boolean Length() {
        int msglength = 0;

        try {
            Scanner x;
            for(x = new Scanner(new File(this.inputPic)); x.hasNext(); ++msglength) {
                ;
            }

            msglength = Math.round((float)(msglength / 7));
            x.close();
        } catch (Exception var4) {
            System.out.println(var4);
        }

        boolean b;
        if (this.messageASCII.length * 7 < msglength) {
            b = true;
        } else {
            b = false;
        }

        return b;
    }


}
