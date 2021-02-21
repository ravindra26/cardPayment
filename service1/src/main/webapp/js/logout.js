$(function () {
    $("#logout").click(function () {
        $.ajax({
            url: "doLogout",
            type: 'GET',
            success: function (data, textStatus, jqXHR) {
                window.location = "login.htm";
            }
        });
    });
    
    $("#backToLogin").click(function(){
        window.location="login.htm";
    });
});


