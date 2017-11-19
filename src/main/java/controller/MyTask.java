package controller;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import component.KeyWord;
import dao.InsuTestMapper;
import dao.PushMapper;
import dao.QuestionMapper;
import dao.UserMapper;
import entity.InsuTest;
import entity.Push;
import entity.Question;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by p on 2017/11/11.
 */

@Component
public class MyTask {
    @Autowired
    private PushMapper pushMapper;

    @Autowired
    private InsuTestMapper insuTestMapper;

    @Autowired
    private KeyWord keyWord;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired


    public void pushPro() throws IOException, ParseException {
        System.out.println("this is a pushPro");
        Constants.useOfficial();
        Sender sender = new Sender("DCNlcmUReegDMhpXjol8Mg==");
        String messagePayload = "产品推送";
        String title = "意外险";
        String description = "全家无忧保障计划";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(messagePayload)
                .restrictedPackageName("com.exercise.p.citicup")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.send(message, "xBSv7AtaNnDd0je77CQw1qBzFJHp95xtZ39y+j5KQpU=", 3);
        System.out.println("Server response: " + "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
    }

    public void pushQue() throws NoSuchFieldException, IllegalAccessException, IOException, ParseException {
        ArrayList<Push> pushes = (ArrayList<Push>) pushMapper.getAll();
        for (Push push : pushes) {
            InsuTest test = insuTestMapper.get(push.getPhone());
            int max = 0;
            for (int i = 0; i < keyWord.length; i++) {
                if (getData(keyWord.keys_en[max], test) < getData(keyWord.keys_en[i], test)) {
                    max = i;
                }
            }
            Question question = questionMapper.get(push.getPhone());

            int code = 0;
            switch (max) {
                case 5:
                case 7:
                case 8:
                    code = 0;
                    break;
                case 3:
                case 9:
                    code = 1;
                    break;
                case 2:
                    code = 2;
                    break;
                case 0:
                case 1:
                    code = 3;
                    break;
                case 4:
                case 10:
                case 11:
                    code = 4;
                    break;
                case 6:
                    code = 5;
                    break;
                default:
                    code = 0;
                    break;
            }

            if (ifComplete(code,question)) {
                pushOne(code, push.getRegId());
            }
        }
    }

    private float getData(String key, InsuTest test) throws IllegalAccessException, NoSuchFieldException {
        Class<?> cls = InsuTest.class;
        Field f = null;
        f = cls.getDeclaredField(key);
        f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
//        System.out.println(key + " : " + f.getFloat(test));
        return f.getFloat(test);
    }

    private void pushOne(int code, String regId) throws IOException, ParseException {
        Constants.useOfficial();
        Sender sender = new Sender("DCNlcmUReegDMhpXjol8Mg==");
        String messagePayload = "问卷推送";
        String title = "question";
        String description = String.valueOf(code);
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(messagePayload)
                .restrictedPackageName("com.exercise.p.citicup")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.send(message, regId, 3);
    }

    private boolean ifComplete(int code, Question question) {
        switch (code) {
            case 0:
                return question.getOne() == null;
            case 1:
                return question.getFour() == null;
            case 2:
                return question.getSeven() == null;
            case 3:
                return question.getTen() == null;
            case 4:
                return question.getThirteen() == null;
            case 5:
                return question.getFifteen() == null;
            default:
                return false;
        }
    }
}
