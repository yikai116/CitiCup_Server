package helper;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by p on 2017/7/31.
 */
public class Helper {

    /**
     * 密码MD5加密算法
     * @param inStr 需要加密的字符串
     * @return 解密之后的字符串
     * @throws Exception
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 得到四位验证码
     * @return 验证码
     */
    public static String getVerCode() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String verCode;
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        verCode = sb.toString();
        return verCode;
    }

    /**
     * 判断date是否过期
     * @param date 需要被判断的时间
     * @return 是否过期
     */
    public static boolean isExpired(Timestamp date){
        Calendar calendar = Calendar.getInstance();
        java.util.Date utilDate = calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        Timestamp nowDate = new Timestamp(utilDate.getTime());
        return nowDate.after(date);
    }
}
