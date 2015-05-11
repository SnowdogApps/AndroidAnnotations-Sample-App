package pl.snowdog.androidannotationstest;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import pl.snowdog.androidannotationstest.bus.OttoBus;
import pl.snowdog.androidannotationstest.bus.OnWindowOperationFinishedEvent;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {


    @ViewById(R.id.window1image)
    ImageView ivWindow1;

    @ViewById(R.id.window1toggle)
    Button btnWindow1Toggle;


    @ViewById(R.id.window2image)
    ImageView ivWindow2;

    @ViewById(R.id.window2toggle)
    Button btnWindow2Toggle;

    @Bean
    Window window1;

    @Bean
    Window window2;

    @StringRes
    String open;

    @StringRes
    String close;

    @Bean
    OttoBus bus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.register(this);
        window1.setId(1);
        window2.setId(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe
    public void onWindowOperationFinished(OnWindowOperationFinishedEvent event) {
        switch (event.window.getId()) {
            case 1:
                if(event.isChangeState) {
                    updateWindowViews(event.window, btnWindow1Toggle, ivWindow1);
                } else {
                    Toast.makeText(MainActivity.this, (event.window.isOpened() ? event.window.alreadyOpenedMessage : event.window.alreadyClosedMessage) + " " + Integer.toString(event.window.getId()), Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(event.isChangeState) {
                    updateWindowViews(event.window, btnWindow2Toggle, ivWindow2);
                } else {
                    Toast.makeText(MainActivity.this, (event.window.isOpened() ? event.window.alreadyOpenedMessage : event.window.alreadyClosedMessage) + " " + Integer.toString(event.window.getId()), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Click(R.id.window1toggle)
    void window1Toggle() {
        window1.toggleWindow();
    }

    @Click(R.id.window2toggle)
    void window2Toggle() {
        window2.toggleWindow();
    }

    @Click
    void openAllWindows() {
        window1.openWindow();
        window2.openWindow();

    }

    @Click
    void closeAllWindows() {
        window1.closeWindow();
        window2.closeWindow();
    }

    @UiThread
    void updateWindowViews(Window w, Button btn, ImageView ivWindow) {
        if(!w.isOpened()) {
            btn.setText(open);
        } else {
            btn.setText(close);
        }

        fadeInWindow(w, ivWindow);
        ivWindow.setImageDrawable(w.getStatusDrawable());
        ivWindow.requestLayout();
        btn.requestLayout();
    }

    @Background
    void fadeInWindow(Window w, ImageView ivWindow) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(300);
        ivWindow.setAnimation(fadeIn);
        fadeIn.start();
    }

}
