package com.appzspot.imbusy;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appzspot.imbusy.dbdebug.DBHomeActivity;
import com.appzspot.imbusy.model.Action;
import com.appzspot.imbusy.model.Contract;
import com.appzspot.imbusy.model.ImbSwitches;
import com.appzspot.imbusy.model.dao.MainActionDa;
import com.appzspot.imbusy.model.dto.MainAction;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "MainActivity";

   private static final String IMBSY_FRAGMENT_TAG = "imbsy_fragment";

   // widgets.
   private TextView mMainHeader;

   private ImageButton mWhenImWalkingBtn;
   private ImageButton mWhenImRunJogBtn;
   private ImageButton mWhenImDrivingBtn;
   //private ImageButton mWhenImAtBtn;
   //private ImageButton mWhenImAtLocBtn;

   private View mWhenImWalkingView;
   private View mWhenImRunJogView;
   private View mWhenImDrivingView;
   //private View mWhenImAtView;


   ///////////////////////////////////////////////////////////////////////////
   // Activity methods.
   ///////////////////////////////////////////////////////////////////////////

   @Override
   protected void onCreate ( Bundle savedInstanceState ) {
      super.onCreate ( savedInstanceState );
      setContentView ( R.layout.activity_main );

      // initialize the toolbar.
      initToolbar ();

      // initialize widgets.
      initWidgets ();

      // initialize widget values.
      initWidgetValues ();

      // initialize listeners.
      initListeners ();

   }

   @Override
   public boolean onCreateOptionsMenu ( Menu menu ) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater ().inflate ( R.menu.menu_main, menu );
      return true;
   }

   @Override
   public boolean onOptionsItemSelected ( MenuItem item ) {
      switch ( item.getItemId () ) {
         case R.id.action_settings :
            return true;
         case R.id.action_dbdebug:
            startActivity ( new Intent ( this, DBHomeActivity.class ) );
            return true;
      }

      return super.onOptionsItemSelected ( item );
   }

   @Override
   protected void attachBaseContext ( Context newBase ) {
      super.attachBaseContext ( CalligraphyContextWrapper.wrap (newBase) );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Helper methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Initialize the toolbar.
    */
   private void initToolbar () {
      Toolbar toolbar = ( Toolbar ) findViewById ( R.id.toolbar );
      setSupportActionBar ( toolbar );
   }

   /**
    * Initialize widgets.
    */
   private void initWidgets () {
      // init buttons.
      //mWhenImAtBtn = ( ImageButton ) findViewById ( R.id.main_when_i_m_at_toggle );
      mWhenImDrivingBtn = ( ImageButton ) findViewById ( R.id.main_when_i_m_driving_toggle );
      mWhenImRunJogBtn = ( ImageButton ) findViewById ( R.id.main_when_i_m_runjog_toggle );
      mWhenImWalkingBtn = ( ImageButton ) findViewById ( R.id.main_when_i_m_walking_toggle );
      //mWhenImAtLocBtn = ( ImageButton ) findViewById ( R.id.main_when_i_m_at_loc );

      // init views
      //mWhenImAtView = findViewById ( R.id.main_when_i_m_at_full );
      mWhenImDrivingView = findViewById ( R.id.main_when_i_m_driving_full );
      mWhenImRunJogView = findViewById ( R.id.main_when_i_m_runjog_full );
      mWhenImWalkingView = findViewById ( R.id.main_when_i_m_walking_full );

      // init textviews.
      mMainHeader = ( TextView ) findViewById ( R.id.main_heading );
   }

   /**
    * Initialize widget values.
    */
   private void initWidgetValues () {

      MainActionDa mainActionDa = new MainActionDa ();

      // init value for walking.
      MainAction walkingAction = mainActionDa.readActionByName ( Action.ACTION_WHEN_WALKING_NAME );
      if ( walkingAction != null ) {
         if ( walkingAction.isToggle () )
            mWhenImWalkingBtn.setImageResource ( R.drawable.check );
         else
            mWhenImWalkingBtn.setImageResource ( R.drawable.cross );
      }
      else
         Log.e ( TAG, "initWidgetValues: Main action walking is null");

      // init value for running/jogging.
      MainAction runJogAction = mainActionDa.readActionByName ( Action.ACTION_WHEN_RUNJOG_NAME );
      if ( runJogAction != null ) {
         if ( runJogAction.isToggle () )
            mWhenImRunJogBtn.setImageResource ( R.drawable.check );
         else
            mWhenImRunJogBtn.setImageResource ( R.drawable.cross );
      }
      else
         Log.e ( TAG, "initWidgetValues: Main action running/jogging is null");

      // init value for driving.
      MainAction drivingAction = mainActionDa.readActionByName ( Action.ACTION_WHEN_DRIVING_NAME );
      if ( drivingAction != null ) {
         if ( drivingAction.isToggle () )
            mWhenImDrivingBtn.setImageResource ( R.drawable.check );
         else
            mWhenImDrivingBtn.setImageResource ( R.drawable.cross );
      }
      else
         Log.e ( TAG, "initWidgetValues: Main action driving is null");

      // init value for when I'm at.
//      MainAction locationAction = mainActionDa.readActionByName ( Action.ACTION_WHEN_AT_NAME );
//      if ( locationAction != null ) {
//         if ( locationAction.isToggle () )
//            mWhenImAtBtn.setImageResource ( R.drawable.check );
//         else
//            mWhenImAtBtn.setImageResource ( R.drawable.cross );
//      }
//      else
//         Log.e ( TAG, "initWidgetValues: Main action when at is null");

   }

   ///////////////////////////////////////////////////////////////////////////
   // Listeners.
   ///////////////////////////////////////////////////////////////////////////

   private void initListeners () {
      // init button listeners.
      mWhenImWalkingBtn.setOnClickListener ( this );
      mWhenImRunJogBtn.setOnClickListener ( this );
      mWhenImDrivingBtn.setOnClickListener ( this );
//      mWhenImAtBtn.setOnClickListener ( this );
//      mWhenImAtLocBtn.setOnClickListener ( this );

      // init view listeners.
      mWhenImWalkingView.setOnLongClickListener ( this );
      mWhenImRunJogView.setOnLongClickListener ( this );
      mWhenImDrivingView.setOnLongClickListener ( this );

   }

   @Override
   public void onClick ( View view ) {

      MainActionDa mainActionDa = new MainActionDa ();

      // when the user presses when im walking button.
      if ( view == mWhenImWalkingBtn ) {
         // if the resource is triggered.
         if ( mainActionDa.isActionToggle ( Action.ACTION_WHEN_WALKING_NAME ) ) {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_WALKING_NAME , false )) {
               animate ( mWhenImWalkingBtn, R.drawable.cross );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_unchecked ), Action.ACTION_WHEN_WALKING_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         } else {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_WALKING_NAME , true )) {
               animate ( mWhenImWalkingBtn, R.drawable.check );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_checked ), Action.ACTION_WHEN_WALKING_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         }
      }
      // when user presses WhenImRunning/Jogging button.
      else if ( view == mWhenImRunJogBtn ) {
         if ( mainActionDa.isActionToggle ( Action.ACTION_WHEN_RUNJOG_NAME ) ) {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_RUNJOG_NAME , false )) {
               animate ( mWhenImRunJogBtn, R.drawable.cross );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_unchecked ), Action.ACTION_WHEN_RUNJOG_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         }
         else {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_RUNJOG_NAME , true ) ) {
               animate ( mWhenImRunJogBtn, R.drawable.check );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_checked ), Action.ACTION_WHEN_RUNJOG_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         }
      }

      // when user presses WhenImDriving button.
      else if ( view == mWhenImDrivingBtn ) {
         if ( mainActionDa.isActionToggle ( Action.ACTION_WHEN_DRIVING_NAME ) ) {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_DRIVING_NAME , false ) ) {
               animate ( view, R.drawable.cross );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_unchecked ), Action.ACTION_WHEN_DRIVING_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         }
         else {
            if ( mainActionDa.updateActionToggle ( Action.ACTION_WHEN_DRIVING_NAME , true ) ) {
               animate ( view, R.drawable.check );
               Snackbar.make ( mWhenImWalkingBtn,
                       String.format ( getResources ().getString ( R.string.busy_status_checked ), Action.ACTION_WHEN_DRIVING_NAME ),
                       Snackbar.LENGTH_LONG
               ).show ();
            }
         }
      }

      // when I'm at location btn was used.
//      if ( view == mWhenImAtLocBtn ) {
//         startActivity ( new Intent ( this, LocationActivity.class ) );
//      }

   }

   @Override
   public boolean onLongClick ( View view ) {

      String whenImVal = "";

      if ( view == mWhenImWalkingView ) {
         whenImVal = Action.ACTION_WHEN_WALKING_NAME;
      }
      else  if ( view == mWhenImRunJogView ) {
         whenImVal = Action.ACTION_WHEN_RUNJOG_NAME;
      }
      else if ( view == mWhenImDrivingView ) {
         whenImVal = Action.ACTION_WHEN_DRIVING_NAME;
      }

      FragmentManager fragmentManager = getSupportFragmentManager ();
      Fragment oldFrag = fragmentManager.findFragmentByTag ( IMBSY_FRAGMENT_TAG );
      if ( oldFrag != null ) {
         fragmentManager.beginTransaction ().remove ( oldFrag ).commit ();
      }

      ImbsyDialogFragment fragment = ImbsyDialogFragment.newInstance ( whenImVal );
      fragment.show ( fragmentManager , IMBSY_FRAGMENT_TAG );

      return true;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Animation methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Animate the view with move in , move out animation.
    * @param view the view to animate.
    * @param rId the resource id for new image.
    */
   private void animate ( final View view, final int rId ) {

      view.animate ()
              .translationYBy ( 500 )
              .setDuration ( 200 )
              .setListener ( new Animator.AnimatorListener () {
                 @Override
                 public void onAnimationStart ( Animator animator ) {
                 }

                 @Override
                 public void onAnimationEnd ( Animator animator ) {
                    view.animate ()
                            .translationY ( 0 )
                            .setDuration ( 200 )
                            .start ();
                    if ( view instanceof ImageButton )
                       ((ImageButton)view).setImageResource ( rId );
                 }

                 @Override
                 public void onAnimationCancel ( Animator animator ) {
                 }

                 @Override
                 public void onAnimationRepeat ( Animator animator ) {
                 }
              } )
              .start ();

   }

}
