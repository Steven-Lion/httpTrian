package demo.example.com.httpdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostActivity extends Activity {



    //登录云平台需要的数据
    private static  String ip_address = "";
    private static  String PROJECT = "";
    private static  String USERNAME = "";
    private static  String PWD = "";
    private Button btnLogin;//登录按钮对象
    private TextView tvResponse;//用于显示返回结果
    private EditText   ip;
    private EditText user_name;
    private  EditText user_pwd ;
    private EditText project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置显示的视图 10.5.15.253
        setContentView(R.layout.activity_httppost);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 2-1: 创建子线程，调用登录方法
                new Thread() {
                    public void run() {

                        login(); // 调用login方法登陆云平台
                    }
                }.start();
            }
        });
    }
    private void init(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
        EditText ip = (EditText)findViewById(R.id.ip);
        EditText user_name = (EditText)findViewById(R.id.user_name);
        EditText user_pwd = (EditText)findViewById(R.id.user_pwd);
        EditText project_id = (EditText)findViewById(R.id.project_id);
       ip_address = ip.getText().toString().trim();
        USERNAME = user_name.getText().toString().trim();
        PWD = user_pwd.getText().toString().trim();
        PROJECT = project_id.getText().toString().trim();
        String Address = "http://"+ip_address+"/api/Account/Logon";
    }

    /**
     * POST请求操作
     */
    public void login() {
        try {
            //todo 2-2: 根据登陆接口地址创建URL对象
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        URL url=new URL("http://10.5.15.253/api/Account/Logon");
                        // todo 3:根据URL对象打开链接
                        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                        // todo 4:设置请求的方式和超时时间
                        urlConnection.setRequestMethod("POST");
                        // 设置连接主机超时时间
                        urlConnection.setConnectTimeout(5000);
                        // 设置从主机读取数据超时间
                        urlConnection.setReadTimeout(5000);

                        // todo 5:因和云平台之间通信确定的数据格式是Json字符串，所以将用户名，密码和项目表示转换为Json字符串
                        //得到一个Json对象
                        JSONObject object=new JSONObject();
                        //写入数据
                        object.put("UserName",USERNAME);
                        object.put("Password",PWD);
                        object.put("ProjectTag",PROJECT);
                        //将Json对象转换为字符串
                        String data =object.toString();
                        // todo 6:设置连接属性
                        urlConnection.setRequestProperty("Connection","keep-alive");
                        urlConnection.setRequestProperty("Content-Type","application/json");
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        // todo 7:获得输出流，通过输出流向服务器提交数据。
                        OutputStream os=urlConnection.getOutputStream();
                        os.write(data.getBytes());
                        // todo 8:对返回码进行判断，返回码为200则请求成功，获得输入流，然后将输入流转换为StringBuffer并显示在文本框中，否则弹出请求失败的提示。


                        if (urlConnection.getResponseCode()==200) {
                            InputStream is = urlConnection.getInputStream();

                            InputStreamReader isr = new  InputStreamReader(is);

                            BufferedReader br  = new BufferedReader(isr);

                            final StringBuffer result = new StringBuffer();

                            String str = "";
                            while((str = br.readLine()) != null ){
                                result.append(str);
                            }
                            br.close();
                            urlConnection.connect();

                            // 通过runOnUiThread方法进行修改主线程的控件内容
                            HttpPostActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                               tvResponse.setText(result.toString());
                                        }
                                    });
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HttpPostActivity.this, "登录失败", Toast.LENGTH_SHORT);
                                }
                            });

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                                 }
                }
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
