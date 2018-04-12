package com.example.asterisk.mymarkets;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CategoryAdapter extends FragmentPagerAdapter {

//todo look for this class carefully

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    //TODO: Make a factory class
    public Fragment getItem(int position) {
        //TODO: put them into array and retrieve by index
        if (position == 0) {
            return new MarketUKFragment();
        } else if (position == 1) {
            return new MarketGermanyFragment();
        } else if (position == 2){
            return new MarketFranceFragment();
        } else {
            return new MarketUKFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.markets_UK);
        } else if (position == 1) {
            return mContext.getString(R.string.markets_Germany);
        } else {
            return mContext.getString(R.string.markets_France);
        }
    }
}