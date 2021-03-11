package loader;

import conservation.ConservationManager;
import installApp.Logging;

import java.io.File;

public class LoaderManager {
    //Общая директория сохранения файлов
    private static String outputDir;
    //Поле хранящее экземпляр класса
    private static LoaderManager instance;
    /**
     * Создание класса возможно только посредством
     * метода getInstance()
     */
    private LoaderManager(String dir) {
        outputDir = dir + "\\savegames\\";
    }
    /**
     * Допустимо иметь только один экземпляр
     * данного класса
     * @return ConservationManager object
     */
    public static LoaderManager getInstance(String dir) {
        if(instance == null) {
            instance = new LoaderManager(dir);
        }
        return instance;
    }
    public void loadFiles() {
        Loading loading = new Loading();
        Logging logging = loading.getLogging();
        File logFile = new File(logging.getLogFileDir());
        loading.unZipFiles(outputDir);
        logging.writeLog(logFile);
    }
}
