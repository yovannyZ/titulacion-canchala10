import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Horario} from '../model/horario';
import {GLOBAL} from './global';

@Injectable()
export class HorarioService {

    public url: string;

    constructor(private _http:Http) { 
        this.url = GLOBAL.url;
    }

    getAll(){
        return this._http.get(this.url  + 'horario')
        .map(res => res.json());
    }

}