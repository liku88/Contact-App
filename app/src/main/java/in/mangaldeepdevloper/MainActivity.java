package in.mangaldeepdevloper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   ListView listView;
   MyAdapter myAdapter;
   public static ArrayList<StudentE> studentEArrayList = new ArrayList<>();
   String url = "https://studentapp2k21.000webhostapp.com/displayListView.php";
   String dltUrl = "https://studentapp2k21.000webhostapp.com/delete.php";
   StudentE studentE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        myAdapter = new MyAdapter(this,studentEArrayList);
        listView.setAdapter(myAdapter);
        retriveData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


                final CharSequence dialogItem[] = {"View Data", "Edit Data", "Delete Data"};
                builder.setTitle(studentEArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                                intent.putExtra("ID", position);
                                startActivity(intent);
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), UpdateActivity.class)
                                .putExtra("po",position));
                                break;

                            case 2:
                                final String idToDlt= studentEArrayList.get(position).getId();
                                StringRequest request = new StringRequest(Request.Method.POST, dltUrl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                  Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id", idToDlt);
                                        return params;
                                    }
                                };

                                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                                requestQueue.add(request);
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });
    }


    private void retriveData() {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                studentEArrayList.clear();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if(success.equals("1")){
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String email = object.getString("email");
                        String contact = object.getString("contact");
                        String address = object.getString("address");

                        studentE = new StudentE(id, name ,email, contact ,address);
                        studentEArrayList.add(studentE);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(), Add_Data_Activity.class));
    }
}