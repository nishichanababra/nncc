package com.mandaliyamedicals.medical.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ItemTypeAdapterFactory implements TypeAdapterFactory {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "msg";
    private static final String KEY_RESULT = "result";

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {

                    final JsonObject jsonObject = jsonElement.getAsJsonObject();

                    String status = "";
                    String message = "";
                    if (jsonObject.has(KEY_STATUS) && jsonObject.has(KEY_MESSAGE)) {
                        status = jsonObject.get(KEY_STATUS).getAsString();
                        if (!jsonObject.get(KEY_MESSAGE).isJsonNull())
                            message = jsonObject.get(KEY_MESSAGE).getAsString();

                        if (status.equalsIgnoreCase("success")) {
                            if (jsonObject.get(KEY_RESULT).isJsonObject()) {
                                jsonElement = jsonObject.get(KEY_RESULT).getAsJsonObject();
                            } else if (jsonObject.get(KEY_RESULT).isJsonArray()) {
                                jsonElement = jsonObject.get(KEY_RESULT).getAsJsonArray();
                            }
                        } else {
                            throw new IOException(message);
                        }
                    }

                }

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}

