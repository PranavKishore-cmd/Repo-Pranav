package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.CompanyStatus;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChangeCompanyStatusRequest
{

    @NotBlank
    private String companyRefID;

    @NotBlank
    private CompanyStatus newStatus;

    public ChangeCompanyStatusRequest() {

    }

   public ChangeCompanyStatusRequest(
         @NotBlank String companyRefID,
         @NotBlank CompanyStatus newStatus
   )
   {
      this.companyRefID = companyRefID;
      this.newStatus = newStatus;
   }

   public String getCompanyRefID()
   {
      return companyRefID;
   }

   public void setCompanyRefID(String companyRefID)
   {
      this.companyRefID = companyRefID;
   }

   public CompanyStatus getNewStatus()
   {
      return newStatus;
   }

   public void setNewStatus(CompanyStatus newStatus)
   {
      this.newStatus = newStatus;
   }
}
