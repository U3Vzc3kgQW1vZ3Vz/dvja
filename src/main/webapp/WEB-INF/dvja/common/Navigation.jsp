<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta charset="UTF-8">
    <style>

        /* Ensure navbar items align in the center */
        .navbar-nav > li {
            display: flex;
            align-items: center; /* Centers vertically */
        }

        /* Toggle Switch Styling */
        .switch {
            position: relative;
            display: inline-block;
            width: 50px;
            height: 25px;
            vertical-align: middle;
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            transition: .4s;
            border-radius: 25px;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 19px;
            width: 19px;
            left: 3px;
            bottom: 3px;
            background-color: white;
            transition: .4s;
            border-radius: 50%;
        }

        input:checked + .slider {
            background-color: #4CAF50;
        }

        input:checked + .slider:before {
            transform: translateX(25px);
        }
    </style>
</head>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><i class='fa fa-bug'></i> Damn Vulnerable Java Application</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class='nav navbar-nav'>

            </ul>
            <ul class='nav navbar-nav navbar-right'>
                <li>
                    <a href="<s:url action="assessmentHome"/>">
                        <i class="fa fa-exchange"></i> Assessment Mode
                    </a>
                </li>
                <li>
                    <a href="/home.action">
                        <i class="fa fa-exchange"></i> Learning Mode
                    </a>
                </li>
                <s:if test="authenticated">
                    <li>
                        <a href='/logout.action'>
                            <i class='fa fa-sign-out'></i> Logout
                        </a>
                    </li>
                </s:if>
                <li>

                    <a>
                        <i class="fa fa-lock"></i> Safe Mode
                    </a>
                </li>
                <li>
                    <a>
                        <label class="switch">
                            <input type="checkbox" name="safemode" id="toggleSwitch">
                            <span class="slider"></span>
                        </label>
                    </a>
                </li>
            </ul>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Fetch the persisted safe state when the page loads
        fetch('getSafeState')
            .then(response => response.text()) // Expect plain text
            .then(data => {
                let toggleSwitch = document.getElementById('toggleSwitch');
                toggleSwitch.checked = (data.trim() === "true"); // Convert plain text to boolean
            })
            .catch(error => console.error("Error fetching safe state:", error));
    });
    document.getElementById('toggleSwitch').addEventListener('change', function () {
        let toggleValue = this.checked ? "true" : "false";

        fetch('safe', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'safe=' + toggleValue
        })
            .then(response => console.log("Toggle updated"))
            .catch(error => console.error("Error:", error));
    });
</script>