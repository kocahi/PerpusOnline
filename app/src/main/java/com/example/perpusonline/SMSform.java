package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.perpusonline.Adapter.MessageAdapter;
import com.example.perpusonline.Model.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class SMSform extends AppCompatActivity implements View.OnClickListener {
    TextView tvPhone;
    RecyclerView rvMessage;
    EditText etMessage;
    String phone;
    FloatingActionButton fabSend;
    SmsManager smsManager;
    MessageAdapter messageAdapter;
    Vector<Message> vMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsform);
        tvPhone = findViewById(R.id.tv_phone);
        rvMessage = findViewById(R.id.rv_message);
        etMessage = findViewById(R.id.et_message);
        fabSend = findViewById(R.id.fab_send);
        MainActivity test = new MainActivity();
        vMessage = new Vector<>();
        vMessage.add(new Message("Hello from me", false));
        vMessage.add(new Message("Hello from other", true));
        vMessage.add(new Message("Hello "+test.CheckEmail()+" ,how can i help?", false));
        messageAdapter = new MessageAdapter(this, vMessage);

        phone = "012452653";
        tvPhone.setText(phone);
        fabSend.setOnClickListener(this);

        rvMessage.setAdapter(messageAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        smsManager = SmsManager.getDefault();
        checkUserPermission();
    }

    private void checkUserPermission() {
        int sendPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (sendPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == fabSend){
            //kirim message ke targetphone number + tampilin message  yang ada ke recycler view  + reset value di etmessage
            String message = etMessage.getText().toString();
            if (message.isEmpty()){
                return;
            }
            smsManager.sendTextMessage(phone,null, message, null,null);
            vMessage.add(new Message(message, true));
            messageAdapter.notifyDataSetChanged();
            etMessage.setText("");
        }
    }
}