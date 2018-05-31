(function($){
	$.fn.ShowDialog = function(options) {
	var opts = $.extend({},$.fn.ShowDialog.defaults,options);
	$(this).click(function(){
						   		var skinurl="",yanse="";
								switch(opts.skin)
								{
									case "blue":
									skinurl="diag/images/blue/close_1.gif";	
									skinurl2="diag/images/blue/close_2.gif";	
									yanse="blue";
									break;
									case "red":
									skinurl="diag/images/red/close_1.gif";	
									skinurl2="diag/images/blue/close_2.gif";	
									yanse="red";
									break;
									case "green":
									skinurl="diag/images/green/close_1.gif";	
									skinurl2="diag/images/blue/close_2.gif";	
									yanse="green";
									break;
								}	
								$("#showdialogcss").attr("href","diag/images/"+yanse+"/css.css");

									if(!$("#diawindow").length>0)
									{
									 var maktemp='<div id="markdiag" style="background:#666;width:'+$(document).width()+'; height:'+($(document).height())+'px; position:absolute; top:0px; left:0px; z-index:80;"></div>';
									 var tempstr ='<div class="showdiv" id="diawindow"><div class="top"><div class="topleft"></div><div class="topmiddle" id="topmiddle"> <span style="float:left; color:#FFF; font-weight:bold; line-height:26px; font-size:12px;">&nbsp;'+opts.Title+'</span><span style="float:right"><a href="javascript:"><img border="0" src="'+skinurl+'" width="26" height="20" id="diagclose" /></a></span></div><div class="topright"></div></div><div class="clear"></div><div class="middle"><div class="middleleft" id="middleleft"></div><div class="middlemiddle"  id="middlemiddle"><div style="background:#fff;" id="middlecontent"><iframe src="'+opts.FrameURL+'" style="height:100%; width:100%;" scrolling="no" frameborder="0"></iframe></div></div><div class="middleright" id="middleright"></div></div><div class="clear"></div><div class="end"><div class="endleft"></div><div class="endmiddle" id="endmiddle"></div><div class="endright"></div></div><div class="clear"></div></div>';
									 if(opts.ContentFlag==1)
									{
										tempstr ='<div class="showdiv" id="diawindow"><div class="top"><div class="topleft"></div><div class="topmiddle" id="topmiddle"> <span style="float:left; color:#FFF; font-weight:bold; line-height:26px; font-size:12px;">&nbsp;'+opts.Title+'</span><span style="float:right"><a href="javascript:"><img border="0" src="'+skinurl+'" width="26" height="20" id="diagclose" /></a></span></div><div class="topright"></div></div><div class="clear"></div><div class="middle"><div class="middleleft" id="middleleft"></div><div class="middlemiddle"  id="middlemiddle"><div style="background:#fff;" id="middlecontent">'+opts.Contents+'</div></div><div class="middleright" id="middleright"></div></div><div class="clear"></div><div class="end"><div class="endleft"></div><div class="endmiddle" id="endmiddle"></div><div class="endright"></div></div><div class="clear"></div></div>';
									}
									 $("body").append(maktemp);
									 $("body").append(tempstr);									  
									}
									else
									{
										$("#markdiag").show();
										$("#markdiag").show();
									}
											var css={}
											   if(window.navigator.userAgent.indexOf('MSIE')>=1)
											   {
												   css.filter= 'progid:DXImageTransform.Microsoft.Alpha(opacity='+opts.Intopacity*100+')';
											   }
											   else
											   {
												   css.opacity= opts.Intopacity;
											    }
											$("#markdiag").css(css);
											var w,h,de;
											de = document.documentElement;
											w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
											h = self.innerHeight || (de&&de.clientHeight)|| document.body.clientHeight;
											var diagtop = h/2-(opts.Height/2)+eval($(document).scrollTop());
											var diagleft = w/2-(opts.Width/2)+eval($(document).scrollLeft());	
											$("#diawindow").css({"top" : diagtop,"left":diagleft,"width":opts.Width,"height":opts.Height});
											$("#topmiddle").css({"width":opts.Width-20});
											$("#middlemiddle").css({"width":opts.Width-8,"height":opts.Height-34});
											$("#middleleft").css({"height":opts.Height-34});
											$("#middleright").css({"height":opts.Height-34});
											$("#middlecontent").css({"height":opts.Height-44});								
											$("#endmiddle").css({"width":opts.Width-20});
											$(window).scroll(function(){
											 var diagtop = h/2-(opts.Height/2)+eval($(document).scrollTop());
											 var diagleft = w/2-(opts.Width/2)+eval($(document).scrollLeft());
											 $("#diawindow").css({"top" : diagtop,"left":diagleft });
											 });
											$("#diagclose").mousemove(
																	  function(){
																		  $(this).attr("src",skinurl2);
																	  }
																	  ).mouseout(
																	  function(){
																		  $(this).attr("src",skinurl);
																	  }
																	  ).click(function(){
												if($("#diawindow").length>0)
												{
													$("#diawindow").remove();
												}
												if($("#markdiag").length>0)
												{
													$("#markdiag").remove();
												}						   
								   			})

						   })
    };	
	$.fn.ShowDialog.defaults={
		Width:"300",
		Height:"300",
		Title:"对话框",
		Intopacity:"0.2",
		ContentFlag:"0",
		skin:"blue",
		FrameURL:"",
		Contents:""
	};
})(jQuery);