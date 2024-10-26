package com.epam.autotasks;

import com.google.common.collect.ImmutableTable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CatDataProcessor {

    public ImmutableTable<String, Cat.Breed, Integer> createCatTable(List<Cat> cats) {
        ImmutableTable.Builder<String, Cat.Breed, Integer> tableBuilder = ImmutableTable.builder();
        
        for (Cat cat : cats) {
            if (cat != null && cat.getName() != null && cat.getContestResult() != null && cat.getBreed() != null) {
                int sumOfResults = cat.getContestResult().getRunning() +
                                   cat.getContestResult().getJumping() +
                                   cat.getContestResult().getPurring();
                
                tableBuilder.put(cat.getName(), cat.getBreed(), sumOfResults);
            }
        }
    
        return tableBuilder.build();
    }
    

    public JSONArray createCatJson(List<Cat> cats) {
        JSONArray jsonArray = new JSONArray();
        
        for (Cat cat : cats) {
            if (cat != null) {
                JSONObject catJson = new JSONObject();
                
                catJson.put("name", cat.getName());
                catJson.put("age", cat.getAge());
                catJson.put("weight", cat.getWeight());
                catJson.put("awards", cat.getAwards());
                catJson.put("breed", cat.getBreed());

                ContestResult result = cat.getContestResult();
                if (result != null) {
                    JSONObject contestResultJson = new JSONObject();
                    contestResultJson.put("running", result.getRunning());
                    contestResultJson.put("purring", result.getPurring());
                    contestResultJson.put("jumping", result.getJumping());
                    contestResultJson.put("sum", result.getRunning() + result.getPurring() + result.getJumping());

                    catJson.put("contestResult", contestResultJson);
                }

                jsonArray.put(catJson);
            }
        }

        return jsonArray;
    }
}
