package sg.edu.rp.desmond.p08_map;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;

    LatLng Central = new LatLng(1.297802, 103.847441);
    LatLng North = new LatLng(1.441073, 103.772070);
    LatLng East = new LatLng(1.367149, 103.928021);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnNorth = (Button) findViewById(R.id.btnNorth);
        btnCentral = (Button) findViewById(R.id.btnCentral);
        btnEast = (Button) findViewById(R.id.btnEast);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng singapore = new LatLng(1.3521,103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        11));

                final Marker np = map.addMarker(new
                        MarkerOptions()
                        .position(North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));


                final Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(Central)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                final Marker ep = map.addMarker(new
                        MarkerOptions()
                        .position(East)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker.getTitle().equals("HQ - North")){
                            Toast.makeText(MainActivity.this, np.getTitle(), Toast.LENGTH_LONG).show();
                        }
                        else if(marker.getTitle().equals("HQ - Central")){
                            Toast.makeText(MainActivity.this, cp.getTitle(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, ep.getTitle(), Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }

                });

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                    return;
                }

            }



        });



        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(North,
                            16));
                }
            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,
                            16));
                }
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(East,
                            16));
                }
            }
        });



    }

}
