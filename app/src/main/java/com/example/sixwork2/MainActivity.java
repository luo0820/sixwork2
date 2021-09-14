package com.example.sixwork2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;
    TextView e1;
    EditText e2;
    String str="0";
    boolean tus=false;
    int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.bu1);
        b2=findViewById(R.id.bu2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        b3=findViewById(R.id.bu3);
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                str=editable.toString();
            }
        });


        Handler h1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                e1.setText(msg.arg1+"");


            }
        };
        Handler h2=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
               m=msg.arg1;
            }
        };

        final Runnable r1=new Runnable() {
            @Override
            public void run() {
                Message ns=new Message();

                    ns.arg1 = Integer.valueOf(str);
                    h2.sendMessage(ns);

                int p=0;
                while (!tus && (p < 100)){
                    Message m1=new Message() ;
                m1.arg1=p;
                h1.sendMessage(m1);
                p++;
                try {
                Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                    Message msg = h1.obtainMessage();//同 new Message();
                    msg.arg1 =0;
                   h1.sendMessage(msg);

                }
            }
        };
        Thread t1=new Thread(null,r1,"workthread");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.start();
                Toast.makeText(MainActivity.this,"线程已启动！！",Toast.LENGTH_LONG).show();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e2.setText("0");
                tus=true;
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m!=0) {
                    Log.v("TAG", m + "");
                    boolean isPrime = true;
                    if (m > 0) {
                        int k = (int) Math.sqrt(m);//k为num的正平方根，取整数
                        for (int i = 2; i <= k; i++) {
                            if (m % i == 0) {
                                isPrime = false;//不是素数
                                break;
                            }
                        }
                    }
                    if (isPrime) {
                        Toast.makeText(MainActivity.this, m+ "是素数！！", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, m+ "不是素数！！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}