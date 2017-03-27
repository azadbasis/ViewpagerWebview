package azhar.scbenefit.nanosoft.dynamictextviewprogrammatically;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    LinearLayout familyInfoLayout;

    int familyMember = 5;

    String[] name = {"A", "B", "C", "D", "E"};
    String[] birth = {"2011", "2012", "2013", "2014", "2015"};
    String[] relation = {"Father", "Mother", "Sister", "Brother", "Uncle"};
    String[] occupation = {"Farmer", "Housewife", "Doctor", "Student", "Engineer"};
    String[] career = {"No", "No", "Yes", "Yes", "Yes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }


    private void findViewById() {
        familyInfoLayout = (LinearLayout) findViewById(R.id.familyInfoLayout);
        //  verticalDesignFamilyInfo();
        horizontallyDesignFamilyInfo();
    }

    private void horizontallyDesignFamilyInfo() {


        for (int i = 0; i < familyMember; i++) {

            //LinearLayout linearLayout = new LinearLayout(this);
            //linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView nameTv = new TextView(this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            llp.setMargins(40,50,0,0);
            nameTv.setTextColor(Color.BLACK);
            nameTv.setLayoutParams(llp);

            TextView birthTv = new TextView(this);
            LinearLayout.LayoutParams birthL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            birthL.setMargins(40,0,0,0);
            birthTv.setTextColor(Color.BLACK);
            birthTv.setLayoutParams(birthL);

            TextView relationTv = new TextView(this);
            LinearLayout.LayoutParams relationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            relationL.setMargins(40,0,0,0);
            relationTv.setTextColor(Color.BLACK);
            relationTv.setLayoutParams(relationL);

            TextView occupationTv = new TextView(this);
            LinearLayout.LayoutParams occupationL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            occupationL.setMargins(40,0,0,0);
            occupationTv.setTextColor(Color.BLACK);
            occupationTv.setLayoutParams(occupationL);

            TextView primaryCareerTv = new TextView(this);
            LinearLayout.LayoutParams primaryCareerL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            primaryCareerL.setMargins(40,0,0,50);
            primaryCareerTv.setTextColor(Color.BLACK);
            primaryCareerTv.setLayoutParams(primaryCareerL);


            View view = new View(this);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 10));
            view.setBackgroundColor(Color.BLUE);

            nameTv.setId(i);
            birthTv.setId(i);
            relationTv.setId(i);
            occupationTv.setId(i);
            primaryCareerTv.setId(i);

            familyInfoLayout.addView(nameTv);
            familyInfoLayout.addView(birthTv);
            familyInfoLayout.addView(relationTv);
            familyInfoLayout.addView(occupationTv);
            familyInfoLayout.addView(primaryCareerTv);
            familyInfoLayout.addView(view);


           /* linearLayout.addView(nameTv);
            linearLayout.addView(birthTv);
            linearLayout.addView(relationTv);
            linearLayout.addView(occupationTv);
            linearLayout.addView(primaryCareerTv);
*/
            //familyInfoLayout.addView(linearLayout);

            if (nameTv.getId() == i) {
                nameTv.setText("NAME : " + name[i]);
            }
            if (birthTv.getId() == i) {
                birthTv.setText("BIRTH : " + birth[i]);
            }
            if (relationTv.getId() == i) {
                relationTv.setText("RELATION : " + relation[i]);
            }
            if (occupationTv.getId() == i) {
                occupationTv.setText("OCCUPATION : " + occupation[i]);
            }
            if (primaryCareerTv.getId() == i) {
                primaryCareerTv.setText("CAREER : " + career[i]);

            }


        }


    }
}
