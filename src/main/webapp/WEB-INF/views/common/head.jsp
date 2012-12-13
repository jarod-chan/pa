<%@ page language="java" pageEncoding="UTF-8"%>

<title>方远房产卓越绩效管理平台</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/layout.css" /> 

<script type="text/javascript" src="/${ctx}/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/${ctx}/resources/js/myplug.js"></script>
<script type="text/javascript" src="/${ctx}/resources/js/mycommon.js"></script>
<script type="text/javascript" src="/${ctx}/resources/js/myfloat.js"></script>
<script type="text/javascript" src="/${ctx}/resources/js/jquery.autoresize.js"></script>
<script type="text/javascript" src="/${ctx}/resources/js/ptahjs.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
	
	
	function OpenEnvDefineWin(url,width,height)
    {
        var left = eval(screen.width - width) / 2;
        var top = eval(screen.height - height) / 2;
        var open_feature = "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top + ", scrollbars=yes,fullscreen=1";
        var hwnd = window.open(url, "_blank", open_feature);
        if ((window != null) && (!hwnd.opener))
            hwnd.opener = window;
        hwnd.focus();
        return false;
    }
</script>  
<style type="text/css"></style>
