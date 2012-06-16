package overheat.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import overheat.app.mainActivity;
import overheat.app.Cards;

public class subContentActivity extends Activity{
	private int pos = 0;
	private String facePath = null;
	private String backPath = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subactivity);
		
		Intent intent = getIntent();
		pos = intent.getIntExtra("position", 0);
		facePath = intent.getStringExtra("face");
		backPath = intent.getStringExtra("back");

		
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
	    gallery.setAdapter(new ImageAdapter(this));
	    gallery.setSelection(pos);
	    
	    gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(subContentActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });

	}
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private Context mContext;

	    private Integer[] mImageIds = {
	            R.drawable.plus_icon
	    };

	    public ImageAdapter(Context c) {
	        mContext = c;
	        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
	        mGalleryItemBackground = attr.getResourceId(
	                R.styleable.HelloGallery_android_galleryItemBackground, 0);
	        attr.recycle();
	    }

	    public int getCount() {
	        return 2;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView = new ImageView(mContext);
	        //Cards card = new Cards();
	        //Bitmap bm = null;
	        

	        BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 4;
            //opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
            opts.inJustDecodeBounds = false;
            try {
            	Bitmap bmp;
            	//Bitmap bmp = BitmapFactory.decodeFile(facePath, opts);
            	//imageView.setImageBitmap(bmp);
            	if(position == 0){
            		bmp = BitmapFactory.decodeFile(facePath,opts);
	            }else{
	            	bmp = BitmapFactory.decodeFile(backPath,opts);
	            }
            	imageView.setImageBitmap(bmp);

                } catch (OutOfMemoryError err) {
            }
            //System.out.println("BitmapFactory.decodeFile is "+ imagePathStringlist[position]);
            /*if(BitmapFactory.decodeFile(card.getFace(), opts) != null)
            	{
            		System.out.println("decodeFile is ok!");
            	}
            else{
        			System.out.println("decodeFile is fail!");

            	}*/
            //opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
            /*opts.inJustDecodeBounds = false;
            try {
            	Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
            	imageView.setImageBitmap(bmp);
                } catch (OutOfMemoryError err) {
            }*/
            //GridimageView.setImageBitmap(bm);
            //imageView.setImageResource(mThumbIds[position]);
            
            
            
	        /*imageView.setImageResource(R.drawable.plus_icon);
	        
	        imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
	        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	        imageView.setBackgroundResource(mGalleryItemBackground);*/
            /*if(position == 0){
    	        //System.out.println("face is " + facePath);

                bm = BitmapFactory.decodeFile(facePath,opts);

            }else{
    	        //System.out.println("back is " + backPath);

            	bm = BitmapFactory.decodeFile(backPath,opts);
            }*/
            
        	

	        return imageView;
	    }
	}
}
