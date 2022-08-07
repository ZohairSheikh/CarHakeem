<?php

namespace App\Controllers;
use Config\Services;
use Endroid\QrCode\Color\Color;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelLow;
use Endroid\QrCode\QrCode;
use Endroid\QrCode\Label\Label;
use Endroid\QrCode\Logo\Logo;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;


class Dashboard extends BaseController
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




        $this->session->set('Header', "Dashboard");
        return view('dashboard');
    }



}
