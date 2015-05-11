package pl.snowdog.androidannotationstest.bus;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

// Declare the bus as an enhanced bean
@EBean(scope = EBean.Scope.Singleton)
public class OttoBus extends Bus {

}