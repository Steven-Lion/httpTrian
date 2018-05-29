package demo.example.com.httpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import demo.example.com.httpdemo.asynctask.AsyncTaskActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void MyCLick(View v){
        switch (v.getId()){
            case R.id.btnGet:
                startActivity(new Intent(MainActivity.this,HttpGetActivity.class));
                break;
            case R.id.btnPost:
                startActivity(new Intent(MainActivity.this,HttpPostActivity.class));
                break;

            case R.id.btnAsynchTask:
                startActivity(new Intent(MainActivity.this,AsyncTaskActivity.class));
                break;
        }
    }

}
