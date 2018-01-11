package com.appzspot.imbusy.model.dao;

import android.util.Log;
import com.appzspot.imbusy.model.dto.CallAction;
import com.appzspot.imbusy.model.dto.MainAction;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;
import com.orm.query.Select;
import java.util.List;

/**
 * Created by Muhammad Faisal Nadeem on 10/21/2017.
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

public class CallActionDa {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "CallActionDa";

   ///////////////////////////////////////////////////////////////////////////
   // Read methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Read call action for a given main action.
    * @param mainActionName the name of the main action.
    * @return call action.
    */
   public CallAction readActionForMA ( String mainActionName ) {

      List<CallAction> caList = Select
              .from ( CallAction.class )
              .list ();

      for ( CallAction ca : caList ) {
         if ( ca.getMainActionRef () != null ) {
            if ( ca.getMainActionRef ().getAction ().equals ( mainActionName ) )
               return ca;
         }
      }

      return null;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Create methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Create a new call action.
    * @param action call action to create.
    * @return true if call action created.
    */
   public boolean create ( final CallAction action ) {

      if ( action.getMainActionRef () == null ) {
         Log.e ( TAG, "create: Call action does not references a main action.");
         return false;
      }

      if ( readActionForMA ( action.getMainActionRef ().getAction () ) != null ) {
         Log.e ( TAG, "create: Attempt to create duplicate call action for MA : "
                 +action.getMainActionRef ().getAction ());
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            if ( action.save () <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error saving action MA : "+action.getMainActionRef ().getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Initilize call action for given main action.
    * @param mainAction the main action.
    */
   public static void initCallAction ( String mainAction ) {
      MainAction action = new MainActionDa ()
              .readActionByName ( mainAction );

      if ( action != null ) {

         if (!new CallActionDa ().create ( new CallAction (
                 "" // phone status.
                 ,"" // custom vmail.
                 ,false // end call.
                 ,false // toggle.
                 ,action
         ) ))
            Log.e ( TAG, "initCallAction: Error creating call action for MA : "+mainAction );

      }
      else
         Log.e ( TAG, "initCallAction: main action : "+mainAction+" is null." );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Update methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Update call action toggle state.
    * @param mainAction the main action.
    * @param toggle the toggle value.
    * @return true if updated successfully.
    */
   public boolean updateToggle ( String mainAction , final boolean toggle ) {
      final CallAction action = readActionForMA ( mainAction );

      if ( action == null ) {
         Log.e ( TAG, "updateToggle: The call action is null for MA : "+mainAction );
         return false;
      }

      if ( action.isActionToggle () == toggle ) {
         Log.d ( TAG, "updateToggle: call action's toggle value is already "+toggle );
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            action.setActionToggle ( toggle );
            if ( action.update () <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error updating call toggle for MA : " + action.getMainActionRef ().getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Update custom voice mail URI.
    * @param mainAction the main action.
    * @param vMail the v mail uri.
    * @return true if updated successfully.
    */
   public boolean updateCustomVMail ( String mainAction, final String vMail ) {
      final CallAction action = readActionForMA ( mainAction );
      if ( action == null ) {
         Log.e ( TAG, "updateToggle: The call action is null for MA : "+mainAction );
         return false;
      }
      if ( action.getCustomVoiceMail ().equals ( vMail ) ) {
         Log.d ( TAG, "updateToggle: call action's custom voice mail uri is already set to '"+vMail+"'" );
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            action.setCustomVoiceMail ( vMail );
            if ( action.save () <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error updating call vmal for MA : " + action.getMainActionRef ().getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Update the end call toggle.
    * @param mainAction the main action.
    * @param toggle the toggle value.
    * @return true if toggle value updated successfully.
    */
   public boolean updateEndCallToggle ( final String mainAction, final boolean toggle ) {
      final CallAction action = readActionForMA ( mainAction );
      if ( action == null ) {
         Log.e ( TAG, "updateToggle: The call action is null for MA : "+mainAction );
         return false;
      }
      if ( action.isEndCall () == toggle ) {
         Log.d ( TAG, "updateToggle: call action's custom voice mail uri is already set to '"+toggle+"'" );
         return false;
      }

      final boolean[]  bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            action.setEndCall ( toggle );
            if ( action.save () <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error updating end call toggle for MA : " + mainAction );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

}
