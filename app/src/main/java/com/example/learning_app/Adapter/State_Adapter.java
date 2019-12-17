package com.example.learning_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learning_app.Models.StateModel;
import com.example.learning_app.R;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class State_Adapter extends RecyclerView.Adapter<State_Adapter.StateViewHolder> {
    private List<StateModel> stateModel;
    public class StateViewHolder extends RecyclerView.ViewHolder{
        public TextView  name;
        public StateViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.stateName);
        }
    }
    public State_Adapter(List<StateModel> stateModel){
        this.stateModel=stateModel;
    }
    @Override
    public State_Adapter.StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.state_list_row , parent, false);

        return new StateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull State_Adapter.StateViewHolder holder, int position) {
        StateModel state = stateModel.get(position);
        holder.name.setText(state.getName());

    }

    @Override
    public int getItemCount() {
        return stateModel.size();
    }
}
