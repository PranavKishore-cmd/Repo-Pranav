/**
 * 
 */
package com.payment.xborder.model.onboard.ws;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.model.onboard.BeneficiaryInformationDetails;

/**
 * 
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateBeneficiaryOwnerKycRequest {

	@NotBlank
    private String companyRefID;
	
	@NotBlank
	private List<BeneficiaryInformationDetails> beneficiaryDetailsList;
	
	public UpdateBeneficiaryOwnerKycRequest() {
		
	}
	
	/**
	 * @param companyRefID
	 * @param beneficiaryDetailsList
	 */
	public UpdateBeneficiaryOwnerKycRequest(@NotBlank String companyRefID,
			@NotBlank List<BeneficiaryInformationDetails> beneficiaryDetailsList) {
		super();
		this.companyRefID = companyRefID;
		this.beneficiaryDetailsList = beneficiaryDetailsList;
	}

	/**
	 * @return the companyRefID
	 */
	public String getCompanyRefID() {
		return companyRefID;
	}

	/**
	 * @param companyRefID the companyRefID to set
	 */
	public void setCompanyRefID(String companyRefID) {
		this.companyRefID = companyRefID;
	}

	/**
	 * @return the beneficiaryDetailsList
	 */
	public List<BeneficiaryInformationDetails> getBeneficiaryDetailsList() {
		return beneficiaryDetailsList;
	}

	/**
	 * @param beneficiaryDetailsList the beneficiaryDetailsList to set
	 */
	public void setBeneficiaryDetailsList(List<BeneficiaryInformationDetails> beneficiaryDetailsList) {
		this.beneficiaryDetailsList = beneficiaryDetailsList;
	}
	
	
}
