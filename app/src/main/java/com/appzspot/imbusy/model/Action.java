package com.appzspot.imbusy.model;

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

public class Action {

   // waiting period.
   public static final int ACTION_WAIT_NONE = 0;
   public static final int ACTION_WAIT_30_SEC = 1;
   public static final int ACTION_WAIT_1_MIN = 2;
   public static final int ACTION_WAIT_2_MIN = 3;
   public static final int ACTION_WAIT_3_MIN = 4;
   public static final int ACTION_WAIT_4_MIN = 5;
   public static final int ACTION_WAIT_5_MIN = 6;

   // action names.
   public static final String ACTION_WHEN_WALKING_NAME = "action_when_walking";
   public static final String ACTION_WHEN_RUNJOG_NAME = "action_when_runjog";
   public static final String ACTION_WHEN_DRIVING_NAME = "action_when_driving";
   public static final String ACTION_WHEN_AT_NAME = "action_when_at";


   public static String getActionString ( String actionName ) {

      if ( actionName == ACTION_WHEN_WALKING_NAME )
         return "when I'm walking";
      else if ( actionName == ACTION_WHEN_RUNJOG_NAME )
         return "when I'm running/jogging";
      else if ( actionName == ACTION_WHEN_DRIVING_NAME )
         return "when I'm driving";
      else if ( actionName == ACTION_WHEN_AT_NAME )
         return "when I'm at";

      return "";
   }

}
