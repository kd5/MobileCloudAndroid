package mansoor.aziz.camera_selfie;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Receiver extends BroadcastReceiver {

	private final int MY_NOTIFICATION_ID = 11151990;

	@Override
	public void onReceive(Context context, Intent intent) {
		shownitification(context);

	}

	public void shownitification(Context context) {
		Intent restartmainactivity = new Intent(context, MainActivity.class);
		// restartmainactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		PendingIntent cameraintent = PendingIntent.getActivity(context, 0,
				restartmainactivity, PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(
				context).setSmallIcon(R.drawable.camera_cam)
				.setTicker("Selfie Time").setContentText("Its Time For Selfie")
				.setAutoCancel(true).setContentIntent(cameraintent);
		mbuilder.build();
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(MY_NOTIFICATION_ID, mbuilder.build());

	}

}
