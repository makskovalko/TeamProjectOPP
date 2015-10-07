var organizerActions = (function() {

    function saveData() {
        var organizer = {};

        organizer.firstName = $("#firstName").val();
        organizer.lastName = $("#lastName").val();
        organizer.email = $("#email").val();
        organizer.phoneNumber = $("#phoneNumber").val();
        organizer.dateOfBirth = $("#dateOfBirth").val();
        organizer.userName = $("#userName").val();
        organizer.userType = "organizer";
        alert(JSON.stringify(organizer));
        $.ajax({
            type: 'POST',
            data: JSON.stringify(organizer),
            url: '/updateOrganizerData',
            contentType: 'application/json',
            success: function(data) {
                alert(JSON.stringify(data));
            }
        });
    }

    return {
        saveData: saveData
    }

})();