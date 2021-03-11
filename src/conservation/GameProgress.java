package conservation;

import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;                // необходимо для сериализации объекта в файл

    private int health;                                             // здоровье объекта
    private int weapons;                                            // кол-во оружия
    private int lvl;                                                // уровень объекта
    private double distance;                                        // проделанный путь
    /**
     * Конструктор инициализирует поля класса для объекта
     * @param health здоровье
     * @param weapons кол-во оружия
     * @param lvl уровень объекта
     * @param distance проделанный путь
     */
    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }
    /**
     * Переопределённый toString для
     * корректного вывода параметров объекта
     * на печать
     * @return строка с параметрами объекта
     */
    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}