import { CampoService } from './../../../@core/data/campo.service';
import { GLOBAL } from './../../../@core/data/global';
import { NotificationsComponent } from './../../components/notifications/notifications.component';
import { Campo } from './../../../@core/model/campo';
import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute,Params} from '@angular/router';
import { SedeService } from '../../../@core/data/sede.service';

import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
  selector: 'ngx-campo-edit',
  templateUrl: './campo-edit.component.html'
})

export class CampoEditComponent implements OnInit {

  public campo:Campo;
  public sedes;
  config: ToasterConfig;
  position = 'toast-top-center';
  animationType = 'flyleft';
  timeout = 4000;
  toastsLimit = 5;
  isNewestOnTop = true;
  isHideOnClick = true;
  isDuplicatesPrevented = false;
  isCloseButton = true;
  public filesToUpload;
  public resultUpload;
  public url_base: string;

  constructor(
    private _route:ActivatedRoute,
    private _sedeService:SedeService,
    private _campoService:CampoService,    
    private _router:Router,
    private toasterService: ToasterService) {

    this.url_base = GLOBAL.url_base
    this.campo = new Campo(0,"","","",0,'1');
   }

  ngOnInit() {
    this.listarSedes();
    this.get();
  }

  get(){
   this._route.params.forEach((params:Params)=>{
     let id = params['id'];
     this._campoService.get(id).subscribe(
      res => {
          if(res.status){
            this.campo = res.response;
          }
      },
      err => {
        var error = <any>err;
        console.log(error);
      }
     );
   })
  }

  
  onSubmit(){
    if(this.filesToUpload && this.filesToUpload.length >= 1){
      this._sedeService.makeFileRequest([],this.filesToUpload).then((result) => {
        if(result.status){
         this.campo.imagen = result.response.file_name;
         this.saveSede();

        }
       
      }, (error)=> {  
        console.log(error);
      });
    }
    else{
      this.saveSede();
    }
    

    
  }

  saveSede(){
    let campoEdit = new Campo(this.campo.id, this.campo.descripcion,this.campo.cantidad_jugadores, this.campo.imagen,this.campo.id_sede, this.campo.estado);
    this._campoService.edit(campoEdit)
    .subscribe(
      res => {
        if(res.status){
          this.showToast("info", "Éxito", "Campo actualizado correctamente ");
          this.campo = new Campo(0,"","","",0,'1');
          this._router.navigate(['/pages/mantenimientos/campo']);
        }
      },
      err => {
        console.log(<any>err);
      }
    );
  }


  private showToast(type: string, title: string, body: string) {
    this.config = new ToasterConfig({
      positionClass: this.position,
      timeout: this.timeout,
      newestOnTop: this.isNewestOnTop,
      tapToDismiss: this.isHideOnClick,
      preventDuplicates: this.isDuplicatesPrevented,
      animation: this.animationType,
      limit: this.toastsLimit,
    });
    const toast: Toast = {
      type: type,
      title: title,
      body: body,
      timeout: this.timeout,
      showCloseButton: this.isCloseButton,
      bodyOutputType: BodyOutputType.TrustedHtml,
    };
    this.toasterService.popAsync(toast);
  }


  fileChangeEvent(fileInput: any){
    this.filesToUpload = <Array<File>>fileInput.target.files;
    console.log( this.filesToUpload);
  }

  listarSedes(){
    this._sedeService.getAll().subscribe(
      result => {
        if(result.status){
          this.sedes = result.response;
        }
        else{
          this.showToast("error", "Error", "Ocurrio un error al listar las sedes");
          console.log(result.response);
        }
      },
      error => {
        var err = <any>error;
        this.showToast("error", "Error", "Ocurrio un error al listar las sedes");
        console.log(err);
      }
    );
  }
}
