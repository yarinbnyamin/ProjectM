package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

        for (int i = 0; i < 12; i++)
            b[i] = (byte) read();

        int pos = 12;
        byte num = 0;
        int count;
        for (int i = 12; i < b.length; i++) {
            count = read();
            /*
            if(count == 0) {
                i -= 2;
                continue;
            }
            */
            for (int j = 0; j < count; j++) {
                b[pos] = num;
                pos++;
            }
            if(num == 1)
                num = 0;
            else
                num = 1;
        }

        return 0;
    }
}