
package edu.buffalo.cse.wakelockmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class AnotherActivity extends ActionBarActivity {
    CheckBox cb;
    ArrayList<CheckBox> cbList;
    LinearLayout my_layout;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        ArrayList<String> myList = new ArrayList<String>();
        myList.add("Wakelock 1");
        myList.add("Wakelock 2");
        myList.add("Wakelock 3");
        myList.add("Wakelock 4");
        myList.add("Wakelock 5");
        myList.add("Wakelock 6");
        myList.add("Wakelock 7");
        myList.add("Wakelock 8");
        myList.add("Wakelock 9");
        myList.add("Wakelock 10");
        myList.add("Wakelock 11");

        my_layout = (LinearLayout) findViewById(R.id.ll);
        cbList = new ArrayList<CheckBox>();

        for (int i = 0; i < myList.size(); i++)
        {
            // create row for new checkbox
            TableRow row = new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            cb = new CheckBox(this);
            cb.setId(i);
            cb.setText(myList.get(i).toString());
            row.addView(cb);
            my_layout.addView(row);

            // add checkbox to checkbox list
            cbList.add(cb);
        }

        for (int i = 0; i < cbList.size(); i++) {
            // attach listeners

            cbList.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    if (cbList.get(v.getId()).isChecked()) {
                        // handle item checked case here
                        Toast.makeText(getApplicationContext(),
                                "Item " + cbList.get(v.getId()).getText() + " checked",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // handle item unchecked case here
                        Toast.makeText(getApplicationContext(),
                                "Item " + cbList.get(v.getId()).getText() + " unchecked",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.another, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
