(function($) { 
   
	function calendarWidget(params) { 
		var monthNames = ['1', '2', '3', '4', '5', '6', '7', '8', '8', '10', '11', '12'];
		var dayNames = ['日', '一', '二', '三', '四', '五', '六'];
		
		
		
		
		
		var now   = new Date();
		var thismonth = now.getMonth();
		var thisyear  = now.getYear() + 1900;
		
		var opts = {
			month: thismonth,
			year: thisyear
		};
		
		$.extend(opts, params);
		
		
		month = i = parseInt(opts.month);
		year = parseInt(opts.year);
		var m = 0;
		var table = '';
		
			// next month
			if (month == 11) {
				var next_month = '<a href="?month=' + 1 + '&amp;year=' + (year + 1) + '" title="' + monthNames[0] + ' ' + (year + 1) + '">' + monthNames[0] + ' ' + (year + 1) + '</a>';
			} else {
				var next_month = '<a href="?month=' + (month + 2) + '&amp;year=' + (year) + '" title="' + monthNames[month + 1] + ' ' + (year) + '">' + monthNames[month + 1] + ' ' + (year) + '</a>';
			}
				
			// previous month
			if (month == 0) {
				var prev_month = '<a href="?month=' + 12 + '&amp;year=' + (year - 1) + '" title="' + monthNames[11] + ' ' + (year - 1) + '">' + monthNames[11] + ' ' + (year - 1) + '</a>';
			} else {
				var prev_month = '<a href="?month=' + (month) + '&amp;year=' + (year) + '" title="' + monthNames[month - 1] + ' ' + (year) + '">' + monthNames[month - 1] + ' ' + (year) + '</a>';
			}		
				
			table += ('<div class="calendar-title"><span id="current-month">'+year+'年'+monthNames[month]+'月</span>');
			table += ('<input type="button"  class="nav-prev" value="&lt;" />');
			table += ('&nbsp;');
			table += ('<input type="button"  class="nav-prev" value="&gt;" />&nbsp;</div>');

//			table += ('<div>'+ prev_month +'</div>');
//			table += ('<div class="nav-next">'+ next_month +'</div>');
			table += ('<table class="calendar-month " ' +'id="calendar-month'+i+' " cellspacing="0">');	
		
			table += '<tr>';
			
			for (d=0; d<7; d++) {
				table += '<th class="weekday">' + dayNames[d] + '</th>';
			}
			
			table += '</tr>';
		
			var days = getDaysInMonth(month,year);
            var firstDayDate=new Date(year,month,1);
            var firstDay=firstDayDate.getDay();
			
			var prev_m = month == 0 ? 11 : month-1;
			var prev_y = prev_m == 11 ? year - 1 : year;
			var prev_days = getDaysInMonth(prev_m, prev_y);
			firstDay = (firstDay == 0 && firstDayDate) ? 7 : firstDay;
	
            for (j=0;j<42;j++){
			  
              if ((j<firstDay)){
                table += ('<td class="other-month"><span class="day">&nbsp;</span></td>');
			  } else if (j>=firstDay+days) {
                table += ('<td class="other-month"><span class="day">&nbsp;</span></td>');			 
              }else{
                table += ('<td class="current-month day'+(j-firstDay+1)+'"><span class="day">'+(j-firstDay+1)+'</span><ul class="ampm"><li><a href="#">上午</a></li><li><a href="#">下午</a></li></ul></td>');
              }
              if (j%7==6)  table += ('</tr>');
            }

            table += ('</table>');
            
            var div=$('<div><div>');
    		div.css("position","absolute");
    		div.css("left",30);
    		div.css("top",300);
    		div.css("background-color","#FFFFFF");
    		div.html(table);
    		$("body").append(div);
    		
            return div;
	}
	
	function getDaysInMonth(month,year)  {
		var daysInMonth=[31,28,31,30,31,30,31,31,30,31,30,31];
		if ((month==1)&&(year%4==0)&&((year%100!=0)||(year%400==0))){
		  return 29;
		}else{
		  return daysInMonth[month];
		}
	}
	
	
	// jQuery plugin initialisation
	$.fn.calendarWidget = function(params) { 
		var obj=$(this);
		var div=calendarWidget( params);
		$(this).click(function(){
			div.toggle();
		});
		$(".current-month",div).hover(
			function(){
				var t=$(this).find(".day").html();
				if(t>10){
				$(this).find(".day").hide();
				$(this).find(".ampm").find("li:eq(0)").hide();
				
				$(this).find(".ampm").css("float","right").show();
				}
			},
			function(){
				$(this).find(".day").show();
				$(this).find(".ampm").hide();
			}
		); 
		
		$(".current-month a",div).click(function(){
			$("#target").html("DD");
			obj.calendarWidget(params);
		}); 
		
		return this; 
	}; 

})(jQuery);
