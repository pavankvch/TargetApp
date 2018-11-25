package kuwait.com.targetlogistics;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseFragment extends Fragment implements OnClickListener {
    public BaseActivity me;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.me = (BaseActivity) getActivity();
    }

    @CallSuper
    protected void init(View rootView) {
    }

    public void onClick(View v) {
    }
}
