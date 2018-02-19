<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';
class Sede extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->model('Sede_model');
        $this->load->model('Campo_model');
        
    }

    public function index_get()
    {
        $data = $this->Sede_model->getAll();

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
        $data = $this->Sede_model->get($id);

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
            'descripcion' => $this->post('descripcion'),
            'direccion' => $this->post('direccion'),
            'url_imagen' => $this->post('url_imagen'),
            'estado' => $this->post('estado'),
            'implementos' => $this->post('implementos'),
            'vestidores' => $this->post('vestidores'),
            'snack' => $this->post('snack'),
            'estacionamiento' => $this->post('estacionamiento')
            
        );

        $id = $this->Sede_model->create($data);
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
            'descripcion' => $this->put('descripcion'),
            'direccion' => $this->put('direccion'),
            'url_imagen' => $this->put('url_imagen'),
            'estado' => $this->put('estado'),
            'implementos' => $this->put('implementos'),
            'vestidores' => $this->put('vestidores'),
            'snack' => $this->put('snack'),
            'estacionamiento' => $this->put('estacionamiento')
        );
        
        $update = $this->Sede_model->update($data);
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
        $delete = $this->Sede_model->delete($id);
        if ($delete) {
            $this->response(array('status' => true, 'response' => $delete), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_options() {
        return $this->response(NULL, 200);
    }

  
}
?>