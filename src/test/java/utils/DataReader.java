package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;

@NoArgsConstructor
public class DataReader {
    public static final String DEFAULT_PATH = "src/test/resources/Data/";
    protected Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public <T> T get(String fileName, Class<T> type) {
        String path = DEFAULT_PATH + fileName;
        try {
            return gson.fromJson(new FileReader(path), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}


