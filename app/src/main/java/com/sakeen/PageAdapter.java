package com.sakeen;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    public PageAdapter(FragmentManager fm) {
        super(fm);
        addFragment(new home());
        addFragment(new cakes());
        addFragment(new orders());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }

}
