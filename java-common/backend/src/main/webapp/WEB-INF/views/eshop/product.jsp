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
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://unpkg.com/bootstrap-table@1.13.5/dist/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script
	src="https://unpkg.com/bootstrap-table@1.13.5/dist/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body>

	<h1 align="center">商品管理</h1>
	<!-- 大类查询 -->
	<select id="selectpicker-parent">
	</select>
	<button id="searchByParent" type="button" class="btn">
		<span aria-hidden="true" class="icon icon-plus-sign"></span>查询子分类
	</button>
	<br>
	<br>
	<!-- 子类查询 -->
	<select id="selectpicker-child">
	</select>
	<button id="searchByChild" type="button" class="btn">
		<span aria-hidden="true" class="icon icon-plus-sign"></span>查询商品
	</button>
	<br>
	<br>
	<div class="btn-group">
		<button id="btn-add" type="button" class="btn">
			<span aria-hidden="true" class="icon icon-plus-sign"></span>新增
		</button>
	</div>

	<div class="btn-group">
		<button id="btn-batch-delete" type="button" class="btn">
			<span aria-hidden="true" class="icon icon-plus-sign"></span>批量删除
		</button>
	</div>
	<div class="btn-group">
		<button id="btn-batch-delete" type="button" class="btn">
			<span aria-hidden="true" class="icon icon-plus-sign"></span>批量上架
		</button>
	</div>

	<!--工具-->

	<!--bootstrap-table表格-->
	<table id="data-table"></table>

	<!-- 新增用的modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">在此类下新增一个商品</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="txt_departmentname">商品名称</label> <input type="text"
							class="form-control" id="productName" placeholder="名称">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品介绍</label> <input type="text"
							class="form-control" id="productDesc" placeholder="介绍">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品价格</label> <input type="text"
							class="form-control" id="productPrice" placeholder="图片Url">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品库存</label> <input type="text"
							class="form-control" id="productInventory" placeholder="库存">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品销售量</label> <input type="text"
							class="form-control" id="productSalesCount" placeholder="销售量">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品主图Url</label> <input type="text"
							class="form-control" id="productMainImgUrl" placeholder="商品主图Url">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">品牌中文名</label> <input type="text"
							class="form-control" id="productBrandNameCN" placeholder="品牌中文名">
					</div>

					<div class="form-group">
						<label for="txt_departmentname">品牌英文名</label> <input type="text"
							class="form-control" id="productBrandNameEN" placeholder="品牌英文名">
					</div>
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
						<label for="txt_departmentname">商品名称</label> <input type="text"
							class="form-control" id="edit-productName" placeholder="名称">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品介绍</label> <input type="text"
							class="form-control" id="edit-productDesc" placeholder="介绍">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品价格</label> <input type="text"
							class="form-control" id="edit-productPrice" placeholder="图片Url">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品库存</label> <input type="text"
							class="form-control" id="edit-productInventory" placeholder="库存">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品销售量</label> <input type="text"
							class="form-control" id="edit-productSalesCount"
							placeholder="销售量">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">商品主图Url</label> <input type="text"
							class="form-control" id="edit-productMainImgUrl"
							placeholder="商品主图Url">
					</div>
					<div class="form-group">
						<label for="txt_departmentname">品牌中文名</label> <input type="text"
							class="form-control" id="edit-productBrandNameCN"
							placeholder="品牌中文名">
					</div>

					<div class="form-group">
						<label for="txt_departmentname">品牌英文名</label> <input type="text"
							class="form-control" id="edit-productBrandNameEN"
							placeholder="品牌英文名">
					</div>
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

	//根据子分类id加载所有商品然后进行操作
	function initTable(categoryId) {
		$table.bootstrapTable({
			url : '/backend/category/getProductsByCategory/' + categoryId, //请求后台的URL（*）
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
			columns : [
					{
						checkbox : true
					},
					{
						field : 'id',
						title : 'id',
						align : 'center'
					},
					{
						field : 'name',
						title : '类别名',
						align : 'center'
					},
					{
						field : 'desc',
						title : '介绍',
						align : 'center'
					},
					{
						field : 'price',
						title : '价格',
						align : 'center'
					},
					{
						field : 'inventory',
						title : '库存',
						align : 'center'
					},
					{
						field : 'salesCount',
						title : '销量',
						align : 'center'
					},
					{
						field : 'mainImgUrl',
						title : '主图url',
						align : 'center'
					},
					{
						field : 'parentCategoryId',
						title : '大分类Id',
						align : 'center'
					},
					{
						field : 'categoryId',
						title : '分类Id',
						align : 'center'
					},
					{
						field : 'brandNameCN',
						title : '品牌中文名',
						align : 'center'
					},
					{
						field : 'brandNameEN',
						title : '品牌英文名',
						align : 'center'
					},
					{
						field : 'activeStatus',
						title : '状态',
						align : 'center'
					},
					{
						title : '操作',
						field : 'id',
						formatter : function(id) {
							var html = '<a href="javascript:editProduct(' + id
									+ ')">编辑</a>';
							html += '　<a href="javascript:deleteProduct(' + id
									+ ')">下架</a>';
							html += '　<a href="javascript:reActiveProduct('
									+ id + ')">上架</a>';
							return html;
						}
					}

			]
		});
	}

	/* 展开modal，新增一个分类 */
	$('#btn-add').on("click", function() {
		$table.bootstrapTable('refresh');
		$("#myModal").modal().on("shown.bs.modal", function() {

		});
	});

	/* 提交新建的 */
	$('#btn_submit').on("click", function() {
		var parentCategoryId = $("#selectpicker-parent").val();
		var categoryId = $("#selectpicker-child").val();
		var name = $('#productName').val();
		var desc = $('#productDesc').val();
		var price = $('#productPrice').val();
		var inventory = $('#productInventory').val();
		var salesCount = $('#productSalesCount').val();
		var mainImgUrl = $('#productMainImgUrl').val();
		var parentCategoryId = $('#productParentCategoryId').val();
		var categoryId = $('#productCategoryId').val();
		var brandNameCN = $('#productBrandNameCN').val();
		var brandNamEN = $('#productBrandNamEN').val();
		$.ajax({
			url : "/backend/admin/product/add",
			type : "post",
			data : {
				name : name,
				desc : desc,
				price : price,
				inventory : inventory,
				salesCount : salesCount,
				mainImgUrl : mainImgUrl,
				parentCategoryId : parentCategoryId,
				categoryId : categoryId,
				brandNameCN : brandNameCN,
				brandNamEN : brandNamEN,
			},
			success : function(status) {
				$table.bootstrapTable('destroy');
				initTable(categoryId);
			}
		});
	});

	//编辑操作
	function editProduct(id) {
		var row = $table.bootstrapTable('getRowByUniqueId', id);
		$("#editModal").modal().on("shown.bs.modal", function() {
			$('#edit-categoryName').val(row.name);
			$('#edit-categoryDesc').val(row.desc);
			$('#edit-categoryImgUrl').val(row.imgUrl);
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
					url : "/backend/admin/category/update",
					type : "post",
					data : {
						id : editModalParentCategoryId,
						name : editModalParentCategoryName
					},
					success : function(status) {
						$table.bootstrapTable('destroy');
						initTable(parentCategoryId);
					}
				});
			});

	//删除操作
	function deleteProduct(id) {
		var categoryId = $("#selectpicker-child").val();
		if (confirm("确定删除此分类吗？")) {
			$.ajax({
				url : "/backend/admin/product/delete",
				type : "post",
				data : {
					id : id,
					type : "single"
				},
				success : function(status) {
					alert(status);
					$table.bootstrapTable('destroy');
					initTable(categoryId);
				}
			});
		}
	}

	//上架操作
	function reActiveProduct(id) {
		var categoryId = $("#selectpicker-child").val();
		if (confirm("确定重新上架吗？")) {
			$.ajax({
				url : "/backend/admin/category/reActive", //url
				type : "post",
				data : {
					id : id,
					type : "single"
				},
				success : function(status) {
					alert(status);
					$table.bootstrapTable('destroy');
					initTable(categoryId);
				}
			});
		}
	}

	/* 批量上架 */
	$('#btn-batch-reactive').on("click", function() {
		var rows = $table.bootstrapTable('getSelections');
		var categoryId = $("#selectpicker-child").val();
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			ids += rows[i].id + ',';
		}
		ids = ids.substring(0, ids.length - 1);
		$.ajax({
			url : "/backend/admin/product/reActive", //url
			type : "post",
			data : {
				id : ids,
				type : "batch"
			},
			success : function(status) {
				$table.bootstrapTable('destroy');
				initTable(parentCategoryId);
				console.log(parentCategoryId);
			}
		});
	});

	/* 批量删除 */
	$('#btn-batch-delete').on("click", function() {
		var rows = $table.bootstrapTable('getSelections');
		var categoryId = $("#selectpicker-child").val();
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			ids += rows[i].id + ',';
		}
		ids = ids.substring(0, ids.length - 1);
		$.ajax({
			url : "/backend/admin/product/delete", //url
			type : "post",
			data : {
				id : ids,
				type : "batch"
			},
			success : function(status) {
				alert("ds");
				$table.bootstrapTable('destroy');
				initTable(parentCategoryId);
				console.log(parentCategoryId);
			}
		});
	});

	$(document).ready();

	/* 查询子类 */
	$('#searchByParent')
			.on(
					"click",
					function() {
						var parentCategoryId = $("#selectpicker-parent").val();
						$
								.ajax({
									url : "/backend/category/getCategoryByParent/"
											+ parentCategoryId, //url
									type : "get",
									success : function(data) {
										var depart_list = data;
										var opts = "";
										for (var depart_index = 0; depart_index < depart_list.length; depart_index++) {
											var item = depart_list[depart_index];
											opts += "<option value='"+item.id+"'>"
													+ item.name + "</option>";
										}
										// 查询界面
										$('#selectpicker-child').append(opts);
										$('#selectpicker-child').selectpicker(
												"refresh");
									}
								});
					});

	/* 查询此分类下的商品 */
	$('#searchByChild').on("click", function() {
		var categoryId = $("#selectpicker-child").val();
		console.log(categoryId);
		$.ajax({
			url : "/backend/category/getProductsByCategory/" + categoryId, //url
			type : "get",
			success : function(status) {
				$table.bootstrapTable('destroy');
				initTable(categoryId);
			}
		});
	});

	/**大类的select picker数据加载 **/
	$
			.ajax({
				"type" : 'get',
				"url" : '/backend/admin/parentCategory/getAll',
				"dataType" : "json",
				"success" : function(data) {
					console.log(data);
					var depart_list = data;
					var opts = "";
					for (var depart_index = 0; depart_index < depart_list.length; depart_index++) {
						var item = depart_list[depart_index];
						opts += "<option value='"+item.id+"'>" + item.name
								+ "</option>";
					}
					// 查询界面
					$('#selectpicker-parent').append(opts);
					$('#selectpicker-parent').selectpicker("refresh");
				}
			});
</script>
</html>
