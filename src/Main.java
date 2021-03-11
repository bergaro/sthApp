import conservation.ConservationManager;
import installApp.Install;
import loader.LoaderManager;

public class Main {
    public static void main(String[] args) {
        String path = "E:\\\\Game";
        Install installApp = Install.getInstall();
        ConservationManager conservationManager = ConservationManager.getInstance(path);
        LoaderManager loaderManager = LoaderManager.getInstance(path);
        //Установка
        installApp.installing(path);
        //Сохранение игры (Процесс пишем тоже в temp.txt)
        conservationManager.saving();
        //Загрузка (Процесс также пишем в лог. Но по заданию ещё печатаем в консоль конечный результат)
        loaderManager.loadFiles();

    }
}
