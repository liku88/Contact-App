package in.mangaldeepdevloper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<StudentE> {
    Context context;
    List<StudentE> arrayListStudent;
    public MyAdapter(@NonNull Context context, List<StudentE> arrayListStudent) {
        super(context, R.layout.custom_list_item, arrayListStudent);

        this.context = context;
        this.arrayListStudent = arrayListStudent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView textView1 = view.findViewById(R.id.txt_id);
        TextView textView2 = view.findViewById(R.id.txt_name);

        textView1.setText(arrayListStudent.get(position).getId());
        textView2.setText(arrayListStudent.get(position).getName());
        return view;
    }
}
