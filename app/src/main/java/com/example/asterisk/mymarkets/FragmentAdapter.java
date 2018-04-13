package com.example.asterisk.mymarkets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.asterisk.mymarkets.MarketFragmentFactory.MARKETS_URLS;

class FragmentAdapter extends FragmentPagerAdapter {

    private static List<MarketFragment> allFragments = new ArrayList<>();
    private static List<String> tabsNames = new ArrayList<>();

    static {
        for (Map.Entry<String, String> pair : MARKETS_URLS.entrySet()){
            tabsNames.add(pair.getKey());
            allFragments.add(MarketFragmentFactory.getMarketFragment(pair.getKey()));
        }
    }

    /**
     * Create a new {@link FragmentAdapter} object.
     *
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        return allFragments.get(position);
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return MARKETS_URLS.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabsNames.get(position);
    }
}