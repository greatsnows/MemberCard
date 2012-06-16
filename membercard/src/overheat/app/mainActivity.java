package overheat.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sqlite3.db.DatabaseHelper;

import overheat.app.Cards;
import overheat.app.subContentActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class mainActivity extends Activity {
	private ImageView plusButton;
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private File filePath;
	private boolean needBack = false;
	private boolean isBack = false;

	static final int DIALOG_ASKFORBACK_ID = 00;
	static final int DIALOG_NAME_ID = 10;
	
	Cards card = new Cards();
	
	private GVImageAdapter myGVImageAdapter;
	
	public List<Cards> imagePathList;
	//public String[] imagePathStringlist;
    private DatabaseHelper dbHelper = new DatabaseHelper(mainActivity.this, "huiyuanka_db");

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        plusButton = (ImageView)findViewById(R.id.plusbutton);
        plusButton.setOnClickListener(new OnClickPlusListener());
        
        imagePathList=getImagePathFromDB();   
        //imagePathStringlist = imagePathList.toArray(new String[imagePathList.size()]);
      //GridView
        GridView gridview = (GridView) findViewById(R.id.gridview);
        myGVImageAdapter = new GVImageAdapter(this);
        gridview.setAdapter(myGVImageAdapter);
        gridview.setOnItemClickListener(new OnClickItemListener());
        
        
    }
    /*
     * GridView 的ItemListener,用于启动gallery。
     */
    class  OnClickItemListener implements OnItemClickListener{
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	
        	Intent GridViewintent = new Intent();  
        	GridViewintent.setClass(mainActivity.this, subContentActivity.class);  
        	GridViewintent.putExtra("position", position); 
        	GridViewintent.putExtra("face", imagePathList.get(position).getFace()); 
        	GridViewintent.putExtra("back", imagePathList.get(position).getBack()); 

            startActivity(GridViewintent);  
            
            //Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
        }
    }
    /*
     * Grid View的数据 adapter
     */
    public class GVImageAdapter extends BaseAdapter {
        private Context mContext;

        public GVImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            /*SQLiteDatabase db = dbHelper.getReadableDatabase();
            long id = db.compileStatement(
                    "SELECT COUNT(*) FROM CARDS")
                    .simpleQueryForLong();
             */
        	
            //return imagePathStringlist.length;
            return imagePathList.size();

        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView GridimageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
            	GridimageView = new ImageView(mContext);
            	GridimageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            	GridimageView.setScaleType(ImageView.ScaleType.FIT_XY);
            	GridimageView.setPadding(8, 8, 8, 8);
            } else {
            	GridimageView = (ImageView) convertView;
            }

            /* 设定图片给imageView对象 */  
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 4;
            //opts.
            //opts.inJustDecodeBounds = true;
            Cards card = new Cards();
            card = imagePathList.get(position);
            //System.out.println("BitmapFactory.decodeFile is "+ imagePathStringlist[position]);
            Bitmap bm = BitmapFactory.decodeFile(card.getFace(), opts);
            if(BitmapFactory.decodeFile(card.getFace(), opts) != null)
            	{
            		System.out.println("decodeFile is ok!");
            	}
            else{
        			System.out.println("decodeFile is fail!");

            	}
            //opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
            /*opts.inJustDecodeBounds = false;
            try {
            	Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
            	imageView.setImageBitmap(bmp);
                } catch (OutOfMemoryError err) {
            }*/
            GridimageView.setImageBitmap(bm);
            //imageView.setImageResource(mThumbIds[position]);
            return GridimageView;
        }

    }
    /*
     * 从db卡中取出图片
     */
	private List<Cards> getImagePathFromDB(){
		
		List<Cards> it = new ArrayList<Cards>(); 
		Cards card = new Cards();
		//TODO
		//DatabaseHelper dbHelper = new DatabaseHelper(mainActivity.this, "huiyuanka_db");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        Cursor cursor = db.query("cards", new String[] { "name",
        "face","back" }, null, null, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        //String id = null;
        //String facePath = null;
        //String backPath = null;

        int i = 0;
        System.out.println("getImagePathFromDB ");

        //String name = null;
        while (cursor.moveToNext()) {
        	card.setName(cursor.getString(cursor.getColumnIndex("name")));
        	card.setFace(cursor.getString(cursor.getColumnIndex("face")));
        	card.setBack(cursor.getString(cursor.getColumnIndex("back")));

            //facePath = cursor.getString(cursor.getColumnIndex("face"));
            //facePath = cursor.getString(cursor.getColumnIndex("back"));

            //System.out.println("face path is " + facePath);
            it.add(card);
           }
        /*
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
		File[] files = mediaStorageDir.listFiles();
		for (int i = 0; i < files.length; i++) {  
            File file = files[i];  
            
            it.add(file.getPath());  
        }  */
		return it;  
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
		    	//fileUri = getOutputMediaFileUri();
		    	filePath = getOutputMediaFile();
		    	System.out.println("putExtra "+Uri.fromFile(filePath).toString());
			    Cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath)); // set the image file name
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
	 * Camera 回调函数
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	        	
	        	//imagePathList=getImagePathFromSD();   
	            //imagePathStringlist = imagePathList.toArray(new String[imagePathList.size()]); 
	        	// TODO save to card obj.
	        	if(!isBack){
	        		card.setFace(filePath.toString());
	        		card.setBack("null");
		        	showDialog(DIALOG_ASKFORBACK_ID);
	        	}
	        	else{
	        		card.setBack(filePath.toString());
    	        	showDialog(DIALOG_NAME_ID);
                	needBack = false;
                	isBack = false;
	        	}
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
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog=null;
	    switch(id) {
	    case DIALOG_ASKFORBACK_ID:
	        // do the work to define the isback Dialog
	    	return new AlertDialog.Builder(mainActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
	    	.setMessage("想拍反面吗？")
  	        .setCancelable(false)
            .setTitle("提示")
            .setPositiveButton("想", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	needBack = true;
                	isBack = true;
                	// TODO save to card object.
                	Intent Cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        		    //fileUri = getOutputMediaFileUri();
        		    filePath = getOutputMediaFile();
        		    Cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath)); // set the image file name
        		    // start the image capture Intent
        		    startActivityForResult(Cameraintent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            })
            .setNegativeButton("不想", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	needBack = false;
    	        	showDialog(DIALOG_NAME_ID);

                }
            })
            .create();
	    case DIALOG_NAME_ID:
	    	// This example shows how to add a custom layout to an AlertDialog
            LayoutInflater factory = LayoutInflater.from(this);
            final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
            return new AlertDialog.Builder(mainActivity.this)
                //.setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("提示")
                .setView(textEntryView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                    	EditText editName = (EditText)textEntryView.findViewById(R.id.name_edit);
                    	card.setName(editName.getText().toString());
                    	
                        //DatabaseHelper dbHelper = new DatabaseHelper(mainActivity.this, "huiyuanka_db");
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues cv=new ContentValues(); 
                        cv.put("name", card.getName()); 

                        
                        cv.put("face", card.getFace()); 
                        cv.put("back", card.getBack()); 

                        db.insert("cards", null, cv); 
                        
                        imagePathList=getImagePathFromDB();   
                        //imagePathStringlist = imagePathList.toArray(new String[imagePathList.size()]);
                        
                        //notify
                        myGVImageAdapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    		card = null;
                        /* User clicked cancel so do some stuff */
                    }
                })
                .create();
	    default:
	        dialog = null;
	    }
	    return dialog;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		dbHelper.close();
		super.onDestroy();
	}
	
}
