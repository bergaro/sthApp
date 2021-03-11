package loader;

import conservation.GameProgress;
import installApp.Logging;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loading {
    // Объект типа Logging аккумулирует и пишет логи в файл
    private Logging logging = Logging.getInstance();
    public Logging getLogging() {
        return logging;
    }
    /**
     * Распаковыает zip-архив в папку и десериализует фалы .dat
     * @param dir путь к каталогу с архивом
     */
    public void unZipFiles(String dir) {
        for (File file : getAllFile(dir)) {
            if(file.getName().contains("zip")) {
                unzip(file, dir);
            }
        }
        logging.add("Все файлы распакованы.");
        deserialization(getAllFile(dir));
        logging.add("Все файлы десериализованы!");
    }
    /**
     * Десериализует файлы в поле типа GameProgress
     * @param desFiles массив сериализованых файлов
     */
    private void deserialization(File[] desFiles) {
        GameProgress player;
        for (File file : desFiles) {
            if(file.getName().contains("unzip")) {
                try (FileInputStream fileInputStream = new FileInputStream(file.getPath());
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                    player = (GameProgress) objectInputStream.readObject();
                    logging.add("Десериализованный объект: " + player.toString());
                    System.out.println(player);
                } catch (Exception ex) {
                    logging.add(ex.getMessage());
                }

            }
        }
    }
    /**
     * Распаковывает архив с файлмами в каталог по переданному пути
     * помечая их префиксом unzip_
     * @param file архив для распаковки
     * @param dir директория
     */
    private void unzip(File file, String dir) {
        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()))){
            ZipEntry entry;
            String name;
            String outFile;
            while ((entry = zipInputStream.getNextEntry()) != null ) {
                name = entry.getName();
                outFile = dir + "unzip_" + name;
                logging.add("Распаковка - " + name);
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                for(int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    fileOutputStream.write(c);
                }
                logging.add("Файл распакован - " + outFile);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }catch(Exception ex){
            logging.add(ex.getMessage());
        }

    }
    /**
     * Собирает в массив типа File[] все файлы из каталога
     * @param dir
     * @return
     */
    private File[] getAllFile(String dir) {
        File catalog = new File(dir);
        File[] fileDirList = catalog.listFiles();
        return fileDirList;
    }
}
