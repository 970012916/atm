## ATM系统接口文档 1.0 ##

### 一：用户注册 ###

* 接口地址

<pre>
	/user/regis.do
</pre>

* 请求参数
 <pre>
	参数名		必填	默认值	说明

	username	是	无	注册用户名，唯一
	password	是	无	用户的密码
	confirmPassword	是	无	确认密码，必须和password相同
 </pre>

* 返回信息
 <pre> 
{
  "success" : true,
  "data" : null,
  "message" : null
}
 </pre>

### 二：登录 ###

* 接口地址

<pre>
	/user/login.do
</pre>

* 请求参数
 <pre>
	参数名		必填	默认值	说明

	username	是	无	登录用户名
	password	是	无	瞪了鲁的密码
 </pre>

* 返回信息
 <pre> 
{
  "success" : true,
  "data" : null,
  "message" : null
}
 </pre>

### 三：开户 ###

* 接口地址

<pre>
	/bank/openAccount.do
</pre>

* 请求参数
 <pre>
	无
 </pre>

* 返回信息
 <pre> 
{
  "success" : true,
  "data" : null,
  "message" : null
}
 </pre>

