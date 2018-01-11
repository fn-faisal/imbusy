package com.appzspot.imbusy;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.appzspot.imbusy.events.AppLaunchEvent;
import com.orm.SugarApp;
import com.orm.SugarContext;

import org.greenrobot.eventbus.EventBus;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Muhammad Faisal Nadeem on 10/16/2017.
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

public class App extends SugarApp {

   @Override
   public void onCreate () {
      super.onCreate ();
      CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
              .setDefaultFontPath("fonts/PoiretOne-Regular.ttf")
              .setFontAttrId(R.attr.fontPath)
              .build()
      );

      new AppEvent ( this );
      EventBus.getDefault ().post ( new AppLaunchEvent () );
   }

}
