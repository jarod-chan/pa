//如果是ie浏览器，可能不支持trim方法，此处为了加上支持，fuck ie
if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}

//实现ie下的maxlength方法
(function($) {     
	$.fn.iemaxlength = function() { 
		if($.browser.msie) { 
			this.func=function() {    
		        var len = parseInt($(this).attr("maxlength"), 10);
		        if(this.value.length > len) { 
		          this.value = this.value.substr(0, len); 
		          return false; 
		        } 
		     }
			 $(this).bind("blur",this.func);
			 $(this).bind("keyup",this.func);
		}
		return this;
	};   
})(jQuery);  