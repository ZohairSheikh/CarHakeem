<?php

namespace App\Controllers;

use App\Models\LoginModel;
use App\Models\ProvidersModel;
use CodeIgniter\HTTP\RequestInterface;
use CodeIgniter\HTTP\ResponseInterface;
use Psr\Log\LoggerInterface;

class Providers extends BaseController
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

        $model = new ProvidersModel();

        $userData["providers"] = $model->getAllProviders();


        $this->session->set('Header', "Provider");
        return view('providers', $userData);
    }

    public function postCheck()
    {

        $data = json_decode(file_get_contents('php://input'), true);
        return json_encode($data["name"]);

    }


}
