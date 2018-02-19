<?php
class Usuario_model extends CI_Model
{
    private $table = "usuario";

    public function __construct()
    {
        parent::__construct();
        $this->load->model('TipoUsuario_model');
    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("correo, clave, nombres, id_tipo_usuario");
        $db->from($this->table);
        $query = $db->get();
        $array = array();

        if($query->num_rows() > 0)
        {
            $usuarios = $query->result();

            foreach ($usuarios as $usuario) 
            {
                $tipo_usuario = $this->TipoUsuario_model->get($usuario->id_tipo_usuario);
                $usuario->tipo_usuario =  $tipo_usuario;
                $array[] = $usuario;
            }

            return $array;
        }
    }

    public function get($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("correo, clave, nombres, id_tipo_usuario");
        $db->from($this->table);
        $db->where('correo',$id);
        $query = $db->get();

        if($query->num_rows() == 1)
        {
            $usuario = $query->row();
            $tipo_usuario = $this->TipoUsuario_model->get($usuario->id_tipo_usuario);
            $usuario->tipo_usuario =  $tipo_usuario;
            return $usuario;
        }
    }

    public function create($Usuario)
    {
        $db = $this->load->database('default',TRUE);
        $db->insert($this->table, $Usuario);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

    public function update($Usuario)
    {
        $db = $this->load->database('default',TRUE);
        $correo = $Usuario['correo'];
        $db->where('correo', $correo);
        $db->update($this->table, $Usuario);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

    public function delete($correo)
    {
        $db = $this->load->database('default',TRUE);
        $db->where('correo',$correo);
        $db->delete($this->table);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    } 

}
?>