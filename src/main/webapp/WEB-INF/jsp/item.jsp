<%@ include file="/jspFragments/head.jspf" %>
<%@ include file="/jspFragments/header.jspf" %>
<%@ taglib prefix="item" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags" %>
<%-- <%@ taglib prefix="page" tagdir="/WEB-INF/tags" %> --%>


<div class="bg-light py-3">
<div class="p-3 mb-2 bg-primary text-white">Teapots List</div>

<!-- Container for dropdowns  -->
<div class="container">
<div class="row">

<!-- Dropdown for item per page -->
 <div class="col-sm-4">
<select class="btn btn-secondary dropdown-togglen" name="itemPerPage" onchange='setPerPage(this.value)'>
<option class="dropdown-item" > item per page  </option>
    <option class="dropdown-item" value="10">10</option>
    <option class="dropdown-item" value="20">20</option>
    <option class="dropdown-item" value="30">30</option>
</select>
</div>


<!--Dropdown for name sort -->

<div class="col-sm-4">

<form action="/items" method="post">
   <select class="btn btn-secondary dropdown-togglen">
    <option class="dropdown-item" > sort by name  </option>
    <option class="dropdown-item" value="a-z" href="/">a-z</option>
    <option class="dropdown-item"  value="z-a"    href="/">z-a</option>

    </select>
 </form>
</div>


<!--Dropdown for price sort -->
<div class="col-sm-4">

<form action="/items" method="post">
   <select class="btn btn-secondary dropdown-togglen">
<option class="dropdown-item" > sort by price </option>
    <option class="dropdown-item" value="asc" href="/">asc</option>
    <option class="dropdown-item"  value="desc"    href="/">desc</option>

</select>
 </form>
</div>


</div><!-- close for row-->
</div><!-- close container  -->
<div class="bg-light py-3">
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <form action="items" name="filterForm">
                <div class="card">
                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><fmt:message key="color" bundle="${lang}"/> </h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${requestScope.colorList}" var="color">
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" name="color"
                                        <c:if test="${requestScope.properties.color eq 'color'}">checked</c:if>
                                            style="position: relative !important;" value="${color}">
                                        <span class="form-check-label">
                                            ${color}
                                        </span>
                                    </label> <!-- form-check.// -->
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                 
                </div> <!-- card.// -->
                
                <div class="card">
                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><fmt:message key="location" bundle="${lang}"/></h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${requestScope.locationList}" var="location">
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" name="location"
                                        <c:if test="${requestScope.properties.location eq 'location'}">checked</c:if>
                                            style="position: relative !important;" id="${location}" value="${location}">
                                        <span class="form-check-label">
                                            ${location}
                                        </span>
                                    </label> <!-- form-check.// -->
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                </div> <!-- card.// -->
                <div class="card">
                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><fmt:message key="material" bundle="${lang}"/> </h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${requestScope.materialList}" var="material">
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" name="material"
                                        <c:if test="${requestScope.properties.material eq 'material'}">checked</c:if>
                                            style="position: relative !important;" id="${material}" value="${material}">
                                        <span class="form-check-label">
                                            ${material}
                                        </span>
                                    </label> <!-- form-check.// -->
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                </div> <!-- card.// -->

                <div class="card">
                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><fmt:message key="type" bundle="${lang}"/></h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${requestScope.typeList}" var="type">
                                    <label class="form-check">
                                        <input class="form-check-input" type="checkbox" name="type"
                                        <c:if test="${requestScope.properties.type eq 'type'}">checked</c:if>
                                            style="position: relative !important;" id="${type}" value="${type}">
                                        <span class="form-check-label">
                                            ${type}
                                        </span>
                                    </label> <!-- form-check.// -->
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                </div> <!-- card.// -->

   <input type="submit" class="filterButton"  value="Filter">
           
            </form>
          
        </div>

        <div class="col-lg-9">
         <div class="row" >
               
                    <item:item />
                    
                </div>
         
        </div>
    </div>

    <div class="row">
<pagination:pagination page="${page}" pageCount="4"/>
    </div> 

    <!-- /.row -->


</div>
</div>
<%@ include file="/jspFragments/footer.jspf" %>