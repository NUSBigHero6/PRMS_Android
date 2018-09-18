package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 * Created by liu.cao on 19/9/2018.
 */

import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleAdapter extends ArrayAdapter<ProgramSlot> {

    public ScheduleAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_radio_program, parent, false);
        }
        //    Word currentWord = getItem(position);
        ProgramSlot currentRP = getItem(position);

        EditText radioPMName = (EditText)listItemView.findViewById(R.id.maintain_program_name_text_view);
        radioPMName.setText(currentRP.getName(), TextView.BufferType.NORMAL);
        radioPMName.setKeyListener(null); // This disables editing.

        EditText radioPMDesc = (EditText)listItemView.findViewById(R.id.maintain_program_desc_text_view);
        radioPMDesc.setText(currentRP.getName(), TextView.BufferType.NORMAL);
        radioPMDesc.setKeyListener(null);

        EditText radioPMDuration = (EditText)listItemView.findViewById(R.id.maintain_program_duration_text_view);
        radioPMDuration.setText(currentRP.getName(), TextView.BufferType.NORMAL);
        radioPMDuration.setKeyListener(null);

        return listItemView;
    }
}

