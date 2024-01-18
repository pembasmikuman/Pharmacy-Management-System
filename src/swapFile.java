import java.io.*;

public class swapFile {

    public static void swapping(File a, File b) {

        if(a.exists()) {
            a.delete();
            b.renameTo(database);
        }
    }
}
