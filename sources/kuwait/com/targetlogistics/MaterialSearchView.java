package kuwait.com.targetlogistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Filter.FilterListener;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.miguelcatalan.materialsearchview.R;
import com.miguelcatalan.materialsearchview.SearchAdapter;
import com.miguelcatalan.materialsearchview.utils.AnimationUtil;
import java.lang.reflect.Field;

public class MaterialSearchView extends FrameLayout implements FilterListener {
    public static final int REQUEST_VOICE = 9999;
    private boolean allowVoiceSearch;
    private boolean ellipsize;
    public boolean isFilter;
    private ListAdapter mAdapter;
    private int mAnimationDuration;
    private ImageButton mBackBtn;
    private boolean mClearingFocus;
    private Context mContext;
    private ImageButton mEmptyBtn;
    private boolean mIsSearchOpen;
    private MenuItem mMenuItem;
    private CharSequence mOldQueryText;
    private final OnClickListener mOnClickListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private SavedState mSavedState;
    private View mSearchLayout;
    private EditText mSearchSrcTextView;
    private RelativeLayout mSearchTopBar;
    private SearchViewListener mSearchViewListener;
    private ListView mSuggestionsListView;
    private View mTintView;
    private CharSequence mUserQuery;
    private ImageButton mVoiceBtn;
    private boolean submit;
    private Drawable suggestionIcon;

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean isSearchOpen;
        String query;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            boolean z = true;
            super(in);
            this.query = in.readString();
            if (in.readInt() != 1) {
                z = false;
            }
            this.isSearchOpen = z;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.query);
            out.writeInt(this.isSearchOpen ? 1 : 0);
        }
    }

    public interface SearchViewListener {
        void onSearchViewClosed();

        void onSearchViewShown();
    }

    public MaterialSearchView(Context context) {
        this(context, null);
    }

    public MaterialSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.mIsSearchOpen = false;
        this.submit = false;
        this.ellipsize = false;
        this.mOnClickListener = new OnClickListener() {
            public void onClick(View v) {
                if (v == MaterialSearchView.this.mBackBtn) {
                    MaterialSearchView.this.closeSearch();
                } else if (v == MaterialSearchView.this.mVoiceBtn) {
                    MaterialSearchView.this.onVoiceClicked();
                } else if (v == MaterialSearchView.this.mEmptyBtn) {
                    MaterialSearchView.this.mSearchSrcTextView.setText(null);
                    MaterialSearchView.this.closeSearch();
                } else if (v == MaterialSearchView.this.mSearchSrcTextView) {
                    MaterialSearchView.this.showSuggestions();
                } else if (v == MaterialSearchView.this.mTintView) {
                    MaterialSearchView.this.closeSearch();
                }
            }
        };
        this.mContext = context;
        initiateView();
        initStyle(attrs, defStyleAttr);
    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, R.styleable.MaterialSearchView, defStyleAttr, 0);
        if (a != null) {
            if (a.hasValue(4)) {
                setBackground(a.getDrawable(4));
            }
            if (a.hasValue(0)) {
                setTextColor(a.getColor(0, 0));
            }
            if (a.hasValue(1)) {
                setHintTextColor(a.getColor(1, 0));
            }
            if (a.hasValue(2)) {
                setHint(a.getString(2));
            }
            if (a.hasValue(8)) {
                setVoiceIcon(a.getDrawable(8));
            }
            if (a.hasValue(5)) {
                setCloseIcon(a.getDrawable(5));
            }
            if (a.hasValue(3)) {
                setBackIcon(a.getDrawable(3));
            }
            if (a.hasValue(6)) {
                setSuggestionBackground(a.getDrawable(6));
            }
            if (a.hasValue(7)) {
                setSuggestionIcon(a.getDrawable(7));
            }
            a.recycle();
        }
    }

    private void initiateView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.search_view, this, true);
        this.mSearchLayout = findViewById(R.id.search_layout);
        this.mSearchTopBar = (RelativeLayout) this.mSearchLayout.findViewById(R.id.search_top_bar);
        this.mSuggestionsListView = (ListView) this.mSearchLayout.findViewById(R.id.suggestion_list);
        this.mSearchSrcTextView = (EditText) this.mSearchLayout.findViewById(R.id.searchTextView);
        this.mSearchSrcTextView.setInputType(524288);
        this.mBackBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_up_btn);
        this.mVoiceBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_voice_btn);
        this.mEmptyBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_empty_btn);
        this.mTintView = this.mSearchLayout.findViewById(R.id.transparent_view);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mBackBtn.setOnClickListener(null);
        this.mVoiceBtn.setOnClickListener(this.mOnClickListener);
        this.mEmptyBtn.setOnClickListener(this.mOnClickListener);
        this.mTintView.setOnClickListener(this.mOnClickListener);
        this.allowVoiceSearch = false;
        showVoice(true);
        initSearchView();
        this.mSuggestionsListView.setVisibility(8);
        setAnimationDuration(AnimationUtil.ANIMATION_DURATION_MEDIUM);
    }

    private void initSearchView() {
        this.mSearchSrcTextView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                MaterialSearchView.this.clearFocus();
                return true;
            }
        });
        this.mSearchSrcTextView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MaterialSearchView.this.mUserQuery = s;
                MaterialSearchView.this.startFilter(s);
                MaterialSearchView.this.onTextChanged(s);
            }

            public void afterTextChanged(Editable s) {
            }
        });
        this.mSearchSrcTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    MaterialSearchView.this.showKeyboard(MaterialSearchView.this.mSearchSrcTextView);
                    MaterialSearchView.this.showSuggestions();
                }
            }
        });
    }

    private void startFilter(CharSequence s) {
        if (this.mAdapter != null && (this.mAdapter instanceof Filterable)) {
            ((Filterable) this.mAdapter).getFilter().filter(s, this);
        }
    }

    private void onVoiceClicked() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 1);
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).startActivityForResult(intent, 9999);
        }
    }

    private void onTextChanged(CharSequence newText) {
        boolean hasText;
        CharSequence text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        if (TextUtils.isEmpty(text)) {
            hasText = false;
        } else {
            hasText = true;
        }
        if (hasText) {
            showVoice(false);
        } else {
            showVoice(true);
        }
        if (!(this.mOnQueryChangeListener == null || TextUtils.equals(newText, this.mOldQueryText))) {
            this.mOnQueryChangeListener.onQueryTextChange(newText.toString());
        }
        this.mOldQueryText = newText.toString();
    }

    private void onSubmitQuery() {
        CharSequence query = this.mSearchSrcTextView.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(query.toString())) {
                closeSearch();
                this.mSearchSrcTextView.setText(null);
            }
        }
    }

    private boolean isVoiceAvailable() {
        if (isInEditMode() || getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() == 0) {
            return true;
        }
        return false;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        if (VERSION.SDK_INT <= 10 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public void setBackground(Drawable background) {
        try {
            if (VERSION.SDK_INT >= 16) {
                this.mSearchTopBar.setBackground(background);
            } else {
                this.mSearchTopBar.setBackgroundDrawable(background);
            }
        } catch (Exception e) {
        }
    }

    public void setBackgroundColor(int color) {
        this.mSearchTopBar.setBackgroundColor(color);
    }

    public void setTextColor(int color) {
        this.mSearchSrcTextView.setTextColor(color);
    }

    public void setHintTextColor(int color) {
        this.mSearchSrcTextView.setHintTextColor(color);
    }

    public void setHint(CharSequence hint) {
        this.mSearchSrcTextView.setHint(hint);
    }

    public void setVoiceIcon(Drawable drawable) {
        this.mVoiceBtn.setImageDrawable(drawable);
    }

    public void setCloseIcon(Drawable drawable) {
        this.mEmptyBtn.setImageDrawable(drawable);
    }

    public void setBackIcon(Drawable drawable) {
        this.mBackBtn.setImageDrawable(drawable);
    }

    public void setSuggestionIcon(Drawable drawable) {
        this.suggestionIcon = drawable;
    }

    public void setInputType(int inputType) {
        this.mSearchSrcTextView.setInputType(inputType);
    }

    public void setSuggestionBackground(Drawable background) {
        if (VERSION.SDK_INT >= 16) {
            this.mSuggestionsListView.setBackground(background);
        } else {
            this.mSuggestionsListView.setBackgroundDrawable(background);
        }
    }

    public void setCursorDrawable(int drawable) {
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this.mSearchSrcTextView, Integer.valueOf(drawable));
        } catch (Exception ignored) {
            Log.e("MaterialSearchView", ignored.toString());
        }
    }

    public void setVoiceSearch(boolean voiceSearch) {
        this.allowVoiceSearch = voiceSearch;
    }

    public void showSuggestions() {
        if (this.mAdapter != null && this.mAdapter.getCount() > 0 && this.mSuggestionsListView.getVisibility() == 8) {
            this.mSuggestionsListView.setVisibility(0);
        }
    }

    public void setSubmitOnClick(boolean submit) {
        this.submit = submit;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mSuggestionsListView.setOnItemClickListener(listener);
    }

    public void setAdapter(ListAdapter adapter) {
        this.mAdapter = adapter;
        this.mSuggestionsListView.setAdapter(adapter);
        startFilter(this.mSearchSrcTextView.getText());
    }

    public void setSuggestions(String[] suggestions) {
        if (suggestions == null || suggestions.length <= 0) {
            this.mTintView.setVisibility(8);
            return;
        }
        this.mTintView.setVisibility(0);
        final SearchAdapter adapter = new SearchAdapter(this.mContext, suggestions, this.suggestionIcon, this.ellipsize);
        setAdapter(adapter);
        setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MaterialSearchView.this.setQuery((String) adapter.getItem(position), MaterialSearchView.this.submit);
            }
        });
    }

    public void dismissSuggestions() {
        if (this.mSuggestionsListView.getVisibility() == 0) {
            this.mSuggestionsListView.setVisibility(8);
        }
    }

    public void setQuery(CharSequence query, boolean submit) {
        this.mSearchSrcTextView.setText(query);
        if (query != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = query;
        }
        if (submit && !TextUtils.isEmpty(query)) {
            onSubmitQuery();
        }
    }

    public void showVoice(boolean show) {
        if (show && isVoiceAvailable() && this.allowVoiceSearch) {
            this.mVoiceBtn.setVisibility(0);
        } else {
            this.mVoiceBtn.setVisibility(8);
        }
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMenuItem = menuItem;
        this.mMenuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                MaterialSearchView.this.showSearch();
                return true;
            }
        });
    }

    public boolean isSearchOpen() {
        return this.mIsSearchOpen;
    }

    public void setAnimationDuration(int duration) {
        this.mAnimationDuration = duration;
    }

    public void showSearch() {
        showSearch(true);
    }

    public void showSearch(boolean animate) {
        if (!isSearchOpen()) {
            this.mSearchSrcTextView.setText(null);
            this.mSearchSrcTextView.requestFocus();
            if (animate) {
                setVisibleWithAnimation();
            } else {
                this.mSearchLayout.setVisibility(0);
                if (this.mSearchViewListener != null) {
                    this.mSearchViewListener.onSearchViewShown();
                }
            }
            this.mIsSearchOpen = true;
        }
    }

    private void setVisibleWithAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this.mContext, MyApplication.isLTR ? R.anim.right_to_left : R.anim.left_to_right_ar);
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (MaterialSearchView.this.mSearchViewListener != null) {
                    MaterialSearchView.this.mSearchViewListener.onSearchViewShown();
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mSearchLayout.setVisibility(0);
        this.mSearchLayout.startAnimation(animation);
    }

    private void hideWithAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this.mContext, MyApplication.isLTR ? R.anim.left_to_right : R.anim.right_to_left_ar);
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (MaterialSearchView.this.mSearchViewListener != null) {
                    MaterialSearchView.this.mSearchViewListener.onSearchViewClosed();
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mSearchLayout.startAnimation(animation);
        new CountDownTimer(500, 500) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                MaterialSearchView.this.mSearchLayout.setVisibility(8);
            }
        }.start();
    }

    public void closeSearch() {
        if (isSearchOpen()) {
            this.isFilter = false;
            this.mSearchSrcTextView.setText(null);
            dismissSuggestions();
            clearFocus();
            hideWithAnimation();
            this.mIsSearchOpen = false;
        }
    }

    public void setOnQueryTextListener(OnQueryTextListener listener) {
        this.mOnQueryChangeListener = listener;
    }

    public void setOnSearchViewListener(SearchViewListener listener) {
        this.mSearchViewListener = listener;
    }

    public void setEllipsize(boolean ellipsize) {
        this.ellipsize = ellipsize;
    }

    public void onFilterComplete(int count) {
        if (count > 0) {
            showSuggestions();
        } else {
            dismissSuggestions();
        }
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (!this.mClearingFocus && isFocusable()) {
            return this.mSearchSrcTextView.requestFocus(direction, previouslyFocusedRect);
        }
        return false;
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        hideKeyboard(this);
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mClearingFocus = false;
    }

    public Parcelable onSaveInstanceState() {
        this.mSavedState = new SavedState(super.onSaveInstanceState());
        this.mSavedState.query = this.mUserQuery != null ? this.mUserQuery.toString() : null;
        this.mSavedState.isSearchOpen = this.mIsSearchOpen;
        return this.mSavedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.mSavedState = (SavedState) state;
            if (this.mSavedState.isSearchOpen) {
                showSearch(false);
                setQuery(this.mSavedState.query, false);
            }
            super.onRestoreInstanceState(this.mSavedState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
