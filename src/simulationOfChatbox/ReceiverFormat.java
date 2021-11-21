package simulationOfChatbox;

import java.util.ArrayList;
import java.util.List;

public class ReceiverFormat {
    public static void main(String[] args) {
        String[] receive = {"Hey","how", "are", "you", "today"};
        ReceiverFormat receiverFormat = new ReceiverFormat();
        List<String> receiverPart = receiverFormat.receiverFormat(receive,5,15);
        for(String s : receiverPart){
            System.out.println("|" + s );
        }
    }

    private int maxWidth;
    private int userWidth;

    public List<String> receiverFormat(String[] messages, int userWidth, int maxWidth){
        this.maxWidth = maxWidth;
        this.userWidth = userWidth;

        int left = 0;
        List<String> list = new ArrayList<>();

        while(left < messages.length){
            int right = getRight(left, messages);
            if(left == right){
                String[] oneLineBreak = oneWordLine(messages[left]);

                for(int i = 0; i < oneLineBreak.length - 1; i ++){
                    list.add(addHeadSpace(oneLineBreak[i]));
                }
                int LastLineTaken = oneLineBreak[oneLineBreak.length - 1].length();
                // 最后一行占据的长度

                int left_ = left + 1;
                if(left_ < messages.length && (LastLineTaken + 1 + messages[left_].length() <= userWidth) ){

                    while((left_ < messages.length) && (LastLineTaken + 1 + messages[left_].length() <= userWidth)){
                        left_ ++;
                        LastLineTaken += (messages[left_].length() + 1);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(oneLineBreak[oneLineBreak.length - 1]);
                    sb.append(" ");
                    for(int i = left + 1; i <= left_ - 1; i ++){
                        sb.append(messages[i]);
                        sb.append( i == left_ ? "" : " ");
                    }
                    list.add(addHeadSpace(sb.toString()));
                    System.out.println("left_ :" + left_);
                    left = left_ + 1;
                }else{
                    list.add(addHeadSpace(oneLineBreak[oneLineBreak.length - 1]));
                    left = left + 1;
                }

            }else{
                int curWordLen = 0;
                StringBuilder sb = new StringBuilder();
                for(int i = left; i < right; i ++){
                    sb.append(messages[i]);
                    sb.append(" ");
                    curWordLen += messages[i].length();
                    curWordLen += 1;
                }
                curWordLen += messages[right].length();
                sb.append(messages[right]);
                list.add(addHeadSpace(sb.toString()));

                left = right + 1;
            }
        }
        return list;
    }

    public int getRight(int left, String[] str){
        int curLen = str[left].length();
        int right = left + 1;
        while(right < str.length && (curLen + 1 + str[right].length()) <= userWidth ){
            curLen = curLen + 1 + str[right].length();
            right++;
        }
        return right - 1;
    }

    public String[] oneWordLine(String str){
        int isLen = str.length() % userWidth;
        int len = str.length() / userWidth;
        if(isLen == 0){
            // 整除
            String[] res = new String[len];
            int i = 0;
            int left = 0;
            while(i < len)
            {
                res[i ++] = str.substring(left, left + userWidth);
                left = left + userWidth;
            }
            return res;

        }else{
            //不整除
            String[] res = new String[len + 1];
            int i = 0;
            int left = 0;
            while(i < len){
                res[i ++] = str.substring(left, left + userWidth);
                left = left + userWidth;
            }
            res[len] = str.substring(userWidth * (len), str.length());
            return res;
        }
    }

    public String addHeadSpace(String str){
        int spaceLen = maxWidth - str.length();
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i < spaceLen; i ++){
            sb.insert(0, " ");
        }
        return sb.toString();
    }

}
