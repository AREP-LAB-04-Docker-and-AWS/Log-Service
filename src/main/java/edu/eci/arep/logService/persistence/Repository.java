package edu.eci.arep.logService.persistence;

import com.google.gson.Gson;

import java.util.HashMap;

public class Repository {

    public void saveNewString(String newString) {
        System.out.println("newString = " + newString);
    }

    public String getLastStrings(int qty) {
        HashMap<Integer, String> strings = new HashMap<>();
        for (int i = 0; i < qty; i++) {
            strings.put(i, i+"");
        }
        return new Gson().toJson(strings);
    }
}