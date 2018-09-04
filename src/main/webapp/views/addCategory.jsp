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
                    <h4 class="title">Add New Category</h4>
                </div>
                <div class="content">
                    <form:form action="addCategory" method="post" modelAttribute="newCategory">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Name</label>
                                    <form:input type="text" class="form-control" path="name" required="required"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Code</label>
                                    <form:input type="text" class="form-control" path="code" required="required"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Description</label>
                                    <form:textarea rows="5" class="form-control" path="description"/>
                                </div>
                            </div>
                        </div>
                        <form:button type="submit" class="btn btn-info btn-fill pull-right" onclick="return confirm('Bạn có chắc chắn muốn thêm')">
                        		Add new category</form:button>
                        <form:errors path="error" style="color:red"></form:errors>
                        <div class="clearfix"></div>
                    </form:form>
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
<%@include file="footer_admin.jsp"%>