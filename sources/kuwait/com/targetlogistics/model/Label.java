package kuwait.com.targetlogistics.model;

import android.support.annotation.StringRes;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ConflictAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kuwait.com.targetlogistics.MyApplication;

@Table(name = "LABEL")
public class Label extends TruncatableModel implements Serializable {
    @SerializedName("label_value")
    @Column
    private String label;
    @SerializedName("id")
    @Column(name = "label_Id", onUniqueConflict = ConflictAction.REPLACE, unique = true)
    private long labelId;

    public long getLabelId() {
        return this.labelId;
    }

    public Label setLabelId(long labelId) {
        this.labelId = labelId;
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public Label setLabel(String label) {
        this.label = label;
        return this;
    }

    public static String getLabel(String labelId) {
        try {
            Label label = (Label) new Select().from(Label.class).where("label_Id=?", labelId).executeSingle();
            if (label != null) {
                labelId = label.getLabel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelId;
    }

    public static String getLabel(@StringRes int res) {
        String labelId = MyApplication.getInstance().getString(res);
        try {
            Label label = (Label) new Select().from(Label.class).where("label_Id=?", labelId).executeSingle();
            if (label != null) {
                labelId = label.getLabel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelId;
    }

    public static List<Label> getAllLabels() {
        return new Select().from(Label.class).execute();
    }

    public static int getCount() {
        return new Select().from(Label.class).count();
    }
}
