<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="uploadify/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="uploadify/swfobject.js"></script>
<script type="text/javascript"
	src="uploadify/jquery.uploadify.v2.1.4.js"></script>

<link href="uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#fileupload").uploadify({
			/*注意前面需要书写path的代码*/
			"uploader" : "uploadify/uploadify.swf",
			"script" : "upload",
			"cancelImg" : "uploadify/cancel.png",
			"queueID" : "fileQueue", //和存放队列的DIV的id一致 
			"fileDataName" : "fileupload", //和以下input的name属性一致 
			"auto" : false, //是否自动开始 
			"multi" : true, //是否支持多文件上传 
			"buttonText" : "Browse", //按钮上的文字 
			"simUploadLimit" : 3, //一次同步上传的文件数目 
			"sizeLimit" : 19871202, //设置单个文件大小限制 
			"queueSizeLimit" : 2, //队列中同时存在的文件个数限制 
			"fileDesc" : "支持格式:txt.", //如果配置了以下的"fileExt"属性，那么这个属性是必须的 
			"fileExt" : "*.txt",//允许的格式   
			"buttonImg" : "uploadify/upload.png",
            "auto": true, //选择文件后自动上传
			onComplete : function(event, queueID, fileObj, response, data) {
				//$("<li></li>").appendTo(".files").text(response);
				$("#content").html(response);
			},
			onError : function(event, queueID, fileObj) {
				alert("文件:" + fileObj.name + "上传失败");
			},
			onCancel : function(event, queueID, fileObj) {
				alert("取消了" + fileObj.name);
			}
		});

	});

	//必须的 
	function uploadifyUpload() {
		$("#fileupload").uploadifyUpload();
	}
</script>
<body>
	<table>
		<tr><td><a href="${pageContext.request.contextPath}/fileUpload/export.action">导出Excel</a></td></tr>
		<tr>
			<td>上传图片：</td>
			<td><div id="uploadBtn"><input type="file" name="fileupload" id="fileupload" /></div>
				<div id="fileQueue"></div>
				<p>
					<a href="javascript:uploadifyUpload()">开始上传</a>&nbsp; 
					<a href="javascript:jQuery('#fileupload').uploadifyClearQueue()">取消所有上传</a>
				</p>
				<ol class=files></ol>
			</td>
		</tr>
	</table>
	<div id="content"></div>
</body>