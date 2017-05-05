package lyd.github.library;

import android.view.View;

/**
 * Created by shawn on 17/5/4.
 */

public interface IEmpty {

    void addEmptyView(int key,View view);
    void addEmptyView(int key,int resId);
    void showEmptyView(int key);
    void closeEmotyView(boolean b);
    void closeEmotyView(int... resId);
    View getView(int key);
}
