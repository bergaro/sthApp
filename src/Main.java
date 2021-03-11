import conservation.ConservationManager;
import installApp.Install;

public class Main {
    public static void main(String[] args) {
        String path = "E:\\\\Game";
        Install installApp = Install.getInstall();
        ConservationManager conservationManager = ConservationManager.getInstance(path);
        //Установка
        installApp.installing(path);
        //Сохранение игры (Процесс пишем тоже в temp.txt)
        conservationManager.saving();

    }
}
