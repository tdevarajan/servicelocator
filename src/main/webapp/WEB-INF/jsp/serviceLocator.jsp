<!DOCTYPE html>
<html lang="en">

<head>
    <title>Find Services & Parts</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script src="js/gmap.js"></script>
                
    <link rel="stylesheet" href="css/findstyle.css">
    <style>
        .jumbotron {
            background-color: #F7F6F4;
            color: #808080;
        }
    </style>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
    <div id="search" class="container text-center">
        <div style="height: 60px;"> </div>
        <div class="jumbotron">
        <div class="row">
            <div class="col-sm-7">
                <!-- search panel -->
                <div class="row">
                    <div>
                        <img src="images/xtime-logo-1-2.png" alt="Bird">
                    </div>
                    <div class="panel panel-default text-left">
                        <div class="panel-body">
                            <h2 class="text-center">Find Services &amp; Parts</h2>
                            <div class="col-sm-12 form-group">
                                <label class="radio-inline"><input type="radio" value="S" name="optradio" checked id="optradio" onClick="enableDisable()">Services</label>
                                <label class="radio-inline"><input type="radio" value="P" name="optradio" id="optradio"  onClick="enableDisable()">Parts</label>
                            </div>
                            <div class="col-sm-6 form-group">
                                <select class="form-control" id="make" name="make">
                                	<option value='ANY'>Select Make</option>
                                	<option value="Toyota">Toyota</option>
                                	<option value="Honda">Honda</option>
                                </select>
                            </div>
                            <div class="col-sm-6 form-group">
                                <select class="form-control" id="service" name="service">
                                	<option value='ANY'>Select Service</option>
                                	<option value='AC Service'>AC Service</option>
									<option value='Battery Service'>Battery Service</option>
									<option value='Front Brake Service'>Front Brake Service</option>
									<option value='Oil and Filter Service'>Oil and Filter Service</option>
									<option value='Rare Brake Service'>Rare Brake Service</option>
									<option value='Replace Cabin Air Filter'>Replace Cabin Air Filter</option>
									<option value='Replace Coolant'>Replace Coolant</option>
									<option value='Spark Plug Replace'>Spark Plug Replace</option>
									<option value='5k Maintenance Service'>5k Maintenance Service</option>
									<option value='10K Maintenance Service'>10K Maintenance Service</option>
									<option value='15K Maintenance Service'>15K Maintenance Service</option>
									<option value='20K Maintenance Service'>20K Maintenance Service</option>
									<option value='25K Maintenance Service'>25K Maintenance Service</option>
									<option value='30K Maintenance Service'>30K Maintenance Service</option>
									<option value='35K Maintenance Service'>35K Maintenance Service</option>
									<option value='50K Maintenance Service'>50K Maintenance Service</option>
									<option value='75K Maintenance Service'>75K Maintenance Service</option>
									<option value='100K Maintenance Service'>100K Maintenance Service</option>
									
                                </select>
                            </div>
                            <div class="col-sm-6 form-group">
                                <input class="form-control" id="zipcode" name="zipcode" placeholder="Zipcode" type="text" required>
                            </div>
                            <div class="col-sm-6 form-group">
                                <input type="text" placeholder="Select Date" id="datepicker">
                                <select class="selectpicker" id="time" name="time">
                                	<option value="0">Time</option>
                                	<option value="8">8:00</option>
                                	<option value="9">9:00</option>
                                	<option value="10">10:00</option>
                                	<option value="11">11:00</option>
                                	<option value="12">12:00</option>
                                	<option value="13">13:00</option>
                                	<option value="14">14:00</option>
                                	<option value="15">15:00</option>
                                	<option value="16">16:00</option>
                                	<option value="17">17:00</option>
                                	<option value="18">18:00</option>
                                </select>
                            </div>
                            <div class="col-sm-12 form-group" align="center">
                                <button type="button" class="btn btn-default" id="btnSearch">
                                    Search
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end of search -->
                <!-- results panel -->
                <div class="row">
                    <div class="panel-body">
                        <div id="dealers"> </div>
                    </div>
                </div>
            </div>
            <!-- end of results -->
            <!-- Google map section -->
            <div class="col-sm-5">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <!-- Add Google Maps -->
                        <div id="googleMap"></div>
                    </div>
                </div>
                <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDcEAGnBFdCRDDQYNdf16eWZEhvo6swq70&callback=loadMap"></script>
                <!--
      To use this code on your website, get a free API key from Google.
      Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
      -->
            </div>
        </div>
    </div>
    </div>
    <script type="text/javascript">
    
	$(function () {
        $('#datepicker').datepicker();
    	
    });
    
	    
    
    var service = 'http://servicelocator-env.mip6ccns32.us-west-2.elasticbeanstalk.com/spl/';
    $(document).ready(function() {
        // Initialize Tooltip
        $('[data-toggle="tooltip"]').tooltip();

        // Add smooth scrolling to all links in navbar + footer link
        $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
            // Make sure this.hash has a value before overriding default behavior
            if (this.hash !== "") {

                // Prevent default anchor click behavior
                event.preventDefault();

                // Store hash
                var hash = this.hash;

                // Using jQuery's animate() method to add smooth page scroll
                // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
                $('html, body').animate({
                    scrollTop: $(hash).offset().top
                }, 900, function() {

                    // Add hash (#) to URL when done scrolling (default click behavior)
                    window.location.hash = hash;
                });
            } // End if
        });

        // Ajax calls invoke to load table
        jQuery.support.cors = true;
        
    
        $(function() {
            var makeYears = [];
            var myDate = new Date();
            var year = myDate.getFullYear();
            for (var i = 1950; i < year + 1; i++) {
                // document.write('<option value="'+i+'">'+i+'</option>');
                makeYears.push(i.toString());
            }
            $("#makeYears").autocomplete({
                source: makeYears
            });
        });
    });


    $('#btnSearch').click(function() {
        // call Json script here
        console.log('search clicked');
         var radios = document.getElementsByName('optradio');
	
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked) {
		        if ('P' ==radios[i].value) {
		        	loadPartDealers();
				} else {
					loadDealers();
				}
			}
		}
        
    });


	
    function enableDisable() {
	    var radios = document.getElementsByName('optradio');
	
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked) {
		        
		        if ('P' ==radios[i].value) {
		        	var select = document.getElementById("make");
    				select.options.length = 0;
		        
		        	select.options[select.options.length] = new Option('Select Parts', '0', false, false);
		        	select.options[select.options.length] = new Option('Brake Pad', '0', false, false);
		        	select.options[select.options.length] = new Option('Battery', '0', false, false);
		        	select.options[select.options.length] = new Option('Head Light Bulb', '0', false, false);
		        	
		        	document.getElementById('service').disabled = true;
		        	document.getElementById('datepicker').disabled = true;
		        	document.getElementById('time').disabled = true;
		        } else {
		        	
		        	var select = document.getElementById("make");
    				select.options.length = 0;
    				
		        	select.options[select.options.length] = new Option('Select Make', '0', false, false);
		        	select.options[select.options.length] = new Option('Toyota', '0', false, false);
		        	select.options[select.options.length] = new Option('Honda', '0', false, false);
		        	
		        	
		        	document.getElementById('make').disabled = false;
		        	document.getElementById('service').disabled = false;
		        	document.getElementById('datepicker').disabled = false;
		        	document.getElementById('time').disabled = false;
		        }
		    }
		}
    	
    }
    
    function loadDealers() {
    
		dealers = new Array();	    
		var a = 0;
		
        var service = 'http://servicelocator-env.mip6ccns32.us-west-2.elasticbeanstalk.com/services/';
        $.ajax({
            type: "GET",
            url: service + '?zipcode=' + $("#zipcode").val() + '&date=' + $("#datepicker").val() + '&time=' + $("#time").val() +
            '&make=' + $("#make option:selected").text()  + '&service=' + $("#service option:selected").val() +
             '&optradio=' + $('input[name=optradio]:radio:checked').val() ,

            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function(data) {
                console.log('ajax sucess', data);
                var trHTML = '';               
                // array of objects
                $.each(data, function(index, item) {
 	
				
				    dealers[a] = new Array('test', parseFloat(item.latitude), parseFloat(item.longtidue), a++);
				 
				 	console.log(dealers.length);   
					
                    console.log(index, item);

                    trHTML += '<p><div style="border-style: double;border-color:black"><table class="tg"><tr>' +
                        '<td class="tg-aoky">' +
                        '<img style="max-width: 60px; max-height: 60px;" src=' + item.dealerImage + '>' +
                        '<h3>' + item.name + '</h3>' +
                        '<p style="font-size: 13px"><font color="blue">' + item.address + ',' + item.city + ',' + item.zipcode +'</font></p>' +
                        '</td>' +
                        '<td class="tg-aoky">' +
                        '<span> Today\'s Hours </span>' +
                        '<p style="font-size: 13px "><font color="green">' + item.hours + '</font></p>' +
                        '</td>' +
                        '<td class="tg-aoky">' +
                        '<span>Phone</span>' +
                        '<p style="font-size: 13px"><font color="black">' + item.phone + '</font></p>' +
                        '</td>' +
                        '</tr>' + 
                        '<tr>' +
                        '<td class="tg-aoky" colspan=3>' +
                        '<span>Available Time Slots:</span> <font color="green">' + item.availTimeSlots + '</font>'+ 
                        '</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td class="tg-aoky" colspan=3>' +
                        '<a href="' + item.website + '" style="color:red">Dealer Web Site</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="' + item.serviceLink+ '" style="color:red">Schedule Service</a>'+  
                        '</td>' +
                        '</tr>' +
                        '</table></div></p>' ;

                });
                trHTML += '';
                $('#dealers').html(trHTML);
				initMap(dealers);
            },
            error: function(response) {
                console.log('error', response);
            }
        });
		
    }
    
    
    
    
    function loadPartDealers() {
    
		dealers = new Array();	    
		var a = 0;
		
        var service = 'http://servicelocator-env.mip6ccns32.us-west-2.elasticbeanstalk.com/parts/';
        $.ajax({
            type: "GET",
            url: service + '?zipcode=' + $("#zipcode").val() + '&part=' + $("#make option:selected").text() ,

            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function(data) {
                console.log('ajax sucess', data);
                var trHTML = '';               
                // array of objects
                $.each(data, function(index, item) {
 	
				
				    dealers[a] = new Array('test', parseFloat(item.latitude), parseFloat(item.longtidue), a++);
				 
				 	console.log(dealers.length);   
					
                    console.log(index, item);

                    trHTML += '<p><div style="border-style: double;border-color:black"><table class="tg"><tr>' +
                        '<td class="tg-aoky">' +
                        '<img style="max-width: 60px; max-height: 60px;" src=' + item.dealerImage + '>' +
                        '<h3>' + item.name + '</h3>' +
                        '<p style="font-size: 13px"><font color="blue">' + item.address + ',' + item.city + ',' + item.zipcode +'</font></p>' +
                        '</td>' +
                        '<td class="tg-aoky">' +
                        '<span> Today\'s Hours </span>' +
                        '<p style="font-size: 13px "><font color="green">' + item.hours + '</font></p>' +
                        '</td>' +
                        '<td class="tg-aoky">' +
                        '<span>Phone</span>' +
                        '<p style="font-size: 13px"><font color="black">' + item.phone + '</font></p>' +
                        '</td>' +
                        '</tr>' + 
                        '<tr>' +
                        '<td class="tg-aoky" colspan=3>' +
                        '<a href="' + item.website + '" style="color:red">Dealer Web Site</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+  
                        '</td>' +
                        '</tr>' +
                        '</table></div></p>' ;

                });
                trHTML += '';
                $('#dealers').html(trHTML);
				initMap(dealers);
            },
            error: function(response) {
                console.log('error', response);
            }
        });
		
    }
    </script>
</body>
</html>