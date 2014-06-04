package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created with IntelliJ IDEA.
 * User: magda
 * Date: 09.04.14
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GPS extends Activity {

    private TextView tvProvider;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvInformations;
    private TextView v;
    private TextView s;
    private TextView t;
    private TextView end2;
    private Button koniec;
    double j=1.0;
    boolean dziala=true;
    double predkosc=0;
    double wynik=0;
    dystans d=new dystans();
    //droga pobierana od użytkownika-dystans do przebiegnięcia.
    int droga=d.dalej;
    double czas=0;
    String czasBiegu="";
    String dataBiegu="";


    private LocationManager locationManager;
    private Location savedLocation = null;
    private LocationListener locationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}

        public void onLocationChanged(Location location) {

            showLocation(location);
            showAdditionalInfo(location);
            if (savedLocation == null)
                savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps2);

        tvProvider = (TextView) findViewById(R.id.tvProvider);
        koniec=(Button)findViewById(R.id.button1);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        v = (TextView) findViewById(R.id.textView1);
        s = (TextView) findViewById(R.id.textView);
        t= (TextView) findViewById(R.id.textView2);
        end2 = (TextView) findViewById(R.id.textView2);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        { tvProvider.setText("GPS włączony.");
        Calendar kal=Calendar.getInstance(Locale.getDefault());
        int dzien=kal.get(Calendar.DATE);
        int miesiac=kal.get(Calendar.MONTH);
        int rok=kal.get(Calendar.YEAR);
        int godzina=kal.get(Calendar.HOUR);
        int minuta=kal.get(Calendar.MINUTE);

        dataBiegu=dzien+"."+miesiac+"."+rok;
        czasBiegu=godzina+":"+minuta;
        }
        else
            tvProvider.setText("Gps nie jest włączony, proszę włączyć.");


        koniec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                predkosc=wynik/(j-1);
                czas=droga/predkosc;
                 // String czas2=Double.toString(czas);
                // s.setText("Średnia prędkość: "+predkosc);
                s.setText("Dystans: "+droga+" Średnia prędkość: "+predkosc);
                t.setText("Czas: "+ czas);
                //dodanie do bazy danych wartosci po przycisnieciu przycisku koniec
                dodajDoBazy(czas, droga, predkosc);




            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000, 2, locationListener);
    }

    @Override
    protected void onStop() {
        locationManager.removeUpdates(locationListener);
        super.onStop();
    }

    private void showLocation(Location location) {
        String latitude = getString(R.string.width);
        String longitude = getString(R.string.lenght);
        if (location != null) {
            latitude += location.getLatitude();
            longitude += location.getLongitude();
            tvLatitude.setText(latitude);
            tvLongitude.setText(longitude);
        }
    }
    public void dodajDoBazy(double t,int d, double p)
    {
        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notif);
        r.play();
        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(this.getApplicationContext().VIBRATOR_SERVICE);
        v2.vibrate(500); //wibracja przez 500 ms
        MySQLiteHelper db=new MySQLiteHelper(this);
        Bieg b=new Bieg();
        // b.setDataBiegu(data);
        b.setCzasBiegu(czasBiegu);
        b.setDataBiegu(dataBiegu);
        b.setPrzebiegnietyDystans(d);
        b.setPredkoscBiegu(p);
        b.setCzasPrzebiegniecia(t);
        db.dodajRekordBieg(b);
    }
    private void showAdditionalInfo(Location location) {
        String infos = "Dystans: ";
        if (savedLocation == null || location == null) {
            infos += "can't calculate";
        } else {
            infos += savedLocation.distanceTo(location) + "m\n";
           /* if(savedLocation.distanceTo(location)> droga+(droga*0.20));
            {
                predkosc=wynik/(j-1);
                v.setText("Średnia prędkość: "+predkosc);
                s.setText("Dystans: "+droga);
                t.setText("Czas: "+(droga/predkosc));


            }
            */
            //infos += "Dokładność ";
            //infos += location.getAccuracy() + "m \n";
            infos += "Last fix: ";
            infos += new Date(location.getTime()) + "\n";
            infos += "Prędkość:  ";
            infos += location.getSpeed() + "m/s";


            j++;
            wynik=wynik+location.getSpeed();
            double wynik2;
            wynik2= wynik/(j-1);
            if((wynik2)>(droga+(droga*0.1)))
            {
            //end.setText("wynik: "+ wynik+" licznik: "+j);

                czas=0;
                predkosc=0;
                try
                {
                predkosc=wynik/(j-1);
                czas=droga/predkosc;
                }
                catch (Exception ex){}

            // String czas2=Double.toString(czas);
            // s.setText("Średnia prędkość: "+predkosc);
            s.setText("Dystans: "+droga+" Średnia prędkość: "+predkosc);
            t.setText("Czas: "+ czas);
            //dodanie do bazy danych wartosci po przycisnieciu przycisku koniec
            dodajDoBazy(czas, droga, predkosc);
            }


        }
        tvInformations.setText(infos);


    }

}
