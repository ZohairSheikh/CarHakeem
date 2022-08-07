
<?= $this->extend('index') ?>
<?= $this->section('content') ?>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form id="RegisterValidation" action="<?php echo  site_url('editUser');?>" method="POST">
                    <div class="card ">
                        <div class="card-header card-header-rose card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">create</i>
                            </div>
                            <h4 class="card-title">User Form</h4>
                        </div>
                        <div class="card-body ">
                            <div class="form-group">
                                <label for="exampleName" class="bmd-label-floating"> Name *</label>
                                <input type="text" name="name" class="form-control" value="<?php echo $user->userName; ?>" id="exampleEmail" required="true">
                            </div>
                            <div class="form-group">
                                <label for="exampleEmail" class="bmd-label-floating"> Email Address *</label>
                                <input type="email" class="form-control" id="exampleEmail" readonly value="<?php echo $user->emailAddress; ?>" name="email" required="true">
                            </div>
                            <div class="form-group">
                                <label for="exampleMobileNo" class="bmd-label-floating"> Mobile Number *</label>
                                <input type="number" class="form-control" id="exampleMobileNo" value="<?php echo $user->mobileNo; ?>" name="mobileNo" required="true">
                            </div>
                            <div class="form-group">
                                <label for="examplePassword" class="bmd-label-static"> Password *</label>
                                <input type="password" class="form-control" placeholder="Unchanged..." id="examplePassword"  name="password">
                            </div>
                            <div class="form-group">
                                <input type="hidden" id="Id" name="userId" value="<?php echo $user->id; ?>">
                            </div>

                            <div class="form-group">
                                <input type="hidden" id="Id" name="oldPassword" value="<?php echo $user->password; ?>">
                            </div>




                            <div class="form-check mr-auto">
                                <label class="form-check-label">
                                    <input class="form-check-input" name="cbActive"  <?php echo $user->isActive? "checked": ""; ?> type="checkbox" value="cbActive" > Active
                                    <span class="form-check-sign">
                                      <span class="check"></span>
                                  </span>
                                </label>
                            </div>
                            <div class="category form-category">* Required fields</div>
                        </div>
                        <div class="card-footer text-right"  style="float:right;">

                            <button type="submit" class="btn btn-rose"  style="float:right;">Edit User</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


<style>
    /* Chrome, Safari, Edge, Opera */
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }
</style>


<?= $this->endSection() ?>
