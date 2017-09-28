package com.xtime.servicelocator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xtime.servicelocator.model.Dealer;
import com.xtime.servicelocator.service.DealerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController 
public class ServiceLocatorRestController {

	    @Autowired
	    DealerService dealerService;
	    
	    @ApiOperation(value = "services", nickname = "services")
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure")})
	    @RequestMapping(value = "/services", method = RequestMethod.GET)
	    public List<Dealer> retrievedealers(
	    		@RequestParam(value = "zipcode", required = false) String zipcode,
	    		@RequestParam(value = "date", required = false) String date,
	    		@RequestParam(value = "time", required = false) String time,
	    		@RequestParam(value = "make", required = false) String make,
	    		@RequestParam(value = "service", required = false) String service,
	    		@RequestParam(value = "optradio", required = false) String searchOption) {
	    	
	    	
	    	System.out.println(zipcode);
	    	System.out.println(date);
	    	System.out.println(time);
	    	System.out.println(make);
	    	System.out.println(service);
	    	System.out.println(searchOption);
	        return dealerService.retrieveDealers(zipcode, date, time, make, service, searchOption);
	    }

	    @ApiOperation(value = "parts", nickname = "parts")
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Failure")})

	    @RequestMapping(value = "/parts", method = RequestMethod.GET)
	    public List<Dealer> retrievePartdealers(
	    		@RequestParam(value = "zipcode", required = false) String zipcode,
	    		@RequestParam(value = "part", required = false) String part
	    		) {
	    	
	    	System.out.println(zipcode);
	    	System.out.println(part);
	        return dealerService.retrievePartDealers(zipcode, part);
	    }


}
