package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class HistoryData implements Serializable {
    @SerializedName("date")
    @Expose
    private String date;
    private boolean isHeader;
    @SerializedName("value")
    @Expose
    private ArrayList<Value> valueList;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Value> getValueList() {
        return this.valueList;
    }

    public void setValueList(ArrayList<Value> valueList) {
        this.valueList = valueList;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean header) {
        this.isHeader = header;
    }
}
