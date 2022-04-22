<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>
<%
  String[] test={"test", "test2", "test3", "test4"};
%>

<c:forEach var="counter" items="${test}">
    ${counter}
</c:forEach>
${test}
</body>
</html>
