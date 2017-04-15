
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getBackInfoById(id){
	ajax(
		  {
		  	method:'POST',
    		url:'admin/backManageAction_getBackInfoById.action',
			params: "borrowId=" + id,
			type:"json",
    		callback:function(data) {
				
				$("#borrowId").val(data.borrowId);
				$("#ISBN").val(data.borrowInfo.book.ISBN);
				$("#bookName").val(data.borrowInfo.book.bookName);
				$("#bookType").val(data.borrowInfo.book.bookType.typeName);
				$("#paperNO").val(data.borrowInfo.reader.paperNO);
				$("#readerName").val(data.borrowInfo.reader.name);
				$("#readerType").val(data.borrowInfo.reader.readerType.readerTypeName);
				if (data.state == 0) {
					$("#state").val("未归还");
				}else{
					$("#state").val("已归还");
				}
				$("#admin").val(data.admin.name);
			}
		}
										   
							    
						
	);	
	
	
	
	
	
			

}





function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


