package com.example.auxilio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setData();
    }

    private void setData(){
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Bundle b = getIntent().getExtras();
            double saldo = b.getDouble("saldo");
            cal.setTime(df.parse(b.getString("data")));
            String date = cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
            TextView txtSaldo = (TextView) findViewById(R.id.txtSaldo);
            TextView txtDate = (TextView) findViewById(R.id.txtPayDate);

            txtSaldo.setText("R$ " +saldo);
            txtDate.setText(date);
        }catch (ParseException pe){
            Toast toast = Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }

    }
}
