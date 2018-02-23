<?php
class ReservaDetalle_model extends CI_Model
{
    private $table = "reserva_detalle";

    public function __construct()
    {
        parent::__construct();
        
    }

    public function getAll()
    {
        $db = $this->load->database('default',TRUE);
        $db->select("id, id_reserva,id_tarifa, precio");
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
        $db->select("id, id_reserva,id_tarifa, precio");
        $db->from($this->table);
        $db->where('id',$id);
        $query = $db->get();
        if($query->num_rows() == 1)
        {
            return $query->row();
        }
    }

    public function getAllByReserva($idReserva)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("campo.descripcion, sede.descripcion as sede_descripcion, sede.direccion, horario.hora_inicio, horario.hora_fin, reserva_detalle.precio, sede.url_imagen");
        $db->from($this->table);
        $db->join("tarifa", "tarifa.id = reserva_detalle.id_tarifa", "inner");
        $db->join("horario", "horario.id = tarifa.id_horario", "inner");
        $db->join("campo", "campo.id = tarifa.id_campo", "inner");
        $db->join("sede", "sede.id = campo.id_sede", "inner");
        $db->where('reserva_detalle.id_reserva',$idReserva);
        $query = $db->get();
        if($query->num_rows() > 0)
        {
            return $query->result();
        }
    }

    public function create($sede, $db)
    {
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

    public function gethorasByFecha($idCampo, $fecha)
    {
        $db = $this->load->database('default',TRUE);
        $db->select("tarifa.id_horario");
        $db->from($this->table);
        $db->join("reserva", "reserva.id = reserva_detalle.id_reserva", "inner");
        $db->join("tarifa", "tarifa.id = reserva_detalle.id_tarifa", "inner");
        $db->where('tarifa.id_campo', $idCampo);
        $db->where('reserva.fecha', $fecha);
        $query_1 = $db->get();
        $data = array();
      
        if($query_1->num_rows() > 0)
        {
            $tarifa = $query_1->result();
           
            
            foreach($tarifa as $value){
                $data[] = $value->id_horario;
            }

            $db->select("id, hora_inicio,hora_fin");
            $db->from('horario');
            $db->where_not_in('id',$data);
            $query = $db->get();
            $horarios = $query->result();
            /*foreach($horarios as $value){
                var_dump($value->id);
            }
            $diff = array_udiff($horarios, $tarifa, array($this,'compare_objects') );*/
            return $horarios;
        }
        else{
            $db->select("id, hora_inicio,hora_fin");
            $db->from('horario');
            $query = $db->get();
            return $query->result();
        }

        return null;
    }
}


?>