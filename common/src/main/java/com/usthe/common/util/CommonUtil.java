/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.usthe.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共工具类
 * @author tomsun28
 * @date 2021/11/20 17:29
 */
@Slf4j
public class CommonUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(19[0-9])|(18[0-9])|(17[0-9]))+\\d{8})?$");

    private static final int PHONE_LENGTH = 11;

    /**
     * 将字符串str转换为double数字类型
     * @param str string
     * @return double 数字
     */
    public static Double parseStrDouble(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将字符串str,此字符串可能带单位,转换为double数字类型
     * 将数值小数点限制到4位
     * @param str string
     * @param unit 字符串单位
     * @return string格式的 double 数字 小数点最大到4位
     */
    public static String parseDoubleStr(String str, String unit) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            if (unit != null && str.endsWith(unit)) {
                str = str.substring(0, str.length() - unit.length());
            }
            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.setScale(4, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 邮箱格式校验
     * @param email 邮箱
     * @return 是否校验成功
     */
    public static boolean validateEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        Matcher m = EMAIL_PATTERN.matcher(email);
        return m.find();
    }

    /**
     * 手机号格式校验
     * @param phoneNum 手机号
     * @return 是否校验成功
     */
    public static boolean validatePhoneNum(String phoneNum) {
        if (phoneNum == null || "".equals(phoneNum) || phoneNum.length() != PHONE_LENGTH) {
            return false;
        }
        Matcher m = PHONE_PATTERN.matcher(phoneNum);
        return m.find();
    }

    public static String getMessageFromThrowable(Throwable throwable) {
        if (throwable == null) {
            return "throwable is null, unknown error.";
        }
        String message = null;
        Throwable cause = throwable.getCause();
        if (cause != null) {
            message = cause.getMessage();
        }
        if (message == null || "".equals(message)) {
            message = throwable.getMessage();
        }
        if (message == null || "".equals(message)) {
            message = throwable.getLocalizedMessage();
        }
        if (message == null || "".equals(message)) {
            message = throwable.toString();
        }
        if (message == null || "".equals(message)) {
            message = "unknown error.";
        }
        return message;
    }

}
