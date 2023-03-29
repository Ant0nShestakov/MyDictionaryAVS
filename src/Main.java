import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary first =  new Dictionary("FirstDictionary.txt", Dictionary.Types.firstType);
        Dictionary second =  new Dictionary("SecondDictionary.txt", Dictionary.Types.SecondType);
        Scanner sc = new Scanner(System.in);
        System.out.println(Dictionary.GetInfo());
        while(true) {
            String command = sc.nextLine();
            String[] arg = command.split(" ");
            //Документация
            if (arg[0].equals("Info")) {
                System.out.println(Dictionary.GetInfo());
            }
            //Чтение всего первого словаря
            else if (arg[0].equals("ReadAll") && arg[1].equals("First")) {
                first.ReadAll();
            }
            //Чтение всего второго словаря
            else if (arg[0].equals("ReadAll") && arg[1].equals("Second")) {
                second.ReadAll();
            }
            //Поиск значение по ключу первого словаря
            else if (arg[0].equals("Search") && arg[1].equals("First") && arg[2] != null) {
                first.Search(arg[2]);
            }
            //Поиск значение по ключу второго словаря
            else if (arg[0].equals("Search") && arg[1].equals("Second") && arg[2] != null) {
                second.Search(arg[2]);
            }
            //Удаление значения по ключу первого словаря
            else if (arg[0].equals("Delete") && arg[1].equals("First") && arg[2] != null) {
                first.Delete(arg[2]);
            }
            else if (arg[0].equals("Delete") && args[1].equals("Second") && arg[2] != null) {
                first.Delete(arg[2]);
            }
            //Добавление записей
            else if (arg[0].equals("Add") && arg[1].equals("First") && arg[2] != null) {
                first.Add(arg[2]);
            }
            else if (arg[0].equals("Add") && arg[1].equals("Second") && arg[2] != null) {
                second.Add(arg[2]);
            }
            else if (arg[0].equals("Exit")) {
                break;
            }
        }
        System.exit(0);
    }
}
