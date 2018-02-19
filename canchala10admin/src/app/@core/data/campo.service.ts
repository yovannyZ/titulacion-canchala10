import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Campo} from '../model/campo';
import {GLOBAL} from './global';

@Injectable()
export class CampoService {

    public url: string;
    public url_campo:string;
    public url_upload:string;
    
    constructor(private _http:Http) { 
    this.url = GLOBAL.url;
    this.url_campo = GLOBAL.url_campo;
    this.url_upload = GLOBAL.url_upload;
    
    }

    getAll(){
        return this._http.get(this.url  + this.url_campo)
        .map(res => res.json());
    }

    delete(id:number){
        return this._http.delete(this.url  + this.url_campo + id)
        .map(res => res.json());
    }

    get(id:number){
        return this._http.get(this.url  + this.url_campo + id)
        .map(res => res.json());
    }

    add(campo:Campo){
        const body =  new URLSearchParams();
        body.set('id',String(campo.id));
        body.set('descripcion',campo.descripcion);
        body.set('cantidad_jugadores',campo.cantidad_jugadores);
        body.set('imagen',campo.imagen);
        body.set('id_sede',String(campo.id_sede));
        body.set('estado',String('1'));
        
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.post(this.url + this.url_campo , body, { headers: headers })
                .map(res => res.json());
    }

    edit(campo:Campo){
        const body =  new URLSearchParams();
        body.set('id',String(campo.id));
        body.set('descripcion',campo.descripcion);
        body.set('cantidad_jugadores',campo.cantidad_jugadores);
        body.set('imagen',campo.imagen);
        body.set('id_sede',String(campo.id_sede));
        body.set('estado',String(campo.estado));
        let headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this._http.put(this.url + this.url_campo , body, { headers: headers })
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

            xhr.open("POST", this.url  + this.url_upload, true);
            xhr.send(formData);
        });
    }
}