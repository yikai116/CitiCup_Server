package component;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import component.KeyWord;
import dao.*;
import entity.InsuPro;
import entity.InsuTest;
import entity.Push;
import entity.Question;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
    private InsuProMapper insuProMapper;

    @Autowired


    public void pushPro() throws IOException, ParseException {
        System.out.println("this is a pushPro");
        Constants.useOfficial();
        Sender sender = new Sender("DCNlcmUReegDMhpXjol8Mg==");
        InsuPro pro = insuProMapper.getOneRand();
        String title = pro.getType();
        String description = pro.getName();
        String messagePayload = pro.getId() + "";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(messagePayload)
                .restrictedPackageName("com.exercise.p.citicup")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        List<Push> pushes = pushMapper.getAll();
        List<String> regIds = new ArrayList<>();
        for (Push push:pushes){
            regIds.add(push.getRegId());
        }
        Result result = sender.send(message, regIds, 3);
    }

    public void pushQue() throws NoSuchFieldException, IllegalAccessException, IOException, ParseException {
        System.out.println("this is a pushQue");
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

//            int code = 0;
//            String key = "";
//            switch (max) {
//                case 5:
//                case 7:
//                case 8:
//                    code = 0;
//                    key = "disease";
//                    break;
//                case 3:
//                case 9:
//                    code = 1;
//                    key = "safety";
//                    break;
//                case 2:
//                    code = 2;
//                    key = "car";
//                    break;
//                case 0:
//                case 1:
//                    code = 3;
//                    key = "children";
//                    break;
//                case 4:
//                case 10:
//                case 11:
//                    code = 4;
//                    key = "old";
//                    break;
//                case 6:
//                    code = 5;
//                    key = "transport";
//                    break;
//                default:
//                    code = 0;
//                    key = "children";
//                    break;
//            }

            if (ifComplete(max,question)) {
                pushOne(max, push);
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

    private void pushOne(int code, Push user) throws IOException, ParseException {
        Constants.useOfficial();
        Sender sender = new Sender("DCNlcmUReegDMhpXjol8Mg==");
        String messagePayload = "www.baidu.com";
        String title = "完善信息";
        String description = "帮助我们更好的为您推荐";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(messagePayload)
                .restrictedPackageName("com.exercise.p.citicup")
                .notifyType(1)     // 使用默认提示音提示
                .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
                .extra(Constants.EXTRA_PARAM_WEB_URI, "http://104.236.132.15:8088/#!/"+ keyWord.keys_en[code] + "/" + user.getPhone())
                .build();
        Result result = sender.send(message, user.getRegId(), 3);
    }

    private boolean ifComplete(int code, Question question) throws NoSuchFieldException, IllegalAccessException {
        if (question == null)
            return true;
        Class<?> cls = Question.class;
        Field f = null;
        f = cls.getDeclaredField(keyWord.keys_en[code]);
        f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
        return f.get(question) == null;
    }
}
