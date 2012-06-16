package overheat.app;

import java.io.FileNotFoundException;  
import java.util.ArrayList;
import java.util.List;  
  
import android.app.Activity;
import android.content.Context;  
import android.content.Intent;
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.net.Uri;  
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;  
import android.support.v4.view.ViewPager;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
  
public class VPActivity extends Activity{
	private ViewPager myViewPager;
	private PagerAdapter myAdapter;
	
	private int pos = 0;
	private String facePath = null;
	private String backPath = null;
	//private PagerTitleStrip mPagerTitleStrip;
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.vpactivity);  
	    
	    Intent intent = getIntent();
		pos = intent.getIntExtra("position", 0);
		facePath = intent.getStringExtra("face");
		backPath = intent.getStringExtra("back");
		
	    myViewPager = (ViewPager)findViewById(R.id.viewpager);  
	    myAdapter = new VPAdapter(this);  
	    myViewPager.setAdapter(myAdapter);  
	    //myAdapter.change(getList());  
	}  
	  
	/*private List<String> getList() {  
	    List<String> list = new ArrayList<String>();  
	    list.add("file:///sdcard/Sunset.jpg");  
	    list.add("file:///sdcard/Winter.jpg");  
	    list.add("file:///sdcard/Water lilies.jpg");  
	    list.add("file:///sdcard/Blue hills.jpg");  
	    return list;  
	}  */
	public class VPAdapter extends PagerAdapter {  
		  
	    //private List<String> mPaths;  
	      
	    private Context mContext;  
	      
	    public VPAdapter(Context cx) {  
	        //mContext = cx;

	        mContext = cx.getApplicationContext();  
	    }  
	      
	    /*public void change(List<String> paths) {  
	        mPaths = paths;  
	    } */ 
	      
	    @Override  
	    public int getCount() {  
	        // TODO Auto-generated method stub  
	        //return mPaths.size(); 
	    	return 2;
	    }  
	  
	    @Override  
	    public boolean isViewFromObject(View view, Object obj) {  
	        // TODO Auto-generated method stub  
	        return view == (View) obj;  
	    }  
	  
	    @Override  
	    public Object instantiateItem (ViewGroup container, int position) {  
	        ImageView iv = new ImageView(mContext);  
	        try {  
	        	BitmapFactory.Options opts = new BitmapFactory.Options();
	            opts.inSampleSize = 4;
	            Bitmap bm;
	            if(position == 0){
            		bm = BitmapFactory.decodeFile(facePath,opts);
	            }else{
	            	bm = BitmapFactory.decodeFile(backPath,opts);
	            }
	            //Bitmap bm = BitmapFactory.decodeFile(mPaths.get(position));//‘ÿ»Îbitmap  
	            iv.setImageBitmap(bm);  
	        } catch (OutOfMemoryError e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        ((ViewPager)container).addView(iv, 0);  
	        return iv;  
	    }  
	      
	    @Override  
	    public void destroyItem (ViewGroup container, int position, Object object) {  
	    	((ViewPager) container).removeView((View)object);  
	    }  
	}  
}