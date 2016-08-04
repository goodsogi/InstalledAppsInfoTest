/***
  Copyright (c) 2008-2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Advanced Android Development_
    http://commonsware.com/AdvAndroid
 */

package inbm.packagemanager.test;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InstalledAppInfo extends ListActivity {
	AppAdapter adapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		PackageManager pm = getPackageManager();
		Intent main = new Intent(Intent.ACTION_MAIN, null);

		main.addCategory(Intent.CATEGORY_LAUNCHER);

		List<ResolveInfo> launchables = pm.queryIntentActivities(main, 0);

		Collections
				.sort(launchables, new ResolveInfo.DisplayNameComparator(pm));

		adapter = new AppAdapter(pm, launchables);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ResolveInfo launchable = adapter.getItem(position);
		ActivityInfo activity = launchable.activityInfo;
		Log.i("dictionary", "ybm dictionary     package: "
				+ activity.applicationInfo.packageName + "  class: "
				+ activity.name);
		ComponentName name = new ComponentName(
				activity.applicationInfo.packageName, activity.name);
		Intent i = new Intent();

		// i.addCategory(Intent.CATEGORY_LAUNCHER);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		i.setComponent(name);

		startActivity(i);
	}

	/*
	 * @Override public void onPause() {
	 * 
	 * Log.i("control", "in the onPause"); ComponentName name=new
	 * ComponentName("inbm.packagemanager.test",
	 * "inbm.packagemanager.test.HandleExit"); Intent intent=new
	 * Intent(Intent.ACTION_MAIN); intent.addCategory(Intent.CATEGORY_LAUNCHER);
	 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
	 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	 * 
	 * 
	 * startActivity(intent);
	 * 
	 * super.onPause();
	 * 
	 * }
	 */

	@Override
	public void onStop() {

		Timer timer = new Timer();
		MyTask myTask = new MyTask();
		timer.schedule(myTask, 1000, 1000);

		/*
		 * Log.i("control", "in the onStop"); ComponentName name=new
		 * ComponentName("inbm.packagemanager.test",
		 * "inbm.packagemanager.test.HandleExit"); Intent intent=new
		 * Intent(Intent.ACTION_MAIN);
		 * intent.addCategory(Intent.CATEGORY_LAUNCHER);
		 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 * 
		 * intent.setComponent(name); startActivity(intent);
		 */
		super.onStop();
	}

	class AppAdapter extends ArrayAdapter<ResolveInfo> {
		private PackageManager pm = null;

		AppAdapter(PackageManager pm, List<ResolveInfo> apps) {
			super(InstalledAppInfo.this, R.layout.row, apps);
			this.pm = pm;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = newView(parent);
			}

			bindView(position, convertView);

			return (convertView);
		}

		private View newView(ViewGroup parent) {
			return (getLayoutInflater().inflate(R.layout.row, parent, false));
		}

		private void bindView(int position, View row) {
			TextView label = (TextView) row.findViewById(R.id.label);

			label.setText(getItem(position).loadLabel(pm));

			ImageView icon = (ImageView) row.findViewById(R.id.icon);

			icon.setImageDrawable(getItem(position).loadIcon(pm));
		}
	}

	class MyTask extends TimerTask {
		public void run() {

			/*
			 * ActivityManager am = (ActivityManager)
			 * getSystemService(Context.ACTIVITY_SERVICE);
			 * 
			 * List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
			 * Log.i("ClassName", "Class Name: " +
			 * taskInfo.get(0).topActivity.getClassName()); if
			 * (taskInfo.get(0).topActivity.getPackageName() !=
			 * "inbm.get.runningapp.info") { Log.i("ClassName", "another app");
			 */

			ComponentName name = new ComponentName("inbm.subakc.test",
					"inbm.subakc.test.Intro");
			Intent i = new Intent(Intent.ACTION_MAIN);

			i.addCategory(Intent.CATEGORY_LAUNCHER);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

			getApplication().startActivity(i);

		}
	}
}
