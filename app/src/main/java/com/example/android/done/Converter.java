package com.example.android.done;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    @TypeConverter
    public CustomizeConverter storedStringToDays(String value) {
        List<String> customDays = Arrays.asList(value.split("\\s*,\\s*"));
        return new CustomizeConverter(customDays);
    }

    @TypeConverter
    public String daysToStoredString(CustomizeConverter cd) {
        String value = "";

        for (String days :cd.getCustomizeConverter())
            value += days + ",";

        return value;
    }
}
