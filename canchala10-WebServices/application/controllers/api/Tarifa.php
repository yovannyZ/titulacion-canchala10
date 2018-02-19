<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';
class Tarifa extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->model('Tarifa_model');
        
    }

    public function index_get()
    {
        $data = $this->Tarifa_model->getAll();

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
        $data = $this->Tarifa_model->get($id);

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
        $data = array(
			'id' =>  $this->post('id'),
            'id_horario' => $this->post('id_horario'),
            'id_campo' => $this->post('id_campo'),
            'precio' => $this->post('precio')          
        );

        $id = $this->Tarifa_model->create($data);
        if ($id) {
            $this->response(array('status' => true, 'response' => $id), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_put()
    {
        $data = array(
			'id' =>  $this->put('id'),
            'id_horario' => $this->put('id_horario'),
            'id_campo' => $this->put('id_campo'),
            'precio' => $this->put('precio')   
        );
        
        $update = $this->Tarifa_model->update($data);
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
        $delete = $this->Tarifa_model->delete($id);
        if ($delete) {
            $this->response(array('status' => true, 'response' => $delete), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_options() {
        return $this->response(NULL, 200);
    }

    public function getByCampo_get($id_campo)
    {
        $data = $this->Tarifa_model->getAllByCampo($id_campo);

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