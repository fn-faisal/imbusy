package com.appzspot.imbusy;

import android.content.Context;
import android.util.Log;

import com.appzspot.imbusy.events.AppLaunchEvent;
import com.appzspot.imbusy.model.dao.MainActionDa;
import com.appzspot.imbusy.model.dto.MainAction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

public class AppEvent {

   private static final String TAG = "AppEvent";

   private Context mContext;

   public AppEvent ( Context context ) {
      mContext = context;
      EventBus.getDefault ().register ( this );
   }

   @Subscribe
   public void appStartEvent ( AppLaunchEvent app ) {
      Log.d ( TAG , "app  launched." );
      MainActionDa.initMainActionData ( mContext );
   }

   @Override
   protected void finalize () throws Throwable {
      super.finalize ();
      EventBus.getDefault ().unregister ( this );
   }
}
