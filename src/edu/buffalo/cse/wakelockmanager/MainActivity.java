
package edu.buffalo.cse.wakelockmanager;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    ListView listView;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create adapter for listview
        /*
         * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         * android.R.layout.simple_list_item_multiple_choice, myList);
         */
        ArrayList<String> appList = getInstalledAppPermissions();
        MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.row, appList);

        // attach adapter to listview
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // attach listeners
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Do something in response to the click
                Toast.makeText(getApplicationContext(),
                        "Item " + listView.getItemAtPosition(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_nextactivity:
                openAnotherActivity();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAnotherActivity() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
    }

    private void openSettings() {
        // TODO Auto-generated method stub

    }
    
    public ArrayList<String> getInstalledAppPermissions()
	{
    	ArrayList<String> appList = new ArrayList<String>();
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo applicationInfo : packages) {
			Log.d("WakeLockDetector", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);

			try {
				PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

				//Get Permissions
				String[] requestedPermissions = packageInfo.requestedPermissions;

				if(requestedPermissions != null) {
					for (int i = 0; i < requestedPermissions.length; i++) {
						//Log.d("WakeLockDetector", requestedPermissions[i]);
						if(requestedPermissions[i].equals("android.permission.WAKE_LOCK")) {
							appList.add(applicationInfo.packageName);
						}

					}
				}
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return appList;
	}

}

class MyCustomAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;
    private LayoutInflater inflater;

    public MyCustomAdapter(Context context, int resourceid, List<String> list) {
        super(context, resourceid, list);
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String str = this.getItem(position);
        TextView tv;
        CheckBox cb;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row, parent, false);

            tv = (TextView) convertView.findViewById(R.id.textView1);
            cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

            tv.setText(str);
            cb.setChecked(false);
        }
        return convertView;
    }
    
}
