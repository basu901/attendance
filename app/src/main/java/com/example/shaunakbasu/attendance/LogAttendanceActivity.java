package com.example.shaunakbasu.attendance;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaunakbasu.attendance.data.LoginColumns;
import com.example.shaunakbasu.attendance.data.LoginProvider;

public class LogAttendanceActivity extends AppCompatActivity {

    EditText input_id;
    public static String TAG=LogAttendanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_attendance);
        input_id=(EditText)findViewById(R.id.student_id);
        Button add_student=(Button)findViewById(R.id.button_add);
        add_student.setOnClickListener(onClickAddStudent);
        Button delete_student=(Button)findViewById(R.id.button_delete);
        delete_student.setOnClickListener(onClickDeleteStudent);
        Button display_student=(Button)findViewById(R.id.button_display);
        display_student.setOnClickListener(onClickDisplay);
    }

    private View.OnClickListener onClickAddStudent=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues values = new ContentValues();
            values.put(LoginColumns._ID, input_id.getText().toString());
            values.put(LoginColumns.STUDENT_NAME, "NAME_" + input_id.getText().toString());

            try{
                Uri uri = getApplicationContext().getContentResolver().insert(LoginProvider.Login.CONTENT_URI, values);
                if (ContentUris.parseId(uri) != -1)
                    Toast.makeText(getApplicationContext(),"INSERTED!",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Already Present!", Toast.LENGTH_SHORT).show();
            }

        }
    };


    private View.OnClickListener onClickDeleteStudent=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int del=getApplicationContext().getContentResolver().delete(LoginProvider.Login.CONTENT_URI,
                    LoginColumns._ID + " = "+input_id.getText().toString(),null);
            if(del>0)
                Toast.makeText(getApplicationContext(),"DELETED",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"No Record",Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener onClickDisplay=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cursor cursor=getApplicationContext().getContentResolver().query(LoginProvider.Login.CONTENT_URI,
                    new String[]{LoginColumns._ID, LoginColumns.STUDENT_NAME},
                    null,null,null);
            if(cursor!=null)
                cursor.moveToFirst();

            do{
                int id=cursor.getInt(cursor.getColumnIndex(LoginColumns._ID));
                String name=cursor.getString(cursor.getColumnIndex(LoginColumns.STUDENT_NAME));

                Log.v(TAG,"ID: "+id+" Name: "+name);

            }while(cursor.moveToNext());

            cursor.close();

        }
    };

}
