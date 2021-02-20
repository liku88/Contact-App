package in.mangaldeepdevloper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
   TextView edt_Name , edt_Email , edt_Address, edt_Contact;
   String st_Name, st_Email , st_Address, st_Contact;
   String url = "https://studentapp2k21.000webhostapp.com/Update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edt_Name = findViewById(R.id.et_name);
        edt_Email = findViewById(R.id.et_email);
        edt_Contact = findViewById(R.id.et_contact);
        edt_Address = findViewById(R.id.et_address);

        Intent intent = getIntent();

        int p = intent.getExtras().getInt("po");
        edt_Name.setText(MainActivity.studentEArrayList.get(p).getName());
        edt_Email.setText(MainActivity.studentEArrayList.get(p).getEmail());
        edt_Contact.setText(MainActivity.studentEArrayList.get(p).getContact());
        edt_Address.setText(MainActivity.studentEArrayList.get(p).getAddress());

    }

    public void Update(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(UpdateActivity.this);
        progressDialog.setMessage("Please wait...");
        if(edt_Name.getText().toString().equals("")){
            Toast.makeText(UpdateActivity.this,"Name field is empty",Toast.LENGTH_SHORT).show();
        }else if(edt_Email.getText().toString().equals("")){
            Toast.makeText(UpdateActivity.this,"Email field is empty",Toast.LENGTH_SHORT).show();
        }else if(edt_Contact.getText().toString().equals("")){
            Toast.makeText(UpdateActivity.this,"Contact field is empty",Toast.LENGTH_SHORT).show();
        }else if(edt_Address.getText().toString().equals("")){
            Toast.makeText(UpdateActivity.this,"Email field is empty",Toast.LENGTH_SHORT).show();
        }else {



            Intent intent = getIntent();

            int p = intent.getExtras().getInt("po");
            final String id123 = MainActivity.studentEArrayList.get(p).getId();

            st_Name = edt_Name.getText().toString().trim();
            st_Email = edt_Email.getText().toString().trim();
            st_Contact = edt_Contact.getText().toString().trim();
            st_Address = edt_Address.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(UpdateActivity.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(UpdateActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id",id123);
                    params.put("name", st_Name);
                    params.put("email", st_Email);
                    params.put("contact", st_Contact);
                    params.put("address", st_Address);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(UpdateActivity.this);
            requestQueue.add(request);
        }
    }
}