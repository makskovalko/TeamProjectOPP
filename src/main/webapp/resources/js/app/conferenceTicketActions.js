function increment_count(){ //извращение с количеством пользователей
    Materialize.showStaggeredList(".how_much_participants_in_conference");
    var count = $("#participantCount").text(); //сюда подставить из базы
    var i = 0;
    var interval1 = window.setInterval(function(){

        if(i > count - 1){
            var interval2 = window.setInterval(function(){
                $('.how_much_participants_in_conference li').text(i + " +");
                if(i > count - 1){
                    clearInterval(interval2);
                }
                i++;
            }, 800);
            clearInterval(interval1);
        } else{
            $('.how_much_participants_in_conference li').text(i + " +");
            i++;
        }
    }, 10);
}

var ticketActions = (function() {
    function pay() {
        alert("OK");
    }

    return {
        pay : pay
    }
})();


$(document).ready(function(){ //всякие штуки materialize

    var options = [
        {selector: '.how_much_participants_in_conference', offset: 100, callback: 'increment_count()' }
    ];
    Materialize.scrollFire(options);

    window.setInterval( function(){
        var height = $("video").height();
        $('#navigation').pushpin({top: height, offset: 50});
    },100);

    $('.parallax').parallax();
    $(".button-collapse").sideNav();

    if($("body").width() <= 600) {
        $('.date_and_place i').removeClass("medium").addClass("small");
    }

});