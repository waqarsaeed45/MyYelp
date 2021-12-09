package compioneerx1.httpsgithub.myrestaurants;

import android.os.Build;
import compioneerx1.httpsgithub.myrestaurants.BuildConfig;


/**
 * Created by Guest on 9/11/17.
 */

public class Constants {
    //public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
    public static final String YELP_CONSUMER_KEY = "wl172Y7IDFQqOs88Iq3Kn1CPhbg41IhMhpEgs-5z4aEhWVE4reTtL8u_9t-yav0eZWw3KI46Be_RKUebJW74h0vOhrFTf8a5r_DBO_w5O1Yw-vU7NeY9ecW11qevYXYx";
    public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
   // public static final String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search";
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search?latitude=37.786882&longitude=-122.399972";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
    public static final String YELP_CATEGORIES_QUERY_PARAMETER = "categories";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_RESTAURANTS = "restaurants";
    public static final String FIREBASE_QUERY_INDEX = "index";

    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_RESTAURANTS = "restaurants";

    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}