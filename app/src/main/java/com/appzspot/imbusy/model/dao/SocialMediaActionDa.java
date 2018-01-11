package com.appzspot.imbusy.model.dao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.appzspot.imbusy.R;
import com.appzspot.imbusy.model.Contract;
import com.appzspot.imbusy.model.dto.MainAction;
import com.appzspot.imbusy.model.dto.SocialMediaAction;
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

public class SocialMediaActionDa {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "SocialMediaActionDa";

   ///////////////////////////////////////////////////////////////////////////
   // Read methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Read the social media action.
    * @param mainActionName main action name.
    * @param <RType>
    * @return result.
    */
   public<RType> RType read ( @Nullable String mainActionName ) {

      List<SocialMediaAction> smaList = Select
              .from ( SocialMediaAction.class )
              .list ();

      if ( mainActionName == null )
         return ( RType ) smaList;

      for ( SocialMediaAction sma : smaList ) {
         if ( sma.getMainAction () != null )
            if ( sma.getMainAction ().getAction ().equals ( mainActionName ) )
               return ( RType ) sma;
      }

      return null;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Create methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Create a new social media action.
    * @param socialMediaAction the social media action to create.
    * @return true if action created successfully.
    */
   public boolean create ( final SocialMediaAction socialMediaAction ) {

      if ( socialMediaAction.getMainAction () == null ) {
         Log.e ( TAG, "create: social media's main action is null." );
         return false;
      }

      if ( read ( socialMediaAction.getMainAction ().getAction () ) != null ) {
         Log.e ( TAG, "create: Attempt to create duplicate social media action for MA : "
                 +socialMediaAction.getMainAction ().getAction ()  );
         return false;
      }

      SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
         @Override
         public void manipulateInTransaction () {
            if ( socialMediaAction.save () <= 0 )
               Log.e ( TAG, "manipulateInTransaction: Error saving sma for MA : "+socialMediaAction.getMainAction ().getAction () );
         }
      } );
      
      return SugarRecord.findById ( SocialMediaAction.class, socialMediaAction.getId () ) != null;
   }


   /**
    * Initialize the social media actions.
    * @param context the context.
    * @param mainActionName the main action.
    */
   public static void initSMA ( Context context , String mainActionName ) {
      MainAction mainAction = new MainActionDa ().readActionByName ( mainActionName );
      if ( mainAction == null ) {

         if ( !new SocialMediaActionDa ().create ( new SocialMediaAction (
            false, // fb msg tgl.
            context.getResources ().getString ( R.string.message_response_default ), // fb message response.
            false, // fb call tgl.
            "",    // fb call resp uri.
            false, // twt msg tgl.
            context.getResources ().getString ( R.string.message_response_default ), // twt message response.
            false, // twt auto tweet.
            context.getResources ().getString ( R.string.tweet_default ), // twt auto tweet response.
            false, // wapp msg tgl.
            context.getResources ().getString ( R.string.message_response_default ), // wapp message response.
            false, // wapp call toggle.
            "", // wapp call response uri,
            mainAction
         ) ) )
            Log.e ( TAG, "initSMA: Error create social media action for MA : "+mainActionName );

      }else
         Log.e ( TAG, "initSMA: The main action is null" );

   }

   ///////////////////////////////////////////////////////////////////////////
   // Update methods.
   ///////////////////////////////////////////////////////////////////////////

   public boolean updateToggle ( String mainAction, final String colName, boolean toggle  ) {

      final SocialMediaAction sma = read ( mainAction );

      if ( sma == null ) {
         Log.e ( TAG, "updateToggle: Social media action is null for MA : "+mainAction  );
         return false;
      }

      boolean updated = false;

      // fb message toggle.
      if ( colName.equals ( Contract.COL_SMA_FB_MSG_TGL ) ) {
         if ( sma.isFbMsgToggle () == toggle ) {
            return false;
         }
         sma.setFbMsgToggle ( toggle );
         updated = true;
      }

      // fb call toggle .
      else if ( colName.equals ( Contract.COL_SMA_FB_CALL_TGL ) ) {
         if ( sma.isFbCallToggle () == toggle ) {
            return false;
         }
         sma.setFbCallToggle ( toggle );
         updated = true;
      }

      // twitter msg toggle.
      else if ( colName.equals ( Contract.COL_SMA_TWT_MSG_TGL ) ) {
         if ( sma.isTwtMsgToggle () == toggle ) {
            return false;
         }
         sma.setTwtMsgToggle ( toggle );
         updated = true;
      }

      // twitter auto tweet toggle
      else if ( colName.equals ( Contract.COL_SMA_TWT_AUTO_TGL ) ) {
         if ( sma.isTwtAutoTweet () == toggle ) {
            return false;
         }
         sma.setTwtAutoTweet ( toggle );
         updated = true;
      }

      // whats app msg tgl.
      else if ( colName.equals ( Contract.COL_SMA_WAPP_MSG_TGL ) ) {
         if ( sma.isWappMsgToggle () == toggle ) {
            return false;
         }
         sma.setWappMsgToggle ( toggle );
         updated = true;
      }

      // wapp call toggle.
      else if ( colName.equals ( Contract.COL_SMA_WAPP_CALL_TGL ) ) {
         if ( sma.isWappCallToggle () == toggle ) {
            return false;
         }
         sma.setWappCallToggle ( toggle );
         updated = true;
      }

      final boolean[] bools = new boolean[1];
      if ( updated ) {
         SugarTransactionHelper.doInTransaction ( new SugarTransactionHelper.Callback () {
            @Override
            public void manipulateInTransaction () {
               if ( sma.update () <= 0 ) {
                  Log.e ( TAG, "manipulateInTransaction: Error updating sma col : '" + colName + "' for MA : '" + sma.getMainAction ().getAction () + "' " );
                  bools[0] = false;
               }
               else
                  bools[0] = true;
            }
         } );
      }

      return bools[0];
   }

}
