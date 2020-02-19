package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class FindUsersResponse
{

   public FindUsersResponse()
   {
   }

   List <UserDetails> usersDetailsList;

}



