package com.example.weatherappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    URL url;
    EditText zipEntry;
    TextView weather;
    TextView dateText;
    TextView dateText1;
    TextView dateText2;
    TextView dateText3;
    TextView dateText4;
    TextView dateText5;
    TextView tempLowText1;
    TextView tempLowText2;
    TextView tempLowText3;
    TextView tempLowText4;
    TextView tempLowText5;
    TextView tempHighText1;
    TextView tempHighText2;
    TextView tempHighText3;
    TextView tempHighText4;
    TextView tempHighText5;
    TextView quote;
    Button searchButton;
    JSONObject obj;
    ImageView weatherIcon;
    ImageView day1img;
    ImageView day2img;
    ImageView day3img;
    ImageView day4img;
    ImageView day5img;

    int temp;
    int tempLow1= Integer.MAX_VALUE;
    int tempLow2=Integer.MAX_VALUE;
    int tempLow3=Integer.MAX_VALUE;
    int tempLow4=Integer.MAX_VALUE;
    int tempLow5=Integer.MAX_VALUE;
    int tempHigh1;
    int tempHigh2;
    int tempHigh3;
    int tempHigh4;
    int tempHigh5;


    String mainIdea;
    String mainDay1;
    String mainDay2;
    String mainDay3;
    String mainDay4;
    String mainDay5;
    String mainDate;
    String date1;
    String date2;
    String date3;
    String date4;
    String date5;
    ArrayList<String> quotes=new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather=findViewById(R.id.id_main_weather);
        dateText=findViewById(R.id.id_main_date);
        dateText1=findViewById(R.id.id_main_date1);
        dateText2=findViewById(R.id.id_main_date2);
        dateText3=findViewById(R.id.id_main_date3);
        dateText4=findViewById(R.id.id_main_date4);
        dateText5=findViewById(R.id.id_main_date5);
        tempLowText1= findViewById(R.id.id_main_tempLow1);
        tempLowText2=findViewById(R.id.id_main_tempLow2);
        tempLowText3=findViewById(R.id.id_main_tempLow3);
        tempLowText4=findViewById(R.id.id_main_tempLow4);
        tempLowText5=findViewById(R.id.id_main_tempLow5);
        tempHighText1=findViewById(R.id.id_main_temHigh1);
        tempHighText2=findViewById(R.id.id_main_tempHigh2);
        tempHighText3=findViewById(R.id.id_main_tempHigh3);
        tempHighText4=findViewById(R.id.id_main_tempHigh4);
        tempHighText5=findViewById(R.id.id_main_tempHigh5);
        zipEntry=findViewById(R.id.id_main_zipEntry);
        searchButton=findViewById(R.id.button);
        weatherIcon=findViewById(R.id.id_main_weatherIcon);
        day1img=findViewById(R.id.id_main_day1img);
        day2img=findViewById(R.id.id_main_day2img);
        day3img=findViewById(R.id.id_main_day3img);
        day4img=findViewById(R.id.id_main_day4img);
        day5img=findViewById(R.id.id_main_day5img);
        quote=findViewById(R.id.id_main_quote);
        quotes.add("It's splashing like Curry!");
        quotes.add("It's shining better than Michael Jordan's head!");
        quotes.add("Ice in my veins.");
        quotes.add("Great D by the clouds");

        zipEntry.setHint("Enter ZipCode");
        AsyncClass task = new AsyncClass("08824");
        task.execute();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncClass task = new AsyncClass(String.valueOf(zipEntry.getText()));
                task.execute();
            }
        });
    }

    public class AsyncClass extends AsyncTask<JSONObject, Integer, JSONObject> {
        String zipCode;
        public AsyncClass(String zip){
            super();
            zipCode=zip;
        }
        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            try {
                url= new URL("https://api.openweathermap.org/data/2.5/forecast?zip="+zipCode+",us&lat=44.34&lon=10.99&appid=7b903306ba2385da359292f37c6aadb3&units=imperial");
                URLConnection connection = url.openConnection();
                InputStream inputStream= connection.getInputStream();
                BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));
                String data="";
                String text="";
                while((text=reader.readLine())!=null){
                    data+=text;

                }
                obj= new JSONObject(data);
                JSONArray array= obj.getJSONArray("list");
                JSONObject todayInfo= array.getJSONObject(0);

                JSONObject day1Info= array.getJSONObject(8);
                JSONObject day2Info= array.getJSONObject(16);
                JSONObject day3Info= array.getJSONObject(24);
                JSONObject day4Info= array.getJSONObject(32);
                JSONObject day5Info= array.getJSONObject(39);
                JSONObject main= todayInfo.getJSONObject("main");
                JSONObject main1= day1Info.getJSONObject("main");
                JSONObject main2= day2Info.getJSONObject("main");
                JSONObject main3= day3Info.getJSONObject("main");
                JSONObject main4= day4Info.getJSONObject("main");
                JSONObject main5= day5Info.getJSONObject("main");
                mainDate=todayInfo.getString("dt_txt").split(" ")[0];
                date1=day1Info.getString("dt_txt").split(" ")[0];
                date2=day2Info.getString("dt_txt").split(" ")[0];
                date3=day3Info.getString("dt_txt").split(" ")[0];
                date4=day4Info.getString("dt_txt").split(" ")[0];
                date5=day5Info.getString("dt_txt").split(" ")[0];
                temp=main.getInt("temp");
                for (int i = 2; i < 8; i++) {
                        //Log.d("TempLow", String.valueOf(array.getJSONObject(i).getJSONObject("main").getInt("temp")));
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") < tempLow1) {
                            tempLow1 = array.getJSONObject(i).getJSONObject("main").getInt("temp");
                        }
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") > tempHigh1) {
                            tempHigh1 = array.getJSONObject(i).getJSONObject("main").getInt("temp");

                        }
                    }
                for (int x = 8; x < 17; x++) {
                    Log.d("TempLow", String.valueOf(array.getJSONObject(x).getJSONObject("main").getInt("temp")));
                    if (array.getJSONObject(x).getJSONObject("main").getInt("temp") <tempLow2) {
                        tempLow2 = array.getJSONObject(x).getJSONObject("main").getInt("temp");
                    }
                    if (array.getJSONObject(x).getJSONObject("main").getInt("temp") > tempHigh2) {
                        tempHigh2 = array.getJSONObject(x).getJSONObject("main").getInt("temp");

                    }
                }for (int i = 16; i < 24; i++) {
                    //Log.d("TempLow", String.valueOf(array.getJSONObject(i).getJSONObject("main").getInt("temp")));
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") < tempLow3) {
                        tempLow3 = array.getJSONObject(i).getJSONObject("main").getInt("temp");
                    }
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") > tempHigh3) {
                        tempHigh3 = array.getJSONObject(i).getJSONObject("main").getInt("temp");

                    }
                }
                for (int i = 24; i < 32; i++) {
                    //Log.d("TempLow", String.valueOf(array.getJSONObject(i).getJSONObject("main").getInt("temp")));
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") < tempLow4) {
                        tempLow4 = array.getJSONObject(i).getJSONObject("main").getInt("temp");
                    }
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") > tempHigh4) {
                        tempHigh4 = array.getJSONObject(i).getJSONObject("main").getInt("temp");

                    }
                }
                for (int i = 32; i < 40; i++) {
                    //Log.d("TempLow", String.valueOf(array.getJSONObject(i).getJSONObject("main").getInt("temp")));
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") < tempLow5) {
                        tempLow5 = array.getJSONObject(i).getJSONObject("main").getInt("temp");
                    }
                    if (array.getJSONObject(i).getJSONObject("main").getInt("temp") > tempHigh5) {
                        tempHigh5 = array.getJSONObject(i).getJSONObject("main").getInt("temp");

                    }
                }
                mainIdea=todayInfo.getJSONArray("weather").getJSONObject(0).getString("main");
                mainDay1= day1Info.getJSONArray("weather").getJSONObject(0).getString("main");
                mainDay2= day2Info.getJSONArray("weather").getJSONObject(0).getString("main");
                mainDay3= day3Info.getJSONArray("weather").getJSONObject(0).getString("main");
                mainDay4= day1Info.getJSONArray("weather").getJSONObject(0).getString("main");
                mainDay5= day1Info.getJSONArray("weather").getJSONObject(0).getString("main");

                Log.d("Temp", mainDay1);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return obj;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            weather.setText(String.valueOf(temp)+"F");
            dateText.setText(mainDate);
            dateText1.setText(date1);
            dateText2.setText(date2);
            dateText3.setText(date3);
            dateText4.setText(date4);
            dateText5.setText(date5);
            tempLowText1.setText(String.valueOf(tempLow1)+"F");
            tempLowText2.setText(String.valueOf(tempLow2)+"F");
            tempLowText3.setText(String.valueOf(tempLow3)+"F");
            tempLowText4.setText(String.valueOf(tempLow4)+"F");
            tempLowText5.setText(String.valueOf(tempLow5)+"F");
            tempHighText1.setText(String.valueOf(tempHigh1)+"F");
            tempHighText2.setText(String.valueOf(tempHigh2)+"F");
            tempHighText3.setText(String.valueOf(tempHigh3)+"F");
            tempHighText4.setText(String.valueOf(tempHigh4)+"F");
            tempHighText5.setText(String.valueOf(tempHigh5)+"F");
            quote.setText("If you want to see the sunshine, you have to weather the storm. -Frank Lane");


            if(mainIdea.equals("Rain")){
                quote.setText(quotes.get(0));
                weatherIcon.setImageResource(R.drawable.rainyweather);
            }
            else if(mainIdea.equals("Clear")){
                quote.setText(quotes.get(1));
                weatherIcon.setImageResource(R.drawable.sunnyimg);
            } else if (mainIdea.equals("Clouds")) {
                quote.setText(quotes.get(3));
                weatherIcon.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainIdea.equals("Snow")){
                quote.setText(quotes.get(2));
                weatherIcon.setImageResource(R.drawable.snowimg);

            }
            if(mainDay1.equals("Rain")){
                day1img.setImageResource(R.drawable.rainyweather);
            }
            else if(mainDay1.equals("Clear")){
                day1img.setImageResource(R.drawable.sunnyimg);
            } else if (mainDay1.equals("Clouds")) {
                day1img.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainDay1.equals("Snow")){
                day1img.setImageResource(R.drawable.snowimg);

            }
            if(mainDay2.equals("Rain")){
                day2img.setImageResource(R.drawable.rainyweather);
            }
            else if(mainDay2.equals("Clear")){
                day2img.setImageResource(R.drawable.sunnyimg);
            } else if (mainDay2.equals("Clouds")) {
                day2img.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainDay2.equals("Snow")){
                day2img.setImageResource(R.drawable.snowimg);

            }
            if(mainDay3.equals("Rain")){
                day3img.setImageResource(R.drawable.rainyweather);
            }
            else if(mainDay3.equals("Clear")){
                day3img.setImageResource(R.drawable.sunnyimg);
            } else if (mainDay3.equals("Clouds")) {
                day3img.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainDay3.equals("Snow")){
                day3img.setImageResource(R.drawable.snowimg);

            }
            if(mainDay4.equals("Rain")){
                day4img.setImageResource(R.drawable.rainyweather);
            }
            else if(mainDay4.equals("Clear")){
                day4img.setImageResource(R.drawable.sunnyimg);
            } else if (mainDay4.equals("Clouds")) {
                day4img.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainDay4.equals("Snow")){
                day4img.setImageResource(R.drawable.snowimg);

            }
            if(mainDay5.equals("Rain")){
                day5img.setImageResource(R.drawable.rainyweather);
            }
            else if(mainDay5.equals("Clear")){
                day5img.setImageResource(R.drawable.sunnyimg);
            } else if (mainDay5.equals("Clouds")) {
                day5img.setImageResource(R.drawable.cloudyimg);
            }
            else if(mainDay5.equals("Snow")){
                day5img.setImageResource(R.drawable.snowimg);
            }



        }
    }
}

