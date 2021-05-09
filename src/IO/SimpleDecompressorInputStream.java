package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends MyDecompressorInputStream {

    public SimpleDecompressorInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {

        //first 12 positions are the demotions and property of the maze
        for (int i = 0; i < 12; i++)
            b[i] = (byte) read();

        //the rest of the maze
        int pos = 12;
        byte num = 0;
        int count;
        for (int i = 12; i < b.length; i++) {
            count = read();

            // write count time each num
            for (int j = 0; j < count; j++) {
                b[pos] = num;
                pos++;
            }

            //change the number to the next one ( 0 to 1 , 1 to 0)
            if(num == 1)
                num = 0;
            else
                num = 1;
        }

        return 0;
    }
}