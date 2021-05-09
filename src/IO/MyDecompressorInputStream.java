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
        //first 12 positions are the demotions and property of the maze
        for (int i = 0; i < 12; i++) {
            b[i] = BArr[i];
        }
        //the rest of the maze
        for (int i = 12; i < BArr.length; i++) {
            compressedInfo = Byte.toUnsignedInt(BArr[i]);
            //if we're not in the end of the compressed maze we carry through to the length of 8
            if (i != BArr.length - 1) {
                //we decompress the walls in maze into byte sums of 2 in the power of 8
                for (int j = 0; j < 8 && compressedInfo != 0; j++) {
                    Blist.addFirst((byte)(compressedInfo % 2));
                    compressedInfo= compressedInfo/2;
                }
                listLength = Blist.size();
                for (int k = 0; k < 8 - listLength; k++) {
                    Blist.addFirst((byte)0);
                }
            }
            //if we're in the end we need to see how much 0's we need to add to carry through
            else{
                for (int j = 0; j < 8 && compressedInfo != 0; j++) {
                    Blist.addFirst((byte)(compressedInfo % 2));
                    compressedInfo = compressedInfo/2;
                }
                int offset = (b.length - 12) % 8;
                //if we don't need to add 0's
                if(offset == 0){
                    for (int k = Blist.size(); k < 8; k++) {
                        Blist.addFirst((byte)0);
                    }
                }//if
            }//else
            //the rest of the maze
            for (int j = 0; j < Blist.size(); j++) {
                b[counter] = Blist.get(j);
                counter++;
            }
            Blist = new LinkedList<>();
        }
        return 0;
    }
}