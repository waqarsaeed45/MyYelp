package compioneerx1.httpsgithub.myrestaurants.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.ui.RestaurantDetailFragment;

public class RestaurantPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Restaurant> mRestaurants;
    private String mSource;

    public RestaurantPagerAdapter(FragmentManager fm, ArrayList<Restaurant> restaurants, String source) {
        super(fm);
        mRestaurants = restaurants;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return RestaurantDetailFragment.newInstance(mRestaurants, position, mSource);
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }

}
