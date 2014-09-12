package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Anna on 11.09.14.
 */
public class UzpupelnijProfil extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.uzupelnij_profil);

        EditText etImie=(EditText)findViewById(R.id.etImie);
        EditText etWiek=(EditText)findViewById(R.id.etWiek);
        EditText etWaga=(EditText)findViewById(R.id.etWaga);
        EditText etWzrost=(EditText)findViewById(R.id.etWzrost);
        Button btnZatwierdz=(Button)findViewById(R.id.btnZatwierdzZmiane);
        MySQLiteHelper db=new MySQLiteHelper(getApplicationContext());

        ile=db.getDaneOsobyCount();


        if(ile>0)
        {
            DaneOsoby d=db.showOneDaneOsoby(1);
            etImie.setText(d.getImie());
            etImie.setEnabled(false);
            etWiek.setText(String.valueOf(d.getWiek()));
            etWaga.setText(String.valueOf(d.getWaga()));
            etWzrost.setText(String.valueOf(d.getWzrost()));
        }
        else
        {
            etImie.setEnabled(true);
        }

        btnZatwierdz.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                            MySQLiteHelper db1=new MySQLiteHelper(getApplicationContext());
                            EditText etImie=(EditText)findViewById(R.id.etImie);
                            EditText etWiek=(EditText)findViewById(R.id.etWiek);
                            EditText etWaga=(EditText)findViewById(R.id.etWaga);
                            EditText etWzrost=(EditText)findViewById(R.id.etWzrost);

                           if(ile>0)
                           {

                               DaneOsoby d=new DaneOsoby();
                               d.setWzrost(Integer.parseInt(etWzrost.getText().toString()));
                               d.setWaga(Integer.parseInt(etWaga.getText().toString()));
                               d.setWiek(Integer.parseInt(etWiek.getText().toString()));
                               d.setImie(etImie.getText().toString());
                               d.setId(1);
                               db1.updateDaneOsoby(d);
                           }
                            else
                           {
                               DaneOsoby d=new DaneOsoby(etImie.getText().toString(),Integer.parseInt(etWiek.getText().toString()),Integer.parseInt(etWaga.getText().toString()),Integer.parseInt(etWzrost.getText().toString()));
                               db1.dodajRekordDaneOsoby(d);
                           }
                        Intent myIntent = new Intent(view.getContext(), DaneOsobowe.class);
                        finish();
                        startActivity(myIntent);
                        }


                }
                );
    }
    int ile=0;
}
