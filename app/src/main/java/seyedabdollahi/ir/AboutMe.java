package seyedabdollahi.ir;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        // فعال کردن گزینه برگشت به صفحه قبلی
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //Set font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    // تابع ارسال ایمیل
    public void SendingEmail(View v) {
        Intent SendEmail = new Intent(Intent.ACTION_SEND);
        SendEmail.setType("plain/text");
        SendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"rezaab1375s@gmail.com"});
        SendEmail.putExtra(Intent.EXTRA_SUBJECT, "برنامه تبدیل مبنا");
        startActivity(SendEmail);
    }

    // تابع باز کردن صفحه برنامه در مایکت
    public void OpenPageinMyket(View v) {
        Intent OpenMyket = new Intent(Intent.ACTION_VIEW);

        if(getPackageManager().getLaunchIntentForPackage("ir.mservices.market") == null){
            OpenMyket.setData(Uri.parse("http://myket.ir/app/" + "seyedabdollahi.ir.base"));
            startActivity(OpenMyket);
        }
        else {
            OpenMyket.setPackage("ir.mservices.market");
            OpenMyket.setData(Uri.parse("http://myket.ir/app/" + "seyedabdollahi.ir.base"));
            startActivity(OpenMyket);
        }

    }


    // تابع باز کردن وبسایت
    public void OpenWebsite(View v){
        Intent openWebsit = new Intent(Intent.ACTION_VIEW);

        openWebsit.setData(Uri.parse("http://seyedabdollahi.ir"));
        startActivity(openWebsit);
    }

    // تابع باز کردن صفحه برنامه در بازار
    public void OpenPageinBazaar(View v){
        Intent OpenBazaar = new Intent(Intent.ACTION_VIEW);
        OpenBazaar.setData(Uri.parse("bazaar://details?id=" + "seyedabdollahi.ir.base"));
        OpenBazaar.setPackage("com.farsitel.bazaar");
        startActivity(OpenBazaar);
    }

    // تابع ارتباط با برنامه نویس در تلگرام
    public void OpenTelegramDeveloper(View v){
        Intent OpenTelegram = new Intent(Intent.ACTION_VIEW);
        OpenTelegram.setData(Uri.parse("https://telegram.me/reza7516"));
        startActivity(OpenTelegram);
    }

    //تابع نظر مستقیم در بازار
    public void AddCommentBazaar(View v){
        Intent AddComment = new Intent(Intent.ACTION_EDIT);
        AddComment.setData(Uri.parse("bazaar://details?id=" + "seyedabdollahi.ir.base"));
        AddComment.setPackage("com.farsitel.bazaar");
        startActivity(AddComment);
    }

    // تابع نظر مستقیم در مایکت
    public void AddCommentMyket(View v){

        if(getPackageManager().getLaunchIntentForPackage("ir.mservices.market") == null){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("خطا")
                    .setMessage("برنامه مایکت در دستگاه شما نصب نمی باشد. مایل به دانلود مایکت هستید؟")
                    .setPositiveButton("بله" , new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent installmyket = new Intent(Intent.ACTION_VIEW);

                            if(getPackageManager().getLaunchIntentForPackage("com.android.chrome") == null){
                                installmyket.setData(Uri.parse("https://www.myket.ir/download"));
                                startActivity(installmyket);
                            }
                            else {
                                installmyket.setData(Uri.parse("https://www.myket.ir/download"));
                                installmyket.setPackage("com.android.chrome");
                                startActivity(installmyket);
                            }

                        }
                    })
                    .setNegativeButton("بی خیال", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            Intent AddComment = new Intent(Intent.ACTION_VIEW);
            AddComment.setData(Uri.parse("myket://comment?id=" + "seyedabdollahi.ir.base"));
            AddComment.setPackage("ir.mservices.market");
            startActivity(AddComment);
        }
    }

    // تابع برگشت به صفحه قبل
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
