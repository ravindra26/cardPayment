<%-- 
    Document   : index.jsp
    Created on : 19 Feb, 2021, 7:52:27 PM
    Author     : Ravindra
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row container-fluid">
    <div class="col-sm-4">

    </div>
    <div class="col-sm-4">
        <form:form action="handleLogin" modelAttribute="login">
            <div class="row btn-group">
                <div class="col-sm-12">
                    <div class="row" style="padding-bottom: 1%;">
                        <form:label class="h6" path="username" >Username:  </form:label>
                        <form:input type="text" class="form form-control" required="true" path="username"/>
                    </div>
                    <div class="row" style="padding-bottom: 1%;">
                        <form:label path="password" class="h6">Password:  </form:label>
                        <form:input type="password" class="form form-control" required="true" path="password"/>
                    </div>
                    <div class="row" style="padding-bottom: 1%;">
                        <button class="btn btn-primary" id="btnLogin" type="submit">Login</button>
                    </div>
                </div>
            </div>
        </form:form>
        <div class="row">
            <a href="createUser.htm">Sign Up</a>
        </div>

    </div>
    <div class="col-sm-4">

    </div>  
</div>
