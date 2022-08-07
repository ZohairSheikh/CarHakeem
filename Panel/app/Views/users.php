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
                        <h4 class="card-title">Users</h4>
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
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Mobile</th>
                                        <th>User Type</th>

                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Mobile</th>
                                        <th>User Type</th>

                                    </tr>
                                </tfoot>
                                <tbody>
                                    <?php foreach ($Users as $user) {

                                    ?>
                                    <tr>
                                        <td>
                                            <?php echo $user["_id"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $user["name"]; ?>
                                        </td>  <td>
                                            <?php echo $user["emailAddress"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $user["mobileNo"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $user["userType"]; ?>
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
