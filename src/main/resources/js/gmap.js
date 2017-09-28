
var dealers = [
  ['Golden Gate', 37.8199, -122.4783, 1]
];
var marker;

function loadMap() {
	initMap(dealers);
}

function initMap(dealers) {

    var dealer = dealers[0];

  var myCenter = new google.maps.LatLng(dealer[1], dealer[2]);
  var mapProp = {
    center: myCenter,
    zoom: 12,
    scrollwheel: true,
    draggable: true,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var map = new google.maps.Map(document.getElementById('googleMap'),mapProp);


  for (var i = 0; i < dealers.length; i++) {
    
    
    var dealer = dealers[i];

    marker = new google.maps.Marker({
      map: map,
      draggable: true,
      animation: google.maps.Animation.DROP,
      position: {
        lat: dealer[1],
        lng: dealer[2]
      }
    });
    marker.addListener('click', toggleBounce);

  }

}

function toggleBounce() {
  if (marker.getAnimation() !== null) {
    marker.setAnimation(null);
  } else {
    marker.setAnimation(google.maps.Animation.BOUNCE);
  }
}
// Loop through the results array and place a marker for each
// set of coordinates.
window.eqfeed_callback = function(results) {
  for (var i = 0; i < results.features.length; i++) {
    var coords = results.features[i].geometry.coordinates;
    var latLng = new google.maps.LatLng(coords[1], coords[0]);
    var marker = new google.maps.Marker({
      position: latLng,
      map: map
    });
  }
}