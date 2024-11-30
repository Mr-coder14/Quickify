package Adaptor;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.R;
import com.example.quiz.Reportviewadmin;

import java.util.ArrayList;

import Classes.Reportissue;

public class reportadaptoradmin extends RecyclerView.Adapter<reportadaptoradmin.Viewholder> {
    private ArrayList<Reportissue> arrayList;
    private Context context;

    public reportadaptoradmin(ArrayList<Reportissue> arrayList){
        this.arrayList=arrayList;

    }
    @NonNull
    @Override
    public reportadaptoradmin.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reportissuetemplate,parent,false);
        return new reportadaptoradmin.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reportadaptoradmin.Viewholder holder, int position) {
            Reportissue reportissue=arrayList.get(position);
            holder.phno.setText(reportissue.getPhno());
            holder.username.setText(reportissue.getUsername());
            holder.brandname.setText(reportissue.getBrandname());
            String ad=reportissue.getAddress(),vehi=reportissue.getVehicleno(),year=reportissue.getYearofmani();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, Reportviewadmin.class);
                    intent.putExtra("report", reportissue);

                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView brandname,username,phno;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            brandname=itemView.findViewById(R.id.brandName);
            username=itemView.findViewById(R.id.orderedusername);
            phno=itemView.findViewById(R.id.phnocontact);
        }
    }
}
