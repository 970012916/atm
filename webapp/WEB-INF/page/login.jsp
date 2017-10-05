<%@page contentType="text/html; charset=utf-8" %>

<html>

<head>
	<meta charset="utf-8" />
	<title>atm 系统</title>
	
	<script type="text/javascript" src="/js/jquery-mini.js"></script>
</head>

<body>
<h1>登录页面</h1>
    <form>
    	用户名：<input type="text" id="username" name="username"><br>
    	密码：<input type="password" id="password" name="password"><br>
   		<input type="button" value="登录" onclick="login();"> &nbsp;&nbsp;&nbsp;
   		<a href="/">返回</a>
    </form>
    
    <script type="text/javascript">
    
    function login() {
    	$.ajax({
            url:'/user/login.do',
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{
            	username:$('#username').val(),
            	password:$('#password').val()
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                console.log(xhr)
                console.log('发送前')
            },
            success:function(data,textStatus,jqXHR){
                var obj = data;
                alert(obj.success);
                
                if (!data.success) {
                	alert(data.message);
                	return;
                }
                
                window.location.href='/user/toUsercenter.do';
                
            },
            error:function(xhr,textStatus){
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete:function(){
                console.log('结束')
            }
        });
    }
    
    </script>
    
</body>

<footer>
1997-2017@copy
</footer>

</html>