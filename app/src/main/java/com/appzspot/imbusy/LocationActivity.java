package com.appzspot.imbusy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.appzspot.imbusy.model.dao.LocationActionDa;
import com.appzspot.imbusy.model.dto.LocationAction;
import com.appzspot.imbusy.recyclerv.LocationRecyclerViewAdapter;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String ADD_LOCATION_FRAG_TAG = "add_location_frag";

   private RecyclerView mLocationRecyclerView;
   private LocationRecyclerViewAdapter mAdapter;

   private ImageButton mNewLocBtn;

   ///////////////////////////////////////////////////////////////////////////
   // Activity methods.
   ///////////////////////////////////////////////////////////////////////////

   @Override
   protected void onCreate ( Bundle savedInstanceState ) {
      super.onCreate ( savedInstanceState );
      setContentView ( R.layout.activity_location );

      initWidgets ();
      initWidgetValues ();
      initListeners ();
   }

   ///////////////////////////////////////////////////////////////////////////
   // Helper methods.
   ///////////////////////////////////////////////////////////////////////////

   private void initWidgets () {
      mLocationRecyclerView = ( RecyclerView ) findViewById ( R.id.location_rv );
      mNewLocBtn = ( ImageButton ) findViewById ( R.id.location_add );
   }

   private void initWidgetValues () {
      // get the location list.
//      LocationActionDa actionDa = new LocationActionDa ();
//      ArrayList<LocationAction> locationActions = actionDa.read ( null );
//
//      if ( locationActions.size () > 0 ) {
//         mAdapter = new LocationRecyclerViewAdapter ( this, locationActions );
//         mLocationRecyclerView.setAdapter ( mAdapter );
//      }
   }

   ///////////////////////////////////////////////////////////////////////////
   // Listeners.
   ///////////////////////////////////////////////////////////////////////////

   private void initListeners () {
      mNewLocBtn.setOnClickListener ( new View.OnClickListener () {
         @Override
         public void onClick ( View view ) {
            FragmentManager fragmentManager = getSupportFragmentManager ();
            Fragment oldFrag = fragmentManager.findFragmentByTag ( ADD_LOCATION_FRAG_TAG );
            if ( oldFrag != null ) {
               fragmentManager.beginTransaction ().remove ( oldFrag ).commit ();
            }
            LocationActionCreateFragment fragment = new LocationActionCreateFragment();
            fragment.show ( fragmentManager , ADD_LOCATION_FRAG_TAG );
         }
      } );
   }

}
