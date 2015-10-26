var GoogleMapsService = (function(){

    var map;
    var geocoder;
    var marker;

    function initialize(idElement) {
        var center_map = new google.maps.LatLng(46.459856,30.731506);
        var myOptions = {
            zoom: 13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById(idElement), myOptions);
        geocoder = new google.maps.Geocoder();
        marker = new google.maps.Marker({
            visible: false,
            map: map
        });
    }

    function codeAddress(address, idElement){

        initialize(idElement);
        geocoder.geocode( { 'address':address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                marker.setOptions({
                    visible: true,
                    title: address,
                    position: results[0].geometry.location
                });
                map.setZoom(17);
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }

    return{
        findByAddressAndShow: codeAddress
    }

}());
