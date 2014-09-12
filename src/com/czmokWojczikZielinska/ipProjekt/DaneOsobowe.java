package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Anna on 11.09.14.
 */
public class DaneOsobowe extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dane_osobowe);

        Button btnStworzProfil=(Button)findViewById(R.id.btnStworzProfil);
        TextView tvImie=(TextView)findViewById(R.id.tvImie);
        TextView tvWiek=(TextView)findViewById(R.id.tvWiek);
        TextView tvWaga=(TextView)findViewById(R.id.tvWaga);
        TextView tvWzrost=(TextView)findViewById(R.id.tvWzrost);

        MySQLiteHelper db=new MySQLiteHelper(this.getApplicationContext());
        int ile=db.getDaneOsobyCount();
        if(ile>0)
        {
         DaneOsoby dane=db.showOneDaneOsoby(1);
          tvImie.setText(dane.getImie());
          tvWiek.setText(String.valueOf(dane.getWiek()));
          tvWaga.setText(String.valueOf(dane.getWaga()));
          tvWzrost.setText(String.valueOf(dane.getWzrost()));
        }


        btnStworzProfil.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent = new Intent(view.getContext(), UzpupelnijProfil.class);
                        finish();
                        startActivity(myIntent);
                    }

                }
                );
    }
}
