import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Usuario} from '../model/usuario';
import {GLOBAL} from './global';

@Injectable()
export class UsuarioService {
  public url: string;

  constructor(private _http:Http) { 
    this.url = GLOBAL.url;
  }

  addUsuario(usuario:Usuario){
    const body =  new URLSearchParams();
    body.set('correo',usuario.correo);
    body.set('clave',usuario.clave);
    body.set('nombres',usuario.nombres);
    body.set('id_tipo_usuario',String(usuario.id_tipo_usuario));
    let headers = new Headers();
    headers.append('Content-Type','application/x-www-form-urlencoded');
    return this._http.post(this.url + 'usuario', body, { headers: headers })
            .map(res => res.json());
  }

  getUsuario(correo:String){
    return this._http.get(this.url  + 'usuario/'+ correo)
    .map(res => res.json());
  }

  getAll(){
    return this._http.get(this.url  + 'usuario')
    .map(res => res.json());
  }

  editUsuario(usuario:Usuario){
    console.log(usuario);
    const body =  new URLSearchParams();
    body.set('correo',usuario.correo);
    body.set('clave',usuario.clave);
    body.set('nombres',usuario.nombres);
    body.set('id_tipo_usuario',String(usuario.id_tipo_usuario));
    let headers = new Headers();
    headers.append('Content-Type','application/x-www-form-urlencoded');
    return this._http.put(this.url + 'usuario', body, { headers: headers })
            .map(res => res.json());
  }

  deleteUsuario(correo:String){
    return this._http.delete(this.url  + 'usuario/'+ correo)
    .map(res => res.json());
  }
}