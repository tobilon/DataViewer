$(document).ready(function(){
	var menustatus=0;

	$("ul.topnav li").mouseover(function(){
		$(this).css("background", "url(navi/topnav_hover.gif) no-repeat center top");
	})
	$("ul.topnav li").mouseout(function(){
		$(this).css("background", "");
	})
	$("ul.topnav li").click(function() { //When trigger is clicked...
	    if (menustatus == 0) {
		    //Following events are applied to the subnav itself (moving subnav up and down)
		    $(this).find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click
		    menustatus = 1;
		}
	    else {
		    $(this).find("ul.subnav").slideUp('slow').show(); //Drop down the subnav on click
		    menustatus = 0;
        }

		$(this).hover(
				function() {					
				},
				function() {
					$(this).find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up
					});

									//Following events are applied to the trigger (Hover events for the trigger)
				}).hover(function() {
					$(this).addClass("subhover"); //On hover over, add class "subhover"
					}, function() { //On Hover Out
					$(this).removeClass("subhover"); //On hover out, remove class "subhover"
					});
	});