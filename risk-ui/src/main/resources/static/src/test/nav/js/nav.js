"use strict"	

$(function(){
	
(function ($) {
    $.fn.extend({
        "submenu": function (options) {
            var opts = $.extend({}, defaluts, options); 
            this.append('<ul class="dropdown-menu"></ul>');
            this.css({
            	position:opts.position
            });
            var $this=$(this);
            this.click(function(e){
            	var items='';
            	for(var i=0;i<jsondata.length;i++){
            		var subItems='';
            		if(jsondata[i].sons!==undefined){
            			subItems+='<ul class="m-list">';
            			for(var m=0;m<jsondata[i].sons.length;m++){
            				subItems+='<li>'+jsondata[i].sons[m].value+'</li>';
            			}
            			subItems+='</ul>';
            		}
            		items+='<li>'+jsondata[i].value+subItems+'</li>';
            	}
            	$this.find('.dropdown-menu').html(items);
            	$this.find('.dropdown-menu>li').hover(function(){
            		$(this).find('.m-list').show();
            	},function(){
            		$(this).find('.m-list').hide();
            	});
            	$this.find('.dropdown-menu').toggle();
				$(document).on("click", function(){
					$(".dropdown-menu").hide();
				});
				e.stopPropagation();
            });
				$(".dropdown-menu").on("click", function(e){
					e.stopPropagation();
				});
        }
    });
    var defaluts = {
        position: 'relative'
    };
})(window.jQuery);
});


