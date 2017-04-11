
/**
 * ajax提交修改管理员的信息
 * @param {Object} '#updateType'
 */
$(function () {
	

    $('#updateType').click(function () {

	var postdata = "id="+$.trim($("#readerTypeId").val())+"&maxNum="+$.trim($("#maxNum").val())+"&bday="+$.trim($("#bday").val())+"&penalty="+ $.trim($("#penalty").val())
	+"&readerTypeName="+ $.trim($("#readerTypeName").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerTypeManageAction_updateReaderType.action',
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






function updateReaderType(id){
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerTypeManageAction_getReaderType.action',
				params: "id=" + id,
				type:"json",
	    		callback:function(data) {
					$("#readerTypeId").val(data.readerTypeId);
					$("#readerTypeName").val(data.readerTypeName);
					$("#maxNum").val(data.maxNum);
					$("#bday").val(data.bday);
					$("#penalty").val(data.penalty);							
				}
			}
			   
    	);
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


