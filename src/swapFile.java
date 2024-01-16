import java.io.*;

public class swapFile {

    public static void swapping(File a, File b) {

        if(database.exists()) {
            database.delete();
            temporary.renameTo(database);
        }
    }
}
