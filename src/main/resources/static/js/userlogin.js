/**
 * 
 */

var vm = new Vue({
    el:'#userlogin',
    data:{
    	username:null,
    	password:null,
    	
    },
    created:function(){
    	
    },
    methods:{
    	login:function(){
    		var yanzheng = document.getElementById("yanzheng").value;
    		if(yanzheng == "yes"){
    			 this.$http.post('user/login.do',{username:this.username,password:this.password},{emulateJSON:true}).then(function(res){
	            	if(res.body.success){
	            		location.href="index.html";
	            	}else{
	            		alert("账号或者密码错误！")
	            	}
	            	
	            },function(res){
	                
	            });
    		}else{
    			alert("验证码错误！请重新输入！")
    		}
    	}
    	
	}
});

