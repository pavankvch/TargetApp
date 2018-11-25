package kuwait.com.targetlogistics.export;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import com.stacktips.view.CustomCalendarView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.DateUtils;
import kuwait.com.targetlogistics.common.InputFilterMinMax;
import kuwait.com.targetlogistics.common.OnItemSelected;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityPickupDateTimeBinding;
import kuwait.com.targetlogistics.model.Export;

public class ExportPickUpDateTimeActivity extends BaseActivity {
    ActivityPickupDateTimeBinding binding;
    Calendar calendar = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat(DateUtils.YYYY_MM_DD, Locale.ENGLISH);
    private Export export;
    private int hours = 0;
    private boolean isAM = false;
    private boolean isFromHistory = false;
    private boolean isPM = true;
    private int mins = 0;
    ArrayList<String> timeSlotList = new ArrayList();
    Calendar today = Calendar.getInstance();
    private int validHours = 0;
    private int validMins = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityPickupDateTimeBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.export));
        if (getIntent().hasExtra("data")) {
            this.export = (Export) getIntent().getSerializableExtra("data");
            Utils.printMsg("data", this.export.getAddressId());
        }
        Utils.printMsg("today", this.today.getTime() + "");
        this.binding.calendarView.markDayAsSelectedDay(this.calendar.getTime());
        this.binding.layoutSteps.txtStep3.setText(getString(R.string.shipping_to));
        this.binding.layoutSteps.txtPickupDateTime.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.txtAM.setOnClickListener(this);
        this.binding.txtPM.setOnClickListener(this);
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSkip.setOnClickListener(this);
        this.binding.txtTimeSlot.setOnClickListener(this);
        this.binding.edtHours.setFilters(new InputFilter[]{new InputFilterMinMax("1", "12"), new LengthFilter(2)});
        this.binding.edtMins.setFilters(new InputFilter[]{new InputFilterMinMax("0", "59"), new LengthFilter(2)});
    }

    private boolean validation() {
        if (this.binding.txtTimeSlot.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_skip:
                if (validation()) {
                    setDateTime();
                    Intent intent = new Intent(this.me, ExportDeliveryAddressActivity.class);
                    intent.putExtra("data", this.export);
                    startActivity(intent);
                    return;
                }
                showSnackBar(this.me, getString(R.string.please_select_pickup_time_slot));
                return;
            case R.id.txt_time_slot:
                if (Utils.isSameDay(CustomCalendarView.selectedDate, this.today.getTime())) {
                    this.timeSlotList = Utils.getTodayPickupTimeSlot(this.me);
                } else {
                    this.timeSlotList = Utils.getTomorrowPickupTimeSlot(this.me);
                }
                setRedirectClass(ExportPickUpDateTimeActivity.class);
                showParcelTypeDialog(this.timeSlotList, this.me, getString(R.string.pickup_time), new OnItemSelected() {
                    public void onItemSelected(Object o) {
                        ExportPickUpDateTimeActivity.this.binding.txtTimeSlot.setText((String) o);
                    }
                });
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    private void setDateTime() {
        this.calendar.setTime(CustomCalendarView.selectedDate);
        this.export.setPickupDateTIme(this.df.format(this.calendar.getTime()).concat(" ").concat(this.binding.txtTimeSlot.getText().toString()));
        Utils.printMsg("SelectedDate", this.export.getPickupDateTIme());
    }

    protected void onResume() {
        super.onResume();
        this.calendar = Calendar.getInstance();
    }
}
