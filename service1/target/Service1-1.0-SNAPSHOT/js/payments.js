var customerData;
$(function () {
    console.log(apiKey);
    $("#btnPayment").click(function () {
        getSingleUseToken();
    });
});

function getSingleUseToken() {
    $.ajax({
        url: "getSingleUseToken",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            var response = JSON.parse(data);
            getCustomerData(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error occured");
        }
    });
}

function performCheckout(data) {
    console.log(data.singleUseCustomerToken);
    var option = {
        "currency": $("#curr").val(),
        "amount": Number($("#amount").val()),
        "locale": "en_US",
        "environment": "TEST",
        "merchantRefNum": ''+Math.floor(100000 + Math.random() * 900000),
        "singleUseCustomerToken": data.singleUseCustomerToken,
        "customer": {
            "firstName": customerData.firstName,
            "lastName": customerData.lastName,
            "email": customerData.email,
            "phone": customerData.phone,
            "dateOfBirth": {
                "day": customerData.day,
                "month": customerData.month,
                "year": customerData.year
            }
        },
        "billingAddress": {
            "nickName": customerData.nickname,
            "street": customerData.street,
            "street2": customerData.street2,
            "city": customerData.city,
            "zip": ""+customerData.zip,
            "country": customerData.country,
            "state": customerData.state
        },
        "paymentMethodDetails": {
            card: {
            }
        }
    };
    console.log(option);
    paysafe.checkout.setup(apiKey, option, function (instance, error, result) {
        if (result && result.paymentHandleToken) {
            console.log(result);
            console.log(result.paymentHandleToken);
            $.ajax({
                url: "makePaymentRequest?paymentHandleToken=" + encodeURIComponent(result.paymentHandleToken) + "&description=" + encodeURIComponent($("#description").val())
                        + "&amount=" + encodeURIComponent(Number($("#amount").val())) + "&currCode=" + encodeURIComponent($("#curr").val()),
                type:"GET",
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    if(data !== null){
                        instance.showSuccessScreen("Payment Successful!");
                    }else{
                        instance.showFailureScreen("Payment Declined."); 
                    }
                }
            });
        } else {
            console.error(error);
            // Handle the error
        }
    }, function (stage, expired) {
        switch (stage) {
            case "PAYMENT_HANDLE_NOT_CREATED": // Handle the scenario
            case "PAYMENT_HANDLE_CREATED": // Handle the scenario
            case "PAYMENT_HANDLE_REDIRECT": // Handle the scenario
            case "PAYMENT_HANDLE_PAYABLE": // Handle the scenario
            default: // Handle the scenario
        }
    });
}

function getCustomerData(lastData) {

    $.ajax({
        url: "getCustomerData",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            var response = JSON.parse(data);
            console.log(response);
            customerData = response;
            performCheckout(lastData);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error occured");
        }
    });

}