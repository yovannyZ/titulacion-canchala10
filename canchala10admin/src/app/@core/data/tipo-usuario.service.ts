import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class TipoUsuarioService {
  public url: string;

  constructor(private _http:Http) {
    this.url = 'http://localhost/canchala10/api/tipousuario';
   }

   getTipoUsuarios(){
     return this._http.get(this.url)
                      .map(res => res.json());
   }
  
}
