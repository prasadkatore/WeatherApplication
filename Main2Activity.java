package com.example.prasad.firstweatyahoo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.firstweatyahoo.Common.Common;
import com.example.prasad.firstweatyahoo.Helper.Helper;
import com.example.prasad.firstweatyahoo.Model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class Main2Activity extends AppCompatActivity implements LocationListener {


    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtCelsius, txtSunrise, txtSunset;
    ImageView imageView;
    EditText editcity;


    LocationManager locationManager;
    String provider;
    static double lat;
    static double lng;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    int MY_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtCity = (TextView) findViewById(R.id.txtcity);
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtSunrise = (TextView) findViewById(R.id.txtSunrise);
        txtSunset = (TextView) findViewById(R.id.txtSunset);
       editcity = (EditText) findViewById(R.id.editcity);
        //editcity = (EditText) findViewById(R.id.editcity);
        imageView = (ImageView) findViewById(R.id.imageView);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, MY_PERMISSION);
            return;
        }*/
       // lastLocation();



    }

    protected void lastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, MY_PERMISSION);
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        lat = location.getLatitude();
        lng = location.getLongitude();

        Log.i("Latitude", String.valueOf(lat));
        Log.i("Longitude", String.valueOf(lng));
        new GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));

    }

    public void refresh(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        lat = location.getLatitude();
        lng = location.getLongitude();

        Log.i("Latitude", String.valueOf(lat));
        Log.i("Longitude", String.valueOf(lng));
        new GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));

    }
    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, MY_PERMISSION);
        }
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, MY_PERMISSION);
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        lat=location.getLatitude();
        lng=location.getLongitude();
        new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));
        //Log.i("Latitude", String.valueOf(lat));
        //Log.i("Longitude", String.valueOf(lng));
    }
    public void Getwetheredit(String lat,String lng)
    {
        new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {




    }
    public  void getWeatherInfo(View view)
    {
        String lat1 = null;
        String lng1=null;

        OpenWeatherMap weatherInfo =new OpenWeatherMap();
        try{
            if (editcity.getText().toString().isEmpty())
            {
               Toast.makeText(this,"Please city name", Toast.LENGTH_SHORT).show();
              //  weatherTextview.setText("");

            }else {
                String weatherApiDetails = new GetWeather().execute("http://api.openweathermap.org/data/2.5/weather?q=" + editcity.getText().toString() + "&apikey=611d604ee5fa959fc9c04f5459569200").get();
                Log.i("Weather Api Info",weatherApiDetails);

                JSONObject jsonObject = new JSONObject(weatherApiDetails);
                String coord = jsonObject.getString("coord");
                JSONArray coorarray = new JSONArray(coord);

                for (int i = 0; i < coorarray.length(); i++) {
                    JSONObject arrayObj = coorarray.getJSONObject(i);
                    lat1 = arrayObj.getString("lat");
                    lng1 = arrayObj.getString("lon");

                }

                Getwetheredit(lat1,lng1);
                //weatherTextview.setText("Latitude:"+lat+"\n"+"Longitude"+lon);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class GetWeather extends AsyncTask<String,Void,String>
    {
        ProgressDialog pd=new ProgressDialog(Main2Activity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));
            if(s.contains("Error:not found city"))
            {
                pd.dismiss();
                return;
            }
            Gson gson=new Gson();
            Type mType=new TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap=gson.fromJson(s,mType);
            pd.dismiss();

            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
            txtLastUpdate.setText(String.format("Last Updated:%s", Common.getDateNow()));
            txtDescription.setText("Status               :"+String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
            txtHumidity.setText(" Humidity        :"+String.format("%d%%",openWeatherMap.getMain().getHumidity()));
            txtSunrise.setText("Sunrise            :"+String.format("%s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise())));
            txtSunset.setText("Sunset             :"+String.format("%s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset())));
            double tempe = (int) openWeatherMap.getMain().getTemp();
            tempe = tempe - 273.15;
            txtCelsius.setText("Temperature  :" + String.format("%.2f ℃", tempe));
            Picasso.with(Main2Activity.this)
                    .load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon()))
                    .into(imageView);

        }

        @Override
        protected String doInBackground(String... params) {
            String stream;
            String urlString=params[0];

            Helper http=new Helper();
            stream =http.getHttpDate(urlString);    
            return stream;


        }
    }



}

