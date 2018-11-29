package com.seven.clip;

import java.util.ArrayList;

public class NDMHymn {
    //Header
    public String hymnName;
    public String id;
    public int hymnNumber;
    public String language;
    public ArrayList<String> relationIds;
    public ArrayList<String> relationLanguages;
    boolean hasChorus;
    Captions captions;
    String chorus;
    String[] stanzas;


    class Captions{
        String tune;
        String meter;
        String key;
        String tonicSolfa = "";
        String subtitle;
        String ndebele;
    }



    //Content


    public NDMHymn(){
        relationIds = new ArrayList<>();
        relationLanguages = new ArrayList<>();
        captions = new Captions();

    }
}
