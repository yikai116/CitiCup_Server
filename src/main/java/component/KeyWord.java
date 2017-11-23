package component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by p on 2017/11/16.
 */
@Component
public class KeyWord {
    public int length = 12;
    public String[] keys_en = {"children", "education", "transportation",
            "safety", "elderly", "disease", "travel",
            "accident", "medical",
            "property", "marriage", "female"};

    public String[] keys_cn = {"子女", "教育", "出行", "人身安全",
            "养老", "疾病", "旅游", "意外险", "医疗",
            "财产安全", "婚姻", "女性"};

    public String[] children = {"子女", "儿童", "幼童", "幼儿", "孩子"};
    public String[] education = {"教育", "学习", "学校", "升学", "学费", "教养", "教导", "培养"};
    public String[] transportation = {"出行", "交通", "外出", "观光", "旅游", "行程", "自驾"};
    public String[] safety = {"安全", "危险", "人身安全", "意外", "生命", "财产"};
    public String[] elderly = {"养老", "老人", "养老保险", "养老金", "父母", "高龄", "护理"};
    public String[] disease = {"疾病", "生病", "住院", "医院", "手术", "医疗", "医疗报销"};
    public String[] travel = {"旅游", "境内旅游", "境外旅游", "报团", "观光", "风景名胜", "自驾游", "环游"};
    public String[] accident = {"意外", "车祸", "自然灾害", "残疾", "急救", "治疗", "火灾"};
    public String[] medical = {"医疗", "手术", "住院", "医疗报销", "医疗事故", "绝症", "医药费"};
    public String[] property = {"财产", "金钱", "资产", "存款", "储蓄", "财富", "家产", "遗产", "银行", "投资", "理财", "保险", "消费", "金融"};
    public String[] marriage = {"婚姻", "结婚", "离婚", "生子"};
    public String[] female = {"女性", "女性疾病", "妇科病", "生育", "育儿", "整形"};
}
