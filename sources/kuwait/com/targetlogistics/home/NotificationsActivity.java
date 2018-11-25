package kuwait.com.targetlogistics.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityNotificationsBinding;
import kuwait.com.targetlogistics.model.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends BaseActivity {
    private NotificationsAdapter adapter;
    ActivityNotificationsBinding binding;
    private ArrayList<Notification> list = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityNotificationsBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        this.list = new ArrayList();
        setTitle(getString(R.string.notifications));
        callNotificationData();
        this.binding.recNotificationsList.setLayoutManager(new LinearLayoutManager(this.me, 1, false));
        this.binding.recNotificationsList.setHasFixedSize(true);
    }

    private void callNotificationData() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.GET_NOTIFICATIONS);
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                int i = 0;
                NotificationsActivity.this.dismissDialog();
                TextView textView;
                int i2;
                RecyclerView recyclerView;
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        NotificationsActivity.this.list = (ArrayList) new Gson().fromJson(((JsonObject) response.body()).getAsJsonArray("data"), new TypeToken<ArrayList<Notification>>() {
                        }.getType());
                        if (NotificationsActivity.this.list != null) {
                            NotificationsActivity.this.adapter = new NotificationsAdapter(NotificationsActivity.this.me, NotificationsActivity.this.list);
                            NotificationsActivity.this.binding.recNotificationsList.setAdapter(NotificationsActivity.this.adapter);
                            textView = NotificationsActivity.this.binding.tvNoRecordFound;
                            if (NotificationsActivity.this.list.isEmpty()) {
                                i2 = 0;
                            } else {
                                i2 = 8;
                            }
                            textView.setVisibility(i2);
                            recyclerView = NotificationsActivity.this.binding.recNotificationsList;
                            if (NotificationsActivity.this.list.isEmpty()) {
                                i = 8;
                            }
                            recyclerView.setVisibility(i);
                            return;
                        }
                        return;
                    }
                    textView = NotificationsActivity.this.binding.tvNoRecordFound;
                    if (NotificationsActivity.this.list.isEmpty()) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    textView.setVisibility(i2);
                    recyclerView = NotificationsActivity.this.binding.recNotificationsList;
                    if (NotificationsActivity.this.list.isEmpty()) {
                        i = 8;
                    }
                    recyclerView.setVisibility(i);
                    return;
                }
                textView = NotificationsActivity.this.binding.tvNoRecordFound;
                if (NotificationsActivity.this.list.isEmpty()) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                textView.setVisibility(i2);
                recyclerView = NotificationsActivity.this.binding.recNotificationsList;
                if (NotificationsActivity.this.list.isEmpty()) {
                    i = 8;
                }
                recyclerView.setVisibility(i);
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                int i;
                int i2 = 0;
                NotificationsActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
                TextView textView = NotificationsActivity.this.binding.tvNoRecordFound;
                if (NotificationsActivity.this.list.isEmpty()) {
                    i = 0;
                } else {
                    i = 8;
                }
                textView.setVisibility(i);
                RecyclerView recyclerView = NotificationsActivity.this.binding.recNotificationsList;
                if (NotificationsActivity.this.list.isEmpty()) {
                    i2 = 8;
                }
                recyclerView.setVisibility(i2);
            }
        });
    }
}
