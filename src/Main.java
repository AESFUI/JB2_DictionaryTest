import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

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
            reader.close();
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

            /*String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            System.out.println("HOST: " + host
                    + ", LOGIN: " + login
                    + ", PASSWORD: " + password);*/

        } catch (IOException e) {
            System.err.println("ОШИБКА: Не удается загрузить тест!");
        }


    }
}
