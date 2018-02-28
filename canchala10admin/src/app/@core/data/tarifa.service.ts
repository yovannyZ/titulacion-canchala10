import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Tarifa} from '../model/tarifa';
import {GLOBAL} from './global';

@Injectable()
export class TarifaService {

    public url: string;

    constructor(private _http:Http) { 
    this.url = GLOBAL.url;
    }

    getAll(){
        return this._http.get(this.url  + 'tarifa')
        .map(res => res.json());
    }

    delete(id:number){
        return this._http.delete(this.url  + 'tarifa/'+ id)
        .map(res => res.json());
    }

    get(id:number){
        return this._http.get(this.url  + 'tarifa/'+ id)
        .map(res => res.json());
    }

    add(tarifa:Tarifa){
        const body =  new URLSearchParams();
        body.set('id',String(tarifa.id));
        body.set('id_campo',String(tarifa.id_campo));
        body.set('id_horario',String(tarifa.id_horario));
        body.set('precio',String(tarifa.precio));
        
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.post(this.url + 'tarifa', body, { headers: headers })
                .map(res => res.json());
    }

    edit(tarifa:Tarifa){
        const body =  new URLSearchParams();
        body.set('id',String(tarifa.id));
        body.set('id_campo',String(tarifa.id_campo));
        body.set('id_horario',String(tarifa.id_horario));
        body.set('precio',String(tarifa.precio));
        
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.put(this.url + 'tarifa', body, { headers: headers })
                .map(res => res.json());
      }
}