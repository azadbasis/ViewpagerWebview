package azhar.itemselection.nanosoft.viewpagerdemo001;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    static int pageNumber = 3;
    static Fragment googleFragment,cnnFragment,appleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    private void findViewById() {

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        googleFragment = new GoogleFragment();
        cnnFragment = new CNNFragment();
        appleFragment = new AppleFragment();
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
               /* tabLayout.getTabAt(0).setText("GOOGLE");
                tabLayout.getTabAt(1).setText("APPLE");
                tabLayout.getTabAt(2).setText("CNN");*/
            }
        });

    }

    class  MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                return  googleFragment;
            }
            if(position == 1){
                return  appleFragment;
            }
            if(position == 2){
                return  cnnFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return pageNumber;
        }


        @Override
        public CharSequence getPageTitle(int position) {

            if(position == 0){
                return  "Google";
            }
            if(position == 1){
                return  "Apple";
            }
            if(position == 2){
                return  "CNN";
            }


            return super.getPageTitle(position);
        }
    }


}
