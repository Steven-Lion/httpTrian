package demo.example.com.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetActivity extends AppCompatActivity {
    private TextView tvData;
    private EditText edt_url;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpget);
        tvData = (TextView) findViewById(R.id.tvData);
        edt_url= (EditText) findViewById(R.id.edt_url);
        button= (Button) findViewById(R.id.button);

        //todo 2-2:在需要使用的时候启动线程
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
               // Toast.makeText(HttpGetActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //自定义方法start，用于创建一个线程并启动
    private void start(){
    //todo 2-1:创建一个线程，用于访问网络，并在最后启动线程
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String editUrl = edt_url.getText().toString().trim();
                //todo 3:新建一个URL对象
                URL url = new URL(editUrl);


                //todo 4: 打开一个HttpURLConnection连接
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                //todo 5:设置请求方式为GET，设置连接超时和读取数据超时时间。
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                //todo 6:建立与服务器的连接
                //注意：此时实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求
                conn.connect();


                // todo 7:对返回码进行判断，返回码为200则请求成功，获得输入流，否则弹出请求失败的提示。
                if (200 == conn.getResponseCode()) {
                    //获得输入流，HttpURLConnection调用getInputStream()方法后get请求才发出去。
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    //todo 8:转换输入流，并读取数据到一个StringBuffer对象
                    final StringBuffer result = new StringBuffer();
                    String str = "";

                    //todo 9:使用runOnUiThread方法将StringbUffer对象内容显示在文本框中。
                    while((str = br.readLine())!= null){
                            result.append(str);
                    }
                    br.close();
                    conn.disconnect();//断开连接
                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvData.setText(result.toString());
                            Toast.makeText(HttpGetActivity.this, "tvDate is set Text", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {//在UI线程中显示"请求失败"
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HttpGetActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
    thread.start();;
    }
}
