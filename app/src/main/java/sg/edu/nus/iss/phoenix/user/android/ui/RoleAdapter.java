package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.user.entity.Role;

/**
 * Created by priyankajalan on 28/9/18.
 */

public class RoleAdapter extends ArrayAdapter<Role> {

    public RoleAdapter(@NonNull Context context, ArrayList<Role> roles) {
        super(context, 0, roles);
    }

}
