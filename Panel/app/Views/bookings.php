<?= $this->extend('index') ?>
<?= $this->section('content') ?>
<div class="content">
    <div class="container-fluid">



        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary card-header-icon">
                        <div class="card-icon">
                            <i class="material-icons">perm_identity</i>
                        </div>
                        <h4 class="card-title">Bookings</h4>
                    </div>
                    <div class="card-body">
                        <div class="toolbar">
                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                        </div>

                        <div class="material-datatables">
                            <table id="datatables" class="table table-striped table-no-bordered table-hover"
                                   cellspacing="0" width="100%" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>Booking ID</th>
                                        <th>Provider</th>
                                        <th>Customer</th>
                                        <th>Provider User</th>
                                        <th>Provider Status</th>
                                        <th>Appointment Date</th>
                                        <th>Approve/Reject</th>
                                        <th class="disabled-sorting text-right">Status Change</th>

                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Booking ID</th>
                                        <th>Provider</th>
                                        <th>Customer</th>
                                        <th>Provider User</th>
                                        <th>Provider Status</th>
                                        <th>Appointment Date</th>
                                        <th>Approve/Reject</th>

                                            <th class="disabled-sorting text-right">Status Change</th>


                                    </tr>
                                </tfoot>
                                <tbody>
                                    <?php foreach ($bookings as $booking) {

                                    ?>
                                    <tr>
                                        <td>
                                            <?php echo $booking['_id']; ?>
                                        </td>
                                        <td>
                                            <?php echo $booking['providers']['providerName']; ?>
                                        </td>

                                        <td>
                                            <?php echo $booking['customer']['name']; ?>
                                        </td>
                                        <td>
                                            <?php echo $booking['providerUser']['name']; ?>
                                        </td>
                                        <td>
                                            <?php echo $booking['status'] ?>
                                        </td>

                                        <td>
                                            <?php echo $booking['appointmentDate'] ?>
                                        </td>

                                        <td>
                                            <?php echo $booking['adminStatus'] ?>
                                        </td>


                                        <td class="td-actions text-right">

                                            <button style="width: 25%;" type="button" rel="tooltip"
                                                    class="btn btn-danger"
                                                    onclick="editResult(this,'<?php echo $booking['_id']; ?>','approved')" >
                                                <p style="font-size: 20px; margin: auto"> + </p>
                                            </button>
                                            <button style="width: 25%;" type="button" rel="tooltip"
                                                    class="btn btn-success"
                                                    onclick="editResult(this,'<?php echo $booking['_id']; ?>','rejected')" >
                                                <p style="font-size: 20px; margin: auto"> - </p>
                                            </button>


                                        </td>

                                    </tr>

                                    <?php } ?>

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




    function editResult(data, id, result) {

        console.log(id)
        console.log(result)
        var row = data.parentNode.parentNode;
         console.log(row.childElementCount);
         console.log(row);
         console.log(row.children.item(6).innerHTML);
        swal({
            title: 'Are you sure?',
            text: 'You want change Status of Booking ' + id + " to " + result,
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, change it!',
            cancelButtonText: 'No, keep it',
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger",
            buttonsStyling: false
        }).then(function () {


            $.ajax({
                url: "<?php echo site_url('update-booking-status')?>",
                type: "POST",
                data: {"id": id, "status": result},
                dataType: "JSON",
                success: function (data) {
                 //   console.log(data.effectedRows);
                    console.log(data);

                    console.log("success Ajax");
                    location.reload();

                    // if (data.effectedRows === 1) {
                    //
                    //
                    //     if (result == "Positive") {
                    //         row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-danger">Positive</span> </div>'
                    //     } else if (result == "Negative") {
                    //
                    //         row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-success">Negative</span> </div>'
                    //     } else if (result == "Rejected") {
                    //
                    //         row.children.item(6).innerHTML = '<div class="timeline-heading" style=" margin: auto; "> <span class="badge badge-pill badge-warning">Rejected</span> </div>'
                    //
                    //     }
                    //     row.children.item(10).innerHTML = '   <button style="width: 25%;" type="button" rel="tooltip" class="btn btn-success" onclick="editStatus(this,'+id+',\'Approved\')">  <p style="font-size: 20px; margin: auto"> + </p> </button>'
                    //
                    //
                    //
                    //     swal({
                    //         title: 'Result Updated!',
                    //         text: 'Recipient ' + id + ' result has been updated to ' + result,
                    //         type: 'success',
                    //         confirmButtonClass: "btn btn-success",
                    //         buttonsStyling: false
                    //     }).then(function (value) {
                    //
                    //     }, function (dismiss) {
                    //
                    //     }).catch(swal.noop)
                    // } else {
                    //     swal({
                    //         title: 'Failed',
                    //         text: 'Unable to change result of Recipient' + id,
                    //         type: 'error',
                    //         confirmButtonClass: "btn btn-info",
                    //         buttonsStyling: false
                    //     }).catch(swal.noop)
                    // }



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
                        text: 'Unable to change status of booking' + id,
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
                    text: 'booking status change request cancelled',
                    type: 'error',
                    confirmButtonClass: "btn btn-info",
                    buttonsStyling: false
                }).catch(swal.noop)
            }
        })
    }


    $(document).ready(function () {
        $('#datatables').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            responsive: true,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Search records",
            }
        });


    });

</script>

<?= $this->endSection() ?>
