package cs.aueb.maps;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

	public class MainActivity extends FragmentActivity {
		
		Thread t;
		GoogleMap mMap;
		GoogleMap tempmap;
		double[] lat, lon;
		String[] title, msg;
		LatLng temp;
		MarkerOptions marker;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
	
			lat = new double[]{-33.867,40.714353};
			lon = new double[]{151.206, -74.005973};
			title = new String[]{"Sydney", "New York"};
			msg = new String[]{"fsdfds", "sdass"};
			
			
			mMap = ((MapFragment)
					getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			
			for(int i=0; i<lat.length; i++) {	
				marker = new MarkerOptions().position(new LatLng(lat[i], lon[i])).title(title[i]).snippet(msg[i]);
				mMap.addMarker(marker);
			}	
			
			/*
			
			
			*/
		
			
			t = new Thread(new RandomPoint());
			t.start();  
			
			
			
		}
		
		public void point(double lat, double lon) {
			
			// create marker
			
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
					  new LatLng(lat, lon)).zoom(13).build();
					  
					mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			/*
			
			Log.e("WTF", "point 1");
			temp = new LatLng(lat, lon);
			Log.e("WTF", "point 2");
			
			setUpMapIfNeeded();
			Log.e("WTF", "point 2.5");
			
		
			Log.e("WTF", "point 3");
			//mMap.setMyLocationEnabled(true);
			Log.e("WTF", "point 4");
			
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temp, 13));
			Log.e("WTF", "point 5");
			
			mMap.addMarker(new MarkerOptions().title(title)
					.snippet(msg)
					.position(temp));
					*/
			
		}
		
		private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	            // Check if we were successful in obtaining the map.
	            if (mMap != null) {
	                setUpMap();
	            }
	        }
	    }

	    /**
	     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	     * just add a marker near Africa.
	     * <p>
	     * This should only be called once and when we are sure that {@link #mMap} is not null.
	     */
	    private void setUpMap() {
	      //  mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	    }
		
		private class RandomPoint implements Runnable {
			
			Random r = new Random();
			int num = lat.length;
			int index;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				index = r.nextInt(num);
				
				point(lat[index], lon[index]);
				
				while(true) {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
			
		}
	}