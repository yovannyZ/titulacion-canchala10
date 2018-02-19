<?php
class Sede_model extends CI_Model
{
    private $table = "sede";

    public function __construct()
    {
        parent::__construct();
        
    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion,direccion,implementos,vestidores,snack,estacionamiento,url_imagen,estado");
        $db->from($this->table);
        $query = $db->get();
        if($query->num_rows() > 0)
        {
            return $query->result();
        }
    }

    public function get($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion,direccion,implementos,vestidores,snack,estacionamiento,url_imagen,estado");
        $db->from($this->table);
        $db->where('id',$id);
        $query = $db->get();
        if($query->num_rows() == 1)
        {
            return $query->row();
        }
    }

    public function create($sede)
    {
        $db = $this->load->database('default',TRUE);
        $db->insert($this->table, $sede);
        if ($db->affected_rows() === 1) {
            return $db->insert_id();
        }
        return null;
    }

    public function update($sede)
    {
        $db = $this->load->database('default',TRUE);
        $id = $sede['id'];
        $db->where('id', $id);
        $db->update($this->table, $sede);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

    public function delete($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->where('id',$id);
        $db->delete($this->table);
        if ($db->affected_rows() === 1) {
            return true;
        }
        return null;
    }

}


?>