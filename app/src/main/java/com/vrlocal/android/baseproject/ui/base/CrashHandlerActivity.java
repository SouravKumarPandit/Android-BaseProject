package com.vrlocal.android.baseproject.ui.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;

import com.vrlocal.android.baseproject.R;
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity;
import com.vrlocal.uicontrolmodule.common.VUtil;
import com.vrlocal.uicontrolmodule.ui.VAlertDialog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CrashHandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private String sError = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView clScrollView = new ScrollView(this);
        clScrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        Objects.requireNonNull(getSupportActionBar()).setTitle("Application Error");
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("Report error");

        int i5dp = VUtil.dpToPx(5);
        int i20dp = VUtil.dpToPx(20);
        LinearLayout.LayoutParams clParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        clParams.setMargins(i20dp, i5dp, i20dp, i20dp);


        LinearLayout clButtonsLayout = new LinearLayout(this);
        clButtonsLayout.setOrientation(LinearLayout.HORIZONTAL);
        clButtonsLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        clButtonsLayout.setPadding(i5dp,i5dp,i5dp,i5dp);
        Button btSendLog = new Button(this);
        btSendLog.setText("Report");
        btSendLog.setId(R.id.sendLogButton);
        btSendLog.setOnClickListener(this);
        btSendLog.setBackground(VUtil.getRoundDrawableListState(0xFF2DB143,i20dp,0xFF1B882D,i20dp));
        btSendLog.setTextColor(Color.WHITE);


        Button btClose = new Button(this);
        btClose.setText("Exit");
        btClose.setId(R.id.closeButton);
        btClose.setOnClickListener(this);
        btClose.setBackground(VUtil.getRoundDrawableListState(0xFFF14949,i20dp,0xFFB11212,i20dp));
        btClose.setTextColor(Color.WHITE);


        clButtonsLayout.addView(btSendLog, clParams);
        clButtonsLayout.addView(btClose, clParams);

        final LinearLayout clMainLayout = new LinearLayout(this);
        clMainLayout.setOrientation(LinearLayout.VERTICAL);


        TextView clErrorText = new TextView(this);
        clErrorText.setGravity(Gravity.START);


        clMainLayout.setVisibility(View.GONE);
        clScrollView.addView(clErrorText);
        clMainLayout.addView(clScrollView);
        CardView cardView=new CardView(this);
        cardView.addView(clButtonsLayout);
        cardView.setElevation(8f);
        clMainLayout.addView(cardView);

        setContentView(clMainLayout);

        if (getIntent().getStringExtra("error") != null) {
            sError = getIntent().getStringExtra("error");
            clErrorText.setText(sError);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                clErrorText.    setText(Html.fromHtml(sError, Html.FROM_HTML_MODE_COMPACT));
            } else {
                clErrorText.setText(Html.fromHtml(sError));
            }

        }

        VAlertDialog alertDialog = new VAlertDialog(this, "Application Error", "Unexpected Error occurred ,Would you like to view the error?", null, getResources().getColor(android.R.color.holo_red_light));
        alertDialog.setPositiveButtonColor(0xFF56B864);
        alertDialog.setNegativeBtnColor(0xFFE65656);


        new ContextThemeWrapper(alertDialog.getContext(), android.R.style.Theme_Dialog);

        alertDialog.showDialog(true, true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface clDialog, int iButtonId) {

                if (iButtonId == DialogInterface.BUTTON_POSITIVE) {
                    clMainLayout.setVisibility(View.VISIBLE);
                    clDialog.cancel();
                } else {
                    startMainActiviy();
					 System.exit(10);
                    clDialog.cancel();
                }


            }
        });
    }

    private String extractLogToFile() {
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
        }
        String model = Build.MODEL;
        if (!model.startsWith(Build.MANUFACTURER))
            model = Build.MANUFACTURER + " " + model;

        // Make file name - file must be saved to external storage or it wont be readable by
        // the email app.
        String path = Environment.getExternalStorageDirectory() + "/" + "baseApp";
        File file = null;
        boolean success = false;
        String fullName = null;
        file = new File(Environment.getExternalStorageDirectory() + "/baseApp");

        if (file.exists()) {
            success = true;
        } else {
            success = file.mkdir();
        }
        if (success) {
            fullName = path + "/baseApp.txt";

            // Extract to file.
            file = new File(fullName);
            InputStreamReader reader = null;
            FileWriter writer = null;
            try {
                // For Android 4.0 and earlier, you will get all app's log output, so filter it to
                // mostly limit it to your app's output.  In later versions, the filtering isn't needed.
                String cmd = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) ?
                        "logcat -d -v time MyApp:v dalvikvm:v System.err:v *:s" :
                        "logcat -d -v time";

                // get input stream
                Process process = Runtime.getRuntime().exec(cmd);
                reader = new InputStreamReader(process.getInputStream());

                // write output stream
                writer = new FileWriter(file);
                writer.write("Android version: " + Build.VERSION.SDK_INT + "\n");
                writer.write("Device: " + model + "\n");
                writer.write("App version: " + (info == null ? "(null)" : info.versionCode) + "\n");

                char[] buffer = new char[10000];
                do {
                    int n = reader.read(buffer, 0, buffer.length);
                    if (n == -1)
                        break;
                    writer.write(buffer, 0, n);
                } while (true);

                reader.close();
                writer.close();
            } catch (IOException e) {
                if (writer != null)
                    try {
                        writer.close();
                    } catch (IOException e1) {
                    }
                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }

                // You might want to write a failure message to the log here.
                return null;
            }
        }
        return fullName;
    }


    private void sendLogFile() {
        String fullName = extractLogToFile();
        if (fullName == null) {
            Toast.makeText(CrashHandlerActivity.this, "Log file not created ", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Base App error log file");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + fullName));
        intent.putExtra(Intent.EXTRA_TEXT, sError); // do this so some email clients don't complain about empty body.
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendLogButton)
            sendLogFile();
        else if (v.getId() == R.id.closeButton) {
            //Can not be called to deliver a result at android.app.Activity.finishAffinity
            setResult(Activity.RESULT_CANCELED);
            finishAffinity();
            System.exit(0);
        }

        // respond to button clicks in your UI
    }

    @Override
    public void onBackPressed() {
        startMainActiviy();
    }

    private void startMainActiviy() {
        Intent clMainIntent = new Intent(this, LoginActivity.class);
        clMainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(clMainIntent);

    }
}

