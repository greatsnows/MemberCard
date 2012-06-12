package overheat.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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

public class subContentActivity extends Activity{
	private int pos = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subactivity);
		
		Intent intent = getIntent();
		pos = intent.getIntExtra("position", 0);
		
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
	        return 1;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView = new ImageView(mContext);

	        imageView.setImageResource(R.drawable.content_new);
	        imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
	        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	        imageView.setBackgroundResource(mGalleryItemBackground);

	        return imageView;
	    }
	}
}
