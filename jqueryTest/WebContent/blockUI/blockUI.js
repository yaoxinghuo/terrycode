//DOM ready
$(document).ready(function(){
	init();
});

$().ajaxStop($.unblockUI);

function test() {
        $.ajax({ url: 'stylesheet.html', cache: false });
}
//global functions
function init(){
		//example 1
	    $('#pageDemo1').click(function() {
            $.blockUI();
            test();
        });
        $('#pageDemo2').click(function() {
            $.blockUI({ message: '<h1><img src="busy.gif" /> 请稍候...</h1>' });
            test();
        });
        $('#pageDemo3').click(function() {
            $.blockUI({ css: { backgroundColor: '#f00', color: '#fff' } });
            test();
        });

        $('#pageDemo4').click(function() {
            $.blockUI({ message: $('#domMessage') });
            test();
        });
		
		//example 2
		$('#blockButton').click(function() {
            $('div.blockMe').unblock();
            $('div.blockMe').block({ message: null });
        });

        $('#blockButton2').click(function() {
            $('div.blockMe').unblock();
            $('div.blockMe').block({ 
                message: '<h1>处理中...</h1>', 
                css: { border: '3px solid #a00' } 
            });
        });

        $('#unblockButton').click(function() {
            $('div.blockMe').unblock();
        });

        $('a.test').click(function() {
            alert('链接被点击！');
            return false;
        });
		
		
		//example3
		$('#showDialog').click(function() { 
            $.blockUI({ message: $('#question'), css: { width: '275px' } }); 
        }); 
 
        $('#yes').click(function() { 
            // update the block message 
            $.blockUI({ message: "<h1>正在进行通信...</h1>" }); 
 
            $.ajax({ 
                url: 'stylesheet.html', 
                cache: false, 
                complete: function() { 
                    // unblock when remote call returns 
                    $.unblockUI(); 
                } 
            }); 
        }); 
 
        $('#no').click($.unblockUI); 
}


