package mansoor.aziz.camera_selfie;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	static final int RQCODE = 1;
	ImageView mview;
	static final int TWO_MINUTES = 120000;
	//ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mview = (ImageView) findViewById(R.id.imageView1);
		//listview = (ListView) findViewById(R.id.listview1);
		//int image = R.drawable.camera_cam;


		Intent i = new Intent(this, Receiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, i,
				0);
		AlarmManager al = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		al.setRepeating(al.RTC_WAKEUP, System.currentTimeMillis(), TWO_MINUTES,
				pi);// (al.RTC_WAKEUP,
		// System.currentTimeMillis(),2000,
		// pi);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RQCODE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap bmap = (Bitmap) extras.get("data");
			mview.setImageBitmap(bmap);

		}
		super.onActivityResult(requestCode, resultCode, data);
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
		int id = item.getItemId();
		if (id == R.id.icon_camera) {
			opencamera();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void opencamera() {
		Toast.makeText(this.getApplicationContext(), "touched",
				Toast.LENGTH_LONG).show();
		Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takepic.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takepic, RQCODE);
		}

	}
}

//------------------------------------------------------------------
/*
class Customlistadapter extends ArrayAdapter<String>{

	public Customlistadapter(Context c, int resource, List<String> objects) {
		super(c, R.layout.mylist, objects);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

LayoutInflater inflator = 
		return super.getView(position, convertView, parent);
	}
	
	
}*/
