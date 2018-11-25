package kuwait.com.targetlogistics.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.home.NotificationsActivity;
import org.greenrobot.eventbus.EventBus;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().get("mtype") != null) {
            Utils.setPickupStart(MyApplication.getInstance(), (String) remoteMessage.getData().get("mtype"));
            EventBus.getDefault().post(new Intent("notification"));
            return;
        }
        sendNotification(remoteMessage);
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.addFlags(67108864);
        ((NotificationManager) getSystemService("notification")).notify(0, ((Builder) new Builder(this).setSmallIcon(R.mipmap.ic_launcher).setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)).setContentTitle(getString(R.string.app_name)).setContentText((CharSequence) remoteMessage.getData().get("mtitle")).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824))).build());
    }
}
