package compioneerx1.httpsgithub.myrestaurants.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import compioneerx1.httpsgithub.myrestaurants.Constants;
import compioneerx1.httpsgithub.myrestaurants.R;
import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.util.OnRestaurantSelectedListener;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private static final int MAX_WIDTH = 130;
    private static final int MAX_HEIGHT = 130;


    private List<Restaurant> mRestaurants = new ArrayList<>();
    private Context mContext;
    private OnRestaurantSelectedListener mOnRestaurantSelectedListener;


    public RestaurantListAdapter(Context context, List<Restaurant> restaurants, OnRestaurantSelectedListener restaurantSelectedListener) {
        mContext = context;
        mRestaurants = restaurants;
        mOnRestaurantSelectedListener = restaurantSelectedListener;
    }

    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view, mRestaurants, mOnRestaurantSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position),position+1);
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.priceTextView)
        TextView mPriceTextView;
        @BindView(R.id.categoryTextView)
        TextView mCategoryTextView;
        @BindView(R.id.ratingTextView)
        RatingBar mRatingTextView;
        @BindView(R.id.phoneNumberTextView)
        TextView mPhoneNumberTextView;
        @BindView(R.id.addressTextView)
        TextView mAddressTextView;

        private Context mContext;
        private int mOrientation;
        private List<Restaurant> mRestaurants = new ArrayList<>();
        private OnRestaurantSelectedListener mRestaurantSelectedListener;

        public RestaurantViewHolder(View itemView, List<Restaurant> restaurants, OnRestaurantSelectedListener restaurantSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mRestaurants = restaurants;
            mRestaurantSelectedListener = restaurantSelectedListener;

            itemView.setOnClickListener(this);

        }

        public void bindRestaurant(Restaurant restaurant, int position) {

            Picasso.with(mContext)
                    .load(restaurant.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRestaurantImageView);
            mNameTextView.setText(position+" "+restaurant.getName());
            if (restaurant.getPrice().isEmpty()) {
                mPriceTextView.setVisibility(View.GONE);
            }
            mPriceTextView.setText(restaurant.getPrice() + " ");
            mCategoryTextView.setText("● " + restaurant.getCategories().get(0));
            mRatingTextView.setRating(Float.parseFloat(String.valueOf(restaurant.getRating())));
            mPhoneNumberTextView.setText(restaurant.getPhone());
            mAddressTextView.setText(restaurant.getAddress().get(0)+" "+restaurant.getAddress().get(1));
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mRestaurantSelectedListener.onRestaurantSelected(itemPosition, mRestaurants, Constants.SOURCE_FIND);
        }
    }

//    public String addChar(String str, char ch, int position) {
//        return str.substring(0, position) + ch + str.substring(position);
//    }

}
