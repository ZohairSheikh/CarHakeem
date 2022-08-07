<?php

namespace App\Models;

use App\Libraries\DatabaseConnector;
use CodeIgniter\Model;

class BookingModel extends Model
{


    private $collection;

    function __construct()
    {
        $connection = new DatabaseConnector();
        $database = $connection->getDatabase();
        $this->collection = $database->services;


    }


    function getAllBookings()
    {
        $result = $this->collection->aggregate([
                [
                    '$lookup' => [
                        'from' => "providers",
                        'localField' => "providerId",
                        'foreignField' => "_id",
                        'as' => "providers"
                    ]
                ],
                ['$unwind' => '$providers'],

                [
                    '$lookup' => [
                        'from' => "users",
                        'localField' => "userId",
                        'foreignField' => "_id",
                        'as' => "customer"
                    ]
                ],
                ['$unwind' => '$customer'],

                [
                    '$lookup' => [
                        'from' => "users",
                        'localField' => "providerUserId",
                        'foreignField' => "_id",
                        'as' => "providerUser"
                    ]
                ],
                ['$unwind' => '$providerUser'],
            ]

        );

        $providers = $result->toArray();

        return $providers;
    }


     function updateStatus($id,$status)
    {
        $result = $this->collection->findOneAndUpdate(['_id'=> new \MongoDB\BSON\ObjectID($id)],['$set' =>['adminStatus'=>$status]]);
        return $result;

    }

}
