package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        ArrayList<Byte> Blist = new ArrayList<>();
        int counter = 0;
        int Bsum = 0;
        int saveIndex = 12;
        for (int i = 12; i < b.length; i++) {
            if (counter == 8) {
                Blist.add((byte)Bsum);
                Bsum = 0;
                counter = 0;
                saveIndex = i;
            }
            if (b[i] == 1) {
                Bsum += Math.pow(2, 7 - counter);
            }
            counter++;
        }
        if (Bsum != 0) {
            Bsum = 0;
            counter = 0;
            for (int i = b.length - 1; i >= saveIndex; i--) {
                if(b[i] == 1){
                    Bsum += Math.pow(2, counter);
                }
                counter++;
            }
            Blist.add((byte)Bsum);
        }
        else{
            if((b.length - 12) % 8 != 0 && Bsum == 0){
                Blist.add((byte)0);
            }
        }
        byte[] BArr = new byte[12 + Blist.size()];
        for (int i = 0; i < 12; i++) {
            BArr[i] = b[i];
        }
        for (int i = 0; i < Blist.size(); i++) {
            BArr[i + 12] = Blist.get(i);
        }
        out.write(BArr);
        out.flush();
    }
}
