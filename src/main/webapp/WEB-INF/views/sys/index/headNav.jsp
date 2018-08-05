<%@ taglib uri="http://java.sun.com/jsp/jstl/functionsc" prefix="fnc" %>
<nav class="navbar navbar-default top-navbar" role="navigation">
	<div class="navbar-header">
	    <button type="button" class="navbar-toggle waves-effect waves-dark" 
	    		data-toggle="collapse" data-target=".sidebar-collapse">
	        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span>
	        <span class="icon-bar"></span><span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand waves-effect waves-dark">
	    	<i class="large material-icons">track_changes</i> 
	    	<strong>${fnc:getCompanyName()}</strong>
	    </a>
		<div id="sideNav"><i class="material-icons dp48">toc</i></div>
	</div>
	<ul class="nav navbar-top-links navbar-right"> 
		<li>
			<a class="dropdown-button waves-effect waves-dark" data-activates="dropdown4">
				<i class="fa fa-envelope fa-fw"></i><i class="material-icons right"></i>
			</a>
			<ul id="dropdown4" class="dropdown-content dropdown-tasks w250 taskList">
		 		<li>
		 			<a>
				        <div>
				            <strong>John Doe</strong>
				            <span class="pull-right text-muted"><em>Today</em></span>
				        </div>
				    </a>
				</li>
		    	<li class="divider"></li>
			</ul>  
		</li>				
		<li>
			<a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown3">
				<i class="fa fa-tasks fa-fw"></i><i class="material-icons right"></i>
			</a>
			<ul id="dropdown3" class="dropdown-content dropdown-tasks w250">
				<li>
					<a>
						<div>
							<p>
								<strong>Task 1</strong><span class="pull-right text-muted">60% Complete</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success" role="progressbar" 
								aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:60%">
									<span class="sr-only">60% Complete (success)</span>
								</div>
							</div>
						</div>
					</a>
				</li>
				<li class="divider"></li>
			</ul>   
		</li>
		<li>
			<a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown2">
				<i class="fa fa-bell fa-fw"></i><i class="material-icons right"></i>
			</a>
			<ul id="dropdown2" class="dropdown-content w250">
				<li>
					<a>
			          	<div>
			              	<i class="fa fa-comment fa-fw"></i> New Comment
			              	<span class="pull-right text-muted small">4 min</span>
			          	</div>
			    	</a>
				</li>
				<li class="divider"></li>
			</ul>
		</li>
		<li>
			<a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown1">
				<i class="fa fa-user fa-fw"></i><b>${username}</b> 
				<i class="material-icons right"></i>
			</a>
			<ul id="dropdown1" class="dropdown-content">
				<li><a id="pCenter"><i class="fa fa-user fa-fw"></i></a></li>
				<li><a id="pSetting"><i class="fa fa-gear fa-fw"></i></a></li> 
				<li><a href="logout.do" id="quit"><i class="fa fa-sign-out fa-fw"></i></a></li>
			</ul>
		</li>
	</ul>
</nav>