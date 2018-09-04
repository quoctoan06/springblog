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
	                    <h4 class="title">Add New Post</h4>
	                </div>
	                <div class="content">
	                    <form action="addPost" method="post" enctype="multipart/form-data">
	                    	<select name="categoryId" required="required">
								<option value="0">-------------- Select Category --------------</option>
								<c:forEach items="${categoryList}" var="c">
									<option value="${c.id}">${c.name}</option>
								</c:forEach>
							</select>
	                        <div class="row">
	                            <div class="col-md-6">
	                                <div class="form-group">
	                                    <label>Code</label>
	                                    <input type="text" class="form-control" name="code" required="required"/>
	                                </div>
	                            </div>
	                            <div class="col-md-6">
	                                <div class="form-group">
	                                    <label>Image</label>
	                                    <input type="file" class="form-control" name="image" required="required"/>
	                                </div>
	                            </div>
	                        </div>

	                        <div class="row">
	                            <div class="col-md-12">
	                                <div class="form-group">
	                                    <label>Title</label>
	                                    <input type="text" class="form-control" name="title" required="required"/>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-md-12">
	                                <div class="form-group">
	                                    <label>Content</label>
	                                    <textarea rows="5" class="form-control" name="content" required="required" id="content">
	                                	</textarea>
	                                </div>
	                            </div>
	                        </div>

	                        <button type="submit" class="btn btn-info btn-fill pull-right" 
	                        		onclick="return confirm('Bạn có chắc chắn muốn thêm')">Add new post</button>
	                        <c:if test="${error == 0}">
								<p style="color:red">
								</p>
							</c:if>	
	                        <div class="clearfix"></div>
	                    </form>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-4">
	            <div class="card card-user">
	                <div class="image">
	                    <img src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400" alt="..."/>
	                </div>
	                <div class="content">
	                    <div class="author">
	                         <a href="#">
	                        <img class="avatar border-gray" src="<%= request.getContextPath() %>/resources/admin/assets/img/faces/face-3.jpg" alt="..."/>

	                          <h4 class="title">Mike Andrew<br />
	                             <small>michael24</small>
	                          </h4>
	                        </a>
	                    </div>
	                    <p class="description text-center"> "Lamborghini Mercy <br>
	                                        Your chick she so thirsty <br>
	                                        I'm in that two seat Lambo"
	                    </p>
	                </div>
	                <hr>
	                <div class="text-center">
	                    <button href="#" class="btn btn-simple"><i class="fa fa-facebook-square"></i></button>
	                    <button href="#" class="btn btn-simple"><i class="fa fa-twitter"></i></button>
	                    <button href="#" class="btn btn-simple"><i class="fa fa-google-plus-square"></i></button>

	                </div>
	            </div>
	        </div>

	    </div>
	</div>
</div>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		CKEDITOR.replace('content');
	</script>
<%@include file="footer_admin.jsp"%>