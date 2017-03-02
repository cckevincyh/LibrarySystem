/**
 * 点击修改按钮之后ajax提交数据修改密码
 * @param {Object} '#update_adminPwd'
 */
$(function () {
	
	
    $('#update_adminPwd').click(function () {

	var postdata = "oldPwd="+$.trim($("#oldPwd").val())+"&newPwd="+ $.trim($("#newPwd").val())+"&confirmPwd="+ $.trim($("#confirmPwd").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/adminInfoAction_adminPwd.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updatepwd").modal("hide");//关闭模糊框		
						showInfo("修改成功");	
						$('#btn_info_close').click(function () {
							location.reload();//刷新当前页面	
						});
	                    
	                }else if (data == 0) {
	                    showInfo("确认密码不一致");
	                }else if(data == -1){
						 showInfo("原密码错误");
					}else{
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