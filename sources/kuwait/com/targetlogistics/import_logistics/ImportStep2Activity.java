package kuwait.com.targetlogistics.import_logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityImportStep2Binding;
import kuwait.com.targetlogistics.model.Parcel;
import kuwait.com.targetlogistics.model.ParcelType;
import org.greenrobot.eventbus.Subscribe;

public class ImportStep2Activity extends BaseActivity {
    private ImportStep2Activity$ParcelInfoAdapter adapter;
    ActivityImportStep2Binding binding;
    private boolean isFromHistory = false;
    private JsonArray jsonParcelInfoArray;
    private ArrayList<Parcel> list = new ArrayList();
    private String parcelType = "";
    private ArrayList<ParcelType> parcelTypeArrayList = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityImportStep2Binding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.Import));
        setData();
        if (getIntent().hasExtra("HistoryType")) {
            this.isFromHistory = true;
            this.binding.btnSubmit.setText(getString(R.string.close));
        }
        this.binding.layoutSteps.txtStep2.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.recParcelInfo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.adapter = new ImportStep2Activity$ParcelInfoAdapter(this);
        this.binding.recParcelInfo.setAdapter(this.adapter);
        this.list.add(new Parcel());
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSubmit.setOnClickListener(this);
        AbstractBaseActivity.setRedirectClass(ImportStep2Activity.class);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_submit:
                if (this.isFromHistory) {
                    onFinishAll(ImportStep1Activity.class);
                    finish();
                    return;
                } else if (this.list.size() <= 0 || this.list.size() == 1) {
                    Parcel parcel = (Parcel) this.list.get(this.list.size() - 1);
                    if (parcel.getParcelType() == null || parcel.getParcelType().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.parcel_type_validation));
                        return;
                    } else if (parcel.getQuantity() == null || parcel.getQuantity().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.quantity_validation));
                        return;
                    } else if (parcel.getCostValue() == null || parcel.getCostValue().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.cost_validation));
                        return;
                    } else {
                        setParcelInfo();
                        return;
                    }
                } else {
                    setParcelInfo();
                    return;
                }
            default:
                return;
        }
    }

    private void setParcelInfo() {
        this.jsonParcelInfoArray = new JsonArray();
        for (int i = 0; i < this.list.size(); i++) {
            Parcel parcel = (Parcel) this.list.get(i);
            JsonObject jsonObject = new JsonObject();
            if (!((parcel.getParcelTypeId() == null || parcel.getParcelTypeId().isEmpty()) && ((parcel.getQuantity() == null || parcel.getQuantity().isEmpty()) && ((parcel.getCostValue() == null || parcel.getCostValue().isEmpty()) && ((parcel.getLength() == null || parcel.getLength().isEmpty()) && ((parcel.getHeight() == null || parcel.getHeight().isEmpty()) && (parcel.getWidth() == null || parcel.getWidth().isEmpty()))))))) {
                jsonObject.addProperty("parcel_type", parcel.getParcelTypeId());
                jsonObject.addProperty("parcel_quantity", parcel.getQuantity());
                jsonObject.addProperty("parcel_cost_value", parcel.getCostValue());
                jsonObject.addProperty("parcel_weight", parcel.getWeightOfParcel());
                jsonObject.addProperty("parcel_description", parcel.getParcelDetailDescription());
                jsonObject.addProperty("parcel_length", parcel.getLength());
                jsonObject.addProperty("parcel_width", parcel.getWidth());
                jsonObject.addProperty("parcel_height", parcel.getHeight());
                this.jsonParcelInfoArray.add(jsonObject);
            }
        }
        callImportOrderAPI();
        Utils.printMsg("parcelInfo", this.jsonParcelInfoArray + "");
    }

    private void callImportOrderAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.IMPORT_ORDER);
        requestParams.put("user_id", Utils.getUserInfo(this.me).getId());
        requestParams.put("pickup_fullname", anImport.getName() == null ? "" : anImport.getName());
        requestParams.put("pickup_country", anImport.getCountryId() == null ? "" : anImport.getCountryId());
        requestParams.put("pickup_phone", anImport.getPhoneNo() == null ? "" : anImport.getPhoneNo());
        requestParams.put("pickup_city", anImport.getCityId() == null ? "" : anImport.getCityId());
        requestParams.put("pickup_postcode", anImport.getPostCode() == null ? "" : anImport.getPostCode());
        requestParams.put("pickup_address1", anImport.getAddress1() == null ? "" : anImport.getAddress1());
        requestParams.put("pickup_address2", anImport.getAddress2() == null ? "" : anImport.getAddress2());
        requestParams.put("parcelinfo", String.valueOf(this.jsonParcelInfoArray));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new ImportStep2Activity$1(this));
    }

    private void setData() {
        this.parcelTypeArrayList.clear();
        ParcelType parcelType = new ParcelType();
        parcelType.setId("1");
        parcelType.setParcelType("Type1");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("2");
        parcelType.setParcelType("Type2");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("3");
        parcelType.setParcelType("Type3");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("4");
        parcelType.setParcelType("Type4");
        this.parcelTypeArrayList.add(parcelType);
    }

    @Subscribe
    public void onMessageReceived(Intent i) {
        if (i.hasExtra("parcelType")) {
            this.parcelType = i.getStringExtra("parcelType");
            Log.e("parcel", this.parcelType);
        }
    }

    private String getParcelTypeId(String name) {
        String parcelTypeId = "";
        if (Utils.getParcelTypeList(this.me).size() > 0) {
            Iterator it = Utils.getParcelTypeList(this.me).iterator();
            while (it.hasNext()) {
                ParcelType parcel = (ParcelType) it.next();
                if (parcel.getParcelType().equalsIgnoreCase(name)) {
                    parcelTypeId = parcel.getId();
                }
            }
        }
        return parcelTypeId;
    }
}
