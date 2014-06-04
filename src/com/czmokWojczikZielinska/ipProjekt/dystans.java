package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: magda
 * Date: 08.05.14
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class dystans extends Activity {

        public static int dalej;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dystans2);
        Button start=  (Button)findViewById(R.id.start);
        final TextView zle=  (TextView)findViewById(R.id.zle);
        final EditText tekst= (EditText)findViewById(R.id.startbieg);
       // final int dystans;



        start.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {   try
                    {
                        String edystans=tekst.getText().toString();
                        int dystans=Integer.parseInt(edystans);
                        dalej=dystans;
                        Intent myIntent = new Intent(view.getContext(), GPS.class);
                        startActivity(myIntent);
                    }catch(Exception e)
                        {
                            tekst.setText("");
                            zle.setText("Została podana zła wartość, proszę wprowadzić ponownie.");

                        }

                    }

                }
                );
    }
}
