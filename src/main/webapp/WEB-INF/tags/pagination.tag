<!--<%@ tag language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination">
    <li class="page-item"><a href="/items?pageIndex=previous"><i class="page-link">previous</i></a>
    </li>
    <c:forEach var="i" begin="1" end="${requestScope.pages}">
        <li><a href="/items?pageIndex=${i}">${i}</a></li>
    </c:forEach>
    <li class="page-item"><a href="/items?pageIndex=next"><i class="page-link" >next</i></a>
    </li>
</ul> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag language="java" isELIgnored="false" %>
<%@ attribute name="page" required="true" rtexprvalue="true"%>
<%@ attribute name="pageCount" required="true" rtexprvalue="true"%>

<c:if test="${pageCount != 0}">
    <ul class="pagination">

        <li class="page-item <c:if test='${page - 1 < 1}'>disabled</c:if>"><a class="page-link" href="#">Previous</a></li>
        <c:if test="${page - 1 > 0}">
            <li class="page-item"><a class="page-link" href="#" onclick="setPage(${page - 1})">${page - 1}</a></li>
        </c:if>

        <li class="page-item active"><a class="page-link" href="#" onclick="setPage(${page})">${page}</a></li>
        <c:if test="${page + 1 < pageCount}">
            <li class="page-item"><a class="page-link" href="#" onclick="setPage(${page + 1})">${page + 1}</a></li>
        </c:if>

        <c:if test="${page + 1 < pageCount&& pageCount!= 1}">
            <li class="page-item disabled">
                <span class="page-link">...</span>
            </li>
        </c:if>

        <c:if test="${page != pageCount}">
            <li class="page-item"><a class="page-link" href="#" onclick="setPage(${pageCount})>${pageCount}</a></li>
        </c:if>

        <li class="page-item <c:if test='${page + 1 > pageCount}'>disabled</c:if>"><a class="page-link" href="#">Next</a>
        </li>
    </ul>
</c:if>

