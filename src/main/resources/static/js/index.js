/**
 * 
 */

var vm = new Vue({
    el:'#content',
    data:{
        list1:[],
		list2:[],
    },
    created:function(){
    	this.zufang();
    	this.maifang();
    },
    methods:{
        zufang:function(){
        	this.$http.post('house/zufang.do',{emulateJSON:true}).then(function(res){
        		this.list1 = res.body.rows;
            },function(res){
            	
            });
        },
        maifang:function(){
        	this.$http.post('house/maifang.do',{emulateJSON:true}).then(function(res){
        		this.list2 = res.body.rows;
            },function(res){
            	
            });
        }
    },
});

