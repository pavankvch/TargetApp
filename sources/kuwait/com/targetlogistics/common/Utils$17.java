package kuwait.com.targetlogistics.common;

import android.text.InputFilter;
import android.text.Spanned;

class Utils$17 implements InputFilter {
    Utils$17() {
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            int type = Character.getType(source.charAt(i));
            if (type == 19 || type == 28) {
                return "";
            }
        }
        return null;
    }
}
