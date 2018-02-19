<?php
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . '/libraries/REST_Controller.php';

class Upload extends \Restserver\Libraries\REST_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
    }

    public function index_post()
    {
        $config['upload_path']          = '../uploads/';
        $config['allowed_types']        = 'gif|jpg|png';
        $config['max_size']             = 0;
        $config['max_width']            = 0;
        $config['max_height']           = 0;
        $config['encrypt_name']           = TRUE;
        $this->load->library('upload', $config);

        if (!$this->upload->do_upload('uploads'))
        {
                $error =$this->upload->display_errors();
                $this->response(array('status' => false, 'response' => $error), 400);
        }
        else
        {
                $data =$this->upload->data();
                $this->response(array('status' => true, 'response' => $data), 200);
        }
        
    }
    
}
?>