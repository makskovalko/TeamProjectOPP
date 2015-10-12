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

        $.ajax({
            type: 'POST',
            data: JSON.stringify(organizer),
            url: '/updateOrganizerData',
            contentType: 'application/json',
            success: function(data) {
                if (data) Materialize.toast('Данные успешно изменены!', 4000);
            }
        });
    }

    function createConference() {
        $("form").submit(function(e) {
            e.preventDefault();
            return false;
        });

        var conference = {};

        conference.name = $("#conference_name").val();
        conference.country = $("#country").val();
        conference.city = $("#city").val();
        conference.address = $("#address").val();
        conference.date = $("#date").val();
        conference.participantLimit = $("#participant-limit").val();
        conference.categoryId = $("#category").val();
        conference.description = $("#description").val();
        conference.organizerId = $("#organizer_id").val();
        conference.participantCount = 0;

        alert(JSON.stringify(conference));

        $.ajax({
            type: 'POST',
            data: JSON.stringify(conference),
            url: '/create_conference',
            contentType: 'application/json',
            success: function(data) {
                if (data == "OK") {
                    Materialize.toast('Конференция создана!', 4000);
                    setTimeout(function() {
                        window.location.href = "/profile/organizer";
                    }, 2000);
                }
            }
        });
    }

    return {
        saveData: saveData,
        createConference: createConference
    }

})();