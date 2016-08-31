package sheffer.checkmarkspike;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public int hueRotation = 0;
    WebView wv;
    String html = "<html><style TYPE=\"text/css\">.huerotate {-webkit-filter: hue-rotate(25deg); }}</style><body bgcolor=\"white\"><table width=\"100%\" height=\"100%\"><tr><td align=\"center\" valign=\"center\"><img src=\"file:///android_asset/checkmark.gif\" hight=\"100%\" width=\"100%\" class=\"huerotate\"></td></tr></table></body></html>";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv =(WebView) findViewById(R.id.gif_webview);
        Button changeColorButton = (Button) findViewById(R.id.change_color);
        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorAndReturnHtml();
                wv.reload();
                wv.clearCache(true);
                wv.loadDataWithBaseURL("",html, "text/html", "utf-8", null);


            }
        });
        wv.loadDataWithBaseURL("",html, "text/html", "utf-8", null);
    }


    private String changeColorAndReturnHtml(){
        try {
            hueRotation = rotateHue(hueRotation);
            html = html.replaceAll("hue-rotate\\(.*deg\\)", "hue-rotate("+hueRotation+"deg)");
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private int rotateHue(int hue){
        if(hue+25 < 360){
            return hue+25;
        }
        else return 0;
    }
}
