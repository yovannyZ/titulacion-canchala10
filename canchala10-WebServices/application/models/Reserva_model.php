<?php
class Reserva_model extends CI_Model
{
    private $table = "reserva";

    public function __construct()
    {
        parent::__construct();
        $this->load->model('ReservaDetalle_model');

    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id,correo,fecha");
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
        $db->select("id,correo,fecha");
        $db->from($this->table);
        $db->where('id',$id);
        $query = $db->get();
        if($query->num_rows() == 1)
        {
            return $query->row();
        }
    }

    public function create($reserva)
    {
        $idInsert = "0";
        $items = (array)$reserva['items'];
        $db = $this->load->database('default',TRUE);
        $db->trans_begin();
        $db->insert($this->table, $this->setData($reserva));
        if ($db->affected_rows() === 1) {
            $idInsert =  $db->insert_id();
            foreach ($items as $item) {
                $item['id_reserva'] = $idInsert;
                $db->insert("reserva_detalle", $item);
              
            }
        }

        if  ($db ->trans_status() === FALSE) 
        { 
                $db->trans_rollback(); 
        } 
        else 
        { 
                $db->trans_commit(); 
        }

        return  $idInsert;
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

    private function setData($data){
        return array(
			'id' =>  $data['id'],
            'correo' => $data['correo'],
            'fecha' => $data['fecha']
        );
    }


    

}


?>