var userManager = (function() {

    function signUp() {
        $("form").submit(function(e) {
            e.preventDefault();
            return false;
        });

        var user = {};

        user.userName = $("#username").val();
        user.password = $("#password").val();
        user.firstName = $("#first_name").val();
        user.lastName = $("#last_name").val();
        user.email = $("#email").val();
        user.phoneNumber = $("#phone_number").val();
        user.dateOfBirth = $("#date_of_birth").val();
        user.userType = $("#userType").val();

        if (isValidData(user)) {
            //alert(JSON.stringify(user));
            $.ajax({
                type: 'POST',
                data: JSON.stringify(user),
                url: '/signUp',
                contentType: 'application/json',
                success: function(data) {
                    /*window.location.href = '/';*/
                    alert(data);
                }
            });
        }
    }

    function isValidData(user) {
        return true;
    }

    function signIn() {
        $("form").submit(function(e) {
            e.preventDefault();
            return false;
        });

        var user = {};

        user.userName = $("#username_signIn").val();
        user.password = $("#password_signIn").val();
        user.userType = $("#userTypeSignIn").val();

        //alert(JSON.stringify(user));

        $.ajax({
            type: 'POST',
            data: JSON.stringify(user),
            url: '/signIn',
            contentType: 'application/json',
            success: function(data) {
                if (data == "participant") window.location.href = '/profile/participant';
                else if (data == "organizer") window.location.href = '/profile/organizer';
                else if (data == "speaker") window.location.href = '/profile/speaker';
                else alert(data);
            }
        });

    }

    function cancel() {
        $("#username").val("");
        $("#password").val("");
        $("#first_name").val("");
        $("#last_name").val("");
        $("#email").val("");
        $("#phone_number").val("");
        $("#date_of_birth").val("");
        $("#userType").val("");
    }

    return {
        signUp: signUp,
        cancel: cancel,
        signIn: signIn
    }

})();