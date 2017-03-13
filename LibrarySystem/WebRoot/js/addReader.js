
/**
 * ajax提交添加读者的信息
 * @param {Object} '#addReader'
 */
$(function () {
	

    $('#addReader').click(function () {

	var postdata = "readerId="+$.trim($("#addReaderId").val())+"&name="+$.trim($("#addName").val())+"&phone="+ $.trim($("#addPhone").val())+"&pwd="+ $.trim($("#addPwd").val())+"&readerType="+ $.trim($("#addreaderType").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerManageAction_addReader.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

	                }else if (data == -1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("该管理员已存在");	
					}else {
						$("#addModal").modal("hide");//关闭模糊框
						showInfo("添加失败");
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


