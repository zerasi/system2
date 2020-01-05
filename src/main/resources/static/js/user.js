/**
 * 
 */

var vm = new Vue({
    el:'#user',
    data:{
        id:null,
        username:null,
        password:null,
        sex:null,
        realName:null,
        email:null,
        accNbr:null,
        msg:null,
    },
    created:function(){
    	this.find();
    },
    methods:{
        find:function(){
        	this.$http.post('user/zhanshi.do',{emulateJSON:true}).then(function(res){
        		this.id=res.body.id;
	           	this.username=res.body.username;
	           	this.password=res.body.password;
	           	this.email=res.body.email;
	           	this.accNbr=res.body.accNbr;
	           	this.sex=res.body.sex;
	           	this.realName=res.body.realName;
            },function(res){
            	
            });
        },
        update:function(){
        	var options={id:this.id,username:this.username,password:this.password,sex:this.sex,realName:this.realName,email:this.email,accNbr:this.accNbr};
        	this.$http.post('../user/add2.do',options,{emulateJSON:true}).then(function(res){
        		this.msg="修改成功";
            },function(res){
            	
            });
        }
    },
});

