package com.exercise.utils;

public class URL {
    public static String extractPageName(String urlString){
        if (urlString == null)
            return null;

        int lastSlash = urlString.lastIndexOf("/");
        String pageAndExtensions = urlString.substring(lastSlash + 1);
        int lastQuestion = pageAndExtensions.lastIndexOf("?");

        if (lastQuestion==-1)
            lastQuestion = pageAndExtensions.length();

        return pageAndExtensions.substring(0, lastQuestion);
    }

}
