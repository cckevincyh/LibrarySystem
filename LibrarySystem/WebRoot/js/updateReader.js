
/**
 * ajax提交修改管理员的信息
 * @param {Object} '#updateReader'
 */
$(function () {
	

    $('#updateReader').click(function () {

	var postdata = "readerId="+$.trim($("#updateReaderID").val())+"&readerType="+$.trim($("#updateReaderType").val())+"&name="+$.trim($("#updateName").val())+"&phone="+ $.trim($("#updatePhone").val())+"&pwd="+ $.trim($("#updatePwd").val())+"&maxNum="+ $.trim($("#updateNum").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerManageAction_updateReader.action',
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
function updateReader(id){
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerManageAction_getReader.action',
				params: "readerId=" + id,
				type:"json",
	    		callback:function(data) {
					$("#updateReaderID").val(data.readerId);
					$("#updateName").val(data.name);
					$("#updateNum").val(data.maxNum);
					$("#updatePhone").val(data.phone);
					$("#updatePwd").val(data.pwd);
					$("#updateReaderType").val(data.readerType);
				}
			}
			   
    	);
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


