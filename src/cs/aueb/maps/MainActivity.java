package cs.aueb.maps;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

	public class MainActivity extends Activity {
		
		Thread t;
		GoogleMap mMap;
		GoogleMap tempmap;
		static double[] lat, lon;
		static String[] title, msg;
		LatLng temp;
		MarkerOptions marker;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
	
			lat = new double[]{-33.867,40.714353, 52.066, 40.996484, 39.293391, 38.028622, 34.846776, 35.692995, 48.684614, -18.908885};
			lon = new double[]{151.206, -74.005973, 4.292107, 28.978271, -76.587811, 24.000778, 24.093304, 139.756164, 44.484787, 47.526398};
			title = new String[]{"Sydney", "New York", "The Hague", "Istanbul", "Baltimore", "Rafina", "Gaudos", "Tokyo", "Stalingrad", "Antananarivo"};
			msg = new String[]{"Filthy southern peasants", "Hipsters", "V2 funland", "swagSWAGswag",
					"All in the game yo, all in the game.", "Mpomp's house", "Hippies", "Hotel",
					"Where you locate the app..", "Super fun time!"};
			
			
			mMap = ((MapFragment)
					getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			
			for(int i=0; i<lat.length; i++) {	
				marker = new MarkerOptions().position(new LatLng(lat[i], lon[i])).title(title[i]).snippet(msg[i]);
				mMap.addMarker(marker);
			}	
		
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable(){
				
				Random r = new Random();
				int num = lat.length;
				int index;
				
				@Override
				public void run() {
	
					index = r.nextInt(num);
					point(lat[index], lon[index]);
					Log.e("TIME", ""+System.currentTimeMillis());
					handler.postDelayed(this, 5000);
					
				}
			});	
		}
		
		public void point(double lat, double lon) {		
			
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
					  new LatLng(lat, lon)).zoom(13).build();
					  
					mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		}
		
	}