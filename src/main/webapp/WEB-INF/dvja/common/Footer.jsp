<script src='${request.contextPath}/assets/showdown.min.js'></script>
<script type='text/javascript'>
    var converter = new showdown.Converter();

    $.each($('.markdown'), function (idx, val) {
        txt = $(val).html();
        $(val).html(converter.makeHtml(txt));
        $(val).removeClass('markdown');
    });
</script>

<footer>
    <div class='container'>
        <div class='row'>
            <hr/>
            <div class='col-md-4'>
                <!-- Logo -->
            </div>
            <div class='col-md-5'></div>
            <div class='col-md-3'>
                <div class='row'>
                    <div class='col-md-12'>
                        <small>
                            <i class='fa fa-heart'></i> Damn Vulnerable Java Application
                        </small>
                    </div>
                </div>
                <div class='row'>
                    <div class='col-md-12'>
                        <ul class='list-inline'>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class='row'>
            <div class='col-md-12'>
                <center>
                    <small></small>
                </center>
                <br/>
            </div>
        </div>
    </div>
</footer>