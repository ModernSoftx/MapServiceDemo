# MapServiceDemo


Lab Google Map
Objectives:

    Display Google Map
    Zoom in/out programmatically
    Display zoom control
    Change views
    Add marker
    Animate while visiting to a new location
    Get the location of the map touched
    Geocoding


Preparedness: Don’t skip this section.

    Read the power point presentation, “set up environment for Google Map API” for a high-level view. No need to do anything till you read the next document.
    Refer https://developers.google.com/maps/documentation/android-sdk/start
    Now take actions to:
        Make sure your SDK Tools includes Google Play Services
        Make sure your emulator incorporating with Google APIs
        If graphics acceleration does not work, you might need to switch to Software graphics if your emulator has openGL issue
        [Caution] If you face issues such that some libraries are not consistent during the lab, then you might need to downgrade libraries to keep all libraries matched


Do not do the lab till you read/follow the preparedness instructions listed above.

Steps:
Step 1:  Create a project, MapServiceDemo, by using Google Maps Activity template
Step 1.1: Make sure Minimum SDK is greater than 26 and language is Java
This step may take a while.
Step 2: AndroidManifest.xml is open on the editor. Follow steps in TODO section to obtain an API key and put it in your local property file and copy it to the meta-data element beneath the TODO section to replace the “YOUR_API_KEY” wording. Make sure you leave the double quotes there.

Make sure you follow the instructions to obtain the API key

For more detail, see https://developers.google.com/maps/documentation/javascript/get-api-key
and view https://youtu.be/2_HZObVbe-g
Make sure you enable Map Service under APIs & Services. You will see “+ ENABLE APIS AND SERVICES” on the top. And then click on “Maps SDK for Android”







The basic idea:

First, just setup the key and then copy the key to the AndroidManifest.xml
Test the emulator if no map shows up, go to second step
Second, enable the Map service mentioned above.
Test the emulator if no map shows up, go to step 3
Third, Create Billing account.
If you don’t upgrade your account, and close your app after using it to prevent reaching the quota, there should be no charge based on Google’s announcement.
Make sure you read Google Maps Platform at https://developers.google.com/maps/documentation/android-sdk/overview?section=start for tutorials, usage and billing.
Please read the Google’s charge policy.
There are tutorials on the Map Service website before and after you log in to create your account.
After reading the information on the Google’s website, if you have concern on the charge and your credit card, please contact the instructor before you set up the account.
If you cannot set up the account (e.g., without a credit card, worry about the charging), please contact the instructor first to waive your lab.

After obtaining your key and put it in your manifest file, you can test your app.
By default, you will see Australia.


Step 3: Review two files
Step 3.1: activity_maps.xml under res/layout
In Code, you find a fragment is created to support and display a map defined in MapsActivity.
Step 3.2: build.gradle (Module:) under Gradle Scripts
You will find com.google.android.gms:play-services-maps is included in the dependencies and

one new plug in under plugins, com.google.android.libraries.mapsplatform.secrets-gradle-plugin.



Step 4: Review MapsActivity java code
onMapReady() is called when the map is ready or when the map is refreshed (such as within onRequestPermissionsResult()) and find Sydney with Latitude (-34) and Longitude (151). That is the default map.
Step 5: Open AndroidManifest.xml
Step 5.1: Review AndroidManifest.xml to see what network permissions are added.
Can you find it?
They can be found in the merged Manifest (not in the Text)
You will find “Merged Manifest” tab on the lower left-hand side of the editor. Click on it to open manifests to make sure org.apache.http.legacy library is also included.
Click on “Text” tab (next to “Merged Manifest” tab) to go back to text mode.

Step 6: As mentioned above you should see a map displayed on your emulator. If you have not run it, do so.
You are supposed to see a map with a location around Sydney as the code put in in onMapReady().  Congradulations!
If not works, make sure you follow the instructions listed above and check with the ppt discussed in class and check logcat for errors.
Step 7: You can click the marker to see the title, “Marker in Sydney”, if it has not shown.
Take a screen shot to show you can display the default map.
Step 8: You can keep clicking (for some device you need to click it twice) on the map to zoom in or move your map around (i.e., pane) but not zoom out (You can do it on a real device)
Take a screen shot to show you can move or zoom the map.
Step 9: Put the following statements into onMapReady() after mMap.moveCamera(…) in MapsActivity.java to change the type of map
// Other supported types include: MAP_TYPE_NORMAL,
// MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

Step 10: Test your app and take the screenshot of the satellite view.

Step 11: Comment out the line that set the type
//mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);



There are other map types such as Map_TYPE_HYBRID you can try.

So far, we show you how to control map type programmatically. You can also change it by set the map:mapType attribute to the fragment in activity_maps.xml


Actually, we will discuss how to set up attributes to the map fragment at next step.
Step 12: Add zoom control to the fragment
Open activity_maps.xml, and set map:uiZoomControls to be true as follows:
<fragment ….
map:uiZoomControls=”true”
/>
Step 13: Run the app and use the control (+  and -) on lower right hand corner on the emulator  to test it. Both of zoom in and zoom out work.
Step 14: You can also write code to provide zooming services.
Step 14.1: Remove map:uiZoomControls=”true” from the layout file
Step 14.2: Put the following code in MapsActivity java file
Step 14.2.1: import UiSettings (Notice U is in upper case) by import the following library

                       import com.google.android.gms.maps.UiSettings;

 

                              Step 14.2.2: Declare variable inside the class

                       UiSettings mapSettings;



Step 14.2.3: Put these two lines at the end of onMapReady(). That is, within the onMapReady() method and not out of it.
mapSettings = mMap.getUiSettings();
mapSettings.setZoomControlsEnabled(true);

Step 15: Run the app to verify the Zoom control works.
Before moving on to the next step, let us reverse back to Step 12 to keep the code simple. That is, use fragment attribute to control the Zoom control instead of using the code.
In other words, remove the java statements we put in Step 14 and put the attribute map:uiZoomControls=”true” back to the fragment.
Test it to make sure you do it right.
Step 16: Use GPS to find your current location (See https://www.youtube.com/watch?v=-8gg98ws2Eo to understand the Latitude and Longitude system)

For example, The Lat and Lng for Chicago is listed below:



City of Chicago: Latitude: 41.8781  Longitude: -87.6298



Negative numbers for W (west) or S (south)



Replace the location of Sydney by Chicago and zoom in to City level (10).
Thus, replace the following three lines of code in MapsActivity java file:
LatLng sydney = new LatLng(-34, 151);
mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

by

LatLng chicago = new LatLng(41.8781, -87.6298);   //Negative for W (west) or S (south)
mMap.addMarker(new MarkerOptions().position(chicago).title("Chicago, Illinois"));
mMap.moveCamera(CameraUpdateFactory.newLatLng(chicago));

Test your app.

Then add the following statement:

mMap.animateCamera(CameraUpdateFactory.zoomTo(10));  // 10 for City 15 for Streets

Test it out.

Now change 10 to 15

Test it out again to see any difference. You should see the map with street view.

Take a screen shot.

Step 17: You can comment out the mMap.animateCamera(CameraUpdateFactory.zoomTo(XX)); for simplicity.
Step 18: To get the latitude and longitude of a point that was touched
Put the following code in onMapReady() either after the statement mMap = googleMap; or after mMap.moveCamera(….)
That is, for example, if you put the code behind mMap = googleMap; then the cod should look like the follows:
mMap = googleMap;

mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
@Override
public void onMapClick(LatLng point){
Toast.makeText(getApplicationContext(), "onClick "+ point.latitude + "/" + point.longitude, Toast.LENGTH_LONG).show();
Log.d("Map Service", "Map clicked [" + point.latitude + " / "
+ point.longitude + " ] ");

    }
});

Of course, if you copy the code instead of typing in, you need to use ALT+Enter to include the import statements.
Step 19: Run your app and touch a location to see the Toast message on the screen (shown toward the bottom on my screen) and Map clicked information in Logcat
The most efficient way to see them is to make sure you open logcat first for information to populate (it might take a while) and then zoom in to street level and click on a location on the map. Then you will see the log event and Toast message.
Take a screen shot to show the Toast message
Now let us talk about geocoding. Geocode is the process to convert a textual based geographical location (such as a street address) to geographical coordinates expressed in terms of longitude and latitude. Of course, you can do it reversely.
Step 20: Using geocoding to find the location for a latitude and longitude of a location
Replace the code in public void onMapClick(LatLng point),which is inside mMap.setOnMapClickListener, with the following code:
Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

try {
List<Address> addresses = geoCoder.getFromLocation(point.latitude, point.longitude, 1);   // 1 : only 1 location is needed
String add = "";
if (addresses.size() > 0){
for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++)
add += addresses.get(0).getAddressLine(i) + "\n";
}
Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();

} catch (IOException e){
e.printStackTrace();
}

Again, you need to use Alt+Enter to import libraries.
After launching the map, zoom in to get a closer and detail map. I found it is better when you get into street level. Wait till the street view is shown. Then click a location to have the address in the Toast.
In my earlier version I used i < addresses.get(0).getMaxAddressLineIndex() but this version I have to change it to <=. Otherwise, it won’t work.
Take a screen shot of the Toast message.

That is it. Hope you enjoy the lab.

Submission: Submit the screen shots taken at Steps 7, 8, 10, 16, 19, 20

Listed below you can find the final version of my MapsActivity.java.

package com.example.mapservicedemo;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapservicedemo.databinding.ActivityMapsBinding;

import com.google.android.gms.maps.UiSettings;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    UiSettings mapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mapSettings = mMap.getUiSettings();
        //mapSettings.setZoomControlsEnabled(true);
        LatLng chicago = new LatLng(41.8781, -87.6298);   //Negative for W (west) or S (south)
        mMap.addMarker(new MarkerOptions().position(chicago).title("Chicago, Illinois"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chicago));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point){
                //Toast.makeText(getApplicationContext(), "onClick "+ point.latitude + "/" + point.longitude, Toast.LENGTH_LONG).show();
                //Log.d("Map Service", "Map clicked [" + point.latitude + " / "
                //        + point.longitude + " ] ");
                Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

                try {
                    List<Address> addresses = geoCoder.getFromLocation(point.latitude, point.longitude, 1);   // 1 : only 1 location is needed
                    String add = "";
                    if (addresses.size() > 0){
                        for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++)
                            add += addresses.get(0).getAddressLine(i) + "\n";
                    }
                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        });

    }
}



Notes: The following code is included for your reference. Given an address of a location you can use GeoCoding to find its latitude and longitude by using the code listed below:
Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
try {
List<Address> addresses = geoCoder.getFromLocationName(“White House”, 3);
if (addresses.size() > 0){
LatLng p = new LatLng(
(int)(addresses.get(0).getLatitude()),
(int)(addresses.get(0).getLongitude()));
mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
}
} catch (IOException e){
e.printStackTrace();
}

References:
https://developers.google.com/maps/documentation/android-api
https://developers.google.com/maps/documentation/android-sdk/start
Get API Key https://developers.google.com/maps/documentation/android-sdk/signup
