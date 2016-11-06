import java.io.*;
import java.nio.charset.Charset;
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
            System.err.println("ОШИБКА: Не удаётся загрузить тест!");
            return;
        }

        /*
         * Создание рабочего ArrayList из которого можно удалять использованные вопросы теста
         */
        Set<String> dictionarySet = property.stringPropertyNames();
        String[] dictionaryStringArray;
        dictionaryStringArray = dictionarySet.toArray(new String[dictionarySet.size()]);
        ArrayList<String> dictionaryList = new ArrayList<>(Arrays.asList(dictionaryStringArray));

        System.out.println();

        String entering;
        int yes = 0, no = 0;

        /*
         * Тест, собственно
         */
        try {
            while (!dictionaryList.isEmpty()) {
                String word = dictionaryList.get(
                        (int) (Math.random() * dictionaryList.size())); //случайный индекс
                System.out.print("Напишите перевод слова " + word + ": ");

                entering = reader.readLine();

                if (entering.equals("exit")) {
                    break;
                } else if (entering.equals(property.getProperty(word))) { //сверка со словарём
                    System.out.println("верный ответ");
                    yes++;
                } else {
                    System.out.println("неверный ответ");
                    no++;
                }

                dictionaryList.remove(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Вывод статистики на экран
         */
        System.out.println("Правильных ответов: " + yes);
        System.out.println("Ошибок: " + no);

        /*
         * Вывод статистики в файл по указанному пути
         */
        System.out.println("Укажите куда записать статистику: ");
        try {
            String statFileName = reader.readLine();

            BufferedWriter bufWriter = null;
            try {
                bufWriter = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(statFileName),
                                Charset.forName("CP1251")
                        )
                );
            } catch (FileNotFoundException e) {
                System.err.println("Путь не найден.");
            }

            bufWriter.write("Для языка " + dictionaryName + " получены результаты:");
            bufWriter.newLine();
            bufWriter.write("Правильных ответов: " + yes);
            bufWriter.newLine();
            bufWriter.write("Ошибок: " + no);
            bufWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
