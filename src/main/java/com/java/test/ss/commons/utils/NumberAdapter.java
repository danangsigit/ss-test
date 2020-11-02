package com.java.test.ss.commons.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class NumberAdapter implements JsonSerializer<Double> {
    @Override
    public JsonElement serialize(Double o, Type type, JsonSerializationContext jsonSerializationContext) {
        if (o == o.longValue()) {
            return new JsonPrimitive(o.longValue());
        }
        return new JsonPrimitive(o);
    }
}
