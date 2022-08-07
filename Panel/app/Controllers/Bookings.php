<?php

namespace App\Controllers;

use App\Models\BookingModel;
use App\Models\UserModel;

class Bookings extends BaseController
{

    public function __construct()
    {

    }

    public function index()
    {

        if (!$this->session->isLoggedIn) {
            $this->session->setFlashdata('loginError', 'Session Expired Please Login Again');
            return redirect()->to("login");
        }

        $model = new BookingModel();

        $userData["bookings"] = $model->getAllBookings();




        $this->session->set('Header', "Bookings");
        return view('bookings', $userData);

    }

    public function update_booking_status_ajax()
    {


        $model = new BookingModel();





        return json_encode( $model->updateStatus($_POST["id"],$_POST["status"]));


//        $data = json_decode(file_get_contents('php://input'), true);
//        return json_encode($data["name"]);

    }


}
