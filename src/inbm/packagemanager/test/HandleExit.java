package inbm.packagemanager.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class HandleExit extends Activity {

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main2);
	    
	    Log.i("control", "got in oncreate method of handleexit");
	   
	   int password = 4483;
		  AlertDialog alertDialog = new AlertDialog.Builder(HandleExit.this).create();
		  alertDialog.setTitle("수박씨닷컴");
		  alertDialog.setMessage("정말 나가시겠습니까?");
		  alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		       //here you can add functions
		    	  Log.i("control", "clicked ok button");
		    	  System.exit(0);
		 
		    } });
		  
		  alertDialog.show();
	    
	  }    
}
