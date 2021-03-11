package installApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {
    private static Logging instance;                                 //Поле хранящее экземпляр класса(Синглтон)
    private StringBuilder stringBuilder;                            //Поле аккумулирующее события исполнения кода
    private Date date;                                              //Поле для хранения даты/времени событий
    private SimpleDateFormat simpleDateFormat;                      //Поле хранит шаблон для date/time
    /**
     * <p>
     *     Реализация (Синглтон). Если поле instance содержит
     *     экземпляр класса Logger, возвращает ссылку на этот
     *     экземпляр.
     * </p>
     * <p>
     *     Если поле instance не содержит ссылки на экзеипляр
     *     класса Logger, создаёт новый экземпляр класса Logger.
     * </p>
     * @return экземпляр класса Logger
     */
    public static Logging getInstance() {
        if(instance == null) {
            instance = new Logging();
        }
        return instance;
    }
    /**
     *  <p>
     *      Закрытый конструктор класса Logger.
     *      При выполнении создаёт экземпляр класса
     *      StringBuilder и экземпляр SimpleDateFormat
     *      с паттерном - '['dd.MM.yyyy HH:mm:ss.SSS']'
     *  </p>
     */
    private Logging() {
        stringBuilder = new StringBuilder();
        simpleDateFormat = new SimpleDateFormat("'['dd.MM.yyyy HH:mm:ss.SSS']' ");

    }
    /**
     * <p>
     *     Служит для фиксации выполнения методов
     *     или простых действий, не требующих доп.
     *     анализа.
     * </p>
     * <p>
     *     Добавляет в StringBuilder дату/время своего
     *     вызова вместе с переданным событием.
     * </p>
     * @param str событие для фиксации в файле лога
     */
    public void add(String str) {
        date = new Date();
        stringBuilder.append(simpleDateFormat.format(date)).append(str);
    }
    /**
     * <p>
     *     Перегруженный add для определения и
     *     последующего логирования событий
     *     создания файлов / каталогов.
     * </p>
     * <p>
     *     Определяет является ли переданный в него
     *     объект File - файлом или каталогом. И в зависимости
     *     от полученного статуса фиксирует в событие.
     * </p>
     * <p>
     *     status < 0 - отрицательный статус,
     *     status > 0 - положительниый статус.
     * </p>
     * @param sthFile объект типа File
     * @param status статус успешности действия
     */
    public void add(File sthFile, int status) {
        if(sthFile.isDirectory() && status > 0) {
            add("Каталог: " + sthFile.getAbsolutePath() + "\\ создан успешно! \n");
        } else if(sthFile.isDirectory() && status < 0) {
            add("Каталог: " + sthFile.getAbsolutePath() + "\\ уже существует! \n");
        }
        if(sthFile.isFile() && status > 0) {
            add("Файл: " + sthFile.getAbsolutePath() + " создан успешно! \n");
        } else if(sthFile.isFile() && status < 0) {
            add("Файл: " + sthFile.getAbsolutePath() + " уже существует! \n");
        }
    }
    /**
     * <p>
     *     Создёт файл для записи лога.
     * </p>
     * @param baseDirectory основной каталог программы
     */
    public void createTempFile(String baseDirectory) {
        File logFile = new File(baseDirectory + "\\temp\\temp.txt");
        try {
            if(logFile.createNewFile()) {
                add(logFile, 1);
            } else {
                add(logFile, -1);
            }
            add("Метод createTempFile() отработал успешно.\n");
        } catch (IOException ex) {
            add(ex.getMessage());
        }
        writeLog(logFile);
    }
    /**
     * <p>
     *     Записывает всё содержимое поля класса
     *     типа StringBuilder в полученный файл.
     *     (Предположительно - лог-файл)
     * </p>
     * @param logFile Лог-файл для записи.
     */
    private void writeLog(File logFile) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException ex) {
            add(ex.getMessage());
        }
    }
}
