package kuwait.com.targetlogistics.fcm;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import kuwait.com.targetlogistics.common.Utils;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static final String ACTION_FCM_TOKEN_GENERATED = "hightecit.hallmanagement.kuwait.FCM_TOKEN";
    private static final String TAG = "MyFirebaseIIDService";

    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Utils.setGCMToken(this, refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(new Intent(ACTION_FCM_TOKEN_GENERATED));
        Utils.printMsg("deviceToken", refreshedToken);
    }
}
