//jquery 自定义插件 把数组按spring格式提交
(function($) {     
	$.fn.formatName = function() {   
		var _options = {
    		match : "_"
        };
		var re=new RegExp(_options.match,"g"); 
    	
        this.init = function () {
        	if($(this).is("tbody")){
        		var list=$(this).find("tr");
        	}else if($(this).is("ul")){
        		var list=$(this).find("li");
        	}else{
        		var list=$(this);//传入数组本身，其它两个方法可以修改
        	}
        	list.each(function(index){
        		$(this).find('input[name*='+_options.match+'],select[name*='+_options.match+'],textarea[name*='+_options.match+']').each(function(){	
       				$(this).attr("name",$(this).attr("name").replace(re,"["+index+"]."));
        		});
        	});
        }
        this.init();
	};   
})(jQuery);  
