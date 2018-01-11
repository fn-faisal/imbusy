package com.appzspot.imbusy.recyclerv;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appzspot.imbusy.R;

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

public class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////
   
   private TextView mLocationNameTv;
   private TextView mToggleTv;

   private ImageButton mLocBtnTv;

   ///////////////////////////////////////////////////////////////////////////
   // Constructor.
   ///////////////////////////////////////////////////////////////////////////
   
   public LocationHolder ( View itemView ) {
      super ( itemView );
      initWidgets ( itemView );
      initListeners ();
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getter and Setter methods.
   ///////////////////////////////////////////////////////////////////////////

   public TextView getLocationNameTv () {
      return mLocationNameTv;
   }

   public void setLocationNameTv ( TextView locationNameTv ) {
      mLocationNameTv = locationNameTv;
   }

   public TextView getToggleTv () {
      return mToggleTv;
   }

   public void setToggleTv ( TextView toggleTv ) {
      mToggleTv = toggleTv;
   }

   public ImageButton getLocBtnTv () {
      return mLocBtnTv;
   }

   public void setLocBtnTv ( ImageButton locBtnTv ) {
      mLocBtnTv = locBtnTv;
   }


   ///////////////////////////////////////////////////////////////////////////
   // Helper methods.
   ///////////////////////////////////////////////////////////////////////////

   private void initWidgets ( View view ) {
      mLocationNameTv = ( TextView ) view.findViewById ( R.id.loc_card_name );
      mToggleTv = ( TextView ) view.findViewById ( R.id.loc_card_toggle );
      mLocBtnTv = ( ImageButton ) view.findViewById ( R.id.loc_card_btn );
   }

   private void initListeners () {
      mLocBtnTv.setOnClickListener ( this );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Listeners.
   ///////////////////////////////////////////////////////////////////////////
   
   @Override
   public void onClick ( View view ) {
      if ( view == mLocBtnTv ) {
         Snackbar.make ( mLocBtnTv , " Clicked the location button " , Snackbar.LENGTH_SHORT )
                 .show ();
      }
   }
}
