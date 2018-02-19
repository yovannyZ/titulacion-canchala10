<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';
class Usuario extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->model('Usuario_model');
    }

    public function index_get()
    {
        
        $data = $this->Usuario_model->getAll();

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
        $data = $this->Usuario_model->get($id);

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
			'correo' =>  $this->post('correo'),
            'clave' => $this->post('clave'),
            'nombres' => $this->post('nombres'),
            'id_tipo_usuario' => $this->post('id_tipo_usuario'),
        );

        $id = $this->Usuario_model->create($data);
        if ($id) {
            $this->response(array('status' => true, 'response' => $id), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_put()
    {
        $data = array(
			'correo' =>  $this->put('correo'),
            'clave' => $this->put('clave'),
            'nombres' => $this->put('nombres'),
            'id_tipo_usuario' => $this->put('id_tipo_usuario'),
        );
        
        $update = $this->Usuario_model->update($data);
        if ($update) {
            $this->response(array('status' => true, 'response' => $update), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }
    
    public function index_delete($id)
    {
        
        if (!$id) {
            $this->response(array('status' => false, 'error' => 'A valid value was not entered.'), 400);
        }
        $delete = $this->Usuario_model->delete($id);
        if ($delete) {
            $this->response(array('status' => true, 'response' => $delete), 200);
        } else {
            $this->response(array('status' => false, 'response' => 'There was an error processing the data.'), 500);
        }
    }

    public function index_options() {
        return $this->response(NULL, 200);
    }

    public function do_upload() { 
        $config['upload_path']   = './uploads/'; 
        $config['allowed_types'] = 'gif|jpg|png'; 
        $config['max_size']      = 100; 
        $config['max_width']     = 1024; 
        $config['max_height']    = 768;  
        $this->load->library('upload', $config);
           
        if ( ! $this->upload->do_upload('C:/Users/yovanny/Desktop/logo-web.png')) {
           $error = array('error' => $this->upload->display_errors()); 
           echo $error;
        }
           
        else { 
           $data = array('upload_data' => $this->upload->data()); 
           echo 'bien';
        } 
     } 
}
?>