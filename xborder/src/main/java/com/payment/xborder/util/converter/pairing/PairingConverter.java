package com.payment.xborder.util.converter.pairing;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.model.pairing.ws.Pairing;
import com.payment.xborder.model.pairing.ws.PairingRequest;

public class PairingConverter {

	public static Pairing wsToModel(PairingRequest pairingRequest) {
    	return prepareProfileInfo(pairingRequest);
	}

	public static Pairing wsToModelUpdateProfile(@Valid @NotNull PairingRequest pairingRequest) {
		return prepareProfileInfo(pairingRequest);
	}
	
	private static Pairing prepareProfileInfo(PairingRequest pairingRequest) {
		Pairing pairing = new Pairing();
		pairing.setSenderCompanyRefId(pairingRequest.getSenderCompanyRefId());
		pairing.setReceiverCompanyRefId(pairingRequest.getReceiverCompanyRefId());
		pairing.setSenderPairingcurrency(pairingRequest.getSenderPairingcurrency());
		pairing.setSenderPairingPaymentType(pairingRequest.getSenderPairingPaymentType());
		pairing.setReceiverPairingPaymentType(pairingRequest.getReceiverPairingPaymentType());
		pairing.setReceiverPairingCurrency(pairingRequest.getReceiverPairingCurrency());
		pairing.setPairingStatus(PairingStatus.PAIRING_REQUESTED);
    	return pairing;
	}

}
