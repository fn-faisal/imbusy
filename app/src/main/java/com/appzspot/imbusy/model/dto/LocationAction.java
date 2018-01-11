package com.appzspot.imbusy.model.dto;

import com.appzspot.imbusy.model.Contract;

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

//@Table ( name = Contract.TABLE_LOCATION_ACTION , id = Contract.COL_DB_ID )
public class LocationAction {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   //@Column ( name = Contract.COL_LA_COORDS )
   private String coordinates;

   //@Column ( name = Contract.COL_LA_NAME )
   private String name;

   //@Column ( name = Contract.COL_LA_TOGGLE )
   private boolean toggle;

   // many to one relation.
   //@Column ( name = Contract.TABLE_MAIN_ACTION )
   private MainAction mainAction;

   ///////////////////////////////////////////////////////////////////////////
   // Constructor.
   ///////////////////////////////////////////////////////////////////////////

   public LocationAction () {}

   public LocationAction ( String coordinates, String name, boolean toggle,
                           MainAction mainAction ) {
      this.coordinates = coordinates;
      this.name = name;
      this.toggle = toggle;
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

   public String getCoordinates () {
      return coordinates;
   }

   public void setCoordinates ( String coordinates ) {
      this.coordinates = coordinates;
   }

   public String getName () {
      return name;
   }

   public void setName ( String name ) {
      this.name = name;
   }

   public boolean isToggle () {
      return toggle;
   }

   public void setToggle ( boolean toggle ) {
      this.toggle = toggle;
   }
}
