package lyd.github.emptyview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lyd.github.library.EmptyView;

public class MainActivity extends AppCompatActivity {

    private EmptyView evMainEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
        evMainEmpty = (EmptyView) findViewById(R.id.ev_main_empty);
        evMainEmpty.addEmptyView(0, getImageView());
        evMainEmpty.addEmptyView(1, getTextView());
    }

    private void initListener() {
        findViewById(R.id.tv_main_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evMainEmpty.showEmptyView(0);
            }
        });

        findViewById(R.id.tv_main_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evMainEmpty.showEmptyView(1);
            }
        });

        findViewById(R.id.tv_main_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evMainEmpty.closeEmotyView(true);
            }
        });

        findViewById(R.id.tv_main_test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evMainEmpty.closeEmotyView(R.id.tv_text);
            }
        });

        findViewById(R.id.tv_main_test5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = evMainEmpty.getView(1);
                view.setVisibility(view.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
    }

    private ImageView getImageView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        return imageView;
    }

    private TextView getTextView() {
        TextView textView = new TextView(this);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        textView.setGravity(Gravity.CENTER);
        textView.setText("EmptyView");
        textView.setTextSize(40);
        return textView;
    }
}
