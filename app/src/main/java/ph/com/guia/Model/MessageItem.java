package ph.com.guia.Model;

public class MessageItem {
    public int image;
    public String name;
    public String message_part;
    String message;

    public MessageItem(int image, String name, String message_part, String message) {
        this.image = image;
        this.name = name;
        this.message_part = message_part;
        this.message = message;
    }
}
