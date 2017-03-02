/**
 *管理员点击个人资料修改按钮触发进行ajax异步请求
 * @param {Object} '#admin_updateInfo'
 */

$(function () {
	
	
    $('#admin_updateInfo').click(function () {

	var postdata = "name="+$.trim($("#name").val())+"&phone="+ $.trim($("#phone").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/adminInfoAction_adminInfo.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updateinfo").modal("hide");//关闭模糊框		
						showInfo("修改成功");	
						$('#btn_info_close').click(function () {
							location.reload();  	//刷新当前页面
						});
	                    
	                }else {
						$("#updateinfo").modal("hide");//关闭模糊框
	                    showInfo("修改失败");
	                }
								
				}
			}
			   
    	);
			
		
	});
	
	

});



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}