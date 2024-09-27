package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptor.ChatAdapter;
import Classes.MessageModel;
import Classes.User;

public class ChatActivity extends AppCompatActivity {
    private ImageButton back,voicecall,send;
    private EditText txt;
    private TextView username;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private ArrayList<MessageModel> messageList;
    private DatabaseReference reference,chatrefrence;
    private String senderRoom, receiverRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        txt=findViewById(R.id.chat_edittxt);
        voicecall=findViewById(R.id.voicecall);
        send=findViewById(R.id.send_message_btn);
        back=findViewById(R.id.chat_back_btn);
        recyclerView = findViewById(R.id.chat_recylerview);
        username=findViewById(R.id.chat_txtview);
        messageList = new ArrayList<>();
        User userModel=getIntent().getParcelableExtra("usermodel");
        voicecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatActivity.this, "Voice Call Strated", Toast.LENGTH_SHORT).show();
            }
        });
        chatAdapter = new ChatAdapter(this, messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        String senderUid = FirebaseAuth.getInstance().getUid();
        String receiverUid = userModel.getUserid();
        if (username != null) {
            username.setText(userModel.getName());
        }
        senderRoom = senderUid + receiverUid;
        receiverRoom = receiverUid + senderUid;
        chatrefrence= FirebaseDatabase.getInstance().getReference().child("chatss");
        reference = FirebaseDatabase.getInstance().getReference().child("chatsRooms").child(senderRoom);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    MessageModel message = snapshot1.getValue(MessageModel.class);
                    messageList.add(message);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = txt.getText().toString();
                if (!messageText.isEmpty()) {
                    MessageModel message = new MessageModel(messageText, senderUid, System.currentTimeMillis());
                    txt.setText("");


                    reference.child(reference.push().getKey()).setValue(message);
                    FirebaseDatabase.getInstance().getReference().child("chatsRooms").child(receiverRoom)
                            .child(reference.push().getKey()).setValue(message);
                    User userModel1 = new User(userModel.getPhno(), userModel.getName(), userModel.getUserid());

                    chatrefrence.child(FirebaseAuth.getInstance().getUid()).child(userModel.getUserid()).setValue(userModel1);


                }
            }
        });

    }
}