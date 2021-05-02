package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
    @Override
    public int read(byte[] b) throws IOException {
        int compressedInfo;
        int counter = 12;
        int listLength;
        LinkedList<Byte> Blist = new LinkedList<>();
        byte[] BArr = new byte[in.available()];
        in.read(BArr);
        for (int i = 0; i < 12; i++) {
            b[i] = BArr[i];
        }
        for (int i = 12; i < BArr.length; i++) {
            compressedInfo = Byte.toUnsignedInt(BArr[i]);
            if (i != BArr.length - 1) {
                for (int j = 0; j < 8 && compressedInfo != 0; j++) {
                    Blist.addFirst((byte)(compressedInfo % 2));
                    compressedInfo= compressedInfo/2;
                }
                listLength = Blist.size();
                for (int k = 0; k < 8 - listLength; k++) {
                    Blist.addFirst((byte)0);
                }
            }
            else{
                for (int j = 0; j < 8 && compressedInfo != 0; j++) {
                    Blist.addFirst((byte)(compressedInfo % 2));
                    compressedInfo /= 2;
                }
                int offset = (b.length - 12) % 8;
                if(offset == 0){
                    for (int k = Blist.size(); k < 8; k++) {
                        Blist.addFirst((byte)0);
                    }
                }//if
            }//else
            for (int j = 0; j < Blist.size(); j++) {
                b[counter] = Blist.get(j);
                counter++;
            }
            Blist = new LinkedList<>();
        }
        return 0;
    }
}