<%-- 
    Document   : createCustomer
    Created on : 20 Feb, 2021, 1:00:24 AM
    Author     : Ravindra
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row container-fluid">
    <div class="col-sm-4"></div>
    <div class="col-sm-4" style="background-color: azure; border: 1px black solid;">
        <form:form action="submitForm" modelAttribute="customer">
            <div class="row" style="padding-bottom: 2%;padding-top: 2%;">
                <table>
                    <tr>
                        <th><form:label path="firstName">First Name:</form:label></th>
                        <td><form:input class="form form-control" type="text" required="true" path="firstName"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="middleName">Middle Name:</form:label></th>
                        <td><form:input class="form form-control" type="text" path="middleName"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="lastName">Last Name:</form:label></th>
                        <td><form:input class="form form-control" type="text" required="true" path="lastName"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="username">Username:</form:label></th>
                        <td><form:input class="form form-control" type="text" required="true" path="username"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="password">Password:</form:label></th>
                        <td><form:input class="form form-control" type="password" required="true" path="password"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="day">Day</form:label></th>
                        <td><form:input class="form form-control" type="number" required="true" path="day"/></td>
                    </tr>

                    <tr>
                        <th><form:label path="month">Month</form:label></th>
                        <td><form:input class="form form-control" type="number" required="true" path="month"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="year">Year</form:label></th>
                        <td><form:input class="form form-control" type="number" required="true" path="year"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="email">Email:</form:label></th>
                        <td><form:input class="form form-control" type="email" required="true" path="email"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="phone">Phone</form:label></th>
                        <td><form:input class="form form-control" type="number" required="true" path="phone"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path="nickname">Billing Address Nickname:</form:label></th>
                        <td><form:input class="form form-control" path="nickname" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path = "street">Billing Address Street</form:label></th>
                        <td><form:input class="form form-control"  path = "street" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path = "street2">Billing Address Street2</form:label></th>
                        <td><form:input class="form form-control"  path = "street2" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path = "city">Billing Address City</form:label></th>
                        <td><form:input class="form form-control"  path = "city" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path = "zip">Billing Address Zip</form:label></th>
                        <td><form:input class="form form-control"  path = "zip" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path = "country">Billing Address Country</form:label></th>
                        <td><form:input class="form form-control" path = "country" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label  path="state">Billing Address State</form:label></th>
                        <td><form:input class="form form-control"  path="state" type="text"/></td>
                    </tr>
                    <tr>
                        <th><form:label path="nationality">Nationality:</form:label></th>
                        <td><form:input type="text" path="nationality"></form:input></td>
                        </tr>
                    </table>
                </div>
                <div class="row" style="padding-bottom: 2%;padding-top: 2%">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4 text-center">
                        <button class="btn btn-primary" type="submit">Create Customer</button>
                    </div>
                    <div class="col-sm-4"></div>
            </form:form>
        </div>
    </div>
    <div class="col-sm-4"></div>
</div>


