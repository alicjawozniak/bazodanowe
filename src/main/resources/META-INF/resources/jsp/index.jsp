<%--
  Created by IntelliJ IDEA.
  User: alicja
  Date: 09.10.17
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div>
    <h1 class="text-center">Employee Registry</h1>

</div>
<div>
    <form action="http://localhost:8080/endpoint" method="POST" class="form-group col-md-12 ">
        <br/>
        <br/>
        <label>Podaj tekst</label>
        <br/>
        <input type="text" name="text" class="form-control" required autofocus/>
        <br/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
