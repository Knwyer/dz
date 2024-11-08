import java.util.ArrayList;
import java.util.List;

// Абстрактный класс для компонентов файловой системы
abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display();
    public abstract int getSize();
}

// Класс для представления файла
class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display() {
        System.out.println("Файл: " + name + ", Размер: " + size + "KB");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// Класс для представления папки, которая может содержать другие компоненты
class Directory extends FileSystemComponent {
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        if (!contains(component)) {
            components.add(component);
        } else {
            System.out.println("Компонент " + component.name + " уже существует в " + name);
        }
    }

    public void remove(FileSystemComponent component) {
        if (contains(component)) {
            components.remove(component);
        } else {
            System.out.println("Компонент " + component.name + " не найден в " + name);
        }
    }

    public boolean contains(FileSystemComponent component) {
        return components.contains(component);
    }

    @Override
    public void display() {
        System.out.println("Папка: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// Клиентский код
public class Main1 {
    public static void main(String[] args) {
        // Создаем файлы
        File file1 = new File("file1.txt", 50);
        File file2 = new File("file2.jpg", 150);
        File file3 = new File("file3.doc", 200);

        // Создаем папки и добавляем файлы
        Directory folder1 = new Directory("Documents");
        folder1.add(file1);
        folder1.add(file2);

        Directory folder2 = new Directory("Projects");
        folder2.add(file3);

        // Создаем корневую папку и добавляем другие папки
        Directory root = new Directory("Root");
        root.add(folder1);
        root.add(folder2);

        // Отображаем структуру и рассчитываем общий размер
        root.display();
        System.out.println("Общий размер: " + root.getSize() + "KB");
    }
}

