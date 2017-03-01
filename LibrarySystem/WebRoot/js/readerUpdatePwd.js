
$(function () {
	
	
    $('#update_readerPwd').click(function () {

	var postdata = "oldPwd="+$.trim($("#oldPwd").val())+"&newPwd="+ $.trim($("#newPwd").val())+"&confirmPwd="+ $.trim($("#confirmPwd").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'reader/readerInfoAction_readerPwd.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updatepwd").modal("hide");//关闭模糊框		
						showInfo("修改成功");	
						$('#btn_info_close').click(function () {
							window.location.href = "reader.jsp";  	
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