package com.appzspot.imbusy.model.dto;

import com.appzspot.imbusy.model.Contract;
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by Muhammad Faisal Nadeem on 10/17/2017.
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

@Table ( name = Contract.TABLE_SOCIAL_MEDIA_ACTION )
public class SocialMediaAction extends SugarRecord{

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   @Column ( name = Contract.COL_SMA_FB_MSG_TGL )
   private boolean fbMsgToggle;
   @Column ( name = Contract.COL_SMA_FB_MSG_RESP )
   private String fbMsgResp;
  @Column ( name = Contract.COL_SMA_FB_CALL_TGL )
   private boolean fbCallToggle;
   @Column ( name = Contract.COL_SMA_FB_CALL_RESP )
   private String fbCallMsgUri;  // a media uri to play as call response.

   @Column ( name = Contract.COL_SMA_TWT_MSG_TGL )
   private boolean twtMsgToggle;
   @Column ( name = Contract.COL_SMA_TWT_MSG_RESP )
   private String twtMsgResp;
   @Column ( name = Contract.COL_SMA_TWT_AUTO_TGL )
   private boolean twtAutoTweet;
   @Column ( name = Contract.COL_SMA_TWT_AUTO_RESP )
   private String twtMsgTweet;

   @Column ( name = Contract.COL_SMA_WAPP_MSG_TGL )
   private boolean wappMsgToggle;
   @Column ( name = Contract.COL_SMA_WAPP_MSG_RESP )
   private String wappMsgResp;
   @Column ( name = Contract.COL_SMA_WAPP_CALL_TGL )
   private boolean wappCallToggle;
   @Column ( name = Contract.COL_SMA_WAPP_CALL_RESP )
   private String wappCallMsgUri;   // a media uri to play as call response.

   // 1 to 1.
   @Column ( name = Contract.TABLE_MAIN_ACTION )
   private MainAction mainAction;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public SocialMediaAction () {}

   public SocialMediaAction (
           boolean fbMsgToggle, String fbMsgResp, boolean fbCallToggle, String fbCallMsgUri,
           boolean twtMsgToggle, String twtMsgResp, boolean twtAutoTweet, String twtMsgTweet,
           boolean wappMsgToggle, String wappMsgResp, boolean wappCallToggle, String wappCallMsgUri,
           MainAction mainAction
   ) {
      this.fbMsgToggle = fbMsgToggle;
      this.fbMsgResp = fbMsgResp;
      this.fbCallToggle = fbCallToggle;
      this.fbCallMsgUri = fbCallMsgUri;
      this.twtMsgToggle = twtMsgToggle;
      this.twtMsgResp = twtMsgResp;
      this.twtAutoTweet = twtAutoTweet;
      this.twtMsgTweet = twtMsgTweet;
      this.wappMsgToggle = wappMsgToggle;
      this.wappMsgResp = wappMsgResp;
      this.wappCallToggle = wappCallToggle;
      this.wappCallMsgUri = wappCallMsgUri;
      this.mainAction = mainAction;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getters and Setters.
   ///////////////////////////////////////////////////////////////////////////

   public MainAction getMainAction () {
      return mainAction;
   }

   public void setMainAction ( MainAction mainAction ) {
      this.mainAction = mainAction;
   }

   public boolean isFbMsgToggle () {
      return fbMsgToggle;
   }

   public void setFbMsgToggle ( boolean fbMsgToggle ) {
      this.fbMsgToggle = fbMsgToggle;
   }

   public String getFbMsgResp () {
      return fbMsgResp;
   }

   public void setFbMsgResp ( String fbMsgResp ) {
      this.fbMsgResp = fbMsgResp;
   }

   public boolean isFbCallToggle () {
      return fbCallToggle;
   }

   public void setFbCallToggle ( boolean fbCallToggle ) {
      this.fbCallToggle = fbCallToggle;
   }

   public String getFbCallMsgUri () {
      return fbCallMsgUri;
   }

   public void setFbCallMsgUri ( String fbCallMsgUri ) {
      this.fbCallMsgUri = fbCallMsgUri;
   }

   public boolean isTwtMsgToggle () {
      return twtMsgToggle;
   }

   public void setTwtMsgToggle ( boolean twtMsgToggle ) {
      this.twtMsgToggle = twtMsgToggle;
   }

   public String getTwtMsgResp () {
      return twtMsgResp;
   }

   public void setTwtMsgResp ( String twtMsgResp ) {
      this.twtMsgResp = twtMsgResp;
   }

   public boolean isTwtAutoTweet () {
      return twtAutoTweet;
   }

   public void setTwtAutoTweet ( boolean twtAutoTweet ) {
      this.twtAutoTweet = twtAutoTweet;
   }

   public String getTwtMsgTweet () {
      return twtMsgTweet;
   }

   public void setTwtMsgTweet ( String twtMsgTweet ) {
      this.twtMsgTweet = twtMsgTweet;
   }

   public boolean isWappMsgToggle () {
      return wappMsgToggle;
   }

   public void setWappMsgToggle ( boolean wappMsgToggle ) {
      this.wappMsgToggle = wappMsgToggle;
   }

   public String getWappMsgResp () {
      return wappMsgResp;
   }

   public void setWappMsgResp ( String wappMsgResp ) {
      this.wappMsgResp = wappMsgResp;
   }

   public boolean isWappCallToggle () {
      return wappCallToggle;
   }

   public void setWappCallToggle ( boolean wappCallToggle ) {
      this.wappCallToggle = wappCallToggle;
   }

   public String getWappCallMsgUri () {
      return wappCallMsgUri;
   }

   public void setWappCallMsgUri ( String wappCallMsgUri ) {
      this.wappCallMsgUri = wappCallMsgUri;
   }

}
