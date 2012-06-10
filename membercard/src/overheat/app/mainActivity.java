package overheat.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Environment;


public class mainActivity extends Activity {
	private ImageView plusButton;
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        plusButton = (ImageView)findViewById(R.id.plusbutton);
        plusButton.setOnClickListener(new OnClickPlusListener());
    }
    
    /*
     * plus Button clicked, call camera.
     */
    class OnClickPlusListener implements OnClickListener{

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View v) {
			// create Intent to take a picture and return control to the calling application
			String state = Environment.getExternalStorageState();		    
		    if(Environment.MEDIA_MOUNTED.equals(state)){
		    	Intent Cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    	fileUri = getOutputMediaFileUri();
			    Cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
			    // start the image capture Intent
			    startActivityForResult(Cameraintent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		    }
		    else{
		    	Toast.makeText(mainActivity.this, "Please insert SD card.", Toast.LENGTH_SHORT).show();
		    }
		    
			//Toast.makeText(mainActivity.this, "Click on PlusButton.", Toast.LENGTH_SHORT).show();
		}
    }

    /* 
	 * Camera »Øµ÷º¯Êý
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	        	
	        	
	        	//imagePathList=getImagePathFromSD();   
	            //imagePathStringlist = imagePathList.toArray(new String[imagePathList.size()]); 
	        	//showDialog(DIALOG_PAUSED_ID);

	        	//myImageAdapter.notifyDataSetChanged();

	            //Toast.makeText(this, "notifyDataSetChanged" + imagePathStringlist.length, Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        	
	        }
	    }

	}
	/*
	 * Save Media file by date format
	 */
	
	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(){
	      return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image */
	private static File getOutputMediaFile(){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");

	    return mediaFile;
	}
}