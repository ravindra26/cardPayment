<%-- 
    Document   : home
    Created on : 20 Feb, 2021, 1:06:43 PM
    Author     : Ravindra
--%>
<script>
    apiKey = '${apiKey}';
</script>
<script src="js/payments.js"></script>
<div class="row container-fluid">
    <div class="col-sm-4"></div>
    <div class="col-sm-4" style="background-color: peachpuff;border: 1px blue solid;">
        <div class="row" style="padding-bottom: 2%">
            <h2>Payment Form</h2>
        </div>
        <div class="row" style="padding-bottom: 2%;padding-left: 1%;padding-right: 1%">
            <table>
                <tr>
                    <th><label class="h6">Currency:</label></th>
                    <td><input class="form form-control" id = "curr" type="text"/></td>
                </tr>
                <tr>
                    <th><label class="h6">Amount:</label></th>
                    <td><input class="form form-control" id = "amount" type="number"/></td>
                </tr>
                <tr>
                    <th><label class="h6">Payment Description:</label></th>
                    <td><input class="form form-control" id = "description" type="text"/></td>
                </tr>
            </table>
        </div>
        <div class="row" style="padding-bottom: 2%;">
            <div class="col-sm-12 text-center">
                <button class="btn btn-primary" id="btnPayment">Make Payment</button>
            </div>
        </div>
    </div>
    <div class="col-sm-4"></div>
</div>
