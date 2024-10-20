import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Интерфейс IMediator, определяющий контракт для взаимодействия между пользователями
interface IMediator {
    void sendMessage(String message, IUser user, String channel);
    void addUserToChannel(IUser user, String channel);
    void removeUserFromChannel(IUser user, String channel);
    boolean channelExists(String channel);
}

// Класс ChatMediator, реализующий интерфейс IMediator и управляющий взаимодействием между пользователями
class ChatMediator implements IMediator {
    private Map<String, List<IUser>> channels;

    public ChatMediator() {
        this.channels = new HashMap<>();
    }

    @Override
    public void sendMessage(String message, IUser user, String channel) {
        if (!channelExists(channel)) {
            System.out.println("Channel does not exist!");
            return;
        }

        List<IUser> users = channels.get(channel);
        for (IUser u : users) {
            // Сообщение не отправляется самому отправителю
            if (u != user) {
                u.receive(message, channel);
            }
        }
    }

    @Override
    public void addUserToChannel(IUser user, String channel) {
        if (!channels.containsKey(channel)) {
            channels.put(channel, new ArrayList<>());
        }
        channels.get(channel).add(user);
        System.out.println(user.getName() + " присоединился к каналу " + channel);
    }

    @Override
    public void removeUserFromChannel(IUser user, String channel) {
        if (channelExists(channel)) {
            channels.get(channel).remove(user);
            System.out.println(user.getName() + " покинул канал " + channel);
        }
    }

    @Override
    public boolean channelExists(String channel) {
        return channels.containsKey(channel);
    }
}

// Интерфейс IUser, определяющий методы взаимодействия пользователей с посредником
interface IUser {
    void send(String message, String channel);
    void receive(String message, String channel);
    String getName();
}

// Класс User, представляющий конкретного участника чата
class User implements IUser {
    private IMediator mediator;
    private String name;

    public User(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void send(String message, String channel) {
        System.out.println(this.name + " отправляет сообщение: " + message + " в канал " + channel);
        mediator.sendMessage(message, this, channel);
    }

    @Override
    public void receive(String message, String channel) {
        System.out.println(this.name + " получил сообщение в канале " + channel + ": " + message);
    }

    @Override
    public String getName() {
        return this.name;
    }
}

// Класс ChannelMediator, управляющий несколькими каналами
class ChannelMediator extends ChatMediator {
    // Здесь можно добавить функционал для работы с несколькими каналами
}

// Клиентский код для демонстрации работы системы
public class Main {
    public static void main(String[] args) {
        IMediator chatMediator = new ChatMediator();

        IUser user1 = new User(chatMediator, "User1");
        IUser user2 = new User(chatMediator, "User2");
        IUser user3 = new User(chatMediator, "User3");

        String channel1 = "General";
        String channel2 = "Tech";

        // Добавляем пользователей в каналы
        chatMediator.addUserToChannel(user1, channel1);
        chatMediator.addUserToChannel(user2, channel1);
        chatMediator.addUserToChannel(user3, channel2);

        // Отправка сообщений в разные каналы
        user1.send("Привет всем!", channel1);
        user3.send("Технические вопросы", channel2);

        // Удаление пользователя и отправка сообщения
        chatMediator.removeUserFromChannel(user2, channel1);
        user1.send("Где все?", channel1);

        // Пробуем отправить сообщение в несуществующий канал
        user1.send("Привет!", "Random");
    }
}
