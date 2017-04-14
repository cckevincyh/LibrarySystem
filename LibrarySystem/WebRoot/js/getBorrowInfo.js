
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getBorrowInfoById(id){
	ajax(
		  {
		  	method:'POST',
    		url:'admin/borrowManageAction_getBorrowInfoById.action',
			params: "borrowId=" + id,
			type:"json",
    		callback:function(data) {
				
				$("#borrowId").val(data.borrowId);
				$("#ISBN").val(data.book.ISBN);
				$("#bookName").val(data.book.bookName);
				$("#bookType").val(data.book.bookType.typeName);
				$("#paperNO").val(data.reader.paperNO);
				$("#readerName").val(data.reader.name);
				$("#readerType").val(data.reader.readerType.readerTypeName);
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


