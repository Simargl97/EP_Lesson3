import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lastName = getInput("Введите фамилию: ", scanner);
        String firstName = getInput("Введите имя: ", scanner);
        String middleName = getInput("Введите отчество: ", scanner);
        String birthDate = getInput("Введите дату рождения (в формате dd.mm.yyyy): ", scanner);
        String phoneNumber = getInput("Введите номер телефона: ", scanner);
        String gender = getInput("Введите пол (f - женский, m - мужской): ", scanner);

        // Проверка форматов данных
        if (!isValidDate(birthDate) || !isValidPhoneNumber(phoneNumber) || !isValidGender(gender)) {
            System.err.println("Ошибка: Неверный формат данных.");
            scanner.close();
            return;
        }

        // Создание файла и запись данных
        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String userDataLine = lastName + firstName + middleName + birthDate + " " + phoneNumber + gender;
            writer.write(userDataLine);
            System.out.println("Данные успешно записаны в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false); // Запрещаем нестрогий режим парсинга даты

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Проверяем, что строка состоит только из цифр и не пуста
        return phoneNumber.matches("\\d+") && !phoneNumber.isEmpty();
    }

    private static boolean isValidGender(String gender) {
        return gender.equals("f") || gender.equals("m");
    }
}
