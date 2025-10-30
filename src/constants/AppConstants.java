package constants;

public interface AppConstants {
    String WELCOME = "Добро пожаловать в Криптограф!";
    String MAIN_MENU = """
                Выберите один из вариантов:
                  1. Зашифровать файл
                  2. Расшифровать файл
                  3. Расшифровать файл методом Bruteforce
                  4. Выход
                Ваш выбор: """;
    String ERROR_CHOICE = "Такого пункта в меню нет, попробуйте ещё раз";
    String ERROR_FILE_NOT_EXIST = "Этот файл куда-то потерялся, попробуем другой?";
    String ERROR_FILE_READ = "При чтении из файла произошла ошибка, попробуем другой?";
    String ERROR_FILE_WRITE = "При записи в файл произошла ошибка, попробуем другой?";
    String ERROR_KEY = "Этот ключ не подходит, попробуем другой?";
    String EXIT = "Всего хорошего. Было приятно с Вами поработать.";
    String REQUEST_FILE_PATH = "Введите путь к исходному файлу:";
    String REQUEST_KEY = "Введите ключ для шифрования:";
    String ENCRYPT_GO = "Зашифровываю ваш файл...";
    String ENCRYPT_DONE = "Зашифрованный файл сохранен с именем: ";
    String DECRYPT_GO = "Расшифровываю ваш файл...";
    String DECRYPT_DONE = "Расшифрованный файл сохранен с именем: ";
    String BRUTEFORCE_GO = "Расшифровываю ваш файл методом Bruteforce...";
    String BRUTEFORCE_DONE = "Варианты расшифрованных файлов сохранены в папке Brutforce.";
}
