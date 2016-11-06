import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название языка для тестирования (deutsch/english/espanol): ");

        /*
         * Ввод названия теста
         */
        String dictionaryName = "";
        try {
            dictionaryName = reader.readLine();
            //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Проверка наличия введённого теста
         */
        switch (dictionaryName) {
            case "deutsch":
                break;
            case "english":
                break;
            case "espanol":
                break;
            default:
                System.out.println("Данного теста не существует.");
                return;
        }

        /*
         * Загрузка файла теста
         */
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/resources/" + dictionaryName + ".properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Не удается загрузить тест!");
        }

        Set<String> dictionarySet = property.stringPropertyNames();
        String[] dictionaryStringArray;
        dictionaryStringArray = dictionarySet.toArray(new String[dictionarySet.size()]);

        ArrayList<String> dictionaryList = new ArrayList<>(Arrays.asList(dictionaryStringArray));

        String entering;
        int yes = 0, no = 0;

        try {
            while (!dictionaryList.isEmpty()) {
                String word = dictionaryList.get((int) (Math.random() * dictionaryList.size())); //случайный индекс
                System.out.print("Напишите перевод слова " + word + ": ");

                entering = reader.readLine();
                System.out.println();

                if (entering.equals("exit")) {
                    break;
                } else if (entering.equals(property.getProperty(word))) { //сверка со словарём
                    yes++;
                } else {
                    no++;
                }

                dictionaryList.remove(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Правильных ответов: " + yes);
        System.out.println("Ошибок: " + no);

        System.out.println("Укажите куда записать статистику: ");
        try {
            String statFileName = reader.readLine();
            FileWriter fos = new FileWriter(statFileName);
            fos.write("Правильных ответов: " + yes);
            fos.write("Ошибок: " + no);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
