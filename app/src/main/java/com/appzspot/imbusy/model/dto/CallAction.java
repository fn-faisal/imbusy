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

@Table ( name = Contract.TABLE_CALL_ACTION )
public class CallAction extends SugarRecord {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   @Column ( name = Contract.COL_TOGGLE )
   private boolean actionToggle;

   @Column ( name = Contract.COL_CA_PHONE_STATUS )
   private String phoneStatus;

   @Column ( name = Contract.COL_CA_CUSTOM_VOICE_MAIL )
   private String customVoiceMail;

   @Column ( name = Contract.COL_CA_END_CALL )
   private boolean endCall;

   @Column ( name = Contract.TABLE_MAIN_ACTION )
   private MainAction mainActionRef;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public CallAction () {}

   public CallAction ( String phoneStatus, String customVoiceMail, boolean endCall ,
                       boolean actionToggle,
                       MainAction mainAction ) {
      this.phoneStatus = phoneStatus;
      this.customVoiceMail = customVoiceMail;
      this.endCall = endCall;
      this.mainActionRef = mainAction;
      this.actionToggle = actionToggle;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getters and Setters.
   ///////////////////////////////////////////////////////////////////////////


   public boolean isActionToggle () {
      return actionToggle;
   }

   public void setActionToggle ( boolean actionToggle ) {
      this.actionToggle = actionToggle;
   }

   public String getPhoneStatus () {
      return phoneStatus;
   }

   public void setPhoneStatus ( String phoneStatus ) {
      this.phoneStatus = phoneStatus;
   }

   public String getCustomVoiceMail () {
      return customVoiceMail;
   }

   public void setCustomVoiceMail ( String customVoiceMail ) {
      this.customVoiceMail = customVoiceMail;
   }

   public boolean isEndCall () {
      return endCall;
   }

   public void setEndCall ( boolean endCall ) {
      this.endCall = endCall;
   }

   public MainAction getMainActionRef () {
      return mainActionRef;
   }

   public void setMainActionRef ( MainAction mainActionRef ) {
      this.mainActionRef = mainActionRef;
   }
}
