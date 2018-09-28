package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Stream;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by wengweichen on 25/9/18.
 */

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User currentUser = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_user, parent, false);
        }

        EditText userName = (EditText)listItemView.findViewById(R.id.user_name_text_view);
        userName.setText(currentUser.getName(), TextView.BufferType.NORMAL);
        userName.setKeyListener(null); // This disables editing.

        EditText userRole = (EditText)listItemView.findViewById(R.id.user_role_text_view);

        CharSequence rolesString = currentUser.getRoles().get(0).getRole();

        userRole.setText(rolesString, TextView.BufferType.NORMAL);
        userRole.setKeyListener(null);

        return listItemView;
    }
}
