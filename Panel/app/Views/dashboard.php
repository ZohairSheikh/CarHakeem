<?= $this->extend('index') ?>
<?= $this->section('content') ?>

<style>
    /*svg.ct-chart-bar, svg.ct-chart-line{*/
    /*    overflow: visible;*/
    /*}*/
    /*.ct-label.ct-label.ct-horizontal.ct-end {*/
    /*    position: relative;*/
    /*    justify-content: flex-end;*/
    /*    text-align: right;*/
    /*    transform-origin: 100% 0;*/
    /*    transform: translate(-100%) rotate(-45deg);*/
    /*    white-space:nowrap;*/
    /*}*/
</style>
<div class="content">


    <div class="content">
        <div class="container-fluid">



        </div>
    </div>
</div>

<script src="<?php echo site_url('public/theme/'); ?>assets/js/core/jquery.min.js"
        type="text/javascript"></script>
<script src="<?php echo site_url('public/components/'); ?>chart.js/Chart.js"></script>
<script>

    $(document).ready(function () {
        //  initCharts();
        //  dailyTestChart();


        $.ajax({
            url: "<?php echo site_url('daily-test-chart')?>",
            type: "GET",
            dataType: "JSON",
            success: function (data) {

                console.log(data.data[0]);
                //  var dd = JSON.parse(data);

                //  console.log(dd);
                dailyTestChart(data.data);


            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(errorThrown);
                console.log(textStatus);
                console.log("error ajax");


            }
        });

        dailyPosNegChart('<?php echo date("d/m/Y");?>', 'ready');
        dailyGenderChart('<?php echo date("d/m/Y");?>', 'ready');


    });

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    function dailyPosNegChart(date, from1) {


        $.ajax({
            url: "<?php echo site_url('daily-pos-neg-chart')?>",
            type: "POST",
            data: {"date": date, "from": from1},
            dataType: "JSON",
            success: function (data) {

                console.log(data.data);

                var label = [];
                var series = [];


                data.data.forEach(function (item) {


                    label.push(item.result + " - " + item.name)
                    series.push(item.resultCount)
                });

                var barLabels = label;
                var barValues = series;
                var areaChartData1 = {
                    labels: barLabels,
                    datasets: [
                        {
                            label: 'Paid Credit',
                            fillColor: 'rgba(210, 214, 222, 1)',
                            strokeColor: 'rgba(210, 214, 222, 1)',
                            pointColor: 'rgba(210, 214, 222, 1)',
                            pointStrokeColor: '#c1c7d1',
                            pointHighlightFill: '#fff',
                            pointHighlightStroke: 'rgba(220,220,220,1)',
                            data: barValues
                        }
                    ]
                }


                $('#paidCreditBarChart').remove();

                $('#paidCreditBarChartContainer').append('<canvas id="paidCreditBarChart" style="height:250px"><canvas>');

                if (label.length === 0) {
                    document.getElementById('no-data').style.display = 'block';
                    document.getElementById('paidCreditBarChart').style.display = 'none'
                } else {
                    document.getElementById('no-data').style.display = 'none';
                    document.getElementById('paidCreditBarChart').style.display = 'block'
                }


                var color = getRandomColor();
                var barChartCanvas = $('#paidCreditBarChart').get(0).getContext('2d')
                var barChart = new Chart(barChartCanvas)
                var barChartData = areaChartData1
                barChartData.datasets[0].fillColor = color
                barChartData.datasets[0].strokeColor = color
                barChartData.datasets[0].pointColor = color
                var barChartOptions = {
                    //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
                    scaleBeginAtZero: true,
                    //Boolean - Whether grid lines are shown across the chart
                    scaleShowGridLines: true,
                    //String - Colour of the grid lines
                    scaleGridLineColor: 'rgba(0,0,0,.05)',
                    //Number - Width of the grid lines
                    scaleGridLineWidth: 1,
                    //Boolean - Whether to show horizontal lines (except X axis)
                    scaleShowHorizontalLines: true,
                    //Boolean - Whether to show vertical lines (except Y axis)
                    scaleShowVerticalLines: true,
                    //Boolean - If there is a stroke on each bar
                    barShowStroke: true,
                    //Number - Pixel width of the bar stroke
                    barStrokeWidth: 1,
                    //Number - Spacing between each of the X value sets
                    barValueSpacing: 5,
                    //Number - Spacing between data sets within X values
                    barDatasetSpacing: 1,
                    //String - A legend template
                    legendTemplate: '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
                    //Boolean - whether to make the chart responsive
                    responsive: true,
                    maintainAspectRatio: false,
                }

                barChartOptions.datasetFill = false
                barChart.Bar(barChartData, barChartOptions)


            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(errorThrown);
                console.log(textStatus);
                console.log("error ajax");


            }
        });

    }

    function dailyGenderChart(date, from1) {


        $.ajax({
            url: "<?php echo site_url('daily-gender-chart')?>",
            type: "POST",
            data: {"date": date, "from": from1},
            dataType: "JSON",
            success: function (data) {

                console.log(data.data);

                var PieData = [];
                data.data.forEach(function (item) {

                    var color = getRandomColor();
                    var obj = {
                        value: item.genderCount, color: color,
                        highlight: color, label:item.gender
                    }

                    PieData.push(obj);

                });


                $('#divPi').remove();

             //   $('#dailyGenderChartContainer').append('<canvas id="dailyGenderChart" style="height:250px"><canvas>');
                $('#dailyGenderChartContainer').append('<div id="divPi" class="row"><div class="col-md-8"><canvas id="dailyGenderChart" ></canvas></div><div class="col-md-4"><div id="pieChartLegend"></div></div></div>');

                if (PieData.length === 0) {
                    document.getElementById('no-data-gender-chart').style.display = 'block';
                    document.getElementById('dailyGenderChart').style.display = 'none'
                } else {
                    document.getElementById('no-data-gender-chart').style.display = 'none';
                    document.getElementById('dailyGenderChart').style.display = 'block'
                }

                var pieOptions = {
                    //Boolean - Whether we should show a stroke on each segment
                    segmentShowStroke: true,

                    //String - The colour of each segment stroke
                    segmentStrokeColor: '#fff',
                    //Number - The width of each segment stroke
                    segmentStrokeWidth: 2,
                    //Number - The percentage of the chart that we cut out of the middle
                    percentageInnerCutout: 50, // This is 0 for Pie charts
                    //Number - Amount of animation steps
                    animationSteps: 100,
                    //String - Animation easing effect
                    animationEasing: 'easeOutBounce',
                    //Boolean - Whether we animate the rotation of the Doughnut
                    animateRotate: true,
                    //Boolean - Whether we animate scaling the Doughnut from the centre
                    animateScale: false,

                    //Boolean - whether to make the chart responsive to window resizing
                    responsive: true,
                    // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
                    maintainAspectRatio: true,
                    //String - A legend template
                    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].color%>;\"></span><%if(segments[i].label){%><%=segments[i].label%>:<%=segments[i].value%><%}%></li><%}%></ul>",
                    scaleShowGridLines: true,

                    //String - Colour of the grid lines
                    scaleGridLineColor: "rgba(0,0,0,.05)",

                    //Number - Width of the grid lines
                    scaleGridLineWidth: 1,

                    //Boolean - Whether the line is curved between points
                    bezierCurve: true,

                    //Number - Tension of the bezier curve between points
                    bezierCurveTension: 0.4,

                    //Boolean - Whether to show a dot for each point
                    pointDot: true,

                    //Number - Radius of each point dot in pixels
                    pointDotRadius: 4,

                    //Number - Pixel width of point dot stroke
                    pointDotStrokeWidth: 1,

                    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
                    pointHitDetectionRadius: 20,

                    //Boolean - Whether to show a stroke for datasets
                    datasetStroke: true,

                    //Number - Pixel width of dataset stroke
                    datasetStrokeWidth: 2,

                    //Boolean - Whether to fill the dataset with a colour
                    datasetFill: true,
                }
                //Create pie or douhnut chart
                // You can switch between pie and douhnut using the method below.

                var pieChartCanvas = $('#dailyGenderChart').get(0).getContext('2d')
                var pieChart = new Chart(pieChartCanvas).Pie(PieData, pieOptions);
                var legendAdwordsVsOrganic = pieChart.generateLegend();

                document.getElementById("pieChartLegend").innerHTML = legendAdwordsVsOrganic;

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(errorThrown);
                console.log(textStatus);
                console.log("error ajax");


            }
        });

    }


    function initCharts() {
        console.log("initChart");
        if ($('#colouredRoundedLineChart').length != 0 && $('#multipleBarsChart').length != 0) {
            /* ----------==========    Rounded Line Chart initialization    ==========---------- */

            dataRoundedLineChart = {
                labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
                series: [
                    [12, 17, 7, 17, 23, 18, 38]
                ]
            };

            optionsRoundedLineChart = {
                lineSmooth: Chartist.Interpolation.cardinal({
                    tension: 10
                }),
                axisX: {
                    showGrid: false,
                },
                low: 0,
                high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                chartPadding: {
                    top: 0,
                    right: 0,
                    bottom: 0,
                    left: 0
                },
                showPoint: false
            }

            var RoundedLineChart = new Chartist.Line('#roundedLineChart', dataRoundedLineChart, optionsRoundedLineChart);

            md.startAnimationForLineChart(RoundedLineChart);


            /*  **************** Straight Lines Chart - single line with points ******************** */

            dataStraightLinesChart = {
                labels: ['\'07', '\'08', '\'09', '\'10', '\'11', '\'12', '\'13', '\'14', '\'15'],
                series: [
                    [10, 16, 8, 13, 20, 15, 20, 34, 30]
                ]
            };

            optionsStraightLinesChart = {
                lineSmooth: Chartist.Interpolation.cardinal({
                    tension: 0
                }),
                low: 0,
                high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                chartPadding: {
                    top: 0,
                    right: 0,
                    bottom: 0,
                    left: 0
                },
                classNames: {
                    point: 'ct-point ct-white',
                    line: 'ct-line ct-white'
                }
            }

            var straightLinesChart = new Chartist.Line('#straightLinesChart', dataStraightLinesChart, optionsStraightLinesChart);

            md.startAnimationForLineChart(straightLinesChart);


            /*  **************** Coloured Rounded Line Chart - Line Chart ******************** */


            dataColouredRoundedLineChart = {
                labels: ['\'06', '\'07', '\'08', '\'09', '\'10', '\'11', '\'12', '\'13', '\'14', '\'15'],
                series: [
                    [287, 480, 290, 554, 690, 690, 500, 752, 650, 900, 944]
                ]
            };

            optionsColouredRoundedLineChart = {
                lineSmooth: Chartist.Interpolation.cardinal({
                    tension: 10
                }),
                axisY: {
                    showGrid: true,
                    offset: 40
                },
                axisX: {
                    showGrid: false,
                },
                low: 0,
                high: 1000,
                showPoint: true,
                height: '300px'
            };


            var colouredRoundedLineChart = new Chartist.Line('#colouredRoundedLineChart', dataColouredRoundedLineChart, optionsColouredRoundedLineChart);

            md.startAnimationForLineChart(colouredRoundedLineChart);


            /*  **************** Coloured Rounded Line Chart - Line Chart ******************** */


            dataColouredBarsChart = {
                labels: ['\'06', '\'07', '\'08', '\'09', '\'10', '\'11', '\'12', '\'13', '\'14', '\'15'],
                series: [
                    [287, 385, 490, 554, 586, 698, 695, 752, 788, 846, 944],
                    [67, 152, 143, 287, 335, 435, 437, 539, 542, 544, 647],
                    [23, 113, 67, 190, 239, 307, 308, 439, 410, 410, 509]
                ]
            };

            optionsColouredBarsChart = {
                lineSmooth: Chartist.Interpolation.cardinal({
                    tension: 10
                }),
                axisY: {
                    showGrid: true,
                    offset: 40
                },
                axisX: {
                    showGrid: false,
                },
                low: 0,
                high: 1000,
                showPoint: true,
                height: '300px'
            };


            var colouredBarsChart = new Chartist.Line('#colouredBarsChart', dataColouredBarsChart, optionsColouredBarsChart);

            md.startAnimationForLineChart(colouredBarsChart);


            /*  **************** Public Preferences - Pie Chart ******************** */

            var dataPreferences = {
                labels: ['62%', '32%', '6%'],
                series: [62, 32, 6]
            };

            var optionsPreferences = {
                height: '230px'
            };

            Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);

            /*  **************** Simple Bar Chart - barchart ******************** */

            var dataSimpleBarChart = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                series: [
                    [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895]
                ]
            };

            var optionsSimpleBarChart = {
                seriesBarDistance: 10,
                axisX: {
                    showGrid: false
                }
            };

            var responsiveOptionsSimpleBarChart = [
                ['screen and (max-width: 640px)', {
                    seriesBarDistance: 5,
                    axisX: {
                        labelInterpolationFnc: function (value) {
                            return value[0];
                        }
                    }
                }]
            ];

            var simpleBarChart = Chartist.Bar('#simpleBarChart', dataSimpleBarChart, optionsSimpleBarChart, responsiveOptionsSimpleBarChart);

            //start animation for the Emails Subscription Chart
            md.startAnimationForBarChart(simpleBarChart);


            var dataMultipleBarsChart = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                series: [
                    [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895]
                ]
            };

            var optionsMultipleBarsChart = {
                seriesBarDistance: 10,
                axisX: {
                    showGrid: false
                },
                height: '300px'
            };

            var responsiveOptionsMultipleBarsChart = [
                ['screen and (max-width: 640px)', {
                    seriesBarDistance: 5,
                    axisX: {
                        labelInterpolationFnc: function (value) {
                            return value[0];
                        }
                    }
                }]
            ];

            var multipleBarsChart = Chartist.Bar('#multipleBarsChart', dataMultipleBarsChart, optionsMultipleBarsChart, responsiveOptionsMultipleBarsChart);

            //start animation for the Emails Subscription Chart
            md.startAnimationForBarChart(multipleBarsChart);
        }

    };

    function dailyTestChart(dailyTestData) {
        console.log("initChart");

        var label = [];
        var series = [];


        dailyTestData.forEach(function (item) {
            //console.log(item.count);

            label.push(item.registeredDate.substring(0, 2))
            series.push(item.count)
        });

        $('#dailyTestChart').remove();

        $('#dailyTestChartContainer').append('<canvas id="dailyTestChart" style="height:250px"><canvas>');


        var data = {
            labels: label,
            datasets: [
                {
                    label: "Monthly Date Wise Test",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: series
                },

            ]
        };

        var barChartCanvas = $('#dailyTestChart').get(0).getContext('2d')
        var barChart = new Chart(barChartCanvas)

        barChart.Line(data, {
            bezierCurve: true,
            datasetFill: true,


        });

    };

</script>

<?= $this->endSection() ?>
