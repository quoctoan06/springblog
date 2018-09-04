<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@include file="header_admin.jsp"%>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
			    <div class="card">
			        <div class="header">
			            <h4 class="title">Manage Post</h4>
			        </div>
			        <div class="content">
			            <form:form action="searchPost" modelAttribute="post" method="get">
			                <div class="row">
			                    <div class="col-md-5">
			                        <div class="form-group">
			                            <label>Title</label>
			                            <form:input type="text" class="form-control" path="title" />
			                        </div>
			                    </div>
			                    <div class="col-md-3">
			                        <div class="form-group">
			                            <label>Author</label>
			                            <form:input type="text" class="form-control" path="authorName" />
			                        </div>
			                    </div>
			                    <div class="col-md-4">
			                        <div class="form-group">
			                            <label>Date</label>
			                            <form:input type="date" class="form-control" path="date" />
			                        </div>
			                    </div>
			                 </div>
			                 <div class="row">
			                    <div class="col-md-3">
			                    	<div class="form-group">
			                        	<form:button type="submit" class="btn btn-primary btn-fill" style="margin-top:23px; margin-left:24px;">
			                        		Search</form:button>
			                    	</div>
			                    </div>
			                    <div class="col-md-4">
			                        <div class="form-group">
			                            <a href="addPost" class="btn btn-success btn-fill" style="margin-top:23px;">Add new post</a>
			                        </div>
			                    </div>
			                </div>
			                <div class="clearfix"></div>
			            </form:form>
			        </div>
			    </div>
			</div>
		</div>
                                    
		<div class="row">
			<div class="col-md-12">
			    <div class="card">
			        <div class="header">
			            <h4 class="title">Post List</h4>
			        </div>
			        <div class="content table-responsive table-full-width">
			            <table class="table table-hover table-striped">
			                <thead>
			                    <th>Code</th>
			                	<th>Category Name</th>
			                	<th>Title</th>
			                	<th>Author</th>
			                	<th>Image</th>
			                	<th>Action</th>
			                </thead>
			                <tbody>
			                	<c:forEach items="${ postList }" var="post">
			                		<tr>
				                    	<td>${ post.code }</td>
				                    	<td>${ post.categoryName }</td>
				                    	<td>${ post.title }</td>
				                    	<td>${ post.authorName }</td>
				                    	<td><img src="${post.image}" class="img-responsive" alt="Post Image" style="width:50px; height:50px"></td>
				                    	<td>
				                    		<a href="updatePost?id=${ post.id }"><span class="label label-info">Edit</span></a>
				                    		<a href="deletePost?id=${ post.id }" onclick="return confirm('Thực sự xoá?')"><span class="label label-danger">Delete</span></a>
				                    	</td>
			                   		</tr>
			                	</c:forEach>
			                </tbody>
			            </table>
			        </div>
			    </div>
			</div>
    	</div>
    	
		<%-- <div style="text-align: center;">
			<ul class="pagination modal-1">
				<c:if test="${nextSection > 1}">
					<li><a href="post?curSection=${ prevSection }&status=prev&curPage=${startPage}" class="prev">&laquo;</a></li>
				</c:if>
				
				<c:forEach var="index" begin="${startPage}" end="${endPage}">
						<li><a class="<c:if test="${index==activePage}">active</c:if>" href="category?curPage=${index}&curSection=${curSection}">${index}</a></li>
				</c:forEach>
				<c:if test="${nextSection < numberOfSection}">
					<li><a href="post?curSection=${ nextSection }&status=next&curPage=${startPage}" class="next">&raquo;</a></li>
				</c:if>
			</ul>
		</div> --%>
		
	</div>
</div>

<%@include file="footer_admin.jsp"%>