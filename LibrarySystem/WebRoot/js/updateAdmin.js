
/**
 * ajax提交修改管理员的信息
 * @param {Object} '#updateAdmin'
 */
$(function () {
	

    $('#updateAdmin').click(function () {

	var postdata = "id="+$.trim($("#updateId").val())+"&username="+$.trim($("#updateUsername").val())+"&name="+$.trim($("#updateName").val())+"&phone="+ $.trim($("#updatePhone").val())+"&pwd="+ $.trim($("#updatePwd").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/adminManageAction_updateAdmin.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updateModal").modal("hide");//关闭模糊框		
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






/**
 * 获取需要修改用户信息
 * @param {Object} username 需要修改的用户名
 */
function updateAdmin(id){
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/adminManageAction_getAdmin.action',
				params: "id=" + id,
				type:"json",
	    		callback:function(data) {
					$("#updateId").val(data.id);
					$("#updateUsername").val(data.username);
					$("#updateName").val(data.name);
					$("#updatePhone").val(data.pwd);
					$("#updatePwd").val(data.phone);
								
				}
			}
			   
    	);
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


