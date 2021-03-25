<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<%@ include file="head.jsp"%>

<body>

<jsp:include page="navigation.jsp">
    <jsp:param name="title" value="Customer"/>
</jsp:include>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/customer/new" var="customerUrl"/>
            <a class="btn btn-primary" href="${customerUrl}">Add Customer</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Age</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>

                <%--                <%!--%>
                <%--                    private ProductRepository productRepository;--%>

                <%--                    @Override--%>
                <%--                    public void jspInit() {--%>
                <%--                        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");--%>
                <%--                    }--%>
                <%--                %>--%>


                <c:choose>
                    <c:when test="${requestScope.customers.isEmpty()}">
                        <tr>
                            <td colspan="4">
                                No data
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <%--                <% for (Product product : (List<Product>) request.getAttribute("products")) { %>--%>
                        <c:forEach var="customer" items="${requestScope.customers}">
                            <tr>
                                <th scope="row">
                                        <%--                        <%= product.getId() %>--%>
                                    <c:out value="${customer.id}"/>
                                </th>
                                <td>
                                        <%--                        <%= product.getName() %>--%>
                                    <c:out value="${customer.name}"/>
                                </td>
                                <td>
                                        <%--                        <%= product.getDescription() %>--%>
                                    <c:out value="${customer.age}"/>
                                </td>
                                <td>
                                    <c:url value="/customer/${customer.id}" var="customerUrl"/>
                                    <a class="btn btn-success" href="${customerUrl}"><i class="fas fa-edit"></i></a>
                                    <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        <%--                <% } %>--%>
                    </c:otherwise>
                </c:choose>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="scripts.jsp"%>

</body>
</html>