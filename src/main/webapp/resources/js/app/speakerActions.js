var speakerActions = (function(){

    var user = {};

    function addToUserObject(self){
        user.conferenceId = $(self).attr("id");
        user.organizerId =  $(self).attr("alt");
    }

    function sendMessageToOrganizer(self){
        user.userId = $(self).attr("id");
        user.topic = $("#topic").val();
        user.description = $("#description").val();

        $.ajax({
            type: 'POST',
            data: JSON.stringify(user),
            url: '/getMessage',
            contentType: 'application/json',
            success: function (data) {
                Materialize.toast(data, 4000);
            }
        });
    }

    return{
        addToUserObject: addToUserObject,
        sendMessageToOrganizer:sendMessageToOrganizer
    }
}());
