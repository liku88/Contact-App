package in.mangaldeepdevloper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Add_Data_Activity extends AppCompatActivity {
    EditText ed_Name, ed_Email , ed_Contact, ed_Address;
    String edt_Name, edt_Email , edt_Contact , edt_Address;
    String urlInsert = "https://studentapp2k21.000webhostapp.com/studentDetails.php";
    String urlLogout = "https://studentapp2k21.000webhostapp.com/logout.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__data_);
        ed_Name = findViewById(R.id.ed_username);
        ed_Email = findViewById(R.id.ed_email);
        ed_Contact = findViewById(R.id.ed_contact);
        ed_Address = findViewById(R.id.ed_address);
    }


    public void logout(View view) {
      StringRequest request = new StringRequest(Request.Method.GET, urlLogout, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              startActivity(new Intent(getApplicationContext(), login.class));
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(Add_Data_Activity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
          }
      });

      RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Activity.this);
      requestQueue.add(request);
    }

    public void saveContact(View view) {
        if(ed_Name.getText().toString().equals("")){
            Toast.makeText(Add_Data_Activity.this, "Please fill Name", Toast.LENGTH_SHORT).show();
        }else if(ed_Email.getText().toString().equals("")){
            Toast.makeText(Add_Data_Activity.this, "Please fill Email", Toast.LENGTH_SHORT).show();
        }else if(ed_Contact.getText().toString().equals("")){
            Toast.makeText(Add_Data_Activity.this, "Please fill Contact", Toast.LENGTH_SHORT).show();
        }else if(ed_Address.getText().toString().equals("")){
            Toast.makeText(Add_Data_Activity.this, "Please fill Address", Toast.LENGTH_SHORT).show();
        }else{
            edt_Name = ed_Name.getText().toString().trim();
            edt_Contact = ed_Contact.getText().toString().trim();
            edt_Address = ed_Address.getText().toString().trim();
            edt_Email = ed_Email.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ed_Name.setText("");
                    ed_Email.setText("");
                    ed_Contact.setText("");
                    ed_Address.setText("");
                  Toast.makeText(Add_Data_Activity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Data_Activity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", edt_Name);
                    params.put("email", edt_Email);
                    params.put("contact", edt_Contact);
                    params.put("address", edt_Address);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Activity.this);
            requestQueue.add(request);

        }
    }
}