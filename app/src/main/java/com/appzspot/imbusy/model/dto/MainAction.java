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
public class MainAction extends SugarRecord {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   @Column ( name = Contract.COL_MA_ACTION )
   private String action;

   @Column ( name = Contract.COL_TOGGLE )
   private boolean toggle;

   @Column ( name = Contract.COL_MA_WAIT )
   private int wait;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public MainAction () {}

   public MainAction ( String action, boolean toggle, int wait ) {
      this.action = action;
      this.toggle = toggle;
      this.wait = wait;
   }

   ///////////////////////////////////////////////////////////////////////////
   // Getters and Setters.
   ///////////////////////////////////////////////////////////////////////////

   public String getAction () {
      return action;
   }

   public void setAction ( String action ) {
      this.action = action;
   }

   public boolean isToggle () {
      return toggle;
   }

   public void setToggle ( boolean toggle ) {
      this.toggle = toggle;
   }

   public int getWait () {
      return wait;
   }

   public void setWait ( int wait ) {
      this.wait = wait;
   }
}
