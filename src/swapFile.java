import java.io.File;

public class swapFile {

    public static void main(String[] args) {
        
        File database = new File("database.csv");
        File temporary = new File("database.tmp");

        if(database.exists()) {
            database.delete();
            temporary.renameTo(database);
        }
    }
}
