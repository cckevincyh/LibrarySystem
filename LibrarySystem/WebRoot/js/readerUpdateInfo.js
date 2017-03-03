
$(function () {
	
	
    $('#reader_updateInfo').click(function () {

	var postdata = "name="+$.trim($("#name").val())+"&phone="+ $.trim($("#phone").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'reader/readerInfoAction_readerInfo.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updateinfo").modal("hide");//关闭模糊框		
						showInfo("修改成功");	
				
	                    
	                }else {
						$("#updateinfo").modal("hide");//关闭模糊框
	                    showInfo("修改失败");
	                }
								
				}
			}
			   
    	);
			
		
	});
	
	$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    });

});



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}