<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<html>
<head>
   <title>500-Internal Server Error</title>
</head>
<body>
<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
${trace}
</c:forEach>
</body>
</html>