package com.appzspot.imbusy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.appzspot.imbusy.model.Action;
import com.appzspot.imbusy.model.Contract;
import com.appzspot.imbusy.model.dao.CallActionDa;
import com.appzspot.imbusy.model.dao.MainActionDa;
import com.appzspot.imbusy.model.dao.MessageActionDa;
import com.appzspot.imbusy.model.dao.SocialMediaActionDa;
import com.appzspot.imbusy.model.dto.CallAction;
import com.appzspot.imbusy.model.dto.MainAction;
import com.appzspot.imbusy.model.dto.MessageAction;
import com.appzspot.imbusy.model.dto.SocialMediaAction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImbsyDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImbsyDialogFragment extends DialogFragment implements View.OnClickListener{

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   private static final String TAG = "ImbsyDialogFragment";

   private static final String ARG_WHEN_IM = "when_im";
   private String mWheIm;

   // widgets.
   private RadioGroup mWaitRdoGp;

   private Button mDoneBtn;

   private CheckBox mCustomVoiceMailChkBx;
   private CheckBox mEndCalChkBx;
   private CheckBox mMsgResponseAutoChkBx;
   private CheckBox mFbCallResponseChkBx;
   private CheckBox mFbChatResponseChkBx;
   private CheckBox mTwtChatResponseChkBx;
   private CheckBox mTwtAutoTweetChkBx;
   private CheckBox mWappChatResponseChkBx;
   private CheckBox mWappCallResponseChkBx;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public ImbsyDialogFragment () {
      // Required empty public constructor
   }

   ///////////////////////////////////////////////////////////////////////////
   // Fragment methods.
   ///////////////////////////////////////////////////////////////////////////


   @Override
   public void onActivityCreated ( Bundle savedInstanceState ) {
      super.onActivityCreated ( savedInstanceState );
      getDialog().getWindow()
              .getAttributes().windowAnimations = R.style.DialogAnimation;
   }

   @Override
   public void onCreate ( Bundle savedInstanceState ) {
      super.onCreate ( savedInstanceState );
      if ( getArguments () != null ) {
         mWheIm = getArguments ().getString ( ARG_WHEN_IM );
      }
   }

   @Override
   public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

      View view = inflater.inflate ( R.layout.imbsy_dialog , container );

      // set fragment title.
      setFragTitle ( mWheIm );

      // initialize widgets.
      initWidgets ( view );

      // initialize widget values.
      initWidgetValues ();

      // initialize listeners.
      initListeners ();

      return view;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Helper methods.
   ///////////////////////////////////////////////////////////////////////////

   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @param whenIm
    *         when I am param.
    *
    * @return A new instance of fragment ImbsyDialogFragment.
    */
   public static ImbsyDialogFragment newInstance ( String whenIm ) {
      ImbsyDialogFragment fragment = new ImbsyDialogFragment ();
      Bundle args = new Bundle ();
      args.putString ( ARG_WHEN_IM, whenIm );
      fragment.setArguments ( args );
      return fragment;
   }

   /**
    * Initialize widgets.
    * @param view the parent view.
    */
   private void initWidgets ( View view ) {
      mWaitRdoGp = ( RadioGroup ) view.findViewById ( R.id.imbsy_dialogue_wait );

      mCustomVoiceMailChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_call_action_custom_voicemail );
      mEndCalChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_call_action_end_call );

      mMsgResponseAutoChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_msg_resp );

      mFbCallResponseChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_fb_call_response );
      mFbChatResponseChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_fb_chat_response );

      mTwtChatResponseChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_twt_chat_response );
      mTwtAutoTweetChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_twt_tweet );

      mWappChatResponseChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_wapp_chat_response );
      mWappCallResponseChkBx = ( CheckBox ) view.findViewById ( R.id.imbsy_dialogue_social_wapp_call_response );

      mDoneBtn = ( Button ) view.findViewById ( R.id.imbsy_dialogue_done );
   }

   private void initWidgetValues () {

      // get main action.
      MainAction action = new MainActionDa (  ).readActionByName ( mWheIm );

      if ( action != null ) {
         // set the wait period.
         switch ( action.getWait () ) {
            case Action.ACTION_WAIT_30_SEC :
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_30_sec );
               break;
            case Action.ACTION_WAIT_1_MIN :
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_1_min );
               break;
            case Action.ACTION_WAIT_2_MIN :
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_2_min );
               break;
            case Action.ACTION_WAIT_3_MIN:
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_3_min );
               break;
            case Action.ACTION_WAIT_4_MIN:
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_4_min );
               break;
            case Action.ACTION_WAIT_5_MIN:
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_5_min );
               break;
            case Action.ACTION_WAIT_NONE:
               mWaitRdoGp.check ( R.id.imbsy_dialogue_wait_none );
               break;
         }

         // get call action
         CallAction callAction = new CallActionDa (  ).readActionForMA ( mWheIm );

         if ( callAction != null ) {
            // set the phone custom vmail location.
            if ( callAction.getCustomVoiceMail () != null ) {
               if ( !callAction.getCustomVoiceMail ().equals ( "" ) )
                  mCustomVoiceMailChkBx.setChecked ( true );
               else
                  mCustomVoiceMailChkBx.setChecked ( false );
            }
            else
               mCustomVoiceMailChkBx.setChecked ( false );

            // terminate call check box.
            if ( callAction.isEndCall () )
               mEndCalChkBx.setChecked ( true );
            else
               mEndCalChkBx.setChecked ( false );
         }
         else
            Log.e ( TAG, "initWidgetValues: Call action is null for MA : "+mWheIm );

         // get message action.
         MessageAction messageAction = new MessageActionDa ().read ( action.getAction () );
         if ( messageAction != null ) {
            if ( messageAction.isAutoResponse () )
               mMsgResponseAutoChkBx.setChecked ( true );
            else
               mMsgResponseAutoChkBx.setChecked ( false );
         } else
            Log.e ( TAG, "initWidgetValues: Message action is null for MA : "+mWheIm );

         // get social media action.
         SocialMediaAction socialMediaAction = new SocialMediaActionDa (  ).read ( action.getAction () );
         if ( socialMediaAction != null ) {

            // set fb chat resp tgl.
            if ( socialMediaAction.isFbMsgToggle () )
               mFbChatResponseChkBx.setChecked ( true );
            else
               mFbChatResponseChkBx.setChecked ( false );

            // set fb call resp tgl.
            if ( socialMediaAction.isFbCallToggle () )
               mFbCallResponseChkBx.setChecked ( true );
            else
               mFbCallResponseChkBx.setChecked ( false );

            // set twitter chat resp.
            if ( socialMediaAction.isTwtMsgToggle () )
               mTwtChatResponseChkBx.setChecked ( true );
            else
               mTwtChatResponseChkBx.setChecked ( false );

            // set twitter call resp.
            if ( socialMediaAction.isTwtAutoTweet () )
               mTwtAutoTweetChkBx.setChecked ( true );
            else
               mTwtAutoTweetChkBx.setChecked ( false );

            // set wapp chat resp.
            if ( socialMediaAction.isWappMsgToggle () )
               mWappChatResponseChkBx.setChecked ( true );
            else
               mWappChatResponseChkBx.setChecked ( false );

            // set wapp call resp.
            if ( socialMediaAction.isWappCallToggle () )
               mWappCallResponseChkBx.setChecked ( true );
            else
               mWappCallResponseChkBx.setChecked ( false );

         } else
            Log.e ( TAG, "initWidgetValues: Social media action is null for MA : "+mWheIm );

      }
      else
         Log.e ( TAG, "initWidgetValues: action for : "+mWheIm+" is null." );

   }

   private void setFragTitle ( String whenIm ) {
      if ( whenIm.equals ( Action.ACTION_WHEN_WALKING_NAME ) ) {
         getDialog ().setTitle ( "When I'm walking" );
      }
      else if ( whenIm.equals ( Action.ACTION_WHEN_RUNJOG_NAME ) ) {
         getDialog ().setTitle ( "When I'm running/jogging" );
      }
      else if ( whenIm.equals ( Action.ACTION_WHEN_DRIVING_NAME ) ) {
         getDialog ().setTitle ( "When I'm driving" );
      }
//      else if ( whenIm.equals ( Contract.WHEN_IM_AT ) ) {
//         getDialog ().setTitle ( "When I'm at" );
//      }
   }

   ///////////////////////////////////////////////////////////////////////////
   // Listeners
   ///////////////////////////////////////////////////////////////////////////

   private void initListeners () {
      mDoneBtn.setOnClickListener ( this );

      // set radio listener.
      mWaitRdoGp.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( RadioGroup radioGroup, @IdRes int i ) {

            switch ( i ) {
               case R.id.imbsy_dialogue_wait_none:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_NONE ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_30_sec:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_30_SEC ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_1_min:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_1_MIN ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_2_min:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_2_MIN ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_3_min:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_3_MIN ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_4_min:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_4_MIN ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
               case R.id.imbsy_dialogue_wait_5_min:
                  if ( !new MainActionDa ().updateActionWait ( mWheIm , Action.ACTION_WAIT_5_MIN ) )
                     Log.e ( TAG, "onCheckedChanged: Error updating wait period for MA : "+mWheIm );
                  break;
            }

         }
      } );

      // custom voice mail toggle.
      mCustomVoiceMailChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            MainAction mainAction = new MainActionDa ().readActionByName ( mWheIm );
            if ( mainAction != null ) {
               if ( checked ) {
                  if ( !new CallActionDa ().updateToggle ( mWheIm , true ) ) {
                     Snackbar.make ( mCustomVoiceMailChkBx, " Could not update custom voice mail response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new CallActionDa ().updateToggle ( mWheIm , false ) ) {
                     Snackbar.make ( mCustomVoiceMailChkBx, " Could not update custom voice mail response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }
            } else
               Log.e ( TAG, "onCheckedChanged: Main action '"+mWheIm+"' is null." );
         }
      } );

      // terminate call toggle.
      mEndCalChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            MainAction mainAction = new MainActionDa ().readActionByName ( mWheIm );
            if ( mainAction != null ) {
               if ( checked ) {
                  if ( !new CallActionDa ().updateEndCallToggle ( mWheIm , true ) ){
                     Snackbar.make ( mEndCalChkBx, " Could not toggle auto terminate call. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new CallActionDa ().updateEndCallToggle ( mWheIm , false ) ){
                     Snackbar.make ( mEndCalChkBx, " Could not toggle auto terminate call. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            } else
               Log.e ( TAG, "onCheckedChanged: Main action '"+mWheIm+"' is null. " );
         }
      } );

      // facebook chat response check change listener.
      mFbChatResponseChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_FB_MSG_TGL , true ) ) {
                     Snackbar.make ( mFbChatResponseChkBx, " Could not toggle facebook message auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_FB_MSG_TGL , false ) ) {
                     Snackbar.make ( mFbChatResponseChkBx, " Could not toggle facebook message auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );

         }
      } );

      // facebook call response toggle.
      mFbCallResponseChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_FB_CALL_TGL , true ) ) {
                     Snackbar.make ( mFbCallResponseChkBx, " Could not toggle facebook call auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_FB_CALL_TGL , false ) ) {
                     Snackbar.make ( mFbCallResponseChkBx, " Could not toggle facebook call auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );
         }
      } );

      // twitter chat resp toggle.
      mTwtChatResponseChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_TWT_MSG_TGL , true ) ) {
                     Snackbar.make ( mTwtChatResponseChkBx, " Could not toggle facebook message auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_TWT_MSG_TGL , false ) ) {
                     Snackbar.make ( mTwtChatResponseChkBx, " Could not toggle facebook message auto response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );
         }
      } );

      // twitter auto tweet toggle.
      mTwtAutoTweetChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_TWT_MSG_TGL , true ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle twitter auto tweet response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_TWT_MSG_TGL , false ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle twitter auto tweet response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );
         }
      } );

      // wapp chat response tgl.
      mWappChatResponseChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_WAPP_MSG_TGL , true ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle wapp chat response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_WAPP_MSG_TGL , false ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle wapp chat response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );
         }
      } );

      // wapp call response tgl.
      mWappCallResponseChkBx.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
         @Override
         public void onCheckedChanged ( CompoundButton compoundButton, boolean checked ) {
            SocialMediaAction sma = new SocialMediaActionDa ().read ( mWheIm );
            if ( sma != null ) {
               if ( checked ) {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_WAPP_CALL_TGL , true ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle wapp call response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( false );
                  }
               }
               else {
                  if ( !new SocialMediaActionDa ().updateToggle ( mWheIm , Contract.COL_SMA_WAPP_CALL_TGL , false ) ) {
                     Snackbar.make ( mTwtAutoTweetChkBx, " Could not toggle wapp call response. ", Snackbar.LENGTH_SHORT )
                             .show ();
                     compoundButton.setChecked ( true );
                  }
               }

            }
            else
               Log.e ( TAG, "onCheckedChanged: Social Media Action is null for MA : "+mWheIm );
         }
      } );

   }

   @Override
   public void onClick ( View view ) {
      // if the done button is pressed.
      if ( view == mDoneBtn ) {
         this.getDialog ().dismiss ();
      }

   }
}
