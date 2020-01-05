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
        	var options={flag:0};
        	this.$http.post('house/findAll.do',options,{emulateJSON:true}).then(function(res){
        		this.list1 = res.body;
            },function(res){
            	
            });
        },
        maifang:function(){
        	var options={flag:1};
        	this.$http.post('house/findAll.do',options,{emulateJSON:true}).then(function(res){
        		this.list2 = res.body;
            },function(res){
            	
            });
        }
    },
});

