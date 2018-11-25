package kuwait.com.targetlogistics.searchable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivitySearchBinding;
import kuwait.com.targetlogistics.databinding.RowSearchableCityListItemBinding;
import kuwait.com.targetlogistics.databinding.RowSearchableCountryListItemBinding;
import kuwait.com.targetlogistics.databinding.RowSearchableListItemBinding;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.model.City;
import kuwait.com.targetlogistics.model.Country;
import kuwait.com.targetlogistics.user.MyProfileActivity;
import kuwait.com.targetlogistics.user.RegistrationActivity;

public class SearchActivity extends BaseActivity {
    private AreaAdapter adapter;
    private ArrayList<Area> areaList = new ArrayList();
    private ArrayList<Area> areaListDup = new ArrayList();
    private ActivitySearchBinding binding;
    private CityAdapter cityAdapter;
    private ArrayList<City> cityList = new ArrayList();
    private ArrayList<City> cityListDup = new ArrayList();
    private CountryAdapter countryAdapter;
    private String countryId = "";
    private ArrayList<Country> countryList = new ArrayList();
    private ArrayList<Country> countryListDup = new ArrayList();
    private boolean isCountry = false;

    private class AreaAdapter extends Adapter<ViewHolder> {

        class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            RowSearchableListItemBinding binding;
            View itemView;

            ViewHolder(RowSearchableListItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                this.itemView = binding.getRoot();
            }
        }

        private AreaAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(RowSearchableListItemBinding.inflate(SearchActivity.this.getLayoutInflater(), parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            final Area data = (Area) SearchActivity.this.areaList.get(position);
            holder.binding.setItem(data);
            holder.binding.executePendingBindings();
            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (AbstractBaseActivity.getRedirectClass().equals(RegistrationActivity.class) || AbstractBaseActivity.getRedirectClass().equals(MyProfileActivity.class)) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("Area", data);
                        SearchActivity.this.setResult(-1, resultIntent);
                        SearchActivity.this.finish();
                        return;
                    }
                    resultIntent = new Intent();
                    resultIntent.putExtra("Area", data);
                    SearchActivity.this.setResult(-1, resultIntent);
                    SearchActivity.this.finish();
                }
            });
        }

        public int getItemCount() {
            return SearchActivity.this.areaList.size();
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            SearchActivity.this.areaList.clear();
            if (charText.length() == 0) {
                SearchActivity.this.areaList.addAll(SearchActivity.this.areaListDup);
            } else {
                Iterator it = SearchActivity.this.areaListDup.iterator();
                while (it.hasNext()) {
                    Area obj = it.next();
                    if (obj.getAreaName().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        SearchActivity.this.areaList.add(obj);
                    }
                }
            }
            SearchActivity.this.binding.tvNoRecordFound.setVisibility(SearchActivity.this.areaList.isEmpty() ? 0 : 8);
            notifyDataSetChanged();
        }
    }

    private class CityAdapter extends Adapter<ViewHolder> {

        class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            RowSearchableCityListItemBinding binding;
            View itemView;

            ViewHolder(RowSearchableCityListItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                this.itemView = binding.getRoot();
            }
        }

        private CityAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(RowSearchableCityListItemBinding.inflate(SearchActivity.this.getLayoutInflater(), parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            final City data = (City) SearchActivity.this.cityList.get(position);
            holder.binding.setItem(data);
            holder.binding.executePendingBindings();
            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("City", data);
                    SearchActivity.this.setResult(-1, resultIntent);
                    SearchActivity.this.finish();
                }
            });
        }

        public int getItemCount() {
            return SearchActivity.this.cityList.size();
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            SearchActivity.this.cityList.clear();
            if (charText.length() == 0) {
                SearchActivity.this.cityList.addAll(SearchActivity.this.cityListDup);
            } else {
                Iterator it = SearchActivity.this.cityListDup.iterator();
                while (it.hasNext()) {
                    City obj = it.next();
                    if (obj.getCityName().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        SearchActivity.this.cityList.add(obj);
                    }
                }
            }
            SearchActivity.this.binding.tvNoRecordFound.setVisibility(SearchActivity.this.cityList.isEmpty() ? 0 : 8);
            notifyDataSetChanged();
        }
    }

    private class CountryAdapter extends Adapter<ViewHolder> {

        class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            RowSearchableCountryListItemBinding binding;
            View itemView;

            ViewHolder(RowSearchableCountryListItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                this.itemView = binding.getRoot();
            }
        }

        private CountryAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(RowSearchableCountryListItemBinding.inflate(SearchActivity.this.getLayoutInflater(), parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            final Country data = (Country) SearchActivity.this.countryList.get(position);
            holder.binding.setItem(data);
            holder.binding.executePendingBindings();
            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("Country", data);
                    SearchActivity.this.setResult(-1, resultIntent);
                    SearchActivity.this.finish();
                }
            });
        }

        public int getItemCount() {
            return SearchActivity.this.countryList.size();
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            SearchActivity.this.countryList.clear();
            if (charText.length() == 0) {
                SearchActivity.this.countryList.addAll(SearchActivity.this.countryListDup);
            } else {
                Iterator it = SearchActivity.this.countryListDup.iterator();
                while (it.hasNext()) {
                    Country obj = it.next();
                    if (obj.getCountryName().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        SearchActivity.this.countryList.add(obj);
                    }
                }
            }
            SearchActivity.this.binding.tvNoRecordFound.setVisibility(SearchActivity.this.countryList.isEmpty() ? 0 : 8);
            notifyDataSetChanged();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySearchBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    public void init() {
        int i = 0;
        this.areaList = Utils.getAreaList(this.me);
        this.areaListDup.addAll(this.areaList);
        this.countryList = Utils.getCountryList(this.me);
        this.countryListDup.addAll(this.countryList);
        TextView textView;
        if (getIntent().hasExtra("Country")) {
            if (getIntent().getBooleanExtra("Country", false)) {
                this.isCountry = true;
                this.countryAdapter = new CountryAdapter();
                this.binding.rvSearchable.setAdapter(this.countryAdapter);
                textView = this.binding.tvNoRecordFound;
                if (!this.countryList.isEmpty()) {
                    i = 8;
                }
                textView.setVisibility(i);
            }
        } else if (!getIntent().hasExtra("City")) {
            this.adapter = new AreaAdapter();
            this.binding.rvSearchable.setAdapter(this.adapter);
            textView = this.binding.tvNoRecordFound;
            if (!this.areaList.isEmpty()) {
                i = 8;
            }
            textView.setVisibility(i);
        } else if (getIntent().hasExtra("countryId")) {
            this.countryId = getIntent().getStringExtra("countryId");
            if (!(this.countryId == null || this.countryId.isEmpty())) {
                int i2;
                this.cityList = getCityList(this.countryId);
                City city = new City();
                city.setCityId("4232");
                city.setCityName(getString(R.string.other));
                city.setCityNameAr(getString(R.string.other));
                this.cityList.add(city);
                this.cityListDup.addAll(this.cityList);
                RecyclerView recyclerView = this.binding.rvSearchable;
                if (this.cityList.isEmpty()) {
                    i2 = 8;
                } else {
                    i2 = 0;
                }
                recyclerView.setVisibility(i2);
                this.cityAdapter = new CityAdapter();
                this.binding.rvSearchable.setAdapter(this.cityAdapter);
                textView = this.binding.tvNoRecordFound;
                if (!this.cityList.isEmpty()) {
                    i = 8;
                }
                textView.setVisibility(i);
            }
        }
        setFullScreen();
        this.binding.edtSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (SearchActivity.this.getIntent().hasExtra("Country")) {
                    SearchActivity.this.countryAdapter.filter(SearchActivity.this.binding.edtSearch.getText().toString().trim());
                } else if (SearchActivity.this.getIntent().hasExtra("City")) {
                    SearchActivity.this.cityAdapter.filter(SearchActivity.this.binding.edtSearch.getText().toString().trim());
                } else {
                    SearchActivity.this.adapter.filter(SearchActivity.this.binding.edtSearch.getText().toString().trim());
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
        super.onClick(view);
    }

    private ArrayList<City> getCityList(String countryId) {
        this.cityList.clear();
        this.cityListDup.clear();
        Iterator it = Utils.getCityList(this.me).iterator();
        while (it.hasNext()) {
            City city = (City) it.next();
            if (city.getCountryId().equals(countryId)) {
                this.cityList.add(city);
            }
        }
        return this.cityList;
    }
}
