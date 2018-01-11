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

@Table ( name = Contract.TABLE_MESSAGE_ACTION )
public class MessageAction extends SugarRecord {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   @Column ( name = Contract.COL_MSGA_AUTO_RESPONSE )
   private boolean autoResponse;

   @Column ( name = Contract.COL_MSGA_MESSAGE )
   private String message;

   // make unique for 1 to 1 relation.
   @Column ( name = Contract.TABLE_MAIN_ACTION )
   private MainAction mainActionRef;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public MessageAction () {
   }

   public MessageAction ( boolean autoResponse, String message, MainAction mainAction ) {
      this.autoResponse = autoResponse;
      this.message = message;
      this.mainActionRef = mainAction;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getter and Setter methods.
   ///////////////////////////////////////////////////////////////////////////

   public MainAction getMainActionRef () {
      return mainActionRef;
   }

   public void setMainActionRef ( MainAction mainActionRef ) {
      this.mainActionRef = mainActionRef;
   }

   public boolean isAutoResponse () {
      return autoResponse;
   }

   public void setAutoResponse ( boolean autoResponse ) {
      this.autoResponse = autoResponse;
   }

   public String getMessage () {
      return message;
   }

   public void setMessage ( String message ) {
      this.message = message;
   }
}
