package pl.snowdog.androidannotationstest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.DrawableRes;
import org.androidannotations.annotations.res.StringRes;

import pl.snowdog.androidannotationstest.bus.OnWindowOperationFinishedEvent;
import pl.snowdog.androidannotationstest.bus.OttoBus;

/**
 * Created by chomi3 on 2015-05-08.
 */

@EBean
public class Window {
    private int id = 0;

    @Bean
    OttoBus bus;

    @StringRes
    String alreadyOpenedMessage;

    @StringRes
    String alreadyClosedMessage;

    @DrawableRes
    Drawable windowClosed;

    @DrawableRes
    Drawable windowOpened;

    private boolean isOpened = false;


    public void toggleWindow() {
        if(isOpened()) {
            closeWindow();
        } else {
            openWindow();
        }
    }


    @Background
    public void openWindow() {
        boolean isChangeState = false;
        if(!isOpened) {
            isChangeState = true;
            isOpened = true;
        }
        postResult(isChangeState);
    }


    @Background
    public void closeWindow() {
        boolean isChangeState = false;
        if(isOpened) {
            isOpened = false;
            isChangeState = true;
        }
        postResult(isChangeState);

    }

    @UiThread
    void postResult(boolean isChangeState) {
        bus.post(new OnWindowOperationFinishedEvent(this, isChangeState));
    }

    public boolean isOpened() {
        return isOpened;
    }

    public Drawable getStatusDrawable() {
        return isOpened ? windowOpened : windowClosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
