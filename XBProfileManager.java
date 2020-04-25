package com.payment.xborder.model.onboard;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "XBPROFILE_MANAGER")
public class XBProfileManager {

	 @NotBlank
	 private String entityName;

	 private String comments;
	 
	 @NotBlank
	 private String companyRefId;
	 
	 private String profileType;
	 
	 private List<ServiceOffered> servicesOffered;

}