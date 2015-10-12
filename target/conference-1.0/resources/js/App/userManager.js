var userManager = (function () {

    function signUp() {
        $("form").submit(function (e) {
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
                success: function (data) {
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
        $("form").submit(function (e) {
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
            success: function (data) {
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


    $('#profileImage').on("change", function () {

        var data = new FormData();
        var file = this.files.item(0);
        data.append("file", file);

        var progress = $("#progressUploadingImage");
        progress.append(
            "<div class='progress'>" +
            "<div class='indeterminate'></div>" +
            "</div>"
        );
        $("#btn_photo_load").attr("disabled","");

        $.ajax({
            url: '/upload/profile/img',
            type: 'POST',
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success: function (response) {

                switch (response){
                    case "SUCCESS": alert("SUCCESS");
                        break;
                    case "MAXIMUM_UPLOAD_SIZE_EXCEEDED": alert("MAXIMUM_UPLOAD_SIZE_EXCEEDED");
                        break;
                    case "FILE_IS_EMPTY": alert("FILE_IS_EMPTY");
                        break;
                    case "IO_ERROR": alert("IO_ERROR");
                        break;
                }


                $(".progress").remove();
                dinamicViewImage(file);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //заглушка тут MAXIMUM_UPLOAD_SIZE_EXCEEDED

                alert("MAXIMUM_UPLOAD_SIZE_EXCEEDED");
                $(".progress").remove();

            }
        });
    });

    function dinamicViewImage(file) {

        var reader = new FileReader();

        reader.onload = (function (theFile) {
            return function (e) {

                var profileImg = $("#profileImg");
                profileImg.attr("src", e.target.result);
                profileImg.attr("title", theFile.name);
            };
        })(file);

        reader.readAsDataURL(file);
    }

    return {
        signUp: signUp,
        cancel: cancel,
        signIn: signIn
    }

})();