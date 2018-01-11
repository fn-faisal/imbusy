package com.appzspot.imbusy.model.dao;

import android.content.Context;
import android.util.Log;
import com.appzspot.imbusy.model.Action;
import com.appzspot.imbusy.model.Contract;
import com.appzspot.imbusy.model.dto.MainAction;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;
import com.orm.query.Condition;
import com.orm.query.Select;


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

public class MainActionDa {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "MainActionDa";

   ///////////////////////////////////////////////////////////////////////////
   // Read methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Check if the action exists.
    * @param actionName the name of the action.
    * @return true if action exists, false otherwise.
    */
   public boolean isAction ( String actionName ) {
      return Select.from ( MainAction.class )
              .where ( Condition.prop ( Contract.COL_MA_ACTION ).eq ( actionName ) )
              .first () != null;

   }

   /**
    * Check to see if the main action is toggle.
    * @param mainAction the name of the main action.
    * @return true if action is toggled, false otherwise.
    */
   public boolean isActionToggle ( String mainAction ) {
      MainAction action = Select
              .from ( MainAction.class )
              .where ( Condition.prop ( Contract.COL_MA_ACTION ).eq ( mainAction ) )
              .first ();

      if ( action == null ) {
         Log.e ( TAG, "isActionToggle: Main action : "+mainAction+" is null.");
         return false;
      }

      return action.isToggle ();
   }

   /**
    * Get the action by name.
    * @param actionName the action name.
    * @return main action, if found else null.
    */
   public MainAction readActionByName ( String actionName ) {
      return Select
              .from ( MainAction.class )
              .where ( Condition.prop ( Contract.COL_MA_ACTION ).eq ( actionName ) )
              .first ();
   }

   ///////////////////////////////////////////////////////////////////////////
   // Create methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Add an action to main action database.
    * uses methods : isAction().
    * @param mainAction the main action to add.
    * @return true if action added, false otherwise.
    */
   public boolean createAction ( final MainAction mainAction ) {
      if ( isAction ( mainAction.getAction () ) )
         return false;

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            if ( SugarRecord.save ( mainAction ) <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Could not save action : "+mainAction.getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Create a new action.
    * Uses method : isAction()
    * @param action the action name.
    * @param toggle the action switch (true/false).
    * @param wait the wait period until the action is triggered.
    * @return true if action saved.
    */
   public boolean createAction ( String action, boolean toggle, int wait ) {
      return createAction ( new MainAction ( action, toggle , wait ) );
   }

   /**
    * initialize the main action data.
    */
   public static void initMainActionData ( Context context ) {
      MainActionDa mainActionDa = new MainActionDa ();
      // walking action.
      if ( !mainActionDa.isAction ( Action.ACTION_WHEN_WALKING_NAME ) ) {
         if ( !mainActionDa.createAction (
                 Action.ACTION_WHEN_WALKING_NAME,
                 false,
                 Action.ACTION_WAIT_NONE
         ) )
            Log.e ( TAG, "initMainActionData: Could not create action : "+Action.ACTION_WHEN_WALKING_NAME );
         else {
            // add call action for main action
            CallActionDa.initCallAction ( Action.ACTION_WHEN_WALKING_NAME );

            // add message action.
            MessageActionDa.initMessageAction ( context, Action.ACTION_WHEN_WALKING_NAME );

            // add social media action.
            SocialMediaActionDa.initSMA ( context, Action.ACTION_WHEN_WALKING_NAME );
         }
      }

      // running/jogging action.
      if ( !mainActionDa.isAction ( Action.ACTION_WHEN_RUNJOG_NAME ) ) {
         if ( !mainActionDa.createAction (
                 Action.ACTION_WHEN_RUNJOG_NAME,
                 false,
                 Action.ACTION_WAIT_NONE
         ) )
            Log.e ( TAG, "initMainActionData: Could not create action : "+Action.ACTION_WHEN_RUNJOG_NAME );
         else {
            // add call action for main action
            CallActionDa.initCallAction ( Action.ACTION_WHEN_RUNJOG_NAME);

            // add message action.
            MessageActionDa.initMessageAction ( context, Action.ACTION_WHEN_RUNJOG_NAME );

            // add social media action.
            SocialMediaActionDa.initSMA ( context, Action.ACTION_WHEN_RUNJOG_NAME );
         }
      }

      // driving action.
      if ( !mainActionDa.isAction ( Action.ACTION_WHEN_DRIVING_NAME ) ) {
         if ( !mainActionDa.createAction (
                 Action.ACTION_WHEN_DRIVING_NAME,
                 false,
                 Action.ACTION_WAIT_NONE
         ) )
            Log.e ( TAG, "initMainActionData: Could not create action : "+Action.ACTION_WHEN_DRIVING_NAME );
         else {
            // add call action for main action
            CallActionDa.initCallAction ( Action.ACTION_WHEN_DRIVING_NAME );

            // add message action.
            MessageActionDa.initMessageAction ( context, Action.ACTION_WHEN_DRIVING_NAME );

            // add social media action.
            SocialMediaActionDa.initSMA ( context, Action.ACTION_WHEN_DRIVING_NAME );
         }
      }

      // at location action.
      if ( !mainActionDa.isAction ( Action.ACTION_WHEN_AT_NAME ) ) {
         if ( !mainActionDa.createAction (
                 Action.ACTION_WHEN_AT_NAME,
                 false,
                 Action.ACTION_WAIT_NONE
         ) )
            Log.e ( TAG, "initMainActionData: Could not create action : "+Action.ACTION_WHEN_AT_NAME );
         else {
            // add call action for main action
            CallActionDa.initCallAction ( Action.ACTION_WHEN_AT_NAME);

            // add message action.
            MessageActionDa.initMessageAction ( context, Action.ACTION_WHEN_AT_NAME );

            // add social media action.
            SocialMediaActionDa.initSMA ( context, Action.ACTION_WHEN_AT_NAME );
         }
      }
   }


   ///////////////////////////////////////////////////////////////////////////
   // Delete actions.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Remove an action from the database.
    * @param actionName the action name.
    * @return true if removed successfully, false otherwise.
    */
   public boolean removeActionByName ( String actionName ) {
      MainAction action = readActionByName ( actionName );

      if ( action == null ) {
         Log.e ( TAG, "removeActionByName: Main action '" + actionName + "' is null." );
         return false;
      }

      return SugarRecord.delete ( action );
   }

   /**
    * Remove an action from the database.
    * uses method : isAction()
    * @param mainAction the main action to remove.
    * @return true if removed successfully.
    */
   public boolean removeAction ( MainAction mainAction ) {
      if ( !isAction ( mainAction.getAction () ) ) {
         Log.e ( TAG, "removeAction: Main action '" + mainAction.getAction () + "' is null." );
         return false;
      }

      return SugarRecord.delete ( readActionByName ( mainAction.getAction () ) );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Update methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Update main action's toggle property.
    * @param mainAction the main action name.
    * @param toggle the toggle property.
    * @return true if action updated successfully.
    */
   public boolean updateActionToggle ( String mainAction , final boolean toggle ) {
      final MainAction action = readActionByName ( mainAction );

      if ( action == null ) {
         Log.e ( TAG, "updateActionToggle: Main action : "+mainAction+" is null." );
         return false;
      }

      if ( action.isToggle () == toggle ) {
         Log.e ( TAG, "updateActionToggle: Main action : "+mainAction+" toggle value already set to : "+toggle );
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            action.setToggle ( toggle );
            if ( SugarRecord.save ( action ) <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error saving action : "+action.getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Update action wait.
    * @param actionName the action name.
    * @param wait the wait period 0-5
    * @return true if updated successfully.
    */
   public boolean updateActionWait ( String actionName , final int wait ) {

      if ( wait < 0 || wait > 5 ) {
         Log.e ( TAG, "updateActionWait: Wait period has to be between 0-5" );
         return false;
      }

      final MainAction mainAction = readActionByName ( actionName );
      if ( mainAction == null ) {
         Log.e ( TAG, "updateActionWait: the main action '"+actionName+"' does not exists." );
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            mainAction.setWait ( wait );
            if ( SugarRecord.save ( mainAction ) <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error saving action : "+mainAction.getAction () );
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

}
