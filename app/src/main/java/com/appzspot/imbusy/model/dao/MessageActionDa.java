package com.appzspot.imbusy.model.dao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appzspot.imbusy.R;
import com.appzspot.imbusy.model.dto.MainAction;
import com.appzspot.imbusy.model.dto.MessageAction;
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

public class MessageActionDa {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "MessageActionDa";


   ///////////////////////////////////////////////////////////////////////////
   // Read methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Read message action.
    *
    * @param mainActionName the main action name.
    *
    * @return result of the read query.
    */
   public < RType > RType read ( @Nullable String mainActionName ) {
      List < MessageAction > messageActions = Select
              .from ( MessageAction.class )
              .list ();
      if ( mainActionName == null ) {
         if ( messageActions != null )
            return ( RType ) messageActions;
      } else {
         for ( MessageAction action : messageActions ) {
            if ( action.getMainActionRef () != null ) {
               if ( action.getMainActionRef ().getAction ().equals ( mainActionName ) )
                  return ( RType ) action;
            }
         }
      }

      return null;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Create methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Create a new message action.
    * @param messageAction the message action to create.
    * @return true if message action created.
    */
   public boolean create ( final MessageAction messageAction ) {
      if ( messageAction.getMainActionRef () == null ) {
         Log.e ( TAG, "create: Message action does not contain main action ref.");
         return false;
      }

      if ( read ( messageAction.getMainActionRef ().getAction () ) ) {
         Log.e ( TAG, "create: Attempt to create duplicate message action for MA : "
                 +messageAction.getMainActionRef ().getAction () );
         return false;
      }

      final boolean[] bools = new boolean[1];
      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            if ( SugarRecord.save ( messageAction ) <= 0 ) {
               Log.e ( TAG, "manipulateInTransaction: Error saving message action for MA : "
                       + messageAction.getMainActionRef ().getAction () + ".");
               bools[0] = false;
            }
            else
               bools[0] = true;
         }
      } );

      return bools[0];
   }

   /**
    * Initialize message action.
    * @param mainAction the main action
    */
   public static void initMessageAction ( Context context, String mainAction ) {
      MainAction action = new MainActionDa ().readActionByName ( mainAction );
      if ( action != null ) {
         if ( !new MessageActionDa ().create ( new MessageAction (
                 false, // auto response.
                 context.getResources ().getString ( R.string.message_response_default ), // response message.
                 action // the main action ref.
         ) ) )
            Log.e ( TAG, "initMessageAction: Error creating message action for MA : "+mainAction );
      }
      else
         Log.e ( TAG, "initMessageAction: main action : "+mainAction+" is null." );

   }

}
