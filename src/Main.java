import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dc = new Dictionary();
        dc.LoadFile("Dicionary.txt");
        dc.ReadAll();
        System.out.println("_______________________________________");
        dc.Delete("Слово");
        dc.ReadAll();

    }
}
