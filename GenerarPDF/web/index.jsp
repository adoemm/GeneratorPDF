<%-- 
    Document   : Visual
    Created on : Dec 28, 2016, 5:49:33 PM
    Author     : emmanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function enviaController (form)
            {
                alert('ya');
                 $.ajax({type: 'POST'
                    , contentType:  'application/x-www-form-urlencoded;charset=utf-8'
                    , cache: false
                    , async: false
                    , url: '/GenerarPDF/pdf'
                    , data: $(form).serialize()
                    , success: function(response) {
                        alert(response);
                        //$('#wrapper').find('#divResult').html(response);


                    }});
            }
            
        </script>
    </head>
    <body>
        <%
            response.sendRedirect("/GenerarPDF/pdf");
        %>
        <h1>Hello World!</h1>
        <form name="reporte"  action="">
            <input value="generaPDF" name="generaPDF" hidden="true">
            <input type="button" value="Visualizar PDF" name="visualiza"  />
        </form>
    </body>
</html>
