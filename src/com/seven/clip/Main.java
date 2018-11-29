package com.seven.clip;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {


        try {
            NDMHymn[] hymnDB = new Gson().fromJson(readJSONFromAsset("en_ZW.json"), NDMHymn[].class);
            ArrayList<Hymn> newHymnDb = new ArrayList<>();
            for(NDMHymn object : hymnDB){
                Hymn hymn = new Hymn();
                hymn.header.hymnName = object.hymnName;
                hymn.header.id = object.id;
                hymn.attributes.hymnNumber = object.hymnNumber;
                hymn.attributes.language = object.language;
                hymn.attributes.relationIds = object.relationIds;
                hymn.attributes.relationLanguages = object.relationLanguages;
                hymn.attributes.hasChorus = object.hasChorus;
                hymn.captions.tune = object.captions.tune;
                hymn.captions.meter = object.captions.meter;
                hymn.captions.key = object.captions.key;
                hymn.captions.tonicSolfa = object.captions.tonicSolfa;
                hymn.captions.subtitle = object.captions.subtitle;
                hymn.captions.ndebele = object.captions.ndebele;
                hymn.content.chorus = object.chorus;
                hymn.content.stanzas = object.stanzas;
                newHymnDb.add(hymn);
            }
            FileWriter writer = new FileWriter("_en_ZW.json");
            JsonArray json = new Gson().fromJson(new Gson().toJson(newHymnDb),JsonArray.class);
            JsonObject db = new JsonObject();
            db.add("database",json);
            JsonObject manifest = new JsonObject();
            manifest.addProperty("databaseName", "en_ZW_hymns");
            manifest.addProperty("lastModified", new Date().getTime());
            manifest.addProperty("count", newHymnDb.size());
            db.add("manifest", manifest);
            writer.write(new Gson().toJson(db));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readJSONFromAsset(String path) throws IOException {
        String json;
        InputStream is = new FileInputStream(path);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line;
        line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        json = sb.toString();

        return json;
    }

    public static NDMHymn.Captions copyCaptions(String id,ArrayList<NDMHymn> hymns){
        for(NDMHymn hymn : hymns){
            if(hymn.id.equals(id)){
                return hymn.captions;
            }
        }
        return null;
    }

}
