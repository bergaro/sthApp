package installApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Install {
    private String baseDirectory;                                   //Директория установки файлов
    private final List<File> myFiles = new ArrayList<>();           //Список файлов для установки
    private Logging logging = Logging.getInstance();                   //Экземпляр класса логирования
    /**
     * Возвращает новый экземпляр класса Install
     * @return new Install.
     */
    public static Install getInstall() {
        return new Install();
    }
    /**
     * Для создания экземпляра класса есть
     * статический фабричный метод getInstall()
     */
    private Install() { }
    /**
     * <p>
     *     Создаёт три директории в каталоге res:
     * </p>
     * <p>
     *     drawables, vectors, icons.
     * </p>
     */
    private void createResDir() {
        myFiles.add(new File(baseDirectory + "\\res\\drawables"));
        myFiles.add(new File(baseDirectory + "\\res\\vectors"));
        myFiles.add(new File(baseDirectory + "\\res\\icons"));
        for(File newDir : myFiles) {
            if(newDir.mkdir()) {
                logging.add(newDir, 1);
            } else {
                logging.add(newDir, -1);
            }
        }
        myFiles.clear();
        logging.add("Метод createRecDir() отработал успешно.");
    }
    /**
     * <p>
     *     Создаёт два файла в каталоге main:
     * </p>
     * <p>
     *     Main.java, Utils.java.
     * </p>
     */
    private void createMainFiles() {
        myFiles.add(new File(baseDirectory + "\\src\\main\\Main.java"));
        myFiles.add(new File(baseDirectory + "\\src\\main\\Utils.java"));
        for(File newFile : myFiles) {
            try{
                if(newFile.createNewFile()) {
                    logging.add(newFile, 1);
                } else {
                    logging.add(newFile, -1);
                }
            } catch (IOException ex) {
                logging.add(ex.getMessage());
            }
        }
        myFiles.clear();
        logging.add("Метод createMainFiles() отработал успешно.");
    }
    /**
     * <p>
     *     Создаёт две директории в каталоге src:
     * </p>
     * <p>
     *     main, test.
     * </p>
     */
    private void createSrcDir() {
        myFiles.add(new File(baseDirectory + "\\src\\main"));
        myFiles.add(new File(baseDirectory + "\\src\\test"));
        for(File newDir : myFiles) {
            if(newDir.mkdir()) {
                logging.add(newDir, 1);
            } else {
                logging.add(newDir, -1);
            }
        }
        myFiles.clear();
        logging.add("Метод createSrcDir() отработал успешно.");
    }
    /**
     * <p>
     *     Создаёт основной каталог и в нём четыре директории:
     * </p>
     * <p>
     *     src, res, savegame, temp.
     * </p>
     */
    private void createMainDir() {
        myFiles.add(new File(baseDirectory));
        myFiles.add(new File(baseDirectory + "\\src"));
        myFiles.add(new File(baseDirectory + "\\res"));
        myFiles.add(new File(baseDirectory + "\\savegames"));
        myFiles.add(new File(baseDirectory + "\\temp"));

        for(File dir : myFiles) {
            if(dir.mkdir()) {
                logging.add(dir, 1);
            } else {
                logging.add(dir, -1);
            }
        }
        myFiles.clear();
        logging.add("Метод createMainDir() отработал успешно.");
    }
    /**
     * Производит последовательное создание фалов и каталогов
     * @param directoryInstall директория установки
     */
    public void installing(String directoryInstall) {
        baseDirectory = directoryInstall;
        createMainDir();
        createSrcDir();
        createMainFiles();
        createResDir();
        logging.createTempFile(baseDirectory);
    }
}
