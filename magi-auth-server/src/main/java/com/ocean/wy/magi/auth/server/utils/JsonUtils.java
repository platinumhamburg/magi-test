package com.ocean.wy.magi.auth.server.utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * 类JsonUtils.java的实现描述：fastjson的接口抽象
 * 
 * @author ocean.wy 2016年1月2日 上午11:23:08
 */
public class JsonUtils {

    // fastjson 的序列化配置
    public final static SerializeConfig      fastjson_serializeConfig_noYear = new SerializeConfig();
    public final static SerializeConfig      fastjson_serializeConfig_time   = new SerializeConfig();
    public final static SerializeConfig      fastjson_free_datetime          = new SerializeConfig();

    // 默认打出所有属性(即使属性值为null)|属性排序输出,为了配合历史记录
    private final static SerializerFeature[] fastJsonFeatures                = { SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteEnumUsingToString, SerializerFeature.SortField,
            SerializerFeature.DisableCircularReferenceDetect                };

    static {
        fastjson_serializeConfig_time.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    @SuppressWarnings("unchecked")
    public static final <T> T parseObject(String input, Type clazz) {
        return (T) JSON.parseObject(input, clazz);
    }

    public static JSONObject parseObject(String item) {
        if (StringUtils.isBlank(item)) {
            return null;
        }
        return JSON.parseObject(item);
    }
    public static final JSONArray parseArray(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return JSON.parseArray(text);
    }

    public static <T> T parseObject(String item, Class<T> clazz) {
        if (StringUtils.isBlank(item)) {
            return null;
        }
        return JSON.parseObject(item, clazz);
    }

    public static <T> T parseObject(String item, TypeReference<T> type) {
        if (StringUtils.isBlank(item)) {
            return null;
        }
        return JSON.parseObject(item, type);
    }

    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return JSON.parseArray(text, clazz);
    }

    public static String toJsonString(Object object) {
        return toJsonString(object, fastjson_serializeConfig_noYear);
    }

    public static String toJsonString(Object object, SerializeConfig serializeConfig) {
        if (null == object) {
            return "";
        }
        return JSON.toJSONString(object, serializeConfig, fastJsonFeatures);
    }

}
