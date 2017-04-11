window.onload = new function(){
	ajax(
		  {
	    		url:"admin/readerTypeManageAction_getAllReaderTypes.action",
	    		type:"json",
	    		callback:function(data) {
					// 循环遍历每个读者分类，每个名称生成一个option对象，添加到<select>中
					for(var index in data) {
						var op = document.createElement("option");//创建一个指名名称元素
						op.value = data[index].readerTypeId;//设置op的实际值为当前的读者分类编号
						var textNode = document.createTextNode(data[index].readerTypeName);//创建文本节点
						op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值
						
						document.getElementById("addreaderType").appendChild(op);
					}
				}
		   }
	);
};
/**
 * ajax提交添加读者的信息
 * @param {Object} '#addReader'
 */
$(function () {
	

    $('#addReader').click(function () {

	var postdata = "paperNO="+$.trim($("#addPaperNO").val())+"&name="+$.trim($("#addName").val())+"&phone="+ $.trim($("#addPhone").val())+"&pwd="+ $.trim($("#addPwd").val())+"&readerType="+ $.trim($("#addreaderType").val())
	+"&email="+ $.trim($("#addEmail").val());
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
						showInfo("该读者已存在");	
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


