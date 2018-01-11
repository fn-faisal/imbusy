package com.appzspot.imbusy.recyclerv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appzspot.imbusy.R;
import com.appzspot.imbusy.model.dto.LocationAction;

import java.util.ArrayList;

/**
 * Created by Muhammad Faisal Nadeem on 10/19/2017.
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

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter < LocationHolder > {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "LocationRVAdapter";

   private Context mContext;
   private ArrayList < LocationAction > mLocationList;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public LocationRecyclerViewAdapter ( Context context, ArrayList < LocationAction > locationList ) {
      mContext = context;
      mLocationList = locationList;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Adapter methods.
   ///////////////////////////////////////////////////////////////////////////

   @Override
   public LocationHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
      View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.location_card, null );
      return new LocationHolder ( view );
   }

   @Override
   public void onBindViewHolder ( LocationHolder holder, int position ) {

      LocationAction action = mLocationList.get ( position );

      if ( action != null ) {

         holder.getLocationNameTv ().setText ( action.getName () );
         holder.getToggleTv ().setText (
                 String.format ( mContext.getResources ().getString ( R.string.loc_toggle_text ) ,
                         action.isToggle () ? "Yes" : "No")
         );

      } else
         Log.e ( TAG, "onBindViewHolder: location list returned null at position " + position );

   }

   @Override
   public int getItemCount () {
      return mLocationList.size ();
   }
}
