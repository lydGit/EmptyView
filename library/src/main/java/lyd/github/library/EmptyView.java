package lyd.github.library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by shawn on 17/5/4.
 */

public class EmptyView extends FrameLayout implements IEmpty {

    private static final int KEY_INIT = -1; //lastKey的初始默认值

    private SparseArray<View> emptyViews;

    private int lastKey = KEY_INIT;   //上次显示的view key

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        emptyViews = new SparseArray<>();
    }

    /**
     * 添加EmptyView
     *
     * @param key
     * @param view
     */
    @Override
    public void addEmptyView(int key, View view) {
        if (key < 0)
            throw new ArrayIndexOutOfBoundsException("key值必须大于等于0");
        view.setVisibility(GONE);
        emptyViews.put(key, view);
        this.addView(view);
    }

    @Override
    public void addEmptyView(int key, int resId) {
        View view = LayoutInflater.from(getContext()).inflate(resId,null);
        addEmptyView(key,view);
    }

    /**
     * 显示EmptyView
     *
     * @param key
     */
    @Override
    public void showEmptyView(int key) {
        if (lastKey == key) return;
        if (lastKey == -1) {
            setChildGone();
        }
        if (lastKey != -1) {
            emptyViews.get(lastKey).setVisibility(GONE);
        }
        emptyViews.get(key).setVisibility(VISIBLE);
        lastKey = key;
    }

    /**
     * 关闭EmptyView
     *
     * @param b true 除当前显示的view之外全部显示
     *          false 单纯关闭当前显示的View
     */
    @Override
    public void closeEmotyView(boolean b) {
        if (b) {
            setChildVisible();
        }
        if (lastKey != KEY_INIT)
            emptyViews.get(lastKey).setVisibility(GONE);
        lastKey = KEY_INIT;
    }

    /**
     * 关闭当前 EmptyView  并显示指定view
     * @param resId
     */
    @Override
    public void closeEmotyView(int... resId) {
        if (lastKey != KEY_INIT)
            emptyViews.get(lastKey).setVisibility(GONE);
        for (int i = 0; i < resId.length; i++) {
            getView(resId[i]).setVisibility(VISIBLE);
        }
        lastKey = KEY_INIT;
    }

    /**
     * 获取view
     * @param key
     * @return
     */
    @Override
    public View getView(int key) {
        lastKey = key;
        return emptyViews.get(key);
    }

    private void setChildGone() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(GONE);
        }
    }

    private void setChildVisible() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(VISIBLE);
        }
    }
}
