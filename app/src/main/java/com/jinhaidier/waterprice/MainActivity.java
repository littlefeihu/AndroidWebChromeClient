package com.jinhaidier.waterprice;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    FileChooserWebChromeClient fileChooserWebChromeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileChooserWebChromeClient = FileChooserWebChromeClient.createBuild(new FileChooserWebChromeClient.ActivityCallBack() {
            @Override
            public void FileChooserBack(Intent intent) {
                startActivityForResult(intent, UploadMessage.FILE_CHOOSER_RESULT_CODE);
            }
        });
        webView = (WebView) findViewById(R.id.webview);
        String url = "http://47.102.134.249:81/frm/app/login.html";
         //url="http://mall.yzd2017.com/frm/app/projectmaintain.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);  //设置可以访问文件
        webView.setWebChromeClient(fileChooserWebChromeClient);
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
               }
         });

        webView.loadUrl(url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UploadMessage.FILE_CHOOSER_RESULT_CODE) {
            fileChooserWebChromeClient.getUploadMessage().onActivityResult(requestCode,resultCode,data);
        }
    }
}
