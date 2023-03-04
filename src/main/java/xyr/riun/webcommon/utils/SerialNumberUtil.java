package xyr.riun.webcommon.utils;

import java.util.Random;

public class SerialNumberUtil {

    /**
     * 随机因子
     */
    private static final char[] randomFactor = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int randomFactorLength = randomFactor.length;
    private static final Random r = new Random();


    /**
     * base62压缩nanotime+uuid后(*)位
     * 长度：18位
     */
    public static String getSerialNumberNew2() {
        //first
        StringBuilder sb = new StringBuilder();
        String time = Base62._10_to_62(System.nanoTime());
        sb.append(time);

        for (int i = 0; i < 18 - time.length(); i++) {
            sb.append(randomFactor[r.nextInt(randomFactorLength)]);
        }

        return sb.toString();
    }


}
