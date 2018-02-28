import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Sede} from '../model/sede';
import {GLOBAL} from './global';

@Injectable()
export class SedeService {

    public url: string;

    constructor(private _http:Http) { 
    this.url = GLOBAL.url;
    }

    getAll(){
        return this._http.get(this.url  + 'sede')
        .map(res => res.json());
    }

    delete(id:number){
        return this._http.delete(this.url  + 'sede/'+ id)
        .map(res => res.json());
    }

    get(id:number){
        return this._http.get(this.url  + 'sede/'+ id)
        .map(res => res.json());
    }

    add(sede:Sede){
        const body =  new URLSearchParams();
        body.set('id',String(sede.id));
        body.set('descripcion',sede.descripcion);
        body.set('direccion',sede.direccion);
        body.set('url_imagen',String(sede.url_imagen));
        body.set('estado','1');
        body.set('implementos',sede.implementos);
        body.set('vestidores',sede.vestidores);
        body.set('estacionamiento',sede.estacionamiento);
        body.set('snack',sede.snack);
        body.set('telefono',sede.telefono);
        body.set('hora_atencion',sede.hora_atencion);
        body.set('latitud',sede.latitud);
        body.set('longitud',sede.longitud);
        
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.post(this.url + 'sede', body, { headers: headers })
                .map(res => res.json());
    }

    edit(sede:Sede){
        const body =  new URLSearchParams();
        body.set('id',String(sede.id));
        body.set('descripcion',sede.descripcion);
        body.set('direccion',sede.direccion);
        body.set('url_imagen',String(sede.url_imagen));
        body.set('estado',String(sede.estado));
        body.set('implementos',sede.implementos);
        body.set('vestidores',sede.vestidores);
        body.set('estacionamiento',sede.estacionamiento);
        body.set('snack',sede.snack);
        body.set('telefono',sede.telefono);
        body.set('hora_atencion',sede.hora_atencion);
        body.set('latitud',sede.latitud);
        body.set('longitud',sede.longitud);
        
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.put(this.url + 'sede', body, { headers: headers })
                .map(res => res.json());
      }


    makeFileRequest(params:Array<string>, files:Array<File>){
        return new Promise((resolve, reject)=>{
            var formData: any = new FormData();
            var xhr = new XMLHttpRequest();
            
            
            for(var i = 0; i < files.length; i++){
                formData.append('uploads', files[i], files[i].name);
                console.log(files[i]);
            }

            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        resolve(JSON.parse(xhr.response));
                    }else{
                        reject(xhr.response);
                    }
                }
            };

            xhr.open("POST", this.url  + 'upload/', true);
            xhr.send(formData);
        });
    }
}