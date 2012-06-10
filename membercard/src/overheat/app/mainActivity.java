package overheat.app;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class mainActivity extends Activity {
	private ImageView plusButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        plusButton = (ImageView)findViewById(R.id.plusbutton);
        plusButton.setOnClickListener(new OnClickPlusListener());
    }
    class OnClickPlusListener implements OnClickListener{

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(mainActivity.this, "Click on PlusButton.", Toast.LENGTH_SHORT).show();
		}
    }
}