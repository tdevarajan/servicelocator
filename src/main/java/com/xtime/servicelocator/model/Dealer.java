package com.xtime.servicelocator.model;


import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dealer implements Serializable {

    private static final long serialVersionUID = 6069090715368748845L;
    private String name;
    private TimeZone timeZone;
    private String countryCode;
    private String state;
    private Long id;
    private String address;
    private String adressTwo;
    private String zipcode;
    private String city;
    private String phone;
    private String consumerSite;
    private String website;
    private String hours;
    private String latitude;
    private String longtidue;
    private String availTimeSlots;
    private String serviceLink;
    private String dealerImage;
    private String partName;
    private String partQuantity;
    
    
    public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartQuantity() {
		return partQuantity;
	}

	public void setPartQuantity(String partQuantity) {
		this.partQuantity = partQuantity;
	}

	public String getDealerImage() {
		return dealerImage;
	}

	public void setDealerImage(String dealerImage) {
		this.dealerImage = dealerImage;
	}

	public String getAvailTimeSlots() {
		return availTimeSlots;
	}

	public void setAvailTimeSlots(String availTimeSlots) {
		this.availTimeSlots = availTimeSlots;
	}

	public Dealer() {
    }
    
    public String getWebsite() {
		return website;
	}



	public void setWebsite(String website) {
		this.website = website;
	}



	public String getServiceLink() {
		return serviceLink;
	}

	public void setServiceLink(String serviceLink) {
		this.serviceLink = serviceLink;
	}

	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getLongtidue() {
		return longtidue;
	}



	public void setLongtidue(String longtidue) {
		this.longtidue = longtidue;
	}



	public Dealer(String name, TimeZone timeZone, String countryCode, String state, Long id, String address,
			String adressTwo, String zipcode, String city, String phone, String consumerSite, String dealerSite,
			String hours) {
		super();
		this.name = name;
		this.timeZone = timeZone;
		this.countryCode = countryCode;
		this.state = state;
		this.id = id;
		this.address = address;
		this.adressTwo = adressTwo;
		this.zipcode = zipcode;
		this.city = city;
		this.phone = phone;
		this.consumerSite = consumerSite;
		this.website = dealerSite;
		this.hours = hours;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdressTwo() {
		return adressTwo;
	}

	public void setAdressTwo(String adressTwo) {
		this.adressTwo = adressTwo;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConsumerSite() {
		return consumerSite;
	}

	public void setConsumerSite(String consumerSite) {
		this.consumerSite = consumerSite;
	}

	public String getDealerSite() {
		return website;
	}

	public void setDealerSite(String dealerSite) {
		this.website = dealerSite;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
