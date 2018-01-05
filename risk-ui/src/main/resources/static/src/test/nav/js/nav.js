"use strict"  
;(function ($) {  
	$.fn.extend({
		"submenu": function (options) {
			
			if (!typeof(options) === 'object') return console.error("submenu方法参数必须是object对象");
			if (!options.data || !options.hasOwnProperty('data')) return console.error("submenu方法必须包含data参数");
			if (!options.hasOwnProperty('callback') || !typeof(options.callback) === 'function') return console.error("submenu方法必须包含callback参数并且必须是一个函数");
			
			var cb = options.callback;
			var jsondata = options.data;
			
			
			this.after("<ul class='xy-dropdown-menu'></ul>")
			var tx = this.offset().top;
			var ty = this.offset().left;
			var th = this.height();
			$('.xy-dropdown-menu').offset({top:tx+th+11,left:ty+5});
			
			var $this = $(this);
			this.click(function (e) {

				var items='';

				for (var i = 0; i < jsondata.length; i++) {
					var subItems = '';

					if ( jsondata[i].sons != undefined ) {
						subItems += '<ul class="m-list">';

						for (var m = 0; m < jsondata[i].sons.length; m++) {
							var _value = jsondata[i].sons[m].value;
							subItems += '<li value=' + _value + '>' + jsondata[i].sons[m].text + '</li>';
						}

						subItems+='</ul>';
					}

					items+='<li>'+jsondata[i].text+subItems+'</li>';
				}

				$('.xy-dropdown-menu').html(items);

				// 点击事件
				$('.xy-dropdown-menu').on('click','.m-list li', function (e) {
					var idx = $(this).index();
					var _value = $(this).val();
					cb && cb($(this), _value);
				});

				$('.xy-dropdown-menu>li').hover(function(){
					$(this).find('.m-list').show();
				},function(){
					$(this).find('.m-list').hide();
				});

				$('.xy-dropdown-menu').toggle();

				$(document).on("click", function(){
					$(".xy-dropdown-menu").hide();
				});

				e.stopPropagation();

			});

			$(".xy-dropdown-menu").on("click", function(e){
				e.stopPropagation();
			});
		}
	});
})(window.jQuery);
