package com.java.test.ss.commons.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static String toJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().setDateFormat(DateUtils.YYYY_MM_DD).create().toJson(object);
    }

    public static String toJson(Object object, boolean numberAdapter) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Double.class, new NumberAdapter());
        return builder.setPrettyPrinting().setDateFormat(DateUtils.YYYY_MM_DD).create().toJson(object);
    }

    public static String toJson(Object object, String dateFormat) {
        return new GsonBuilder().setPrettyPrinting().setDateFormat(dateFormat).create().toJson(object);
    }

    public static String toJson(Object object, String dateFormat, FieldNamingPolicy fieldNamingPolicy) {
        return new GsonBuilder().setPrettyPrinting().setDateFormat(dateFormat).setFieldNamingPolicy(fieldNamingPolicy).create().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return new GsonBuilder().setDateFormat(DateUtils.YYYY_MM_DD).create().fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Class<T> clazz, Class... adapterClass) {
        GsonBuilder builder = new GsonBuilder();
        if (adapterClass != null && adapterClass.length > 0) {
            for (int i = 0; i < adapterClass.length; i++) {
                builder.registerTypeAdapter(adapterClass[i], new JsonInterfaceAdapter());
            }
        }
        return builder.setDateFormat(DateUtils.YYYY_MM_DD).create().fromJson(json, clazz);
    }

    public static <T> T fromJsonInputStream(InputStream json, Class<T> clazz) {
        return new GsonBuilder().setDateFormat(DateUtils.YYYY_MM_DD).create().fromJson(new InputStreamReader(json), clazz);
    }

    public static <T> T fromJson(String json, Class<T> clazz, String dateFormat, FieldNamingPolicy fieldNamingPolicy) {
        return new GsonBuilder().setDateFormat(dateFormat).setFieldNamingPolicy(fieldNamingPolicy).create().fromJson(json, clazz);
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        List list = new GsonBuilder().setDateFormat(DateUtils.YYYY_MM_DD).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().fromJson(json, List.class);
        for (Object o : list) {
            T t = fromJson(toJson(o), clazz);
            result.add(t);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> fromJsonList(InputStream stream, Class<T> clazz, String dateFormat, FieldNamingPolicy fieldNamingPolicy) {
        List<T> result = new ArrayList<>();
        List list = new GsonBuilder().setDateFormat(dateFormat).setFieldNamingPolicy(fieldNamingPolicy).create().fromJson(new InputStreamReader(stream), List.class);
        for (Object o : list) {
            T t = fromJson(toJson(o,dateFormat,fieldNamingPolicy), clazz, dateFormat,fieldNamingPolicy);
            result.add(t);
        }
        return result;
    }

    public static <T> T fromJson(InputStream stream, Class<T> clazz) {
        return new GsonBuilder().setDateFormat(DateUtils.YYYY_MM_DD).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().fromJson(new InputStreamReader(stream), clazz);
    }

    public static <T> T fromJson(InputStream stream, Class<T> clazz, Class... adapterClass) {
        GsonBuilder builder = new GsonBuilder();
        if (adapterClass != null && adapterClass.length > 0) {
            for (int i = 0; i < adapterClass.length; i++) {
                builder.registerTypeAdapter(adapterClass[i], new JsonInterfaceAdapter());
            }
        }
        return builder.setDateFormat(DateUtils.YYYY_MM_DD).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().fromJson(new InputStreamReader(stream), clazz);
    }

    public static void main(String... args) {
    }

}
