package net.oschina.git.roland.wimm.common.utils;

import java.math.BigDecimal;

/**
 * Created by Roland on 2017/4/10.
 */

public class NumericUtils {

    /**
     * 获取四舍五入后的Double值
     * @param value 需要四舍五入的值
     * @param scale 几位小数
     * @return 四舍五入后的值
     */
    public static double getRoundDoubleValue(double value, int scale) {
        return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
