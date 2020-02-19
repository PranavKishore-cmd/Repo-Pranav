package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class FindUsersRequest
{

   public FindUsersRequest()
   {
   }

   public FindUsersRequest(String searchString)
   {
      this.searchString = searchString;
   }

   String searchString;

   public String getSearchString()
   {
      return searchString;
   }

   public void setSearchString(String searchString)
   {
      this.searchString = searchString;
   }
}
