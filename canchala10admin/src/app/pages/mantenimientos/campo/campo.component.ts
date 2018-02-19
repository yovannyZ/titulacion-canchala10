import { GLOBAL } from './../../../@core/data/global';
import { CampoService } from './../../../@core/data/campo.service';
import { Campo } from './../../../@core/model/campo';
import { Component, OnInit } from '@angular/core';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';


@Component({
  selector: 'ngx-campo',
  templateUrl: './campo.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
})
export class CampoComponent implements OnInit {

  public Campos: Array<Campo>;
  public url_base: string;
  public filterQuery = "";
  public rowsOnPage = 5;
  public sortBy = "correo";
  public sortOrder = "asc";
  public popoverTitle: string = 'Eliminación';
  public popoverMessage: string = '¿Está seguro(a) de eliminar este registro?';
  public confirmClicked: boolean = false;
  public cancelClicked: boolean = false;
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
      private _campoService:CampoService, 
      private toasterService: ToasterService) {this.url_base = GLOBAL.url_base }

  ngOnInit() {
    this.Listar();
  }

  Listar(){
    this._campoService.getAll().subscribe(
      result => {
        if(result.status){
          this.Campos = new  Array<Campo>();
          this.Campos = result.response;
        }
        else{
          console.log(result.response);
        }
      },
      error => {
        var err = <any>error;
        console.log(err);
      }
    );
  }

  delete($event, id:number){
     this._campoService.get(id).subscribe(
        res => {
            if(res.status){
                console.log(res.response);
                let campo:Campo = res.response;
                campo.estado = '0';
              
                this._campoService.edit(campo).subscribe(
                    res=>{
                      if(res.status){
                        this.showToast("info", "Éxito", "Campo eliminado correctamente ");
                        this.Listar();
                      }
                      else{
                        console.log(res.response);
                      }
                    },
                    err => {
                      console.log(<any>err);
                    }
                  );
            }
            
        },
        err => {
            this.showToast("error", "Error", "Ocurrio un error al obtener el campo");
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

}