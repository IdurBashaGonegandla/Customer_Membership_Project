<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<header style="color:white"> 
   <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">CustomerManagement</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="table">Customer</a>
        </li>
      </ul>     
    </div>
  </div>
</nav>
</header>

<br/>
<br/>

<div class="container" style="width:50%">

   <c:if test="${customer == null }">

     <form action="add" method="post">
     <h2 style="color:red">Add Customer</h2>
     
  </c:if>
  
  <c:if test="${customer != null }">
  
   <form action="edit" method="post">
   <h2  style="color:red" >Edit Customer</h2>
  
  </c:if>
 


 

 <div class="mb-3" hidden>
  <input value="<c:out value="${customer.id }"></c:out>" type="text" class="form-control" id="exampleFormControlInput1" name="tbId"  >
 </div>
  
 <div class="mb-4">
  <label for="exampleFormControlInput1" class="form-label">Name</label>
  <input type="text" value="<c:out value="${customer.name }"></c:out>"  class="form-control" id="exampleFormControlInput1" name="tbName"  placeholder="Enter your name" required="required">
 </div>
 
 <div class="mb-3">
  <label for="exampleFormControlInput2" class="form-label">Email address</label>
  <input type="email" value="<c:out value="${customer.email }"></c:out>" class="form-control" id="exampleFormControlInput2" name="tbEmail" placeholder="Enter your Email" required="required">
 </div>
 
 <div class="mb-3">
  <label for="exampleFormControlInput3" class="form-label">Mobile</label>
  <input type="tel"  value="<c:out value="${customer.mobile }"></c:out>" class="form-control" id="exampleFormControlInput3" name="tbMobile" placeholder="Enter your Mobile no" required="required">
 </div>
 
 <div>
    <button class="btn btn-success">Save</button>
 </div>
 
 </form>
 
 
     

</div>
</body>
</html>