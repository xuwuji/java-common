<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>welcome</title>

<!--引入JavaScript-->
<%-- <script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
 --%>

</head>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.13.5/dist/bootstrap-table.min.css">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://unpkg.com/bootstrap-table@1.13.5/dist/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script
	src="https://unpkg.com/bootstrap-table@1.13.5/dist/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body>
	<h1 align="center">父分类管理</h1>
	<!--工具-->
	<div id="toolbar" class="btn-group">
		<button id="btn-add" type="button" class="btn">
			<span aria-hidden="true" class="icon icon-plus-sign"></span>新增
		</button>
	</div>

	<div id="toolbar" class="btn-group">
		<button id="btn-batch-delete" type="button" class="btn">
			<span aria-hidden="true" class="icon icon-plus-sign"></span>批量删除
		</button>
	</div>
	<!--bootstrap-table表格-->
	<table id="data-table"></table>

	<!-- 新增的modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增一个父分类</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="txt_departmentname">父分类名称</label> <input type="text"
							name="txt_departmentname" data-bind="value:Name"
							class="form-control" id="parentCategoryName" placeholder="名称">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="button" id="btn_submit" class="btn btn-primary"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>新增
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 编辑用的modal -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="txt_departmentname">父分类id</label> <input type="text"
							name="txt_departmentname" data-bind="value:Name"
							class="form-control" id="editModalParentCategoryId"
							disabled="disabled">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">父分类名称</label> <input type="text"
							name="txt_departmentname" data-bind="value:Name"
							class="form-control" id="editModalParentCategoryName">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="button" id="btn_edit_submit" class="btn btn-primary"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>提交变更
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script>
	var $table = $('#data-table');

	$(window).resize(function() {
		//防止表头与表格不对齐
		$table.bootstrapTable('resetView');
	});

	$table.bootstrapTable({
		url : '/backend/admin/parentCategory/getAll', //请求后台的URL（*）
		method : 'get', //请求方式（*）
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortOrder : "asc", //排序方式
		//queryParams : oTableInit.queryParams,//传递参数（*）
		sidePagination : "client", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 5, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		showColumns : true, //是否显示所有的列
		showRefresh : true, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		// clickToSelect: true,                //是否启用点击选中行
		height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		columns : [ {
			checkbox : true
		}, {
			field : 'id',
			title : 'id',
			align : 'center'
		}, {
			field : 'name',
			title : 'name',
			align : 'center'
		}, {
			title : '操作',
			field : 'id',
			formatter : function(id) {
				var html = '<a href="javascript:edit(' + id + ')">编辑</a>';
				html += '　<a href="javascript:disable(' + id + ')">下架</a>';
				html += '　<a href="javascript:reActive(' + id + ')">上架</a>';
				return html;
			}
		}

		]
	});

	/* 展开modal，新增一个父分类 */
	$('#btn-add').on("click", function() {
		$table.bootstrapTable('refresh');
		$("#myModal").modal().on("shown.bs.modal", function() {

		});
	});

	/* 提交 */
	$('#btn_submit').on(
			"click",
			function() {
				console.log($('#parentCategoryName').val());
				var parentCategoryName = $('#parentCategoryName').val();
				$.ajax({
					url : "/backend/admin/parentCategory/add?name="
							+ parentCategoryName, //url
					type : "get",
					success : function(status) {
						//alert(status);
						$table.bootstrapTable('refresh');
					}
				});
			});

	//编辑操作
	function edit(id) {
		var row = $table.bootstrapTable('getRowByUniqueId', id);
		$("#editModal").modal().on("shown.bs.modal", function() {
			$('#editModalParentCategoryName').val(row.name);
			$('#editModalParentCategoryId').val(row.id);
		});
		console.log(row);
	}

	/* 提交变更 */
	$('#btn_edit_submit').on(
			"click",
			function() {
				console.log($('#editModalParentCategoryId').val());
				var editModalParentCategoryId = $('#editModalParentCategoryId')
						.val();
				var editModalParentCategoryName = $(
						'#editModalParentCategoryName').val();
				$.ajax({
					url : "/backend/admin/parentCategory/update",
					type : "post",
					//dataType : "json",
					data : {
						id : editModalParentCategoryId,
						name : editModalParentCategoryName
					},
					success : function(status) {
						//alert(status);
						$table.bootstrapTable('refresh');
					}
				});
			});

	//下架操作
	function disable(id) {
		if (confirm("确定下架此分类吗？")) {
			$.ajax({
				url : "/backend/admin/parentCategory/disable", //url
				type : "post",
				data : {
					id : id,
					type : "single"
				},
				success : function(status) {
					//alert(status);
					$table.bootstrapTable('refresh');
				}
			});
		}
	}

	//上架操作
	function reActive(id) {
		if (confirm("确定上架此分类吗？")) {
			$.ajax({
				url : "/backend/admin/parentCategory/reActive", //url
				type : "post",
				data : {
					id : id,
					type : "single"
				},
				success : function(status) {
					//alert(status);
					$table.bootstrapTable('refresh');
				}
			});
		}
	}

	/* 批量删除 */
	$('#btn-batch-delete').on("click", function() {
		var rows = $table.bootstrapTable('getSelections');
		console.log(rows);
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			//console.log(rows[i]);
			ids += rows[i].id + ',';
		}
		ids = ids.substring(0, ids.length - 1);
		$.ajax({
			url : "/backend/admin/parentCategory/disable", //url
			type : "post",
			//dataType : "json",
			data : {
				id : ids,
				type : "batch"
			},
			success : function(status) {
				//alert(status);
				$table.bootstrapTable('refresh');
			}
		});
		//$table.bootstrapTable('refresh');

	});
</script>
</html>
