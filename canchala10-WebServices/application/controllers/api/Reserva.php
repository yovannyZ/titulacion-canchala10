<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';
class Reserva extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->model('Reserva_model');

        
    }

    public function index_get()
    {
        $data = $this->Reserva_model->getAll();

        if($data)
        {
            $this->response(array('status' => true, 'response' => $data), 200);
        }
        else
        {
            $this->response(array('status' => false, 'response' => 'No data available.'), 200);
        }   
    }

    public function find_get($id)
    {
        $data = $this->Reserva_model->get($id);

        if($data)
        {
            $this->response(array('status' => true, 'response' => $data), 200);
        }
        else
        {
            $this->response(array('status' => false, 'response' => 'No data available.'), 200);
        }   
    }
    
    public function index_post()
    {

        $dataReserva = array(
			'id' =>  $this->post('id'),
            'codigo' => $this->post('codigo'),
            'correo' => $this->post('correo'),
            'fecha' => $this->post('fecha'),
            'items' => $this->post('items')  
            
        );

        $id = $this->Reserva_model->create($dataReserva);

        if ($id) {
            $this->response(array('status' => true, 'response' =>  $id), 200);
        } else {
            $this->response(array('status' => false, 'response' =>  $id), 500);
        }
    }

    public function index_put()
    {
        $data = array(
			'id' =>  $this->put('id'),
            'descripcion' => $this->put('descripcion'),
            'cantidad_jugadores' => $this->put('cantidad_jugadores'),
            'imagen' => $this->put('imagen'),
            'id_sede' => $this->put('id_sede'),
            'estado' => $this->put('estado') 
        );
        
        $update = $this->Campo_model->update($data);
        if ($update) {
            $this->response(array('status' => true, 'response' => $update), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }
    
    public function index_delete($id)
    {
        
        if (!$id) {
            $this->response(array('status' => false, 'response' => 'A valid value was not entered.'), 400);
        }
        $delete = $this->Campo_model->delete($id);
        if ($delete) {
            $this->response(array('status' => true, 'response' => $delete), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_options() {
        return $this->response(NULL, 200);
    }

    public function getAllBySede_get($idSede)
    {
        $data = $this->Campo_model->getAllBySede($idSede);

        if($data)
        {
            $this->response(array('status' => true, 'response' => $data), 200);
        }
        else
        {
            $this->response(array('status' => false, 'response' => $data), 200);
        }   
    }
}
?>