package com.example.auxilio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnCheck);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa ps = getData();
                if(ps.getNasc() != null && ps.getCpf() != 0 && ps.getRenda() != 0) {
                    if(check(ps)){
                        ps = calc(ps);
                        toResultActivity(ps);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Pedido negado", Toast.LENGTH_LONG);
                        toast.setMargin(0,0);
                        toast.show();
                    }

                }
            }
        });
    }

    private Pessoa getData (){
        EditText cpf = (EditText) findViewById(R.id.txtCpf);
        EditText nasc = (EditText) findViewById(R.id.txtNasc);
        EditText renda = (EditText) findViewById(R.id.txtRenda);
        Pessoa ps = new Pessoa(0, null, 0, null, 0);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ps.setCpf(Long.parseLong(cpf.getText().toString()));
            ps.setNasc(df.parse(nasc.getText().toString()));
            ps.setRenda((float) Double.parseDouble(renda.getText().toString()));
            System.out.println(ps.getNasc());
        }catch (NumberFormatException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }catch (ParseException pe){
            Toast toast = Toast.makeText(getApplicationContext(), "Data incorreta", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }catch (NullPointerException npe){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }

        return ps;
    }

    private void toResultActivity(Pessoa ps){
        System.out.println("teste");
        Intent intent = new Intent(MainActivity.this, result.class);
        Bundle b = new Bundle();
        b.putDouble("saldo", ps.getSaldo());
        b.putString("data", ps.getData().toString());

        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
    private Pessoa calc(Pessoa ps){
        Calendar cal = Calendar.getInstance();
        cal.setTime(ps.getNasc());
        cal.add(Calendar.DATE, 20);
        cal.set(Calendar.YEAR, 2020);

        String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
        double money = (ps.getRenda()/100)*70;
        if(money > 475){
            money = 475.00;
        }
        ps.setData(date);
        ps.setSaldo((float) money);
        return ps;
    }
    private boolean check(Pessoa ps){
        boolean valid = true;
        Calendar cal = Calendar.getInstance();
        cal.setTime(ps.getNasc());
        int years = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if(years < 18) {
            valid = false;
        }

        if(ps.getRenda() > 5000){
            valid = false;
        }
        return valid;
    }
}
