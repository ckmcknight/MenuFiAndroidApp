package com.ckmcknight.android.menufi.model.datafetchers;

public class RemoteUrls {
    public static final String BASE_SERVER_URL = "https://menufi-192821.appspot.com";
    public static String LOGIN_EXT = "/patron/loginToken/";
    public static String REGISTRATION_EXT = "/patron/registration/";
    public static String RESTAURANTS_EXT = "/restaurants";
    public static String MENU_ITEMS_FORMAT_EXT = RESTAURANTS_EXT + "/%s/items";
    public static String PREFERENCES_EXT = "/preferences";
    public static String PERSONAL_MENU_ITEM_RATING_FORMAT_EXT = "/items/%s/rating/0";
    public static String AVERAGE_MENU_ITEM_RATING_FORMAT_EXT = "/items/%s/rating";
    public static String REGISTER_MENU_ITEM_CLICK_FORMAT_EXT = "/restaurants/%s/items/%s/clicks";


    public static String JSON_DATA_KEY = "data";
}
