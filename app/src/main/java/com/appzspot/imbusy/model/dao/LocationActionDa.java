package com.appzspot.imbusy.model.dao;

import android.support.annotation.Nullable;
import android.util.Log;
import com.appzspot.imbusy.model.Contract;
import com.appzspot.imbusy.model.dto.LocationAction;

import java.util.List;

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

public class LocationActionDa {

//   ///////////////////////////////////////////////////////////////////////////
//   // Fields.
//   ///////////////////////////////////////////////////////////////////////////
//
//   private static final String TAG = "LocationActionDa";
//
//   ///////////////////////////////////////////////////////////////////////////
//   // Read methods.
//   ///////////////////////////////////////////////////////////////////////////
//
//   /**
//    * Check if the location exists in local database.
//    * @param action the location action.
//    * @return true if location exists.
//    */
//   public boolean isLocationAction ( LocationAction action ) {
//      return new Select (  )
//              .from ( LocationAction.class )
//              .where ( Contract.COL_LA_NAME + "=?" , action.getName () )
//              .executeSingle () != null;
//   }
//
//   /**
//    * Check if the location exists in local db.
//    * @param actionName the location action name.
//    * @return true if location exists.
//    */
//   public boolean isLocationAction ( String actionName ) {
//      return new Select (  )
//              .from ( LocationAction.class )
//              .where ( Contract.COL_LA_NAME + "=?", actionName )
//              .executeSingle () != null;
//   }
//
//   /**
//    * Read locations/location from the local db.
//    * @param actionName (nullable) the name of the action.
//    * @param <Rtyp>
//    * @return a list/model response.
//    */
//   public<Rtyp> Rtyp read ( @Nullable String actionName ) {
//      if ( actionName == null ) {
//         return ( Rtyp ) new Select (  )
//                 .from ( LocationAction.class )
//                 .execute ();
//      }
//      return (Rtyp ) new Select (  )
//              .from ( LocationAction.class )
//              .where ( Contract.COL_LA_NAME + "=?" , actionName )
//              .executeSingle ();
//   }
//
//   ///////////////////////////////////////////////////////////////////////////
//   // Create methods.
//   ///////////////////////////////////////////////////////////////////////////
//
//   /**
//    * Create a location action in local db.
//    * @param action the location action.
//    * @return true if action created, false otherwise.
//    */
//   public boolean create ( LocationAction action ) {
//
//      if ( !validateLocationAction ( action ) ) {
//         Log.e ( TAG, "create: Location action is invalid." );
//         return false;
//      }
//
//      if ( isLocationAction ( action ) ) {
//         Log.d ( TAG, "create: attempt to create a duplicate location action : "+action.getName () );
//         return false;
//      }
//
//      ActiveAndroid.beginTransaction ();
//
//      if ( action.save () > 0 ) {
//         ActiveAndroid.setTransactionSuccessful ();
//         ActiveAndroid.endTransaction ();
//         return true;
//      }
//
//      ActiveAndroid.endTransaction ();
//      return false;
//   }
//
//
//   ///////////////////////////////////////////////////////////////////////////
//   // Helper methods.
//   ///////////////////////////////////////////////////////////////////////////
//
//   /**
//    * Validate location action object.
//    * @param action the action.
//    * @return true if valid.
//    */
//   public boolean validateLocationAction ( LocationAction action ) {
//
//      String msg = "location action validation failed";
//      boolean error = false;
//
//      if ( action.getName () == null ) {
//         msg+= " [ location name is null ]";
//         error = true;
//      }
//
//      if ( action.getCoordinates () == null ) {
//         msg += " [ location coordinates are null ]";
//         error = true;
//      }
//
//      if ( error ) {
//         Log.e ( TAG, "validateLocationAction: "+msg );
//         return false;
//      }
//
//      return false;
//   }
//
}
