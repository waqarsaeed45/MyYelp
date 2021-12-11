package compioneerx1.httpsgithub.myrestaurants.util;

import java.util.List;

import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;

public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, List<Restaurant> restaurants, String source);
}