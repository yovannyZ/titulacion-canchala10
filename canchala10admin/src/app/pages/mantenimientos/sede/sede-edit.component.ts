import { GLOBAL } from './../../../@core/data/global';
import { NotificationsComponent } from './../../components/notifications/notifications.component';
import { Sede } from './../../../@core/model/sede';
import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute,Params} from '@angular/router';
import { SedeService } from '../../../@core/data/sede.service';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
  selector: 'ngx-sede-edit',
  templateUrl: './sede-edit.component.html'
})

export class SedeEditComponent implements OnInit {

  public sede:Sede;
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
    private _router:Router,
    private toasterService: ToasterService) {

    this.url_base = GLOBAL.url_base
    this.sede = new Sede(0,'','','',true,"","","","","","","","");
   }

  ngOnInit() {
    this.get();
  }

  get(){
   this._route.params.forEach((params:Params)=>{
     let id = params['id'];
     this._sedeService.get(id).subscribe(
      res => {
          if(res.status){
            this.sede = res.response;
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
         this.sede.url_imagen = result.response.file_name;
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
    let sedeEdit = new Sede(this.sede.id, this.sede.descripcion, this.sede.direccion, 
      this.sede.url_imagen, this.sede.estado, this.sede.vestidores, this.sede.estacionamiento,
    this.sede.snack, this.sede.implementos, this.sede.latitud, this.sede.longitud, this.sede.telefono, this.sede.hora_atencion);
    console.log(sedeEdit);
    this._sedeService.edit(sedeEdit)
    .subscribe(
      res => {
        if(res.status){
          this.showToast("info", "Éxito", "sede actualizada correctamente ");
          this.sede = new Sede(0,"","","", true, "", "", "", "","","","","");
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
}
