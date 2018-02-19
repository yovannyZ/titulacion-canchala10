<?php
class TipoUsuario_model extends CI_Model
{
    public function __construct()
    {
        parent::__construct();
        
    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion");
        $db->from('tipo_usuario');
        $query = $db->get();
        if($query->num_rows() > 0)
        {
            return $query->result();
        }
    }

    public function get($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion");
        $db->from('tipo_usuario');
        $db->where('id',$id);
        $query = $db->get();
        if($query->num_rows() == 1)
        {
            return $query->row();
        }
    }

    public function create($TipoUsuario)
    {
        $db = $this->load->database('default',TRUE);
        $db->insert('tipo_usuario', $TipoUsuario);
        if ($db->affected_rows() === 1) {
            return $db->insert_id();
        }
        return null;
    }

    public function update($TipoUsuario)
    {
        $db = $this->load->database('default',TRUE);
        $id = $TipoUsuario['Id'];
        $db->where('Id', $id);
        $db->update('tipo_usuario', $TipoUsuario);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

    public function delete($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->where('Id',$id);
        $db->delete('tipo_usuario');
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

}


?>