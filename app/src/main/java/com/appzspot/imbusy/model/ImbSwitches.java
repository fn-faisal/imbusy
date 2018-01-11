package com.appzspot.imbusy.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Muhammad Faisal Nadeem on 10/15/2017.
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

public class ImbSwitches {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "ImbSwitches";

   // singleton instance.
   private static ImbSwitches instance;

   // context
   private Context mContext;

   // switch vars.
   private boolean whenImWalking;
   private boolean whenImRunJog;
   private boolean whenImDriving;

   // set vars
   private Set < String > whenImAt;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Private constructor
    */
   private ImbSwitches ( Context context ) {
      // set context.
      this.mContext = context;

      // read values from prefs.
      if ( checkPref ( Contract.WHEN_IM_WALKING ) )
         this.whenImWalking = ( Boolean ) readFromPrefs ( Contract.WHEN_IM_WALKING );
      else {
         this.whenImWalking = false;
         saveToPrefs ( Contract.WHEN_IM_WALKING, false, null );
      }
      if ( checkPref ( Contract.WHEN_IM_DRIVING ) )
         this.whenImDriving = ( Boolean ) readFromPrefs ( Contract.WHEN_IM_DRIVING );
      else {
         this.whenImDriving = false;
         saveToPrefs ( Contract.WHEN_IM_DRIVING, false, null );
      }
      if ( checkPref ( Contract.WHEN_IM_RUNJOG ) )
         this.whenImRunJog = ( Boolean ) readFromPrefs ( Contract.WHEN_IM_RUNJOG );
      else {
         this.whenImRunJog = false;
         saveToPrefs ( Contract.WHEN_IM_RUNJOG, false, null );
      }
      if ( checkPref ( Contract.WHEN_IM_AT ) )
         this.whenImAt = ( Set < String > ) readFromPrefs ( Contract.WHEN_IM_AT );
      else {
         this.whenImAt = new HashSet < String > ();
         saveToPrefs ( Contract.WHEN_IM_AT, new HashSet < String > (), null );
      }
   }

   ///////////////////////////////////////////////////////////////////////////
   // Methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Get( and optionally initialize ) the singleton instance.
    *
    * @return single instance.
    */
   public static ImbSwitches getInstance ( Context context ) {
      if ( instance == null ) {
         instance = new ImbSwitches ( context );
      }
      return instance;
   }

   /**
    * Read from shared preferences.
    *
    * @param prefName
    *         the pref name.
    *
    * @return true if preference found, false otherwise.
    */
   private < RType > RType readFromPrefs ( String prefName ) {
      SharedPreferences sharedPreferences = getAndCheckSharedPrefs ( prefName );
      if ( sharedPreferences == null ) return null;

      if ( isPrefSet ( prefName ) )
         return ( RType ) sharedPreferences.getStringSet ( prefName, new HashSet < String > () );

      else if ( isPrefSwitch ( prefName ) )
         return ( RType ) new Boolean ( sharedPreferences.getBoolean ( prefName, false ) );

      return null;
   }

   /**
    * Change pref values.
    *
    * @param prefName
    *         the name of the prefs.
    * @param value
    *         the new value.
    * @param oldVal
    *         NULLABLE old value, used for the prefs.
    * @param <ParamVal>
    *         the value generic type
    *
    * @return true if saved successfully, false otherwise.
    */
   private < ParamVal > boolean saveToPrefs ( String prefName, ParamVal value,
                                              @Nullable ParamVal oldVal ) {

      SharedPreferences sharedPreferences = getSharedPrefs ();
      if ( sharedPreferences == null ) return false;
      SharedPreferences.Editor editor = sharedPreferences.edit ();

      if ( isPrefSwitch ( prefName ) ) {
         if ( checkPref ( prefName ) )
            editor.putBoolean ( prefName, ( Boolean ) value );
         else
            editor.putBoolean ( prefName, false );
         return editor.commit ();
      } else if ( isPrefSet ( prefName ) ) {

         if ( value.getClass () == Set.class ) {
            editor.putStringSet ( prefName, ( Set < String > ) value );
            return true;
         } else {
            Set < String > strSet = new HashSet < String > ();
            boolean valUpdated = false;
            for ( String val :
                    sharedPreferences.getStringSet ( Contract.WHEN_IM_AT, new HashSet < String > () ) ) {
               if ( oldVal != null ) {
                  if ( val.equals ( oldVal ) ) {
                     strSet.add ( ( String ) value );
                     valUpdated = true;
                  } else
                     strSet.add ( val );
               } else
                  strSet.add ( val );
            }
            editor.putStringSet ( prefName, strSet );
            editor.commit ();
            return valUpdated;
         }
      }
      return false;
   }

   /**
    * Remove a value from the string set.
    *
    * @param prefName
    *         the name of the preference.
    * @param value
    *         the value to remove from the set.
    *
    * @return true if value was remove, false otherwise.
    */
   private boolean removeFromPrefSet ( String prefName, String value ) {

      SharedPreferences sharedPreferences = getAndCheckSharedPrefs ( prefName );
      if ( sharedPreferences == null ) return false;
      SharedPreferences.Editor editor = sharedPreferences.edit ();

      Set < String > strSet = new HashSet < String > ();

      boolean valRemoved = false;
      for ( String prefVal : sharedPreferences.getStringSet ( prefName, new HashSet < String > () ) ) {
         if ( !prefVal.equals ( value ) )
            strSet.add ( prefVal );
         else
            valRemoved = true;
      }
      if ( valRemoved )
         editor.putStringSet ( prefName, strSet );

      return valRemoved;
   }

   /**
    * Update a value from the string set.
    *
    * @param prefName
    *         the name of the pref.
    * @param valOld
    *         the old value of the pref.
    * @param valNew
    *         the new value of the pref.
    *
    * @return true if updated successfully, false otherwise.
    */
   private boolean updateSetVal ( String prefName, String valOld, String valNew ) {
      SharedPreferences sharedPreferences = getAndCheckSharedPrefs ( prefName );
      if ( sharedPreferences == null ) return false;
      SharedPreferences.Editor editor = sharedPreferences.edit ();

      boolean prefUpdated = false;
      Set < String > strSet = new HashSet < String > ();
      for ( String prefVal :
              sharedPreferences.getStringSet ( prefName, new HashSet < String > () ) ) {
         if ( prefVal.equals ( valOld ) ) {
            strSet.add ( valNew );
            prefUpdated = true;
         } else
            strSet.add ( prefVal );

      }
      if ( prefUpdated )
         editor.putStringSet ( prefName, strSet );

      return prefUpdated;
   }

   /**
    * Get the shared preferences for app and check if the prefs string is present in app prefs.
    *
    * @param prefName
    *         the name of the prefs to check.
    *
    * @return App Preferences.
    */
   private SharedPreferences getAndCheckSharedPrefs ( String prefName ) {
      SharedPreferences sharedPreferences = mContext
              .getSharedPreferences ( Contract.SWITCH_PREFS, Context.MODE_PRIVATE );

      if ( sharedPreferences == null ) {
         Log.e ( TAG, "saveToPrefs: app shared prefs are null" );
         return null;
      }

      if ( !sharedPreferences.contains ( prefName ) ) {
         Log.e ( TAG, "saveToPrefs: App shared prefs does not contain pref : " + prefName );
         return null;
      }

      return sharedPreferences;
   }

   /**
    * Get the shared preferences for the app.
    * @return the shared preference.
    */
   private SharedPreferences getSharedPrefs () {
      SharedPreferences sharedPreferences = mContext
              .getSharedPreferences ( Contract.SWITCH_PREFS, Context.MODE_PRIVATE );

      if ( sharedPreferences == null ) {
         Log.e ( TAG, "saveToPrefs: app shared prefs are null" );
         return null;
      }

      return sharedPreferences;
   }

   /**
    * Check if the preference is a Set or not.
    *
    * @param prefName
    *         the name of the preference.
    *
    * @return true if the shared preference is a set false otherwise.
    */
   private boolean isPrefSet ( String prefName ) {

      // if shared preference is set
      if ( prefName.equals ( Contract.WHEN_IM_AT ) )
         return true;

//      else if ( prefName.equals ( Contract.WHEN_IM_DRIVING_AT ) )
//         return true;

      return false;
   }

   /**
    * Check if the preference is a switch or not.
    *
    * @param prefName
    *         the name of the pref to check.
    *
    * @return true if pref is switch, false otherwise.
    */
   private boolean isPrefSwitch ( String prefName ) {

      if ( prefName.equals ( Contract.WHEN_IM_WALKING ) )
         return true;
      else if ( prefName.equals ( Contract.WHEN_IM_RUNJOG ) )
         return true;
      else if ( prefName.equals ( Contract.WHEN_IM_DRIVING ) )
         return true;

      return false;
   }

   /**
    * Check if preference exists.
    *
    * @param prefName
    *         the name of the preference.
    *
    * @return true if pref exists, false otherwise.
    */
   private boolean checkPref ( String prefName ) {
      SharedPreferences sharedPreference = mContext
              .getSharedPreferences ( Contract.SWITCH_PREFS, Context.MODE_PRIVATE );
      return sharedPreference.contains ( prefName );
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getter and Setter methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Check if when I'm walking switch is active or not.
    *
    * @return true if active, false otherwise.
    */
   public boolean isWhenImWalking () {
      return whenImWalking;
   }

   /**
    * Set the when I'm walking switch.
    *
    * @param whenImWalking
    *         the boolean val for switch.
    */
   public void setWhenImWalking ( boolean whenImWalking ) {
      this.whenImWalking = whenImWalking;
      saveToPrefs ( Contract.WHEN_IM_WALKING, whenImWalking, null );
   }

   /**
    * Check if when I'm running/jogging switch is active or not.
    *
    * @return true if active, false otherwise.
    */
   public boolean isWhenImRunJog () {
      return whenImRunJog;
   }

   /**
    * Set the if when I'm running/jogging switch.
    *
    * @param whenImRunJog
    *         the boolean value for the switch.
    */
   public void setWhenImRunJog ( boolean whenImRunJog ) {
      this.whenImRunJog = whenImRunJog;
      saveToPrefs ( Contract.WHEN_IM_RUNJOG, whenImRunJog, null );
   }

   /**
    * Check if when I'm driving switch is active or not.
    *
    * @return true if active, false otherwise.
    */
   public boolean isWhenImDriving () {
      return whenImDriving;
   }

   /**
    * Set the if I'm driving switch.
    *
    * @param whenImDriving
    *         the boolean value for the switch.
    */
   public void setWhenImDriving ( boolean whenImDriving ) {
      this.whenImDriving = whenImDriving;
      saveToPrefs ( Contract.WHEN_IM_DRIVING, whenImDriving, null );
   }

   /**
    * Get the when I'm at Set.
    *
    * @return String set of when I'm at with format ( coords<=>switchVal )
    */
   public Set < String > getWhenImAt () {
      return whenImAt;
   }

   /**
    * Set the when I'm at Set.
    *
    * @param whenImAt
    *         the when i am at.
    */
   public void setWhenImAt ( Set < String > whenImAt ) {
      this.whenImAt = whenImAt;
   }

   /**
    * Add a new string to WhenImAt Set.
    *
    * @param coords
    *         the coords to add.
    * @param switchVal
    *         the switch val.
    */
   public void addToWhenImAt ( String coords, String switchVal ) {
      String val = coords + Contract.WHEN_IM_AT_SPLITTER + switchVal;
      whenImAt.add ( val );
      saveToPrefs ( Contract.WHEN_IM_AT, val, null );
   }

   /**
    * Remove a value from WhenImAt Set.
    *
    * @param coords
    *         the coords to remove.
    * @param switchVal
    *         the switch value to remove.
    */
   public void removeFromWhenImAt ( String coords, String switchVal ) {
      String val = coords + Contract.WHEN_IM_AT_SPLITTER + switchVal;
      for ( String whenImAt : getWhenImAt () ) {
         if ( whenImAt.equals ( val ) ) {
            getWhenImAt ().remove ( val );
            removeFromPrefSet ( Contract.WHEN_IM_AT, val );
         }
      }
   }

   /**
    * Update the WhenImAt Set.
    *
    * @param oldCoords
    *         the old coordinate value.
    * @param oldSwitchVal
    *         the old switch value.
    * @param newCoords
    *         the new coordinate value.
    * @param newSwitchVal
    *         the new switch value.
    */
   public void updateWhenImAt ( String oldCoords, String oldSwitchVal
           , String newCoords, String newSwitchVal ) {

      String valOld = oldCoords + Contract.WHEN_IM_AT_SPLITTER + oldSwitchVal;
      String valNew = newCoords + Contract.WHEN_IM_AT_SPLITTER + newSwitchVal;

      if ( !updateSetVal ( Contract.WHEN_IM_AT, valOld, valNew ) )
         Log.e (
                 TAG,
                 "updateWhenImAt: Could not update WhenImAt Set Value from : " +
                         "'" + valOld + "' , to :'" + valNew + "'"
         );

   }

}
