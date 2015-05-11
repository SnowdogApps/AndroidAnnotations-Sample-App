package pl.snowdog.androidannotationstest.bus;

import pl.snowdog.androidannotationstest.Window;

/**
 * Created by chomi3 on 2015-05-11.
 */
public class OnWindowOperationFinishedEvent {
    public Window window;
    public boolean isChangeState = false;
    public OnWindowOperationFinishedEvent(Window window, boolean isChangeState) {
        this.window= window;
        this.isChangeState = isChangeState;
    }
}