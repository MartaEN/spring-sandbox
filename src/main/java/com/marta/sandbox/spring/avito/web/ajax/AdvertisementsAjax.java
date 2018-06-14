package com.marta.sandbox.spring.avito.web.ajax;

import com.marta.sandbox.spring.avito.domain.Advertisement;

import java.util.List;

public class AdvertisementsAjax {

	private List<Advertisement> advertisements;

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

}
