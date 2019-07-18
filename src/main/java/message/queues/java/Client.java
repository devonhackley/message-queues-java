package message.queues.java;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;
import java.util.TimerTask;

public class Client extends TimerTask  {

    private List<Message> messages;
    private String url;

    public Client(){}
    public Client(String url){
        this.url = url;
    }

    public List<Message> getMessages(){
        return this.messages;
    }

    @Override
    public void run() throws AmazonSQSException {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        List<Message> messages = sqs.receiveMessage(this.url).getMessages();
        this.messages = messages;

        for(Message msg : messages){
            System.out.println(msg.getBody());
        }
    }
}
