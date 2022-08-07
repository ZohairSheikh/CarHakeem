<?php

namespace App\Models;

use App\Libraries\DatabaseConnector;
use CodeIgniter\Model;
use CodeIgniter\Database\ConnectionInterface;


class ProvidersModel extends Model
{


    private $collection;

    function __construct()
    {
        $connection = new DatabaseConnector();
        $database = $connection->getDatabase();
        //  $this->collection = $database->providers;
        $this->collection = $database->providerratings;


    }


    function getAllProviders()
    {

//        $cursor = $this->collection->find([],[]);
//        $providers = $cursor->toArray();
//

        $result = $this->collection->aggregate([
                ['$group' => ['_id' => '$providerId', 'count' => ['$sum' => 1], "avg" => ['$avg' => '$rating']]],
                ['$project' => ['_id' => 1, 'count' => 1, 'avg' => ['$round' => ['$avg', 1]]]],
                [
                    '$lookup' => [
                        'from' => "providers",
                        'localField' => "_id",
                        'foreignField' => "_id",
                        'as' => "providers"
                    ]
                ],
                  [   '$unwind'=>'$providers' ],
                [
                    '$lookup' => [
                        'from' => "users",
                        'localField' => "providers.userId",
                        'foreignField' => "_id",
                        'as' => "user"
                    ]
                ],
                [   '$unwind'=>'$user' ],
            ]

        );

        $providers = $result->toArray();


        return $providers;


    }

    /**
     * Hashes the password after field validation and before insert/update
     */


}
