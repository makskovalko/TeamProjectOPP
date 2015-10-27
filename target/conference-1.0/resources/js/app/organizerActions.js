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
        conference = encodeURIComponent(JSON.stringify(conference));

        $.ajax({
            type: 'POST',
            data: conference,
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

    function searchConference() {
        var search = $("#search").val();

        $.ajax({
            type: "GET",
            url: "/search_conference/" + (search === ""? "_": search),
            success: function(data) {
                var conferences = JSON.parse(data);
                var confs = "";

                $("#confs").html('<div class="row"><div class="progress" style=""><div class="indeterminate""></div></div></div>');

                setTimeout(function() {

                    if (conferences.length == 0) {
                        $("#confs").html("<div class='col s12 m12'><h5>Ничего не найдено!</h5></div>");
                        return;
                    }

                    $("#confs").html("");
                    conferences.forEach(function(item, index, conferences) {
                        var conf = JSON.parse(item);
                        var time = new Date(conf.date);
                        var day = addZero(time.getDay());
                        var month = addZero(time.getMonth());
                        var year = addZero(time.getYear());
                        var date = day + "/" + month + "/" + year;

                        confs +=
                            '<div id="all_conferences" class="col s12 m6">' +
                            '<div class="card small" style="background-color:'+conf.color+'" id="conferences">' +
                            '<div class="card-content white-text">' +
                            '<span class="card-title">' + conf.name + '</span>' +
                            '<p>' + conf.description +
                            '</p>' +
                            '</div>' +
                            '<div class="card-action right">' +
                            '<a href="/conference/'+ conf.id +'">Подробнее</a>' +
                            '<a href="#" style="position: absolute; right:0; color: #fff;">' + date + '</a>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                    });

                    $("#confs").html(confs);

                }, 2000);

            }
        });
    }

    function addZero(x) {
        if (x.toString().length == 1) x = "0" + x;
        return x;
    }

    return {
        saveData: saveData,
        createConference: createConference,
        searchConference: searchConference
    }
})();