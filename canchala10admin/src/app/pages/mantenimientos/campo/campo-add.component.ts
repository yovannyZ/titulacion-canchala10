import { Campo } from './../../../@core/model/campo';
import { CampoService } from './../../../@core/data/campo.service';
import { Component, OnInit } from '@angular/core';
import { SedeService } from '../../../@core/data/sede.service';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
  selector: 'ngx-campo-add',
  styleUrls: ['./campo-add.component.scss'],
  templateUrl: './campo-add.component.html'
})
export class CampoAddComponent implements OnInit {

  public sedes;
  public campo:Campo;
  public filesToUpload;
  public resultUpload;

  //toast
  config: ToasterConfig;
  position = 'toast-top-center';
  animationType = 'flyleft';
  timeout = 4000;
  toastsLimit = 5;
  isNewestOnTop = true;
  isHideOnClick = true;
  isDuplicatesPrevented = false;
  isCloseButton = true;

  constructor(
        private _sedeServices: SedeService, 
        private _campoService:CampoService,
        private toasterService: ToasterService) {
    this.campo = new Campo(0,"","","",0,'1');
   }

  ngOnInit() {
    this.listarSedes();
  }

  listarSedes(){
    this._sedeServices.getAll().subscribe(
      result => {
        if(result.status){
           
          this.sedes = result.response;
          console.log(this.sedes);
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
  
  onSubmit(){
    if(this.filesToUpload && this.filesToUpload.length >= 1){
          this._campoService.makeFileRequest([],this.filesToUpload).then((result) => {
            if(result.status){
             this.campo.imagen = result.response.file_name;
             this.saveCampo();
            }
           
          }, (error)=> {  
            this.showToast("error", "Error", "Ocurrio un error al subir la imagen");
            console.log(error);
          });
        }
        else{
          this.campo.imagen ='sin_imagen.jpg';
          this.saveCampo();
        
        }
    }  

    saveCampo(){
        this._campoService.add(this.campo)
              .subscribe(
                res => {
                  if(res.status){
                    this.showToast("info", "Éxito", "Campo agregado corectamente");
                    this.campo = new Campo(0,"","","",0,'1');
                  }
                },
                err => {
                    this.showToast("error", "Error", "Ocurrio un error al guardar el campo");
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
   // console.log( this.filesToUpload);
  }
  
}
