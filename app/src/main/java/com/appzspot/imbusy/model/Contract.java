package com.appzspot.imbusy.model;

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

public class Contract {

   // app pref name in shared prefs.
   public static final String SWITCH_PREFS = "imbsy_prefs";

   // switch prefs.
   public static final String WHEN_IM_WALKING = "pref_when_im_walking";
   public static final String WHEN_IM_RUNJOG = "pref_when_im_runjog";
   public static final String WHEN_IM_DRIVING = "pref_when_im_driving";

   // set prefs.

   public static final String WHEN_IM_AT = "pref_when_im_at";
   public static final String WHEN_IM_AT_SPLITTER = "<=>";
   public static final int WHEN_IM_AT_COORDS_POS = 1;
   public static final int WHEN_IM_AT_SWITCH_POS = 2;

   ///////////////////////////////////////////////////////////////////////////
   // Database.
   ///////////////////////////////////////////////////////////////////////////

   // tables.
   public static final String TABLE_MAIN_ACTION = "main_action";
   public static final String TABLE_CALL_ACTION = "call_action";
   public static final String TABLE_LOCATION_ACTION = "location_action";
   public static final String TABLE_MESSAGE_ACTION = "message_action";
   public static final String TABLE_SOCIAL_MEDIA_ACTION = "social_media_action";

   // columns.
   // common cols.
   public static final String COL_DB_ID = "_id";
   public static final String COL_TOGGLE = "toggle";

   // main action cols
   public static final String COL_MA_ACTION = "action";
   public static final String COL_MA_WAIT = "wait";

   // call action cols.
   public static final String COL_CA_PHONE_STATUS = "phone_status";
   public static final String COL_CA_CUSTOM_VOICE_MAIL = "custom_voice_mail";
   public static final String COL_CA_END_CALL = "end_call";

   // message action.
   public static final String COL_MSGA_AUTO_RESPONSE = "auto_response";
   public static final String COL_MSGA_MESSAGE = "message";

   // location action.
   public static final String COL_LA_COORDS = "coords";
   public static final String COL_LA_NAME = "name";
   public static final String COL_LA_TOGGLE = "toggle";

   // social media action.
   // facebook.
   public static final String COL_SMA_FB_MSG_TGL = "fb_msg_tgl";
   public static final String COL_SMA_FB_MSG_RESP = "fb_msg_resp";
   public static final String COL_SMA_FB_CALL_TGL = "fb_call_tgl";
   public static final String COL_SMA_FB_CALL_RESP = "db_call_resp_uri";

   // twitter.
   public static final String COL_SMA_TWT_MSG_TGL = "twt_msg_tgl";
   public static final String COL_SMA_TWT_MSG_RESP = "twt_msg_resp";
   public static final String COL_SMA_TWT_AUTO_TGL = "twt_auto_tgl";
   public static final String COL_SMA_TWT_AUTO_RESP = "twt_auto_resp";

   // whatsapp.
   public static final String COL_SMA_WAPP_MSG_TGL = "wapp_msg_tgl";
   public static final String COL_SMA_WAPP_MSG_RESP = "wapp_msg_resp";
   public static final String COL_SMA_WAPP_CALL_TGL = "wapp_call_tgl";
   public static final String COL_SMA_WAPP_CALL_RESP = "wapp_call_resp_uri";



}
