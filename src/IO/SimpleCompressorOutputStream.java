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

        for (int i = 0; i < 12; i++)
            write(Byte.toUnsignedInt(b[i]));

        byte lastNum = 0;
        int sum = 0;
        for (int i = 12; i < b.length; i++) {
            if(lastNum == b[i]){
                if(sum < 255) {
                    sum++;
                }else{
                    write(sum);
                    write(0);
                    sum = 1;
                }
            }else{
                write(sum);
                sum = 1;
                lastNum = b[i];
            }
        }
        write(sum);

    }
}