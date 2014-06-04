package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnUstawDateICzas = (Button) findViewById(R.id.btnUstawDateCzas);
        Button btnPokazZaplanowaneBiegi=(Button) findViewById(R.id.btnPokazZaplanowaneBiegi);
        Button btnBiegnij=(Button)findViewById(R.id.btnBiegnij);

        btnUstawDateICzas.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent = new Intent(view.getContext(), UstawDateCzasBiegu.class);
                        startActivity(myIntent);
                    }

                }
                );

        btnPokazZaplanowaneBiegi.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent = new Intent(view.getContext(), ZaplanowaneBiegi.class);
                        startActivity(myIntent);
                    }

                }
                );

        btnBiegnij.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent = new Intent(view.getContext(), dystans.class);
                        startActivity(myIntent);
                    }

                }
                );


    }
}
