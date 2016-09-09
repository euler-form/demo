<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${global.singin}</title>

    <!-- Bootstrap -->
    <link href="${contextPath}/resources/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/lib/icon.css" rel="stylesheet">
    <link href="${contextPath}/resources/bootstrap-3.3.5/local/global.css" rel="stylesheet">
    <link href="${contextPath}/resources/bootstrap-3.3.5/local/center-from.css" rel="stylesheet">
</head>

<body>
    <div class="wrapper">

        <div class="wrapper-inner">
            <div class="title-wrapper">
                <span class="demo-brand-200-50-fff"></span>
                <%-- <img alt="Brand" src="${contextPath}/resources/images/Euler-Formula-800_200-fff.png" width="300px" height="75px"> --%>
            </div>

            <div class="main-form-wrapper">
                <form method="post" class="main-form" action="signin">
                    <div class="form-group">
                        <input type="username" name="username" class="form-control" id="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                    </div>
                    <div class="form-group button-group">
                        <span><a href="forgotpasswd">Forgot password?</a></span><span><input id="remember-me" type="checkbox" value="false" name="remember-me">&nbsp;Remember me</label></span>
                    </div>
                    <div class="form-group button-group">
                        <span><button 
                                type="submit" class="btn btn-success">Sign in</button></span><span><button 
                                type="button" class="btn btn-info" onClick="signUp()">Sign up</button></span>
                    </div>
                </form>
            </div>
            
            <div class="info-wrapper">
                <c:if test="${param.containsKey('error')}">
                    <b>Login failed. Please try again.</b>
                </c:if>
                <c:if test="${param.containsKey('loggedOut')}">
                    <b>You are now logged out.</b>
                </c:if>
            </div>
        </div>

        <footer class="navbar navbar-inverse navbar-fixed-bottom footer-wrapper">
            &copy;2016&nbsp;cFrost&nbsp;<a id="icp" href="http://www.miitbeian.gov.cn" target="_Blank">粤ICP备15054669号</a>
        </footer>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${contextPath}/resources/bootstrap-3.3.5/js/jquery-3.0.0.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${contextPath}/resources/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    
    <script>
    
        function signUp(){
            var username = $('#username').val();
            var password = $('#password').val();
            var data = {};
            data.username = username;
            data.password = password;
            $.ajax({
                url:'authentication/signUp',
                type:'POST',
                async:true,
                data: data,
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.responseText);
                },
                success:function(data, textStatus) {
                    $('#login-form').submit();
                }
            });
        }
    
    </script>
</body>

</html>



<%-- <!DOCTYPE html>
<html>
    <head>
        <title>Log In</title>
    </head>
    <body>
        <h2>Log In</h2>
        <c:if test="${param.containsKey('error')}">
            <b>Login failed. Please try again.</b><br><br>
        </c:if>
        <c:if test="${param.containsKey('loggedOut')}">
            <b>You are now logged out.</b><br><br>
        </c:if>
        <form method="post" action="<c:url value="/login">">
            Username<br>
            <input type="text" name="username"><br><br>

            Password<br>
            <input type="password" name="password"><br><br>

            <input type="submit" name="Log In">
        </form>
        <a href="<c:url value="/logout">">logout</a>
    </body>
</html> --%>
