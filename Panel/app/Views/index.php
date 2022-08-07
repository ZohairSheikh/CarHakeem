<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <link rel="apple-touch-icon" sizes="76x76"
              href="<?php echo site_url('public/theme/'); ?>assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="<?php echo site_url('public/theme/'); ?>assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <title>
           Car Hakeem
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
              name='viewport'/>
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css"
              href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="<?php echo site_url('public/theme/'); ?>assets/css/material-dashboard.css?v=2.0.2"
              rel="stylesheet"/>
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="<?php echo site_url('public/theme/'); ?>assets/demo/demo.css" rel="stylesheet"/>
    </head>


    <?php if (isset($_SESSION['sucMsg']) || isset($_SESSION['failMsg'])){ ?>
    <body class="" onload="md.notifyCard('','<?php

    if (isset($_SESSION['sucMsg']) && $_SESSION['sucMsg'] != "") {
        echo $_SESSION['sucMsg'];
    } else if (isset($_SESSION['failMsg']) && $_SESSION['failMsg'] != "") {
        echo $_SESSION['failMsg'];
    }
    ?>','<?php

    if (isset($_SESSION['sucMsg']) && $_SESSION['sucMsg'] != "") {
        echo "success";
    } else if (isset($_SESSION['failMsg']) && $_SESSION['failMsg'] != "") {
        echo "danger";
    }
    ?>')">
        <?php }else{ ?>

        <body class="">
            <?php } ?>


            <div class="wrapper ">
                <div class="sidebar" data-color="rose" data-background-color="white"
                     data-image="<?php echo site_url('public/theme/'); ?>assets/img/sidebar-1.jpg">
                    <!--
                      Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

                      Tip 2: you can also add an image using data-image tag
                  -->
                    <div class="logo">


                        <div  style="text-align: center">

                            <a href="#" class="simple-text logo-normal">
                               Car Hakeem
                            </a>
                        </div>


                    </div>
                    <div class="sidebar-wrapper">
                        <div class="user">
                            <div class="photo">
                                <img src=""/>
                            </div>
                            <div class="user-info">
                                <a data-toggle="collapse" href="#collapseExample" class="username">
              <span>
                <?php echo $_SESSION["userData"]["name"]; ?>
                <b class="caret"></b>
              </span>
                                </a>
                                <!--                                <div class="collapse" id="collapseExample">-->
                                <!--                                    <ul class="nav">-->
                                <!--                                        <li class="nav-item">-->
                                <!--                                            <a class="nav-link" href="#">-->
                                <!--                                                <span class="sidebar-mini"> MP </span>-->
                                <!--                                                <span class="sidebar-normal"> My Profile </span>-->
                                <!--                                            </a>-->
                                <!--                                        </li>-->
                                <!--                                        <li class="nav-item">-->
                                <!--                                            <a class="nav-link" href="#">-->
                                <!--                                                <span class="sidebar-mini"> EP </span>-->
                                <!--                                                <span class="sidebar-normal"> Edit Profile </span>-->
                                <!--                                            </a>-->
                                <!--                                        </li>-->
                                <!--                                        <li class="nav-item">-->
                                <!--                                            <a class="nav-link" href="#">-->
                                <!--                                                <span class="sidebar-mini"> S </span>-->
                                <!--                                                <span class="sidebar-normal"> Settings </span>-->
                                <!--                                            </a>-->
                                <!--                                        </li>-->
                                <!--                                    </ul>-->
                                <!--                                </div>-->
                            </div>
                        </div>


                        <ul class="nav">

                            <li class="nav-item  ">
                                <a class="nav-link" href="<?php echo site_url('dashboard') ?>">
                                    <i class="material-icons">dashboard</i>
                                    <p> Dashboard </p>
                                </a>
                            </li>


                            <li class="nav-item  ">
                                <a class="nav-link" href="<?php echo site_url('users') ?>">
                                    <i class="material-icons">dashboard</i>
                                    <p> Users </p>
                                </a>
                            </li>


                            <li class="nav-item  ">
                                <a class="nav-link" href="<?php echo site_url('providers') ?>">
                                    <i class="material-icons">dashboard</i>
                                    <p> Providers </p>
                                </a>
                            </li>

                            <li class="nav-item  ">
                                <a class="nav-link" href="<?php echo site_url('booking') ?>">
                                    <i class="material-icons">dashboard</i>
                                    <p> Bookings </p>
                                </a>
                            </li>


                        </ul>
                    </div>
                </div>
                <div class="main-panel">
                    <!-- Navbar -->
                    <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top "
                         id="navigation-example">



                        <div class="container-fluid">
                            <div class="navbar-wrapper">
                                <div class="navbar-minimize">
                                    <button id="minimizeSidebar" class="btn btn-just-icon btn-white btn-fab btn-round">
                                        <i class="material-icons text_align-center visible-on-sidebar-regular">more_vert</i>
                                        <i class="material-icons design_bullet-list-67 visible-on-sidebar-mini">view_list</i>
                                    </button>
                                </div>
                                <a class="navbar-brand"><?php echo $_SESSION["Header"]; ?></a>


                            </div>

                            <button class="navbar-toggler" type="button" data-toggle="collapse"
                                    aria-controls="navigation-index" aria-expanded="false"
                                    aria-label="Toggle navigation" data-target="#navigation-example">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="navbar-toggler-icon icon-bar"></span>
                                <span class="navbar-toggler-icon icon-bar"></span>
                                <span class="navbar-toggler-icon icon-bar"></span>
                            </button>

                            <a class="navbar-brand" href="">
                                <a href="#" class="simple-text logo-normal">
                                    Car Hakeem
                                </a>
                            </a>
                            <div class="collapse navbar-collapse justify-content-end">

                                <ul class="navbar-nav">


                                    <li class="nav-item dropdown">
                                        <a class="nav-link" href="http://example.com" id="navbarDropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="material-icons">person</i>

                                            <p class="d-lg-none d-md-block">
                                                Some Actions
                                            </p>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right"
                                             aria-labelledby="navbarDropdownMenuLink">
                                            <a class="dropdown-item" href="<?php echo site_url("logoutUser"); ?>">Log
                                                Out</a>

                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>


                    <?= $this->renderSection('content') ?>
                    <footer class="footer">
                        <div class="container-fluid">
                            <div class="copyright float-right">


                            </div>
                        </div>
                    </footer>
                </div>
            </div>
            <div class="fixed-plugin">
                <div class="dropdown show-dropdown">
                    <a href="#" data-toggle="dropdown">
                        <i class="fa fa-cog fa-2x"> </i>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header-title"> Sidebar Filters</li>
                        <li class="adjustments-line">
                            <a href="javascript:void(0)" class="switch-trigger active-color">
                                <div class="badge-colors ml-auto mr-auto">
                                    <span class="badge filter badge-purple" data-color="purple"></span>
                                    <span class="badge filter badge-azure" data-color="azure"></span>
                                    <span class="badge filter badge-green" data-color="green"></span>
                                    <span class="badge filter badge-warning" data-color="orange"></span>
                                    <span class="badge filter badge-danger" data-color="danger"></span>
                                    <span class="badge filter badge-rose active" data-color="rose"></span>
                                </div>
                                <div class="clearfix"></div>
                            </a>
                        </li>
                        <li class="header-title">Sidebar Background</li>
                        <li class="adjustments-line">
                            <a href="javascript:void(0)" class="switch-trigger background-color">
                                <div class="ml-auto mr-auto">
                                    <span class="badge filter badge-black active" data-background-color="black"></span>
                                    <span class="badge filter badge-white" data-background-color="white"></span>
                                    <span class="badge filter badge-red" data-background-color="red"></span>
                                </div>
                                <div class="clearfix"></div>
                            </a>
                        </li>
                        <li class="adjustments-line">
                            <a href="javascript:void(0)" class="switch-trigger">
                                <p>Sidebar Mini</p>
                                <label class="ml-auto">
                                    <div class="togglebutton switch-sidebar-mini">
                                        <label>
                                            <input type="checkbox">
                                            <span class="toggle"></span>
                                        </label>
                                    </div>
                                </label>
                                <div class="clearfix"></div>
                            </a>
                        </li>
                        <li class="adjustments-line">
                            <a href="javascript:void(0)" class="switch-trigger">
                                <p>Sidebar Images</p>
                                <label class="switch-mini ml-auto">
                                    <div class="togglebutton switch-sidebar-image">
                                        <label>
                                            <input type="checkbox" checked="">
                                            <span class="toggle"></span>
                                        </label>
                                    </div>
                                </label>
                                <div class="clearfix"></div>
                            </a>
                        </li>
                        <li class="header-title">Images</li>
                        <li class="active">
                            <a class="img-holder switch-trigger" href="javascript:void(0)">
                                <img src="<?php echo site_url('public/theme/'); ?>assets/img/sidebar-1.jpg" alt="">
                            </a>
                        </li>
                        <li>
                            <a class="img-holder switch-trigger" href="javascript:void(0)">
                                <img src="<?php echo site_url('public/theme/'); ?>assets/img/sidebar-2.jpg" alt="">
                            </a>
                        </li>
                        <li>
                            <a class="img-holder switch-trigger" href="javascript:void(0)">
                                <img src="<?php echo site_url('public/theme/'); ?>assets/img/sidebar-3.jpg" alt="">
                            </a>
                        </li>
                        <li>
                            <a class="img-holder switch-trigger" href="javascript:void(0)">
                                <img src="<?php echo site_url('public/theme/'); ?>assets/img/sidebar-4.jpg" alt="">
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
            <!--   Core JS Files   -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/core/jquery.min.js"
                    type="text/javascript"></script>
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/core/popper.min.js"
                    type="text/javascript"></script>
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/core/bootstrap-material-design.min.js"
                    type="text/javascript"></script>
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
            <!-- Plugin for the momentJs  -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/moment.min.js"></script>
            <!--  Plugin for Sweet Alert -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/sweetalert2.js"></script>
            <!-- Forms Validations Plugin -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/jquery.validate.min.js"></script>
            <!--  Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/jquery.bootstrap-wizard.js"></script>
            <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/bootstrap-selectpicker.js"></script>
            <!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
            <!--  DataTables.net Plugin, full documentation here: https://datatables.net/    -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/jquery.dataTables.min.js"></script>
            <!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/bootstrap-tagsinput.js"></script>
            <!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/jasny-bootstrap.min.js"></script>
            <!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/fullcalendar.min.js"></script>
            <!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/jquery-jvectormap.js"></script>
            <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/nouislider.min.js"></script>
            <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
            <!-- Library for adding dinamically elements -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/arrive.min.js"></script>
            <!--  Google Maps Plugin    -->
            <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
            <!-- Chartist JS -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/chartist.min.js"></script>
            <!--  Notifications Plugin    -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/plugins/bootstrap-notify.js"></script>
            <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/js/material-dashboard.min.js?v=2.0.2"
                    type="text/javascript"></script>
            <!-- Material Dashboard DEMO methods, don't include it in your project! -->
            <script src="<?php echo site_url('public/theme/'); ?>assets/demo/demo.js"></script>
            <script>
                $(document).ready(function () {
                    $().ready(function () {
                        $sidebar = $('.sidebar');

                        $sidebar_img_container = $sidebar.find('.sidebar-background');

                        $full_page = $('.full-page');

                        $sidebar_responsive = $('body > .navbar-collapse');

                        window_width = $(window).width();

                        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();

                        if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
                            if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
                                $('.fixed-plugin .dropdown').addClass('open');
                            }

                        }

                        $('.fixed-plugin a').click(function (event) {
                            // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
                            if ($(this).hasClass('switch-trigger')) {
                                if (event.stopPropagation) {
                                    event.stopPropagation();
                                } else if (window.event) {
                                    window.event.cancelBubble = true;
                                }
                            }
                        });

                        $('.fixed-plugin .active-color span').click(function () {
                            $full_page_background = $('.full-page-background');

                            $(this).siblings().removeClass('active');
                            $(this).addClass('active');

                            var new_color = $(this).data('color');


                            if ($sidebar.length != 0) {
                                $sidebar.attr('data-color', new_color);
                            }

                            if ($full_page.length != 0) {
                                $full_page.attr('filter-color', new_color);
                            }

                            if ($sidebar_responsive.length != 0) {
                                $sidebar_responsive.attr('data-color', new_color);
                            }
                        });

                        $('.fixed-plugin .background-color .badge').click(function () {
                            $(this).siblings().removeClass('active');
                            $(this).addClass('active');

                            var new_color = $(this).data('background-color');

                            if ($sidebar.length != 0) {
                                $sidebar.attr('data-background-color', new_color);
                            }
                        });

                        $('.fixed-plugin .img-holder').click(function () {
                            $full_page_background = $('.full-page-background');

                            $(this).parent('li').siblings().removeClass('active');
                            $(this).parent('li').addClass('active');


                            var new_image = $(this).find("img").attr('src');

                            if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                                $sidebar_img_container.fadeOut('fast', function () {
                                    $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                                    $sidebar_img_container.fadeIn('fast');
                                });
                            }

                            if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                                var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                                $full_page_background.fadeOut('fast', function () {
                                    $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                                    $full_page_background.fadeIn('fast');
                                });
                            }

                            if ($('.switch-sidebar-image input:checked').length == 0) {
                                var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
                                var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                                $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                                $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                            }

                            if ($sidebar_responsive.length != 0) {
                                $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
                            }
                        });

                        $('.switch-sidebar-image input').change(function () {
                            $full_page_background = $('.full-page-background');

                            $input = $(this);

                            if ($input.is(':checked')) {
                                if ($sidebar_img_container.length != 0) {
                                    $sidebar_img_container.fadeIn('fast');
                                    $sidebar.attr('data-image', '#');
                                }

                                if ($full_page_background.length != 0) {
                                    $full_page_background.fadeIn('fast');
                                    $full_page.attr('data-image', '#');
                                }

                                background_image = true;
                            } else {
                                if ($sidebar_img_container.length != 0) {
                                    $sidebar.removeAttr('data-image');
                                    $sidebar_img_container.fadeOut('fast');
                                }

                                if ($full_page_background.length != 0) {
                                    $full_page.removeAttr('data-image', '#');
                                    $full_page_background.fadeOut('fast');
                                }

                                background_image = false;
                            }
                        });

                        $('.switch-sidebar-mini input').change(function () {
                            $body = $('body');

                            $input = $(this);

                            if (md.misc.sidebar_mini_active == true) {
                                $('body').removeClass('sidebar-mini');
                                md.misc.sidebar_mini_active = false;

                                $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

                            } else {

                                $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

                                setTimeout(function () {
                                    $('body').addClass('sidebar-mini');

                                    md.misc.sidebar_mini_active = true;
                                }, 300);
                            }

                            // we simulate the window Resize so the charts will get updated in realtime.
                            var simulateWindowResize = setInterval(function () {
                                window.dispatchEvent(new Event('resize'));
                            }, 180);

                            // we stop the simulation of Window Resize after the animations are completed
                            setTimeout(function () {
                                clearInterval(simulateWindowResize);
                            }, 1000);

                        });
                    });
                });
            </script>
            <script>
                $(document).ready(function () {
                    // Javascript method's body can be found in assets/js/demos.js
            //        md.initDashboardPageCharts();

                ///    md.initVectorMap();

                });
            </script>
        </body>

</html>
