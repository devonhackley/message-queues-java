package message.queues.java;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.TimerTask;

public class Publisher extends TimerTask {
    private String url;
    private String message;

    public Publisher() {}
    public Publisher(String url, String message){
        this.url = url;
        this.message = message;
    }
    public void sendMessage(){
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(this.url)
                .withMessageBody(this.message)
                .withDelaySeconds(2);
        sqs.sendMessage(send_msg_request);
        System.out.println("Successfull sent message: " + this.message);
    }

    @Override
    public void run() {
        this.sendMessage();
    }
}
