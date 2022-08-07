<?php

namespace App\Models;

use App\Libraries\DatabaseConnector;
use CodeIgniter\Model;
use CodeIgniter\Database\ConnectionInterface;


class LoginModel extends Model
{


    private $collection;

    function __construct() {
        $connection = new DatabaseConnector();
        $database = $connection->getDatabase();
        $this->collection = $database->users;


    }


    function login($userName, $password)
    {

        $cursor = $this->collection->findOne(['emailAddress'=> $userName]);
        $users = $cursor;

        if(sizeof($users) == 0)
        {
            return 0 ;
        }
        else{
            return $users;
        }






    }

    /**
     * Hashes the password after field validation and before insert/update
     */


}
