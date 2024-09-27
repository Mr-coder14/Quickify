package Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.ChatActivity;
import com.example.quiz.R;

import java.util.ArrayList;

import Classes.User;


public class ChatlistAdaptor extends RecyclerView.Adapter<ChatlistAdaptor.ViewHolder> {
    private ArrayList<User> userModels;
    private Context context;

    public ChatlistAdaptor(Context context, ArrayList<User> userModels){
        this.context=context;
        this.userModels=userModels;
    }
    @NonNull
    @Override
    public ChatlistAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userlisttemplate,parent,false);
        return new ChatlistAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatlistAdaptor.ViewHolder holder, int position) {
        User userModel=userModels.get(position);
        holder.bind(userModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("usermodel",userModel);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,phno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.user_profile_name);
            phno=itemView.findViewById(R.id.user_phone_number);
        }
        public void bind(User userModel){
            name.setText(userModel.getName());
            phno.setText(userModel.getPhno());
        }
    }
}