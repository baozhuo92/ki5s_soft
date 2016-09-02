<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#table{
		width:100%;
	}
	#yourId {
		width: 200px;
		height: 150px;
		position:relative; /* or fixed or absolute */
	}
</style>

<textarea rows="20" id="content" name="content"></textarea>
<script type="text/javascript">
	$(function(){
		
		editor = KindEditor.create('#content',{
			resizeMode :1,
			allowPreviewEmoticons : false,
			allowUpload : true,
			width:'auto',
			uploadJson : '/update/ajaxFileUpload.html'
		});
	}); 
</script>

<div id="yourId"></div>
<input type="file" id="uploadFile" name="uploadFile"/>
<img src="" id="getImage"/>
<button id="updateImage" onclick="updateImg();">上传图片</button>


<div id="table"></div>
<div id="pager"></div>
<div id="tb" style="height:50px;">
	<button class="btn btn-success" id="exportTable" onclick="exportTable();">导出表格</button>
	<button class="btn btn-success" id="exportTable" onclick="exportCSV();">导出表格(CSV版)</button>
</div>

<script>
	$(function(){
		loadTable();
	});
	//导出
	function exportTable(){
		window.open("/exportExcel.html");
	}
	function exportCSV(){
		window.open("/exportCSV.html");
	}
	//ajax上传文件
	function updateImg(){
		 $.ajaxFileUpload({	  
			 url: '/update/ajaxFileUpload.html', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'uploadFile', //文件上传域的ID和Name必须同时与服务器上对应为uploadFile
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function (data)  //服务器成功响应处理函数
	            {
	            	if(data.state=="error"){
	            		alert(data.message);
	            	}else if(data.state=='success'){
	          	  		$("#getImage").attr("src",data.url);
	            	}
	            }
	     });
	}
	
	function loadTable(){
		$('#table').bootstrapTable({
        url:'/admin/loadTable.json',
        rowStyle:function(){
            return $("#table thead tr").css({"background-color":"#1685a9",color:"#ffffff"});
        },
        method:"POST",
        responseHandler:function(res) {
            return res;
        },
        striped:true,
        toolbar: '#tb',
        pagination:true,
        pageSize:10,
        pageList:[5,10,20,50,100],
        showHeader:true,
        sortable:true,
        search:true,
        trimOnSearch:false,
        showColumns:true,
        showToggle:true,
        showRefresh:true,
        undefinedText:'--',
        sidePagination: "server", //表示服务端请求
        columns: [
 			{field:'id',title:'编号',width:"10%",align:'center',sortable:true},
			{field:'name',title:'名称',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'},
			{field:'menuUrl',title:'连接',width:"10%",align:'center'}
		]});
	}
</script>