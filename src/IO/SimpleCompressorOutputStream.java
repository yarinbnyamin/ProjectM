package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends MyCompressorOutputStream {

    public SimpleCompressorOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {

        //first 12 positions are the demotions and property of the maze
        for (int i = 0; i < 12; i++)
            write(Byte.toUnsignedInt(b[i]));

        //the rest of the maze
        byte lastNum = 0; // we start with the number 0 to count
        int count = 0; // how many times we saw the lastNum
        for (int i = 12; i < b.length; i++) {
            if(lastNum == b[i]){ // if we saw the dame number again count it
                if(count < 255) {
                    count++;
                }else{ // we need to store in another byte
                    write(count);
                    write(0);
                    count = 1;
                }
            }else{ // we saw different number
                write(count);
                count = 1;
                lastNum = b[i];
            }
        }
        write(count); // write the last number count

    }
}