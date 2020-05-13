package com.yazd;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyData {
    private static ArrayList<DataModel> allData;

    public static ArrayList<DataModel> getAllData(Context context) {
        if (allData != null){
            return allData;
        }else {
            return getdata(context);
        }
    }
//    public static Integer[] nameArray = {R.string.item_title1,R.string.item_title2,R.string.item_title3,R.string.item_title4,R.string.item_title5,R.string.item_title6,R.string.item_title7,R.string.item_title8,R.string.item_title9,R.string.yadavari,R.string.barnameha,R.string.poshtibani};
//    public static Integer[] SubDataCount = {6, 5, 5, 3,5, 3, 1, 4, 4,1,1,1};

//    public static String[] drawableArray = {R.drawable.a, R.drawable.a2, R.drawable.a3,
//            R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
//            R.drawable.a8, R.drawable.a9,R.drawable.a9,R.drawable.a9,R.drawable.a9};

//    public static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6, 7, 8,9,10,11};

    private static ArrayList<DataModel> getdata(Context context){
        JSONObject jsontxt = null;
        allData = new ArrayList<>();
        try {
            jsontxt = new JSONObject(context.getResources().getString(R.string.json_text)) ;
            allData = dataFinder(jsontxt.getJSONArray("PART"),context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allData;
    }

    private static ArrayList<DataModel> dataFinder(JSONArray parts, Context context){
        ArrayList<DataModel> mydata = new ArrayList<>();
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        try {
            for (int i = 0; i < parts.length(); i++) {
                JSONObject onepart = parts.getJSONObject(i);
                String name = onepart.getString("NAME");
//                String simage = onepart.getString("IMAGE");
                String simage ="a2";
                int image = resources.getIdentifier(simage, "drawable", packageName);
                if (onepart.has("CHILD")) {
                    JSONArray child = onepart.getJSONArray("CHILD");
                    mydata.add(new DataModel(name, child.length(), dataFinder(child, context), i, image));
                } else {
                    mydata.add(new DataModel(name, i, image));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mydata;
    }

}
