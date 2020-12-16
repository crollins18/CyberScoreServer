<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.File" %>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<%
      String context = getServletContext().getRealPath("");
      Scanner in = new Scanner(new File(context + "/board-admin/url.dat"));
      String base = null;
      String prefix = null;
      try {
              	base = "<div class=\"alert alert-dismissible alert-success\">\n" + 
            			"  <strong>Current Scoreboard URL: </strong>" + in.nextLine() + 
            			"</div>"; 
        		prefix = "<div class=\"alert alert-dismissible alert-success\">\n" + 
            			"  <strong>Current Team Prefix: </strong>" + in.nextLine() + "</div>";
        }
      catch (Exception e){
    			base = "<div class=\"alert alert-dismissible alert-danger\"><strong>Oh snap!</strong> <a href=\"board-admin\" class=\"alert-link\">Set server preferences to get started</a> and try again.</div>";
    			prefix = "";
    	}
	%>
    <div class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
      <div class="container">
        <a href="" class="navbar-brand">CyberScoreServer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="board-admin">Set Server Options</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="container">
		<div class="jumbotron">
		  <h1 class="display-3">Hello world!</h1>
		  <p class="lead">This is a simple web app to parse and filter the public Cyberpatriot scoreboard during competitions!</p>
		  <p>Created by Cyberpatriot alumni Caleb Rollins</p>
		</div>
	</div>
    <div class="container">
        <div class="row">
          <div class="col-lg-12">
          <% 
          if (!base.equals("<div class=\"alert alert-dismissible alert-danger\"><strong>Oh snap!</strong> <a href=\"board-admin\" class=\"alert-link\">Set server preferences to get started</a> and try again.</div>")) {
          %>
        <div class="card text-white bg-primary mb-3">
  			<div class="card-header">Send Command</div>
			<div class="card-body">
    		  <form name="commandline" method="post" action="go">
                <fieldset>
                  <div class="form-group row">
                    <label for="commandIn" class="col-sm-2 col-form-label">Input</label>
                    <div class="col-sm-8">
                      <input type="text" name="commandIn" value="!help">
                    </div>
                  </div>
                  <input type="submit" value="Submit" />
                </fieldset>
              </form>
  			</div>
		</div>

          <%}
          else {}
          %>
            
          </div>
          
          <div class="container">
          <%=base%>
          <%=prefix%>
        </div>
        
       </div>
     </div>


</body>

</html>