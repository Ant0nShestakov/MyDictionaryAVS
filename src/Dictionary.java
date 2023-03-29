import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    enum Types {
        firstType,
        SecondType
    }

    private static String info = "info - открыть документацию\n" +
            "ReadAll (Название словаря(First или Second)) - прочитать весь словарь\n" +
            "Search (Название словаря(First или Second)) (Название ключа) - вывести значение по ключу\n" +
            "Delete (Название словаря(First или Second)) (Название ключа) - удалить значение по ключу\n" +
            "Add (Название словаря(First или Second)) (Название ключа) - добавить запись по правилу Ключ-Значение\n" +
                    "Примечание: \n" +
                    "В First длинна слов может быть только 4 символа и эти символы только буквы латинской раскладки\n" +
                    "В Second длина слов может быть только 5 символа и эти символы только цифры.\n";

    private Types tp;
    private Map<String, String> dictionary;
    private String fileName;

    public Dictionary(String fileName, Types type) throws IOException {
        this.fileName = fileName;
        this.tp = type;
        dictionary = new HashMap<String, String>();
        File f = new File(this.fileName);
        f.createNewFile();
        LoadFile();
    }

    private void LoadFile() throws IOException {
        dictionary.clear();
        Path path = Paths.get(fileName);
        List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
        for(String s : list){
            s = s.replace("\n", "").replace("\r", "");
            String[] array = s.split("-");
            dictionary.put(array[0], array[1]);
        }
        list.clear();
        System.out.println("___Файл загружен___");
    }

    public static String GetInfo() {
        return info;
    }

    public void ReadAll() {
        for(Map.Entry<String, String> e : dictionary.entrySet()) {
            System.out.println(e.getKey() + "-" + e.getValue());
        }
    }

    public void Search(String key) {
        System.out.println(dictionary.get(key));
    }

    public void Delete(String key) throws IOException {
        if(dictionary.containsKey(key)) {
            dictionary.remove(key);
            String context = "";
            FileWriter fw = new FileWriter(fileName, false);
            for (Map.Entry<String, String> e : dictionary.entrySet()) {
                if (context.equals(""))
                    context += e.getKey() + "-" + e.getValue();
                else
                    context += "\n" + e.getKey() + "-" + e.getValue();
            }
            fw.write(context + "\n");
            fw.close();
            System.out.println("___Запись по ключу " + key + " удалена!___");
        }
        else
            System.out.println("___Записи по ключу " + key + " нет в словаре!___");
    }

    //Проверка на повтор ключа
    private boolean KeyCheck(String value) {
        String[] array = value.split("-");
        if(dictionary.containsKey(array[0]))
            return false;
        else
            return true;
    }

    private boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean ValidityСheck(String value) {
        String[] array = value.split("-");
        boolean onlyLatinAlphabetFirst = array[0].matches("^[a-zA-Z0-9]+$");
        boolean onlyLatinAlphabetSecond = array[1].matches("^[a-zA-Z0-9]+$");
        if(tp == Types.firstType)
            if(array[0].length() <= 4 && array[1].length() <= 4 && onlyLatinAlphabetFirst && onlyLatinAlphabetSecond)
                return true;
            else
                return false;
        else if(tp == Types.SecondType)
            if(array[0].length() <= 5 && array[1].length() <= 5 && isNumeric(array[0]) && isNumeric(array[1]))
                return true;
            else
                return false;
        return false;
    }

    public void Add(String value) throws IOException {
        if(value.contains("-") && KeyCheck(value) && ValidityСheck(value)) {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(value + "\n");
            System.out.println("___Запись завершена!___");
            fw.close();
            LoadFile();
        }
        else {
            System.out.println("___Неверный формат записи!___");
        }
    }
}
