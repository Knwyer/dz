// Подсистемы мультимедиа-центра
class TV {
    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }

    public void setChannel(int channel) {
        System.out.println("Телевизор переключен на канал " + channel);
    }
}

class AudioSystem {
    public void on() {
        System.out.println("Аудиосистема включена.");
    }

    public void off() {
        System.out.println("Аудиосистема выключена.");
    }

    public void setVolume(int volume) {
        System.out.println("Громкость установлена на " + volume);
    }
}

class DVDPlayer {
    public void play() {
        System.out.println("DVD-проигрыватель начал воспроизведение.");
    }

    public void pause() {
        System.out.println("Воспроизведение DVD приостановлено.");
    }

    public void stop() {
        System.out.println("Воспроизведение DVD остановлено.");
    }
}

class GameConsole {
    public void on() {
        System.out.println("Игровая консоль включена.");
    }

    public void startGame(String game) {
        System.out.println("Запуск игры: " + game);
    }
}

// Фасад для управления мультимедиа-центром
class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade(TV tv, AudioSystem audioSystem, DVDPlayer dvdPlayer, GameConsole gameConsole) {
        this.tv = tv;
        this.audioSystem = audioSystem;
        this.dvdPlayer = dvdPlayer;
        this.gameConsole = gameConsole;
    }

    public void watchMovie() {
        tv.on();
        audioSystem.on();
        dvdPlayer.play();
        System.out.println("Система готова к просмотру фильма.");
    }

    public void endMovie() {
        dvdPlayer.stop();
        tv.off();
        audioSystem.off();
        System.out.println("Просмотр фильма завершен, система выключена.");
    }

    public void playGame(String game) {
        tv.on();
        gameConsole.on();
        gameConsole.startGame(game);
        System.out.println("Система готова к игре.");
    }

    public void listenToMusic() {
        tv.on();
        audioSystem.on();
        audioSystem.setVolume(15);
        System.out.println("Система готова к прослушиванию музыки.");
    }

    public void adjustVolume(int volume) {
        audioSystem.setVolume(volume);
        System.out.println("Громкость изменена.");
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        // Создание подсистем
        TV tv = new TV();
        AudioSystem audioSystem = new AudioSystem();
        DVDPlayer dvdPlayer = new DVDPlayer();
        GameConsole gameConsole = new GameConsole();

        // Создание фасада
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audioSystem, dvdPlayer, gameConsole);

        // Использование фасада
        homeTheater.watchMovie();
        homeTheater.adjustVolume(20);
        homeTheater.endMovie();

        homeTheater.listenToMusic();

        homeTheater.playGame("Super Mario");
    }
}
