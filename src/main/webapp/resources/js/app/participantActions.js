var participantActions = (function () {

    function saveData() {
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
            success: function (data) {

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

    function showAllParticipatsForThisConference(self) {
        $("#my-conference").closeModal();
        $("#participantWhoBindWithUs").empty();

        var conferenceID = $(self).attr("id");

        $.ajax({
            type: 'GET',
            url: '/participant/getParticipantsForThisConference/' + conferenceID,
            success: function (data) {
                $("#participantWhoBindWithUs").empty();

                for (var i = 0; i < data.length; i++) {
                    $("#participantWhoBindWithUs").append('' +
                        '<div onclick="participantActions.showParticipantInfo(' +
                        '\'' + data[i].firstName + '\',\'' + data[i].lastName + '\',\'' + data[i].profileImage + '\',\'' + data[i].email + '\', \'' + data[i].phoneNumber + '\', \'' + data[i].dateOfBirth + '\'' +
                        ')"' +
                        ' class="chip col m2" style="margin-left: 1%">' +
                        ' <img src="' + data[i].profileImage + '" alt="Contact Person"/> ' + data[i].userName +
                        ' </div></a>');
                }
            }
        });
    }

    function returnPrewModal() {
        $("#my-conference").openModal();
    }

    function showParticipantInfo(firstName, lastName, profileImage, email, phoneNumber, dateOfBirth) {
        $("#participantInfo").empty();
        $("#participant").openModal();

        $("#participantInfo").append(' <div class="card-image col m6 waves-effect waves-block waves-light"> ' +
            '<img style="width: 100%; height: 100%" src="'+profileImage+'" class="activator"/> </div>'+
            ' <div class="collection col m6">' +
            ' <a href="#!" class="collection-item">'+firstName+' '+lastName+'</a>' +
            ' <a href="#!" class="collection-item">'+dateOfBirth+'</a>' +
            ' <a href="#!" class="collection-item">'+email+'</a>' +
            ' <a href="#!" class="collection-item">'+phoneNumber+'</a>' +
            ' </div>');
    }

    return {
        showAllParticipatsForThisConference: showAllParticipatsForThisConference,
        returnPrewModal: returnPrewModal,
        saveData: saveData,
        showParticipantInfo: showParticipantInfo
    }

}());