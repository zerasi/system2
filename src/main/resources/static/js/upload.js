/**
 * 
 */

var vm = new Vue({
    el:'#wrapper',
    data:{
    	name:null,
    	flag:null,
    	location:null,
    	type:null,
    	area:null,
    	price:null,
    	decoration:null,
    	imgList: [],
        size: 0
    	
    },
    created:function(){
    	
    },
    methods:{
    	tijiao:function(){
    		this.$http.post('user/zhanshi.do',{emulateJSON:true}).then(function(res){
            	if(res.body.username == null){
            		alert("对不起，您还没有登录")
            	}else{
            		var options={name:this.name,flag:this.flag,location:this.location,type:this.type,area:this.area,price:this.price,decoration:this.decoration,img:JSON.stringify(this.imgList)};
            		this.$http.post('house/useradd.do',options,{emulateJSON:true}).then(function(res){
                    	if(res.body.success){
                    		alert("添加成功")
                    	}else{
                    		alert("添加失败")
                    	}
                    	
                    },function(res){
                        
                    });
            	}
            	
            },function(res){
                
            });
    	},
        fileClick(){
            document.getElementById('upload_file').click()
          },
          fileChange(el){
            if (!el.target.files[0].size) return;
            this.fileList(el.target.files);
            el.target.value = ''
          },
          fileList(files){
            for (let i = 0; i < files.length; i++) {
              this.fileAdd(files[i]);
            }
          },
          fileAdd(file){
            this.size = this.size + file.size;//总大小
            let reader = new FileReader();
            reader.vue = this;
            reader.readAsDataURL(file);
            reader.onload = function () {
              file.src = this.result;
              this.vue.imgList.push({
                file
              });
            }
          },
          fileDel(index){
            this.size = this.size - this.imgList[index].file.size;//总大小
            this.imgList.splice(index, 1);
          },
          bytesToSize(bytes){
            if (bytes === 0) return '0 B';
            let k = 1000, // or 1024
              sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
              i = Math.floor(Math.log(bytes) / Math.log(k));
            return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
          },
          dragenter(el){
            el.stopPropagation();
            el.preventDefault();
          },
          dragover(el){
            el.stopPropagation();
            el.preventDefault();
          },
          drop(el){
            el.stopPropagation();
            el.preventDefault();
            this.fileList(el.dataTransfer.files);
          }
          
    	
    	
	}
});

