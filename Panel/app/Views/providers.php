<?= $this->extend('index') ?>
<?= $this->section('content') ?>
<div class="content">
    <div class="container-fluid">
        <!--        <div class="row">-->
        <!--            <div class="col-md-12">-->
        <!---->
        <!--                <div class="card-body" style="float: right">-->
        <!---->
        <!---->
        <!--                    <button class="btn btn-primary btn-lg" onclick="window.location.href='createUser';">-->
        <!--                      <span class="btn-label">-->
        <!--                        <i class="material-icons">add</i>-->
        <!--                      </span>-->
        <!--                        Add Health Offices-->
        <!--                    </button>-->
        <!---->
        <!--                </div>-->
        <!--            </div>-->
        <!---->
        <!--        </div>-->
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary card-header-icon">
                        <div class="card-icon">
                            <p> P </p>
                        </div>
                        <h4 class="card-title">Providers</h4>
                    </div>
                    <div class="card-body">



                        <div class="material-datatables">


                            <table id="datatables" class="table table-striped table-no-bordered table-hover"
                                   cellspacing="0" width="100%" style="width:100%">
                                <thead>

                                    <?php

                                    $roleDetail = json_decode($_SESSION["userData"]["roleDetails"]);

                                    //                                    $roleDetail[0]->menuDashboard

                                    ?>
                                    <tr>
                                        <th>ID</th>
                                        <th>Provider User Name</th>
                                        <th>Provider Name</th>
                                        <th>Address</th>
                                        <th>Category</th>
                                        <th>ContactNo</th>
                                        <th>District</th>
                                        <th>Area</th>

                                        <th>Rate</th>

                                        <th>Rating</th>

                                        <th class="disabled-sorting text-right">Actions</th>


                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Provider User Name</th>
                                        <th>Provider Name</th>
                                        <th>Address</th>
                                        <th>Category</th>
                                        <th>ContactNo</th>
                                        <th>District</th>
                                        <th>Area</th>


                                        <th>Rate</th>

                                        <th>Rating</th>

                                            <th class="disabled-sorting text-right">Actions</th>


                                    </tr>
                                </tfoot>
                                <tbody>


                                    <?php foreach ($providers as $provider) {

                                    ?>
                                    <tr>
                                        <td>
                                            <?php echo $provider["providers"]["_id"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["user"]["name"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["providerName"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["address"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["category"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["contactNo"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["district"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["area"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["providers"]["rate"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $provider["avg"]; ?>
                                        </td>
                                        <td class="td-actions text-right">



                                                <button type="button" rel="tooltip"
                                                        class="btn btn-success"/>


                                                    <i class="material-icons">done</i>
                                                </button>




                                        </td>
                                    </tr>
                                  <?php
                                    }

                                    ?>




                                </tbody>
                            </table>


                        </div>
                    </div>
                    <!-- end content-->
                </div>
                <!--  end card  -->
            </div>
            <!-- end col-md-12 -->
        </div>
        <!-- end row -->
    </div>
</div>
<script src="<?php echo site_url('public/theme/'); ?>assets/js/core/jquery.min.js" type="text/javascript"></script>
<script src="<?php echo site_url('public/theme/'); ?>assets/demo/demo.js"></script>

<script>


    function deleteUser(id, name) {

        swal({
            title: 'Are you sure?',
            text: 'You want delete user',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it',
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger",
            buttonsStyling: false
        }).then(function () {


            $.ajax({
                url: "<?php echo site_url('deleteUser')?>",
                type: "POST",
                data: {"id": id},
                dataType: "JSON",
                success: function (data) {
                    console.log(data);
                    swal({
                        title: 'Deleted!',
                        text: 'User has been deleted.',
                        type: 'success',
                        confirmButtonClass: "btn btn-success",
                        buttonsStyling: false
                    }).then(function (value) {
                        location.reload();
                    }, function (dismiss) {
                        location.reload();
                    }).catch(swal.noop)


                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal({
                        title: 'Failed',
                        text: 'Unable to delete user',
                        type: 'error',
                        confirmButtonClass: "btn btn-info",
                        buttonsStyling: false
                    }).catch(swal.noop)
                }
            });


        }, function (dismiss) {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal({
                    title: 'Cancelled',
                    text: 'User deletion cancelled',
                    type: 'error',
                    confirmButtonClass: "btn btn-info",
                    buttonsStyling: false
                }).catch(swal.noop)
            }
        })


    }


    function editUser(id) {

        console.log(id);

        window.location.href = "<?php echo site_url('editUser?id=')?>" + id;

    }


    function editResult(data, id, result) {

        // console.log(id)
        // console.log(result)
        var row = data.parentNode.parentNode;
        // console.log(row.childElementCount);
        // console.log(row);
        // console.log(row.children.item(6).innerHTML);
        swal({
            title: 'Are you sure?',
            text: 'You want change result of Recipient ' + id + " to " + result,
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, change it!',
            cancelButtonText: 'No, keep it',
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger",
            buttonsStyling: false
        }).then(function () {


            $.ajax({
                url: "<?php echo site_url('update-recipient-result')?>",
                type: "POST",
                data: {"id": id, "result": result},
                dataType: "JSON",
                success: function (data) {
                    console.log(data.effectedRows);
                    console.log(data);

                    console.log("success Ajax");

                    if (data.effectedRows === 1) {


                        if (result == "Positive") {
                            row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-danger">Positive</span> </div>'
                        } else if (result == "Negative") {

                            row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-success">Negative</span> </div>'
                        } else if (result == "Rejected") {

                            row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-warning">Rejected</span> </div>'

                        }
                        row.children.item(10).innerHTML = '   <button style="width: 25%;" type="button" rel="tooltip" class="btn btn-success" onclick="editStatus(this,'+id+',\'Approved\')">  <p style="font-size: 20px; margin: auto"> + </p> </button>'



                        swal({
                            title: 'Result Updated!',
                            text: 'Recipient ' + id + ' result has been updated to ' + result,
                            type: 'success',
                            confirmButtonClass: "btn btn-success",
                            buttonsStyling: false
                        }).then(function (value) {

                        }, function (dismiss) {

                        }).catch(swal.noop)
                    } else {
                        swal({
                            title: 'Failed',
                            text: 'Unable to change result of Recipient' + id,
                            type: 'error',
                            confirmButtonClass: "btn btn-info",
                            buttonsStyling: false
                        }).catch(swal.noop)
                    }



                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(errorThrown);
                    console.log(textStatus);
                    console.log("error ajax");
                    // swal({
                    //     title: 'Failed',
                    //     text: 'Unable to delete user',
                    //     type: 'error',
                    //     confirmButtonClass: "btn btn-info",
                    //     buttonsStyling: false
                    // }).catch(swal.noop)

                    swal({
                        title: 'Failed',
                        text: 'Unable to change result of Recipient' + id,
                        type: 'error',
                        confirmButtonClass: "btn btn-info",
                        buttonsStyling: false
                    }).catch(swal.noop)
                }
            });


        }, function (dismiss) {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal({
                    title: 'Cancelled',
                    text: 'Recipient result change request cancelled',
                    type: 'error',
                    confirmButtonClass: "btn btn-info",
                    buttonsStyling: false
                }).catch(swal.noop)
            }
        })
    }

    function editStatus(data, id, result) {

        // console.log(id)
        // console.log(result)
        var row = data.parentNode.parentNode;
        // console.log(row.childElementCount);
        // console.log(row);
        // console.log(row.children.item(6).innerHTML);
        swal({
            title: 'Are you sure?',
            text: 'You want change status of Recipient ' + id + " to " + result,
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, change it!',
            cancelButtonText: 'No, keep it',
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger",
            buttonsStyling: false
        }).then(function () {


            $.ajax({
                url: "<?php echo site_url('update-recipient-status')?>",
                type: "POST",
                data: {"id": id, "status": result},
                dataType: "JSON",
                success: function (data) {
                    //   console.log(data.effectedRows);
                    console.log(data);

                    console.log("success Ajax");

                    if (data.data.effectedRow === 1) {
                        row.children.item(8).innerHTML = data.data.date[0].resultDate;

                        row.children.item(7).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-info">Result Already Approved</span></div>';
                        row.children.item(9).innerHTML = '    <div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-success">Approved</span> </div>'
                        row.children.item(11).innerHTML = ' <button type="button" rel="tooltip" class="btn btn-primary" onclick="export_report('+id+')"> <i class="material-icons">print</i> </button>'

                        //
                        // if (result == "Positive") {
                        //     row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-danger">Positive</span> </div>'
                        // } else if (result == "Negative") {
                        //
                        //     row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-success">Negative</span> </div>'
                        // } else if (result == "Rejected") {
                        //     row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-warning">Rejected</span> </div>'
                        // }


                        swal({
                            title: 'Result Updated!',
                            text: 'Recipient ' + id + ' status has been updated to ' + result,
                            type: 'success',
                            confirmButtonClass: "btn btn-success",
                            buttonsStyling: false
                        }).then(function (value) {

                        }, function (dismiss) {

                        }).catch(swal.noop)
                    } else {
                        swal({
                            title: 'Failed',
                            text: 'Unable to change status of Recipient' + id,
                            type: 'error',
                            confirmButtonClass: "btn btn-info",
                            buttonsStyling: false
                        }).catch(swal.noop)
                    }



                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(errorThrown);
                    console.log(textStatus);
                    console.log("error ajax");
                    // swal({
                    //     title: 'Failed',
                    //     text: 'Unable to delete user',
                    //     type: 'error',
                    //     confirmButtonClass: "btn btn-info",
                    //     buttonsStyling: false
                    // }).catch(swal.noop)

                    swal({
                        title: 'Failed',
                        text: 'Unable to change status of Recipient' + id,
                        type: 'error',
                        confirmButtonClass: "btn btn-info",
                        buttonsStyling: false
                    }).catch(swal.noop)
                }
            });


        }, function (dismiss) {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            if (dismiss === 'cancel') {
                swal({
                    title: 'Cancelled',
                    text: 'Recipient status change request cancelled',
                    type: 'error',
                    confirmButtonClass: "btn btn-info",
                    buttonsStyling: false
                }).catch(swal.noop)
            }
        })
    }

    function resultChange(id, resultStatus, hoID, name) {

        console.log(id);
        console.log(resultStatus);
        window.location.href = "<?php echo site_url('update-recipient-result?id=')?>" + id + "&result=" + resultStatus + "&hoId=" + hoID + "&name=" + name;

    }

    function statusChange(id, resultStatus, hoID, name) {

        console.log(id);
        console.log(resultStatus);
        window.location.href = "<?php echo site_url('update-recipient-status?id=')?>" + id + "&result=" + resultStatus + "&hoId=" + hoID + "&name=" + name;

    }

    function export_report(id) {


        window.location.href = "<?php echo site_url('export-report?id=')?>" + id;

    }


    $(document).ready(function () {

        console.log("ready");
        $('#datatables').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            iDisplayLength: 25,
            responsive: true,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Search records",
            },
            dom: 'Bfrtip',
            buttons: [

                'excelHtml5',

                'pdfHtml5',

            ]
        });

        var table = $('#datatable').DataTable();

        // Edit record
        table.on('click', '.edit', function () {
            $tr = $(this).closest('tr');
            var data = table.row($tr).data();
            alert('You press on Row: ' + data[0] + ' ' + data[1] + ' ' + data[2] + '\'s row.');
        });

        // Delete a record
        table.on('click', '.remove', function (e) {
            $tr = $(this).closest('tr');
            table.row($tr).remove().draw();
            e.preventDefault();
        });

        //Like record
        table.on('click', '.like', function () {
            alert('You clicked on Like button');
        });
    });
</script>

<?= $this->endSection() ?>
