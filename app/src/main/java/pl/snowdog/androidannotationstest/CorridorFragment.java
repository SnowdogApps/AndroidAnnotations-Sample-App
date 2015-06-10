package pl.snowdog.androidannotationstest;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

/**
 * Created by chomi3 on 2015-06-03.
 */
@EFragment(R.layout.corridor_fragment)
public class CorridorFragment extends Fragment {
    @FragmentArg
    String fragmentTitle = "Fragment title";

    @ViewById(R.id.tv_fragment_title)
    TextView tvFragmentTitle;


    @AfterViews
    public void afterViews() {
        tvFragmentTitle.setText(fragmentTitle);
    }

    public void setTitle(String title) {
        this.fragmentTitle = title;
        tvFragmentTitle.setText(fragmentTitle);
    }
}
