package com.serbad.androidexample.common.results;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.serbad.androidexample.common.utils.LogUtil;

import java.lang.reflect.Type;

/**
 * Created by zhou on 2017/3/20.
 */

public class ResultDeserializer implements JsonDeserializer<BaseResult> {
    @Override
    public BaseResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        LogUtil.i(obj.get("results")+"-=-=-=-=");
        if (obj.get("results").toString().equals("{}")) {
            obj.remove("results");
        }
        LogUtil.i(json.toString()+"-=-=-=-=");
        return new Gson().fromJson(json, typeOfT);
    }
}
