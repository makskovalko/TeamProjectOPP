var participantActions = (function(){

    function saveData(){
        var participant = {};

        participant.userName = $('#userName').val();
        participant.firstName = $('#firstName').val();
        participant.lastName = $('#lastName').val();
        participant.dateOfBirth = $('#dateOfBirth').val();
        participant.phoneNumber = $('#phoneNumber').val();

        alert(JSON.stringify(participant));

        $.ajax({
            type: 'POST',
            data: JSON.stringify(participant),
            url: '/participant/updateData',
            contentType: 'application/json',
            success: function(data) {

                $('#fullName').text(data.firstName + " " + data.lastName);
                $('#firstName').val(data.firstName);
                $('#lastName').val(data.lastName);
                $('#dateOfBirth').val(new Date(data.dateOfBirth).toString("yyyy-MM-dd"));
                $('#phoneNumber').val(data.phoneNumber);


                $('#phoneNumberContactData').text(data.phoneNumber);
                $('#emailContactData').text(data.email);
            }
        });

    }

    return{
        saveData: saveData
    }

}());
