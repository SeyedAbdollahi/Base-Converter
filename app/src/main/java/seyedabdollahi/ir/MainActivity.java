package seyedabdollahi.ir;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    private EditText binValue;
    private EditText octValue;
    private EditText decValue;
    private EditText hexValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        configViews();
    }

    private void findViews(){
        binValue = findViewById(R.id.bin_base_value);
        octValue = findViewById(R.id.oct_base_value);
        decValue = findViewById(R.id.dec_base_value);
        hexValue = findViewById(R.id.hex_base_value);
    }

    private void configViews(){
        binValue.addTextChangedListener(binTextWatcher);
        octValue.addTextChangedListener(octTextWatcher);
        decValue.addTextChangedListener(decTextWatcher);
        hexValue.addTextChangedListener(hexTextWatcher);
    }

    private void computeBases(String value , String base){
        String number = value;
        String bin;
        String oct;
        String dec = "";
        String hex;
        Log.d("TAG" , "_______________________________");
        Log.d("TAG" , "number: " + number);
        if(value.equals("")){
            removeTextWatcher();
            binValue.setText("");
            octValue.setText("");
            decValue.setText("");
            hexValue.setText("");
            addTextWatcher();
        }else {
            try {
                switch (base){
                    case "BIN":{
                        dec = Integer.toString(Integer.parseInt(number , 2));
                        break;
                    }
                    case "OCT":{
                        dec = Integer.toString(Integer.parseInt(number , 8));
                        break;
                    }
                    case "DEC":{
                        dec = Integer.toString(Integer.parseInt(number , 10));
                        break;
                    }
                    case "HEX":{
                        dec = Integer.toString(Integer.parseInt(number , 16));
                        break;
                    }
                }
                bin = Integer.toBinaryString(Integer.parseInt(dec));
                oct = Integer.toOctalString(Integer.parseInt(dec));
                hex = Integer.toHexString(Integer.parseInt(dec));
                removeTextWatcher();
                binValue.setText(bin);
                octValue.setText(oct);
                decValue.setText(dec);
                hexValue.setText(hex);
                addTextWatcher();
                switch (base){
                    case "BIN":{
                        binValue.setSelection(bin.length());
                        break;
                    }
                    case "OCT":{
                        octValue.setSelection(oct.length());
                        break;
                    }
                    case "DEC":{
                        decValue.setSelection(dec.length());
                        break;
                    }
                    case "HEX":{
                        hexValue.setSelection(hex.length());
                        break;
                    }
                }
                setBlackColor();
            }catch (Exception e){
                Log.d("TAG" , "error computeBases: " + e.toString());
                setRedColor();
            }
        }
    }

    private void addTextWatcher(){
        binValue.addTextChangedListener(binTextWatcher);
        octValue.addTextChangedListener(octTextWatcher);
        decValue.addTextChangedListener(decTextWatcher);
        hexValue.addTextChangedListener(hexTextWatcher);
    }

    private void removeTextWatcher(){
        binValue.removeTextChangedListener(binTextWatcher);
        octValue.removeTextChangedListener(octTextWatcher);
        decValue.removeTextChangedListener(decTextWatcher);
        hexValue.removeTextChangedListener(hexTextWatcher);
    }

    private void setRedColor(){
        binValue.setTextColor(getResources().getColor(R.color.red));
        octValue.setTextColor(getResources().getColor(R.color.red));
        decValue.setTextColor(getResources().getColor(R.color.red));
        hexValue.setTextColor(getResources().getColor(R.color.red));
    }

    private void setBlackColor(){
        binValue.setTextColor(getResources().getColor(R.color.black));
        octValue.setTextColor(getResources().getColor(R.color.black));
        decValue.setTextColor(getResources().getColor(R.color.black));
        hexValue.setTextColor(getResources().getColor(R.color.black));
    }

    TextWatcher binTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("TAG" , "position: " + binValue.getSelectionStart());
            computeBases(binValue.getText().toString() , "BIN");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher octTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            computeBases(octValue.getText().toString() , "OCT");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher decTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            computeBases(decValue.getText().toString() , "DEC");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    TextWatcher hexTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            computeBases(hexValue.getText().toString() , "HEX");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    //Set font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
