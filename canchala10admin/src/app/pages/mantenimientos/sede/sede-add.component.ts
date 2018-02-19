import { Sede } from './../../../@core/model/sede';
import { SedeService } from './../../../@core/data/sede.service';
import { Component, OnInit } from '@angular/core';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
    selector: 'ngx-sede-add',
    styleUrls: ['./sede-add.component.scss'],
    templateUrl: './sede-add.component.html',
  })

  export class SedeAddComponent implements OnInit{

    public sede:Sede;
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
      private _sedeService:SedeService,
      private toasterService: ToasterService) {
      this.sede = new Sede(0,"","","",false,"","","","");
    }

    ngOnInit() {
        
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
          this.sede.url_imagen ='sin_imagen.jpg';
          this.saveSede();
        }  
      }

      saveSede(){
        this._sedeService.add(this.sede)
              .subscribe(
                res => {
                  if(res.status){
                    this.showToast("info", "Éxito", "Sede agregada corectamente");
                    this.sede = new Sede(0,"","","",false,"","","","");
                  }
                },
                err => {
                  console.log(<any>err);
                }
              );
      }

      fileChangeEvent(fileInput: any){
        this.filesToUpload = <Array<File>>fileInput.target.files;
        console.log( this.filesToUpload);
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
  }