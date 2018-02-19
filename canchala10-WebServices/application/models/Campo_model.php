<?php
class Campo_model extends CI_Model
{
    private $table = "campo";

    public function __construct()
    {
        parent::__construct();
        $this->load->model('Sede_model');

    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion,cantidad_jugadores,imagen,id_sede,estado");
        $db->from($this->table);
        $db->where('estado',TRUE);
        $query = $db->get();
        $array = array();
        if($query->num_rows() > 0)
        {
            $campos = $query->result();

            foreach ($campos as $campo) 
            {
                $sede = $this->Sede_model->get($campo->id_sede);
                $campo->sede =  $sede;
                $array[] = $campo;
            }

            return $array;
        }
    }

    public function getAllBySede($idSede){
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion,cantidad_jugadores,imagen,id_sede,estado");
        $db->from($this->table);
        $db->where('estado',TRUE);
        $db->where('id_sede',$idSede);
        $query = $db->get();
        $array = array();
        if($query->num_rows() > 0)
        {
            $campos = $query->result();
            foreach ($campos as $campo) 
            {
                $sede = $this->Sede_model->get($campo->id_sede);
                $campo->sede =  $sede;
                $array[] = $campo;
            }

            return $array;
        }
    }

    public function get($id)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, descripcion,cantidad_jugadores,imagen,id_sede,estado");
        $db->from($this->table);
        $db->where('id',$id);
        $db->where('estado',TRUE);
        $query = $db->get();
        if($query->num_rows() == 1)
        {
            $campo = $query->row();
            $sede = $this->Sede_model->get($campo->id_sede);
            $campo->sede =  $sede;
            return $campo;
        }
    }

    public function create($campo)
    {
        $db = $this->load->database('default',TRUE);
        $db->insert($this->table, $campo);
        if ($db->affected_rows() === 1) {
            return $db->insert_id();
        }
        return null;
    }

    public function update($campo)
    {
        $db = $this->load->database('default',TRUE);
        $id = $campo['id'];
        $db->where('id', $id);
        $db->update($this->table, $campo);
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