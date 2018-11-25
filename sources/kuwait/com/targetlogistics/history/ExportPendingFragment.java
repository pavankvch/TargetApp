package kuwait.com.targetlogistics.history;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.FragmentPendingBinding;
import kuwait.com.targetlogistics.model.HistoryData;
import kuwait.com.targetlogistics.model.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExportPendingFragment extends Fragment {
    static final /* synthetic */ boolean $assertionsDisabled = (!ExportPendingFragment.class.desiredAssertionStatus());
    FragmentPendingBinding binding;
    private ArrayList<HistoryData> completedList;
    private String currentFragment = "";
    private String from;
    private String functionName = "";
    private HistoryAdapter historyAdapter;
    private ArrayList<Value> historyList;
    HistoryActivity me;
    private ArrayList<HistoryData> pendingList;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (FragmentPendingBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_pending, container, false);
        View view = this.binding.getRoot();
        if (getArguments().containsKey("CurrentFragment")) {
            this.currentFragment = getArguments().getString("CurrentFragment");
        }
        if (getArguments().containsKey("HistoryType")) {
            this.from = getArguments().getString("HistoryType");
            if (!$assertionsDisabled && this.from == null) {
                throw new AssertionError();
            } else if (this.from.equalsIgnoreCase("Export")) {
                this.functionName = BaseUrl.GET_EXPORT_HISTORY;
            } else if (this.from.equalsIgnoreCase("Import")) {
                this.functionName = BaseUrl.GET_IMPORT_HISTORY;
            } else if (this.from.equalsIgnoreCase("Delivery")) {
                this.functionName = BaseUrl.GET_DELIVERY_HISTORY;
            }
        }
        this.me = (HistoryActivity) getActivity();
        callHistoryAPI();
        init();
        return view;
    }

    private void init() {
        this.historyList = new ArrayList();
        this.binding.recHistoryList.setLayoutManager(new LinearLayoutManager(this.me, 1, false));
        this.binding.recHistoryList.setHasFixedSize(true);
    }

    public void callHistoryAPI() {
        this.me.showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", this.functionName);
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ExportPendingFragment.this.me.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        ArrayList<HistoryData> historyDataArrayList;
                        ExportPendingFragment.this.pendingList = new ArrayList();
                        ExportPendingFragment.this.completedList = new ArrayList();
                        JsonObject jsonObject = ((JsonObject) response.body()).getAsJsonObject("data");
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<HistoryData>>() {
                        }.getType();
                        ExportPendingFragment.this.pendingList = (ArrayList) gson.fromJson(jsonObject.get("pending"), listType);
                        ExportPendingFragment.this.completedList = (ArrayList) gson.fromJson(jsonObject.get("completed"), listType);
                        boolean isPending = false;
                        if (ExportPendingFragment.this.currentFragment.equalsIgnoreCase("ExportPending") || ExportPendingFragment.this.currentFragment.equalsIgnoreCase("ImportPending") || ExportPendingFragment.this.currentFragment.equalsIgnoreCase("DeliveryPending")) {
                            isPending = true;
                        }
                        if (isPending) {
                            historyDataArrayList = ExportPendingFragment.this.pendingList;
                        } else {
                            historyDataArrayList = ExportPendingFragment.this.completedList;
                        }
                        if (historyDataArrayList == null || historyDataArrayList.size() <= 0) {
                            ExportPendingFragment.this.binding.recHistoryList.setVisibility(8);
                            ExportPendingFragment.this.binding.tvNoRecordFound.setVisibility(0);
                            return;
                        }
                        for (int i = 0; i < historyDataArrayList.size(); i++) {
                            HistoryData data = (HistoryData) historyDataArrayList.get(i);
                            Value value1 = new Value();
                            value1.setDate(Utils.formatDate(data.getDate()));
                            value1.setHeader(true);
                            ExportPendingFragment.this.historyList.add(value1);
                            Iterator it = data.getValueList().iterator();
                            while (it.hasNext()) {
                                Value value = (Value) it.next();
                                if (ExportPendingFragment.this.from.equalsIgnoreCase("Delivery")) {
                                    value.setDelivery(true);
                                } else {
                                    value.setDelivery(false);
                                }
                                value.setHeader(false);
                                ExportPendingFragment.this.historyList.add(value);
                            }
                        }
                        ExportPendingFragment.this.historyAdapter = new HistoryAdapter(ExportPendingFragment.this.historyList, ExportPendingFragment.this.me, ExportPendingFragment.this.currentFragment, ExportPendingFragment.this.from);
                        ExportPendingFragment.this.binding.recHistoryList.setAdapter(ExportPendingFragment.this.historyAdapter);
                        Utils.printMsg("list_size", String.valueOf(ExportPendingFragment.this.historyList.size()));
                        return;
                    }
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ExportPendingFragment.this.me.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
