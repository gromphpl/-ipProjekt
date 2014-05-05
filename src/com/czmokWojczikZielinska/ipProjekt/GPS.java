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

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class GPS extends Activity {

    private TextView tvProvider;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvInformations;
    String czas;
    String data;
    int j=0;
    double wynik=0;

    private LocationManager locationManager;
    private Location savedLocation = null;
    private LocationListener locationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}

        public void onLocationChanged(Location location) {
            j++;
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
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            tvProvider.setText("GPS wlaczaony");
        else
            tvProvider.setText("Gps nie wlaczony,prosze wlaczyc");
        Calendar kal=Calendar.getInstance();
        int dzien=kal.get(Calendar.DATE);
        int miesiac=kal.get(Calendar.MONTH);
        int rok=kal.get(Calendar.YEAR);
        int godzina=kal.get(Calendar.HOUR);
        int minuta=kal.get(Calendar.MINUTE);

        data=dzien+"."+miesiac+"."+rok;
        czas=godzina+":"+minuta;
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
        String latitude = "Szerokosc: ";
        String longitude = "Dlugosc: ";
        if (location != null) {
            latitude += location.getLatitude();
            longitude += location.getLongitude();
            tvLatitude.setText(latitude);
            tvLongitude.setText(longitude);
        }
    }
    public double liczSume(double l)

    {
        wynik=wynik+l;
        return wynik;

    }
    private void showAdditionalInfo(Location location) {
        double wynik=0;
        double[] tab=new double[5];
        String infos = "Dystans";
        if (savedLocation == null || location == null) {
            infos += "can't calculate";
        } else {
            infos += savedLocation.distanceTo(location) + "m\n";
            infos += "Dokladnosc ";
            infos += location.getAccuracy() + "m \n";
            infos += "Last fix: ";
            infos += new Date(location.getTime()) + "\n";
            infos += "Predkosc ";
            infos += location.getSpeed() + "m/s";
            // wynik=wynik+location.getSpeed();
            liczSume(location.getSpeed());

        }
        tvInformations.setText(infos);

        //MySQLiteHelper db=new MySQLiteHelper(this);
        //Bieg b=new Bieg();
        //b.setDataBiegu(data);
        //b.setCzasBiegu(czas);
        //db.dodajRekordBieg(b);
    }

}
