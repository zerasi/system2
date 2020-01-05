/**
 * 
 */

var vm = new Vue({
    el:'#content',
    data:{
        id:null,
        name:null,
        flag:null,
        type:null,
        location:null,
        area:null,
        price:null,
        decoration:null,
        img:null,
        dianji:null,
        username:null,
        accNbr:null,
        img1:null,
        commentList:[],
        content:null,
    },
    created:function(){
    	this.find();
    	this.comment();
    },
    methods:{
        find:function(){
        	var hId = location.search.split("=")[1];
        	this.$http.post('house/findOnet.do',{id:hId},{emulateJSON:true}).then(function(res){
        		this.id=res.body.id;
	           	this.name=res.body.name;
	           	this.flag=res.body.flag;
	           	this.type=res.body.type;
	           	this.location=res.body.location;
	           	this.area=res.body.area;
	           	this.price=res.body.price;
	           	this.decoration=res.body.decoration;
	           	this.img=res.body.img;
	           	this.dianji=res.body.dianji;
	           	this.username=res.body.user.username;
	           	this.accNbr=res.body.user.accNbr;
	           	this.img1 = JSON.parse(res.body.img)[0].file.src;
            },function(res){
            	
            });
        },
        comment:function(){
        	var hId = location.search.split("=")[1];
        	this.$http.post('comment/findComment.do',{id:hId},{emulateJSON:true}).then(function(res){
        		this.commentList = res.body.rows;
            },function(res){
            	
            });
        },
        tijiao:function(){
        	this.$http.post('user/zhanshi.do',{emulateJSON:true}).then(function(res){
            	if(res.body.username == null){
            		alert("对不起，您还没有登录")
            	}else{
            		var hId = location.search.split("=")[1];
            		var options={content:this.content,id:hId};
            		this.$http.post('comment/add.do',options,{emulateJSON:true}).then(function(res){
                    	if(res.body.success){
                    		this.comment();
                    		this.content=null;
                    		alert("评论成功")
                    	}else{
                    		alert("添加失败")
                    	}
                    	
                    },function(res){
                        
                    });
            	}
            	
            },function(res){
                
            });
        }
    },
});

