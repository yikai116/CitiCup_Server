package component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by p on 2017/11/16.
 */
@Component
public class KeyWord {
    public int length = 12;
    public String[] keys_en = {"children","education","transportation",
            "safety","elderly","disease","travel",
            "accident","medical",
            "property","marriage","female"};

    public String[] keys_cn = {"子女","教育","出行","人身安全",
            "养老","疾病","旅游","意外险","医疗",
            "财产安全","婚姻","女性"};

    public String[] children = {"子女"};
    public String[] education = {"教育"};
    public String[] transportation = {"出行"};
    public String[] safety = {"安全"};
    public String[] elderly = {"养老"};
    public String[] disease = {"疾病"};
    public String[] travel = {"旅游"};
    public String[] accident = {"意外"};
    public String[] medical = {"医疗"};
    public String[] property = {"财产"};
    public String[] marriage = {"婚姻"};
    public String[] female = {"女性"};
}
