package kuwait.com.targetlogistics.model;

import java.io.Serializable;

public class HistoryHeader implements Serializable {
    private String date;
    private boolean isHeader;

    public boolean isHeader() {
        return this.isHeader;
    }

    public HistoryHeader setHeader(boolean header) {
        this.isHeader = header;
        return this;
    }

    public String getDate() {
        return this.date;
    }

    public HistoryHeader setDate(String date) {
        this.date = date;
        return this;
    }
}
