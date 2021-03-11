package conservation;

import installApp.Logging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConservationManager {
    //Общая директория сохранения файлов
    private static String outputDir;
    //Список директорий файлов *.dat
    private static List<String> playerDirs = new ArrayList<>(5);
    //Список объектов прогресса
    private static List<GameProgress> playersProgress = new ArrayList<>(5);
    //Поле хранящее экземпляр класса
    private static ConservationManager instance;
    /**
     * Создание класса возможно только посредством
     * метода getInstance()
     */
    private ConservationManager(String dir) {
        outputDir = dir + "\\savegames\\";
    }
    /**
     * Допустимо иметь только один экземпляр
     * данного класса
     * @return ConservationManager object
     */
    public static ConservationManager getInstance(String dir) {
        if(instance == null) {
            instance = new ConservationManager(dir);
        }
        return instance;
    }

    /**
     * Алгоритм сериализации, архивирования и удаления
     * лишних файлов
     */
    public void saving() {
        Keeper keeper = new Keeper();
        Logging log = keeper.getLogging();
        File logFile = new File(log.getLogFileDir());
        createFileDirs();
        createGameProgress();
        for(int i = 0; i < 3; i++) {
            keeper.saveGame(playerDirs.get(i), playersProgress.get(i));
        }
        keeper.zipFiles(outputDir, playerDirs);
        keeper.filesDelete(playerDirs);
        log.writeLog(logFile);
    }

    /**
     * Добавляет в список директории будущих файлов
     */
    private void createFileDirs() {
        //Наш каталог для сериализации объектов E:\Game\savegames
        playerDirs.add(outputDir + "gameSave1.dat");
        playerDirs.add(outputDir + "gameSave2.dat");
        playerDirs.add(outputDir + "gameSave3.dat");
    }
    /**
     * Добавляет в список прогресс новых игроков
     */
    private void createGameProgress() {
        playersProgress.add(new GameProgress(100, 2, 10, 8.4));
        playersProgress.add(new GameProgress(78, 4, 20, 27.3));
        playersProgress.add(new GameProgress(20, 1, 40, 265.8));
    }

}
