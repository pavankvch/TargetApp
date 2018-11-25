package kuwait.com.targetlogistics;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class RVHolder<T> extends ViewHolder {
    public abstract void setData(T t);

    public RVHolder(View itemView) {
        super(itemView);
    }
}
