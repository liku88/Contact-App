package in.mangaldeepdevloper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
   TextView tv_name , tv_email , tv_contact, tv_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        tv_name = findViewById(R.id.tt_name);
        tv_email = findViewById(R.id.tt_email);
        tv_contact = findViewById(R.id.tt_contact);
        tv_address = findViewById(R.id.tt_address);
        int ID = intent.getExtras().getInt("ID");

        tv_name.setText("Name :"+MainActivity.studentEArrayList.get(ID).getName());
        tv_email.setText("Email :"+MainActivity.studentEArrayList.get(ID).getEmail());
        tv_contact.setText("Contact :"+MainActivity.studentEArrayList.get(ID).getContact());
        tv_address.setText("Address :"+MainActivity.studentEArrayList.get(ID).getAddress());



    }


}