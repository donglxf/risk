"use strict";
(function($) {
    // 防止重复的id
    var id = 1;

    // 生成html
    var genHTML = function (jsondata,type,cb) {
        // 根据数据源渲染ul
        var items = '';
        for (var i = 0; i < jsondata.length; i++) {
            var subItems = '';
            // 如果存在子集
            if (jsondata[i].sons != undefined) {
                subItems += '<ul class="m-list">';
                for (var m = 0; m < jsondata[i].sons.length; m++) {
                    var _value = jsondata[i].sons[m].value;
                    var _text = jsondata[i].sons[m].text;
                    var _key = jsondata[i].key + '_'+jsondata[i].sons[m].key;
                    subItems += '<li value=' + _value + '  key= '+_key+ ' ptext="'+jsondata[i].text+'">' + _text + '</li>';
                }
                subItems += '</ul>';
            }
            items += '<li value="'+jsondata[i].value+'" class="one-li" >' + jsondata[i].text + subItems + '</li>';
        }

        // 将完整的ul li 代码插入到页面中，保存$ul的引用
        var $ul = $("<ul class='xy-dropdown-menu' id='xy-dropdown-menu-" + id + "'>" + items + "</ul>").appendTo("body");
        // console.log($ul);
        // 给子列表的li绑定点击事件
        $ul.find('.m-list li').on('click', function(e) {
            var _value = $(this).attr("value");
            cb && cb($(this), _value);
            $ul.hide();
        });
        if(type == 1){
            $ul.find('li.one-li').on('click', function(e) {
                var _value = $(this).val();
                cb && cb($(this), _value);
                $ul.hide();
            });
        }

        // 展示或隐藏子列表
        $ul.find('>li').hover(function() {
            $(this).find(".m-list").show();
        }, function() {
            $(this).find(".m-list").hide();
        });

        // 点击屏幕任何角落，隐藏$ul
        $(document).on("click", function() { $ul.hide(); });
        $ul.on("click", function(e) {
            e.stopPropagation();
        });

        return $ul;
    }

    $.fn.extend({
        // 弹出nav
        submenu: function(options) {
            // 验证参数合法性
            if (!typeof(options) === 'object') return console.error("submenu方法参数必须是object对象");
            if (!options.data || !options.hasOwnProperty('data')) return console.error("submenu方法必须包含data参数");
            if (!options.hasOwnProperty('callback') || !typeof(options.callback) === 'function') return console.error("submenu方法必须包含callback参数并且必须是一个函数");

            // xy-dropdown-menu
            var menuId = $(this).attr('xy-dropdown-menu');
            if (menuId) $(menuId).remove()

            // 根据数据，生成html内容，并且插入到页面中去
            var $ul = genHTML(options.data,options.type, options.callback);
            var $obj = $(this);
            // 根据触发元素的位置，来设置ul的绝对路径
            var tx = this.offset().top;
            var ty = this.offset().left;
            var th = this.height();
            console.log(tx+":left"+ty);
            //$ul.offset({ top: tx + th + 11 + 'px', left: ty + 5 + 'px' });
            $ul.css("top",tx + th + 11 + 'px');
            $ul.css("left",ty - 20 + 'px');
            console.log($ul);
            // 将触发对象设置特殊属性
            $(this).attr('xy-dropdown-menu', '#xy-dropdown-menu-btn-' + id);

            // 增加id的索引
            id++;
            // 给触发对象绑定事件
            $(this).unbind('click').bind('click', function(e) {
                // 根据触发元素的位置，来设置ul的绝对路径
                var tx = $obj.offset().top;
                var ty = $obj.offset().left;
                var th = $obj.height();
                console.log(tx+":left"+ty);
                //$ul.offset({ top: tx + th + 11 + 'px', left: ty + 5 + 'px' });
                $ul.css("top",tx + th + 11 + 'px');
                $ul.css("left",ty - 1 + 'px');

                $ul.toggle();
                e.stopPropagation();
            });
        }
    });
})(window.jQuery);