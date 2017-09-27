package compioneerx1.httpsgithub.myrestaurants.util;

import java.util.ArrayList;

import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;

public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants);
}