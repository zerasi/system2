/**
 * 
 */

var vm = new Vue({
    el:'#header',
    data:{
    	username:null,
    	usernamee:null,
    },
    created:function(){
    	this.zhanshi();
    },
    methods:{
    	zhanshi:function(){
    		this.$http.post('user/zhanshi.do',{emulateJSON:true}).then(function(res){
            	if(res.body.username == null){
            		
            	}else{
            		this.username = res.body.username;
            		this.usernamee = res.body.username;
            	}
            	
            },function(res){
                
            });
    	},
    	logout:function(){
    		this.$http.post('user/logout.do',{emulateJSON:true}).then(function(res){
    			location.href="login.html";
            },function(res){
                
            });
    	}
    	
	}
});

