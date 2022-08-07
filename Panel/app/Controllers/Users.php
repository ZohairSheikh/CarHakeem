<?php

namespace App\Controllers;

use App\Models\ProvidersModel;
use App\Models\UserModel;

class Users extends BaseController
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

        $model = new UserModel();

        $userData["Users"] = $model->getAllUsers();


        $this->session->set('Header', "Users");
        return view('users', $userData);
    }

    public function postCheck()
    {

        $data = json_decode(file_get_contents('php://input'), true);
        return json_encode($data["name"]);

    }


}
