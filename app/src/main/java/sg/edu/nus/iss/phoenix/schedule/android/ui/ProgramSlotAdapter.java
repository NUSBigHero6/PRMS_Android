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

/**
 * Created by liu.cao on 19/9/2018.
 */

import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ProgramSlotAdapter extends ArrayAdapter<ProgramSlot> {

    public ProgramSlotAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_schedule, parent, false);
        }
        ProgramSlot currentPS = getItem(position);

        EditText psDate = (EditText) listItemView.findViewById(R.id.maintain_Date_text_view);
        psDate.setText(currentPS.getDateOfProgram(), TextView.BufferType.NORMAL);
        psDate.setKeyListener(null); // This disables editing.

        EditText programName = (EditText) listItemView.findViewById(R.id.maintain_program_text_view);
        programName.setText(currentPS.getProgramName(), TextView.BufferType.NORMAL);
        programName.setKeyListener(null);

        EditText producerName = (EditText) listItemView.findViewById(R.id.maintain_producer_text_view);
        producerName.setText(currentPS.getProducerName(), TextView.BufferType.NORMAL);
        producerName.setKeyListener(null);

        EditText presenterName = (EditText) listItemView.findViewById(R.id.maintain_presenter_text_view);
        presenterName.setText(currentPS.getPresenterName(), TextView.BufferType.NORMAL);
        presenterName.setKeyListener(null);

        EditText startTime = (EditText) listItemView.findViewById(R.id.maintain_starttime_text_view);
        startTime.setText(currentPS.getDuration(), TextView.BufferType.NORMAL);
        startTime.setKeyListener(null);

        EditText duration = (EditText) listItemView.findViewById(R.id.maintain_programslot_duration_text_view);
        duration.setText(currentPS.getDuration(), TextView.BufferType.NORMAL);
        duration.setKeyListener(null);

        return listItemView;
    }
}

