package conservation;

import installApp.Logging;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Keeper {
    private Logging logging = Logging.getInstance();

    public Logging getLogging() {
        return logging;
    }

    /**
     * Сохраняет прогресс каждого игрока в соответствующий *.dat файл
     *
     * @param path   Путь с именем файла сохранения
     * @param player Экземпляр класса GameProgress для сериализации
     */
    public void saveGame(String path, GameProgress player) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(player);
            logging.add("Прогресс игрока: " + player.toString() + " сохранён.");
        } catch (IOException e) {
            logging.add(e.getMessage());
        }
    }
    /**
     * Формирует ZIP архив с необходимыми файлами
     *
     * @param outputDir  Директрия хранения архива
     * @param playerDirs Директории фалов для архивирования
     */
    public void zipFiles(String outputDir, List<String> playerDirs) {
        String zipDir = outputDir + "saveGames.zip";
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipDir))) {

            for (String srcFile : playerDirs) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
                fis.close();
            }
            logging.add("Архив: " + zipDir + " создан.");

        } catch (Exception ex) {
            logging.add(ex.getMessage());
        }
    }
    /**
     * Удаляет файлы из необходимой директории
     * @param playerDirs список путей к фалам
     */
    public void filesDelete (List<String> playerDirs) {
        File file;
        for (String dir : playerDirs) {
            file = new File(dir);
            if (file.delete()) {
                logging.add("Удалено " + dir);
            } else {
                logging.add("Файл не удалён.");
            }
        }
    }
}
