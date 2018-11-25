package kuwait.com.targetlogistics.history;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.FragmentCompletedBinding;
import kuwait.com.targetlogistics.model.HistoryList;

public class ExportCompletedFragment extends Fragment {
    FragmentCompletedBinding binding;
    private HistoryAdapter historyAdapter;
    private ArrayList<HistoryList> historyList;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (FragmentCompletedBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_completed, container, false);
        View view = this.binding.getRoot();
        init();
        return view;
    }

    private void init() {
        this.historyList = new ArrayList();
        getListData();
        LayoutManager layoutManager = new LinearLayoutManager(getActivity(), 1, false);
    }

    private void getListData() {
        this.historyList.clear();
        HistoryList addres = new HistoryList();
        addres.setDate("15/03/2018");
        addres.setHeader(true);
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Kuwait City");
        addres.setDeliveryCity("India");
        addres.setNoOfParcels("3");
        addres.setWeight("5 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Hawally");
        addres.setDeliveryCity("Dubai");
        addres.setNoOfParcels("2");
        addres.setWeight("7 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Dasma");
        addres.setDeliveryCity("Qatar");
        addres.setNoOfParcels("5");
        addres.setWeight("12 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Salmiya");
        addres.setDeliveryCity("Egypt");
        addres.setNoOfParcels("1");
        addres.setWeight("10 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setDate("18/03/2018");
        addres.setHeader(true);
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Kuwait City");
        addres.setDeliveryCity("India");
        addres.setNoOfParcels("3");
        addres.setWeight("5 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Hawally");
        addres.setDeliveryCity("Dubai");
        addres.setNoOfParcels("2");
        addres.setWeight("7 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Dasma");
        addres.setDeliveryCity("Qatar");
        addres.setNoOfParcels("5");
        addres.setWeight("12 Kgs");
        this.historyList.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Salmiya");
        addres.setDeliveryCity("Egypt");
        addres.setNoOfParcels("1");
        addres.setWeight("10 Kgs");
        this.historyList.add(addres);
    }
}
