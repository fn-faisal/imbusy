package com.appzspot.imbusy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.appzspot.imbusy.model.Action;
import com.appzspot.imbusy.model.dao.LocationActionDa;
import com.appzspot.imbusy.model.dao.MainActionDa;
import com.appzspot.imbusy.model.dto.LocationAction;
import com.appzspot.imbusy.model.dto.MainAction;

/**
 * Created by Muhammad Faisal Nadeem on 10/20/2017.
 * Copyright © 2017 by Muhammad Faisal Nadeem
 * <p>
 * All information contained herein is, and remains
 * the property of Muhammad Faisal Nadeem. No part of this document
 * may be reproduced, distributed, or transmitted in any form or by any means
 * without the prior written permission of the publisher.
 * <p>
 * For permission request write to :-
 * <p>
 * Muhammad Faisal Nadeem.
 * mfaisalnadeem@hotmail.com
 */

public class LocationActionCreateFragment extends DialogFragment {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "LocationActionCreateFra";

   private EditText mLocationNameTv;
   private Switch mLocationToggleTv;
   private ImageButton mLocationCoords;
   private Button mAddLocation;

   private String mCoords;

   ///////////////////////////////////////////////////////////////////////////
   // Fragment methods.
   ///////////////////////////////////////////////////////////////////////////

   @Override
   public void onActivityCreated ( Bundle savedInstanceState ) {
      super.onActivityCreated ( savedInstanceState );
      getDialog ().getWindow ()
              .getAttributes ().windowAnimations = R.style.DialogAnimation;
   }

   @Override
   public void onCreate ( @Nullable Bundle savedInstanceState ) {
      super.onCreate ( savedInstanceState );
   }


   @Nullable
   @Override
   public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
      View parent = inflater.inflate ( R.layout.location_create_dialogue , container );

      initWidgets ( parent );
      initListeners ();

      return parent;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Helper methods.
   ///////////////////////////////////////////////////////////////////////////

   private void initWidgets ( View parent ) {
      mLocationNameTv = ( EditText ) parent.findViewById ( R.id.loc_dia_create_name );
      mLocationToggleTv = ( Switch ) parent.findViewById ( R.id.loc_dia_create_tgl );
      mLocationCoords = ( ImageButton ) parent.findViewById ( R.id.loc_dia_create_map_btn );
      mAddLocation = ( Button ) parent.findViewById ( R.id.loc_dia_create_add_btn );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Listeners.
   ///////////////////////////////////////////////////////////////////////////

   private void initListeners () {
      mLocationCoords.setOnClickListener ( new View.OnClickListener () {
         @Override
         public void onClick ( View view ) {
         }
      } );
      mAddLocation.setOnClickListener ( new View.OnClickListener () {
         @Override
         public void onClick ( View view ) {

            if ( mLocationNameTv.equals ( "" ) ) {
               Snackbar.make ( mLocationNameTv , " Please enter a location name! " , Snackbar.LENGTH_SHORT )
                       .show ();
            }
            else {
//               MainAction mainAction = new MainActionDa ().readActionByName ( Action.ACTION_WHEN_AT_NAME );
//
//               LocationAction action = new LocationAction (
//                       mLocationNameTv.getText ().toString (),
//                       "dummy coords",
//                       mLocationToggleTv.isChecked (),
//                       mainAction );

//               if ( !new LocationActionDa (  ).create ( action ) ) {
//                  Log.e ( TAG, "onClick: Could not create location" );
//                  Snackbar.make ( mLocationNameTv , "Could not create location action" , Snackbar.LENGTH_SHORT )
//                          .show ();
//               }
//               else {
//                  Snackbar.make ( getActivity ().getCurrentFocus () , "Location created successfully" , Snackbar.LENGTH_LONG )
//                          .show ();
//               }
               dismiss ();
            }
         }
      } );

   }
}
