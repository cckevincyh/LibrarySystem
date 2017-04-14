
$(function () {
	

  $('#addBook').click(function () {

	var postdata = "bookName="+$.trim($("#addBookName").val())+"&autho="+ $.trim($("#addAutho").val())+"&press="+ $.trim($("#addPress").val())+"&num="+ $.trim($("#addNum").val())+"&price="+ $.trim($("#addPrice").val())+"&description="
	+ $.trim($("#addDescription").val())+"&bookTypeId="+ $.trim($("#addBookType").val())+"&ISBN="+ $.trim($("#addISBN").val());
	
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/bookManageAction_addBook.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

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
		
		
		 $('#btn_add').click(function () {
		 	$("#addBookType option[value!=-1]").remove();//移除先前的选项
			ajax(
					  {
			    		url:"admin/bookManageAction_getAllBookTypes.action",
			    		type:"json",
			    		callback:function(data) {
							// 循环遍历每个图书分类，每个名称生成一个option对象，添加到<select>中
							for(var index in data) {
								var op = document.createElement("option");//创建一个指名名称元素
								op.value = data[index].typeId;//设置op的实际值为当前的图书分类编号
								var textNode = document.createTextNode(data[index].typeName);//创建文本节点
								op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值
								
								document.getElementById("addBookType").appendChild(op);
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


