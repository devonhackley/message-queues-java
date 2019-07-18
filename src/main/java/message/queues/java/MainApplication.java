package message.queues.java;
import java.util.Timer;
import java.util.TimerTask;

public class MainApplication {
    public static void main(String[] args) {
        String url = "https://sqs.us-east-2.amazonaws.com/373479543786/QueueB";

        // send message to queue
        Timer timer = new Timer();
        TimerTask task = new Publisher(url, "HEEYYYYYYYYYYY");

        timer.schedule(task, 1000, 6000);

        // recieve messages from queue
        TimerTask task2 = new Client(url);
        timer.schedule(task2, 3000, 6000);
    }
}
