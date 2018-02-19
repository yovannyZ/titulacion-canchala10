<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

class TipoUsuario extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->model('TipoUsuario_model');
    }

    public function index_get()
    {
        $data = $this->TipoUsuario_model->getAll();

        if($data)
        {
            $this->response(array('status' => true, 'response' => $data), 200);
        }
        else
        {
            $this->response(array('status' => false, 'response' => 'No existe datos'), 200);
        }   
    }

    public function find_get($id)
    {
        $data = $this->TipoUsuario_model->get($id);

        if($data)
        {
            $this->response($data, 200);
        }
        else
        {
            $this->response(array('status' => 'error', 'message' => 'No data available.'), 400);
        }   
    }

    public function index_post()
    {
        $data = array(
			'Id' => 0,
			'Descripcion' => $this->post('Descripcion')
        );

        $id = $this->TipoUsuario_model->create($data);
        if ($id) {
            $this->response(array('status' => true, 'response' => $id), 200);
        } else {
            $this->response(array('status' => false, 'error' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_put()
    {
        $data = array(
			'Id' =>  $this->put('Id'),
			'Descripcion' => $this->put('Descripcion')
        );
        
        $update = $this->TipoUsuario_model->update($data);
        if ($update) {
            $this->response(array('status' => true, 'response' => $update), 200);
        } else {
            $this->response(array('status' => false, 'error' => 'There was an error processing the data.'), 500);
        }
    }
    
    public function index_delete($id)
    {
        if (!$id) {
            $this->response(array('status' => false, 'error' => 'A valid value was not entered.'), 400);
        }
        $delete = $this->TipoUsuario_model->delete($id);
        if ($delete) {
            $this->response(array('status' => true, 'response' => $delete), 200);
        } else {
            $this->response(array('status' => false, 'error' => 'There was an error processing the data.'), 500);
        }
    }
}
?>