import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

    private Map<String, String> dictionary;
    private String fileName;

    public Dictionary() {
        dictionary = new HashMap<String, String>();
    }

    public void LoadFile(String fileName) throws IOException {
        this.fileName = fileName;
        Path path = Paths.get(fileName);
        List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
        for(String s : list){
            String[] array = s.split("-");
            dictionary.put(array[0], array[1]);
        }
    }

    public void ReadAll() throws IOException {
        for(Map.Entry<String, String> e : dictionary.entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
    }

    public void Read(String key) {
        System.out.println(dictionary.get(key));
    }

    public void Clear() {
        dictionary.clear();
    }

    public void Delete(String key) {
        dictionary.remove(key);
    }

}
