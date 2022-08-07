<?php

namespace App\Controllers;

use App\Models\ProvidersModel;
use Config\Services;
use App\Models\LoginModel;
use App\Models\UserModel;
use Tigo\Recommendation\Recommend;

class Login extends BaseController
{

    protected $config;
    private $usermodel;

    public function __construct()
    {
        $this->usermodel = new UserModel();
    }

    public function index()
    {
        $this->session->remove(['isLoggedIn', 'userData','Header']);
        if ($this->session->isLoggedIn) {
            return redirect()->to("dashboard");
        }

      

       // print_r(password_hash("1234", PASSWORD_DEFAULT));
        return view('login');
    }


    public function login()
    {
//        print_r($_POST);

        $model = new LoginModel();

        $userData = $model->login($_POST["email"], $_POST["password"]);

     //   print_r($userData);
    //    die;

        if ($userData == 0) {
            $this->session->setFlashdata('loginError', 'Wrong email or password');
            return view('login');
        } else {




            $this->session->set('isLoggedIn', true);

            $this->session->set('Header', "Dashboard");



            $this->session->set('userData', [
                'id' 			=> $userData["_id"],
                'name' 			=>  $userData["name"],

                'mobileNo' 		=> $userData["mobileNo"],
                'email' 		=>  $userData["emailAddress"],
                'userType' 		=> $userData["userType"],



            ]);
            return redirect()->to("dashboard");
        }


    }

    public function logOut()
    {


        $this->session->remove(['isLoggedIn', 'userData','Header']);
        return redirect()->to("login");

    }

}
