package xyr.riun.webcommon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 通用工具类
 */
public class CommonUtil {

    public static Object getNotNull(Object original, Object backup) {
        if (backup == null) {
            throw new RuntimeException("备用参数不能为空");
        }
        return original != null ? original : backup;
    }


    public static Object getOrDefault(Object original, Object defaultValue) {
        return original != null ? original : defaultValue;
    }


    /**
     * 获取随机数 eg:0E35E147
     * @param n
     * @return
     */
    public static String getNonce(int n) {
        String nonce = "";
        //一个十六进制的值的数组
        String[] array = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        //得到6个随机数
        for (int i = 0; i < n; i++) {
            int num = (int) (Math.random() * 16);
            nonce += array[num];
        }
        return nonce;
    }


    /**
     * 格式化获取当前时间
     * @return
     */
    public static String getNowDateFomart(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return format.format(date);
    }


}
