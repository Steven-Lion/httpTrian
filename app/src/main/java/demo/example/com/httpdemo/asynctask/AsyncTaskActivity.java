package demo.example.com.httpdemo.asynctask;

import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import demo.example.com.httpdemo.R;

public class AsyncTaskActivity extends Activity {

    private final static String IMAGE_PATH = "http://img1.3lian.com/2015/a2/246/d/58.jpg";
    private ImageView imageView;
    private Button btnCancel, btnDownLoad;
    private ProgressBar progressBar;
    //private MyAsyncTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        imageView = (ImageView) findViewById(R.id.imageView);
        btnDownLoad = (Button) findViewById(R.id.btnDownLoad);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        // 得到进度条
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //todo 2-5-1:在“下载图片”点击事件中实例化MyAsyncTask类并执行异步任务
        btnDownLoad.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {



            }
        });

        //todo 2-5-2:在“重置图片”的点击事件中将进度条清0，将图片设成系统自带的安卓机器人图标
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
    }

   /* *//**
     * 继承AsyncTask
     *//*
    // Params：开始异步任务执行时传入的参数类型，即doInBackground()方法中的参数类型；
    // Progress：异步任务执行过程中，返回下载进度值的类型，即在doInBackground中调用publishProgress()时传入的参数类型；
    // Result：异步任务执行完成后，返回的结果类型，即doInBackground()方法的返回值类型；
    //todo 2-1:在AsyncTaskActivity中新建一个名为MyAsyncTask的类，该类继承AsyncTask，三个参数分别为String，Integer，Bitmap。
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        *//**
         * 准备运行
         *//*
        // 该回调方法在任务被执行之后立即由UI线程调用。这个步骤通常用来建立任务，在UI上显示进度条。
        @Override
        protected void onPreExecute() {
            //todo 2-2:重写AsyncTask中的onPreExecute（）方法在onPreExecute（）中清除显示的图片，并且进度条置0。


        }

        *//**
         * 正在后台运行
         *//*
        // 运行在子线程中，可执行一些耗时操作
        // 该回调方法由后台线程在onPreExecute()方法执行结束后立即调用。
        // 通常在这里执行耗时的后台计算，计算的结果必须由该函数返回，并被传递到onPostExecute()中。
        // 在该方法内也可使用publishProgress()来发布一个或多个进度单位，这些值将会在onProgressUpdate()中被发布到UI线程。
        @Override
        protected Bitmap doInBackground(String... params) {
            *//*todo 2-3:重写AsyncTask中的doInBackground（）方法在doInBackground（）中使用HttpURLConnection访问图片地址，
            建立连接的时候将ProgressBar进度值置为50%，图片下载完成后将ProgressBar进度值设为100%
             *//*


        }

        *//**
         * 进度更新
         *//*
        // 该方法由UI线程在publishProgress()方法调用完后被调用，一般用于动态地显示一个进度条。
        protected void onProgressUpdate(Integer... progress) {
            //todo 2-4-1:重写AsyncTask中的onProgressUpdate（）方法在onProgressUpdate（）中更新进度条的进度。
            // 更新进度条的进度

        }

        *//**
         * 完成后台任务
         *//*
        // 后台任务执行完之后被调用，在UI线程执行。
        protected void onPostExecute(Bitmap result) {
            //todo 2-4-2:重写onPostExecute（）方法，如果下载图片得到的bitmap不为空，弹出“下载图片成功！”的提示，并将bitmap设置到ImageView中，否则弹出“下载图片失败”



        }

    }*/
}