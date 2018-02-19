import { Usuario } from './../../../@core/model/usuario';
import { UsuarioService } from './../../../@core/data/usuario.service';
import { Component, OnInit } from '@angular/core';
import { TipoUsuarioService } from '../../../@core/data/tipo-usuario.service';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
  selector: 'ngx-usuario-add',
  styleUrls: ['./usuario-add.component.scss'],
  templateUrl: './usuario-add.component.html'
})
export class UsuarioAddComponent implements OnInit {

  public tipoUsuarios;
  public usuario:Usuario;

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
        private _tipoUsuarioServices: TipoUsuarioService, 
        private _usuarioService:UsuarioService,
        private toasterService: ToasterService) {
    this.usuario = new Usuario("","","",0);
   }

  ngOnInit() {
    this.listarTipoUsuario();
  }

  listarTipoUsuario(){
    this._tipoUsuarioServices.getTipoUsuarios().subscribe(
      result => {
        if(result.status){
          this.tipoUsuarios = result.response;
        }
        else{
          console.log( result.response);
        }
      },
      error => {
        var err = <any>error;
        console.log(err);
      }
    );
  }
  
  onSubmit(){
    this._usuarioService.getUsuario(this.usuario.correo).subscribe(
      result => {
        if(!result.status){
          this._usuarioService.addUsuario(this.usuario)
          .subscribe(
            res => {
              if(res.status){
                this.showToast("info", "Éxito", "Usuario agregado corectamente");
                this.usuario = new Usuario("","","",0);
              }
            },
            err => {
              console.log(<any>err);
            }
          );
        }else{
            this.showToast("warning", "advertencia", "El correo ya existe");
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
