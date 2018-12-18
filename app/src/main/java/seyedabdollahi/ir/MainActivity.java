package seyedabdollahi.ir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import seyedabdollahi.ir.Adapters.BaseAdapter;
import seyedabdollahi.ir.EventBus.ChangeNumber;
import seyedabdollahi.ir.Models.Base;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Base base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        configBases();
    }

    private void findViews(){
        recyclerView = findViewById(R.id.recycler);
    }

    private void configBases(){
        List<Base> bases = new ArrayList<>();
        base = new Base();
        base.setBase("BIN");
        base.setMaxValue('1');
        bases.add(base);
        base = new Base();
        base.setBase("OCT");
        bases.add(base);
        base = new Base();
        base.setBase("DEC");
        bases.add(base);
        base = new Base();
        base.setBase("HEX");
        bases.add(base);

        changeRecycle(bases);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangeNumber event) {
        List<Base> bases = event.getBases();
        bases.get(1).setValue("YES");
        bases = computeBases(event.getBases() , event.getPosition());
        changeRecycle(bases);
    }

    private void changeRecycle(List<Base> bases){
        BaseAdapter baseAdapter = new BaseAdapter(bases);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(baseAdapter);
    }

    private List<Base> computeBases(List<Base> bases , int position){
        String bin;
        String oct;
        String dec = "";
        String hex;
        Log.d("TAG" , "_______________________________");
        Log.d("TAG" , "number: " + bases.get(position).getValue());
        try {
            switch (position){
                case 0:{
                    dec = Integer.toString(Integer.parseInt(bases.get(position).getValue() , 2));
                    break;
                }
                case 1:{
                    dec = Integer.toString(Integer.parseInt(bases.get(position).getValue() , 8));
                    break;
                }
                case 2:{
                    dec = Integer.toString(Integer.parseInt(bases.get(position).getValue() , 10));
                    break;
                }
                case 3:{
                    dec = Integer.toString(Integer.parseInt(bases.get(position).getValue() , 16));
                    break;
                }
            }
            Log.d("TAG" , "BASE10: " + dec);
            Log.d("TAG" , "BASE2: " + Integer.toBinaryString(Integer.parseInt(dec)));
            Log.d("TAG" , "BASE8: " + Integer.toHexString(Integer.parseInt(dec)));
            Log.d("TAG" , "BASE16: " + Integer.toOctalString(Integer.parseInt(dec)));
        }catch (Exception e){
            Log.d("TAG" , "error computeBases");
        }
        bases.get(0).setValue(Integer.toBinaryString(Integer.parseInt(dec)));
        bases.get(1).setValue(Integer.toOctalString(Integer.parseInt(dec)));
        bases.get(2).setValue(dec);
        bases.get(3).setValue(Integer.toHexString(Integer.parseInt(dec)));
        return bases;
    }
}
