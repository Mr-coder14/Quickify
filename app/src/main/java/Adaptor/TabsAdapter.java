package Adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Fragments.certificatesfragment;
import Fragments.postfragment;
import Fragments.vieosFragment;


public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment fragment=new certificatesfragment();
                return fragment;

            case 1:
                Fragment fragment1=new postfragment();
                return fragment1;

            case 2:
                Fragment fragment2=new vieosFragment();
                return fragment2;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Certificates";
            case 1:
                return "Posts";
            case 2:
                return "Videos";
            default:
                return null;

        }
    }
}
