package fcsit.foodieroute;

/**
 * Created by elliotching on 08-Dec-16.
 */
import android.app.Fragment;

/**
 * Created by Elliot on 05-Aug-16.
 */
public class DrawerItem {
    public String mItem;
    public boolean choosen;
    public Fragment mFragment;

    public DrawerItem(String m) {
        mItem = m;
    }

    public DrawerItem(String m, Fragment f) {
        mItem = m;
        mFragment = f;
    }

}