package simulationOfChatbox;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String[][] texts = {{"1", "hey how are you now"}, {"2", "Hi I am good you look great today"},{"1", "Thank you so much and have a good day"} };
        Test t = new Test();
        List<String> res = t.mimicChatBox(texts, 7, 25);
        for(String s : res){
            System.out.println(s);
        }

    }
    private SenderFormat sender;
    private ReceiverFormat receiver;
    public List<String> mimicChatBox(String[][] texts, int userWidth, int maxWidth){
        sender = new SenderFormat();
        receiver = new ReceiverFormat();
        List<String> res = new ArrayList<>();

        for(int i = 0; i < texts.length; i ++){
            String[] cur = texts[i];
            if(cur[0] == "1"){
                String[] processSender = cur[1].split(" ");
                res.addAll(sender.SenderFormat(processSender, userWidth, maxWidth));
            }

            if(cur[0] == "2"){
                String[] processReceiver = cur[1].split(" ");
                res.addAll(receiver.receiverFormat(processReceiver, userWidth, maxWidth));
            }
        }
        return res;
    }
}
