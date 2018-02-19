import { GLOBAL } from './../../../@core/data/global';
import { SedeService } from './../../../@core/data/sede.service';
import { Sede } from './../../../@core/model/sede';
import { Component, OnInit } from '@angular/core';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';


@Component({
  selector: 'ngx-sede',
  templateUrl: './sede.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
})
export class SedeComponent implements OnInit {

  public Sedes: Array<Sede>;
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

constructor(private _sedeService:SedeService, private toasterService: ToasterService) { this.url_base = GLOBAL.url_base }

  ngOnInit() {
    this.Listar();
  }

  Listar(){
    this._sedeService.getAll().subscribe(
      result => {
        if(result.status){
          this.Sedes = result.response;
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
    this._sedeService.delete(id).subscribe(
      res=>{
        if(res.status){
          this.showToast("info", "Éxito", "sede eliminada correctamente ");
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