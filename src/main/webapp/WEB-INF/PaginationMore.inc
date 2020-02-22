<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:if test="${page.totalPages==0}">
	<div id="paginationtable" style="text-align:center;">
				查找到<font color="#f00;">${page.totalRows }</font>条记录。
	</div>	
</c:if>
<c:if test="${page.totalPages!=0}">
	<div id="showpage" style="float:right;padding-right:10px;">
		<a class="page">共 ${page.totalRows }条</a>
		<a class="page">共 ${page.totalPages }页</a>
		
		<c:if test="${not empty needPageSize}">
			<a class="page">每页</a>
			<a class="page" style="border:0px"><select style="height:21px;color: green;" onchange="onPageSizeChange(this.value)" id="realPageSize"><option <c:if test="${page.pageSize =='8'}">selected</c:if> value="8">8</option><option <c:if test="${page.pageSize =='20'}">selected</c:if> value="20">20</option><option <c:if test="${page.pageSize =='30'}">selected</c:if> value="30">30</option></select></a>
			<a class="page">条</a>
		</c:if>
		<!-- 第一页 -->
		<c:if test="${page.hasPreviousPage6}">
			<a class="page" href="javascript:paginate('1');">首页</a>
		</c:if>

		<!-- 显示上一页 -->
		<c:if test="${page.hasPreviousPage}">
			<a class="page" href="javascript:paginate('${page.previousPage}');"><<上一页</a>
		</c:if>

		<!-- 上3页 -->
		<c:if test="${page.hasPreviousPage3}">
			<a class="page" href="javascript:paginate('${page.previousPage3}');">${page.previousPage3}</a>
		</c:if>

		<!-- 上2页 -->
		<c:if test="${page.hasPreviousPage2}">
			<a class="page" href="javascript:paginate('${page.previousPage2}');">${page.previousPage2}</a>
		</c:if>

		<!-- 上一页 -->
		<c:if test="${page.hasPreviousPage}">
			<a class="page" href="javascript:paginate('${page.previousPage}');">${page.previousPage}</a>
		</c:if>

		<!--当前页 -->
		<a class="page" href="javascript:void(0);" id="on">${page.currentPage}</a>

		<!-- 下一页 -->
		<c:if test="${page.hasNextPage}">
			<a class="page" href="javascript:paginate('${page.nextPage}');">${page.nextPage}</a>
		</c:if>

		<!-- 下2页 -->
		<c:if test="${page.hasNextPage2}">
			<a class="page" href="javascript:paginate('${page.nextPage2}');">${page.nextPage2}</a>
		</c:if>

		<!-- 下3页 -->
		<c:if test="${page.hasNextPage3}">
			<a class="page" href="javascript:paginate('${page.nextPage3}');">${page.nextPage3}</a>
		</c:if>

		<!-- 显示下一页 -->
		<c:if test="${page.hasNextPage}">
			<a class="page" href="javascript:paginate('${page.nextPage}');">下一页>></a>
		</c:if>

		<!-- 最后一页 -->
		<c:if test="${page.hasNextPage5}">
			<a class="page" href="javascript:paginate('${page.totalPages}');">尾页</a>
		</c:if>
		<b class="page"><input type="text" name="forwardPage" id="forwardPage" 
				title="输入数字，回车跳转"
				onkeydown="if (event!== null && 13==event.keyCode) return paginate();"
				value="${page.currentPage}" /></b>
	</div>
</c:if>